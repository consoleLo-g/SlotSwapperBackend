package com.example.SlotSwapperBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SlotSwapperBackend.Model.SwapRequest;
import com.example.SlotSwapperBackend.Service.SwapService;

import java.util.List;

@RestController
@RequestMapping("/swaps")
public class SwapController {

    @Autowired
    private SwapService swapService;

    // ✅ Request swap using parameters
    @PostMapping("/request")
    public SwapRequest requestSwap(
            @RequestParam String requesterId,
            @RequestParam String eventId,
            @RequestParam String requestedSlot,
            @RequestParam String offeredSlot) {
        SwapRequest req = new SwapRequest();
        req.setRequesterId(requesterId);
        req.setEventId(eventId);
        req.setRequestedSlot(requestedSlot);
        req.setOfferedSlot(offeredSlot);
        req.setStatus("PENDING");

        return swapService.requestSwap(req);
    }

    @GetMapping("/all")
    public List<SwapRequest> getAllSwaps() {
        return swapService.getAllSwaps();
    }

    // ✅ Update swap status using param
    @PutMapping("/{id}/status")
    public SwapRequest updateSwap(
            @PathVariable String id,
            @RequestParam String status) {
        return swapService.updateSwapStatus(id, status);
    }
}
