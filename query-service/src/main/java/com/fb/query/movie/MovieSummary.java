package com.fb.query.movie;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieSummary {

    @Id
    private String title;
    private int numberOfCopiesPurchased;
    private int numberOfCopiesAvailableForRenting;

    public MovieSummary() {
    }

    public MovieSummary(String title, int numberOfCopiesPurchased, int numberOfCopiesAvailableForRenting) {
        this.title = title;
        this.numberOfCopiesPurchased = numberOfCopiesPurchased;
        this.numberOfCopiesAvailableForRenting = numberOfCopiesAvailableForRenting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfCopiesPurchased() {
        return numberOfCopiesPurchased;
    }

    public void setNumberOfCopiesPurchased(int numberOfCopiesPurchased) {
        this.numberOfCopiesPurchased = numberOfCopiesPurchased;
    }

    public int getNumberOfCopiesAvailableForRenting() {
        return numberOfCopiesAvailableForRenting;
    }

    public void setNumberOfCopiesAvailableForRenting(int numberOfCopiesAvailableForRenting) {
        this.numberOfCopiesAvailableForRenting = numberOfCopiesAvailableForRenting;
    }

    @Override
    public String toString() {
        return "MovieSummary{" +
                "title='" + title + '\'' +
                ", numberOfCopiesPurchased=" + numberOfCopiesPurchased +
                ", numberOfCopiesAvailableForRenting=" + numberOfCopiesAvailableForRenting +
                '}';
    }
}
