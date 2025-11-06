package com.example.SlotSwapperBackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {

    @Id
    private String id;

    private String userId;
    private String title;
    private String description;
    private String date;
    private String startTime;
    private String endTime;

    // ✅ Added fields required by SwapService
    private String status; // BUSY, SWAPPABLE, SWAP_PENDING
    private Boolean swappable; // true / false

    public Event() {
    }

    public Event(String id, String userId, String title, String description,
            String date, String startTime, String endTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

        // ✅ Defaults (optional)
        this.status = "BUSY";
        this.swappable = false;
    }

    // ---------- Getters & Setters ----------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // ✅ Added: status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ✅ Added: swappable
    public Boolean getSwappable() {
        return swappable;
    }

    public void setSwappable(Boolean swappable) {
        this.swappable = swappable;
    }
}
