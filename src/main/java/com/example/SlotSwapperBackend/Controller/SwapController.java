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

    @PostMapping("/request")
    public SwapRequest requestSwap(@RequestBody SwapRequest req) {
        return swapService.requestSwap(req);
    }

    @GetMapping("/all")
    public List<SwapRequest> getAllSwaps() {
        return swapService.getAllSwaps();
    }

    @PutMapping("/{id}/status")
    public SwapRequest updateSwap(@PathVariable String id, @RequestParam String status) {
        return swapService.updateSwapStatus(id, status);
    }
}
