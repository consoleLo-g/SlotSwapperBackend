package com.example.SlotSwapperBackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "swap_requests")
public class SwapRequest {

    @Id
    private String id;

    private String mySlotId; // Slot of the requesting user
    private String theirSlotId; // Slot they want from other user

    private String requesterId; // user A
    private String receiverId; // user B

    private String status; // PENDING, ACCEPTED, REJECTED

    // --- GETTERS & SETTERS ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMySlotId() {
        return mySlotId;
    }

    public void setMySlotId(String mySlotId) {
        this.mySlotId = mySlotId;
    }

    public String getTheirSlotId() {
        return theirSlotId;
    }

    public void setTheirSlotId(String theirSlotId) {
        this.theirSlotId = theirSlotId;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
