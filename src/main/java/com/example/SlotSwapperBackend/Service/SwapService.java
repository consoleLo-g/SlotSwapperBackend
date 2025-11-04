package com.example.SlotSwapperBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlotSwapperBackend.Model.SwapRequest;
import com.example.SlotSwapperBackend.Repository.SwapRepository;

import java.util.List;

@Service
public class SwapService {

    @Autowired
    private SwapRepository swapRepo;

    public SwapRequest requestSwap(SwapRequest req) {
        req.setStatus("PENDING");
        return swapRepo.save(req);
    }

    public List<SwapRequest> getAllSwaps() {
        return swapRepo.findAll();
    }

    public SwapRequest updateSwapStatus(String id, String newStatus) {
        SwapRequest req = swapRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Swap request not found"));

        req.setStatus(newStatus);
        return swapRepo.save(req);
    }
}
