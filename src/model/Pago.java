package model;

import java.util.Date;

public class Pago {
    private int id;
    private Date date;
    private double amount;
    private String status;
    private String comment;

    public Pago(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "date=" + date +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
