package com.example.SlotSwapperBackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "swap_requests")
public class SwapRequest {

    @Id
    private String id;

    private String requesterId;
    private String eventId;
    private String requestedSlot;
    private String offeredSlot;
    private String status;

    // ✅ Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRequestedSlot() {
        return requestedSlot;
    }

    public void setRequestedSlot(String requestedSlot) {
        this.requestedSlot = requestedSlot;
    }

    public String getOfferedSlot() {
        return offeredSlot;
    }

    public void setOfferedSlot(String offeredSlot) {
        this.offeredSlot = offeredSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
