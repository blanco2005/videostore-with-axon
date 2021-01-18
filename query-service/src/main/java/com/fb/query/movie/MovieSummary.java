package com.fb.query.movie;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieSummary {

  @Id
  private String title;
  private Integer numberOfCopyTotal;
  private Integer numberOfCopyAvailable;

  public MovieSummary() {
  }

  public MovieSummary(
      String title,
      Integer numberOfCopyTotal,
      Integer numberOfCopyAvailable
  ) {
    this.title = title;
    this.numberOfCopyTotal = numberOfCopyTotal;
    this.numberOfCopyAvailable = numberOfCopyAvailable;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getNumberOfCopyTotal() {
    return numberOfCopyTotal;
  }

  public void setNumberOfCopyTotal(Integer numberOfCopyTotal) {
    this.numberOfCopyTotal = numberOfCopyTotal;
  }

  public Integer getNumberOfCopyAvailable() {
    return numberOfCopyAvailable;
  }

  public void setNumberOfCopyAvailable(Integer numberOfCopyAvailable) {
    this.numberOfCopyAvailable = numberOfCopyAvailable;
  }
}
