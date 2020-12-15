package com.fb.query.rental.status;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RentalStatus {

    @Id
    private String rentalId;
    private String status;

    public RentalStatus() {
    }

    public RentalStatus(String rentalId, String status) {
        this.rentalId = rentalId;
        this.status = status;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentalStatus{" +
                "rentalId='" + rentalId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
