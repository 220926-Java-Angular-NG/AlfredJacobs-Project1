package com.revature.models;

public class Ticket {

    private int id;
    private double amount;
    private String description;
    private String status;
    private int creatorId;

    public Ticket() {
    }

    public Ticket(int id, double amount, String description, String status, int creatorId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
    }

    public Ticket(double amount, String description, String status, int creatorId) {
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}