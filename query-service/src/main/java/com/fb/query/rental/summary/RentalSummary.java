package com.fb.query.rental.summary;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RentalSummary {

    @Id
    private String rentalId;
    private String movie;
    private String customer;
    private LocalDate rentalDate;

    public RentalSummary() {
    }

    public RentalSummary(String rentalId, String movie, String customer, LocalDate rentalDate) {
        this.rentalId = rentalId;
        this.movie = movie;
        this.customer = customer;
        this.rentalDate = rentalDate;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public String toString() {
        return "RentalSummary{" +
                "rentalId='" + rentalId + '\'' +
                ", movie='" + movie + '\'' +
                ", customer='" + customer + '\'' +
                ", rentalDate=" + rentalDate +
                '}';
    }
}
