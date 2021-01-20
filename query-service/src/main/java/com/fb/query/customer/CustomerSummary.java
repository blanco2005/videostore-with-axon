package com.fb.query.customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CustomerSummary {

    @Id
    private String customerName;
    private int numberOfOngoingRentals;
    private LocalDate subscriptionDate;

    public CustomerSummary() {
    }

    public CustomerSummary(String customerName, int numberOfOngoingRentals, LocalDate subscriptionDate) {
        this.customerName = customerName;
        this.numberOfOngoingRentals = numberOfOngoingRentals;
        this.subscriptionDate = subscriptionDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfOngoingRentals() {
        return numberOfOngoingRentals;
    }

    public void setNumberOfOngoingRentals(int numberOfOngoingRentals) {
        this.numberOfOngoingRentals = numberOfOngoingRentals;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "CustomerSummary{" +
                "customerName='" + customerName + '\'' +
                ", numberOfOngoingRentals=" + numberOfOngoingRentals +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}