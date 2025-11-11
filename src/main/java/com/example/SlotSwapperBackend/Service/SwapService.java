package com.example.SlotSwapperBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SlotSwapperBackend.Model.Event;
import com.example.SlotSwapperBackend.Model.SwapRequest;
import com.example.SlotSwapperBackend.Repository.SwapRepository;
import com.example.SlotSwapperBackend.Repository.EventRepository;

import java.util.List;

@Service
public class SwapService {

    @Autowired
    private SwapRepository swapRepo;

    @Autowired
    private EventRepository eventRepo;

    public SwapRequest requestSwap(SwapRequest req) {

        // ✅ Get events involved
        Event myEvent = eventRepo.findById(req.getOfferedSlot())
                .orElseThrow(() -> new RuntimeException("My event not found"));

        Event targetEvent = eventRepo.findById(req.getRequestedSlot())
                .orElseThrow(() -> new RuntimeException("Requested event not found"));

        // ✅ Both must be swappable
        if (!Boolean.TRUE.equals(myEvent.getSwappable()) ||
                !Boolean.TRUE.equals(targetEvent.getSwappable())) {
            throw new RuntimeException("Both events must be swappable");
        }

        // ✅ Set them to SWAP_PENDING
        myEvent.setStatus("SWAP_PENDING");
        targetEvent.setStatus("SWAP_PENDING");
        eventRepo.save(myEvent);
        eventRepo.save(targetEvent);

        // ✅ Save request
        req.setStatus("PENDING");
        return swapRepo.save(req);
    }

    public List<SwapRequest> getAllSwaps() {
        return swapRepo.findAll();
    }

    public SwapRequest updateSwapStatus(String id, String newStatus) {

        SwapRequest req = swapRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Swap request not found"));

        Event myEvent = eventRepo.findById(req.getOfferedSlot())
                .orElseThrow(() -> new RuntimeException("My event not found"));

        Event targetEvent = eventRepo.findById(req.getRequestedSlot())
                .orElseThrow(() -> new RuntimeException("Requested event not found"));

        // ✅ Accepted
        if (newStatus.equals("ACCEPTED")) {

            // ✅ Swap owners
            String tempUserId = myEvent.getUserId();
            myEvent.setUserId(targetEvent.getUserId());
            targetEvent.setUserId(tempUserId);

            // ✅ Reset status
            myEvent.setStatus("BUSY");
            targetEvent.setStatus("BUSY");

            myEvent.setSwappable(false);
            targetEvent.setSwappable(false);

            eventRepo.save(myEvent);
            eventRepo.save(targetEvent);
        }

        // ✅ Rejected
        else if (newStatus.equals("REJECTED")) {

            // Restore swappable state
            myEvent.setStatus("SWAPPABLE");
            targetEvent.setStatus("SWAPPABLE");

            myEvent.setSwappable(true);
            targetEvent.setSwappable(true);

            eventRepo.save(myEvent);
            eventRepo.save(targetEvent);
        }

        req.setStatus(newStatus);
        return swapRepo.save(req);
    }

    @Transactional
    public void swapEvents(String offeredId, String requestedId, String requesterId) {

        Event offered = eventRepo.findById(offeredId)
                .orElseThrow(() -> new RuntimeException("Offered event not found"));

        Event requested = eventRepo.findById(requestedId)
                .orElseThrow(() -> new RuntimeException("Requested event not found"));

        // USER A: requester
        String userA = requesterId;

        // USER B: owner of requested slot
        String userB = requested.getUserId();

        // Swap owners
        offered.setUserId(userB);
        requested.setUserId(userA);

        // After swapping, disable swappable flag
        offered.setSwappable(false);
        requested.setSwappable(false);

        eventRepo.save(offered);
        eventRepo.save(requested);
    }

}
