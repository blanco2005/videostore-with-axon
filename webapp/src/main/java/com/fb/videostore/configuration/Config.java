package com.fb.videostore.configuration;

import com.fb.videostore.adapter.AxonCustomerService;
import com.fb.videostore.adapter.AxonMovieService;
import com.fb.videostore.adapter.AxonRentalService;
import com.fb.videostore.service.CustomerService;
import com.fb.videostore.service.MovieService;
import com.fb.videostore.service.RentalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public MovieService movieService() {
    return new AxonMovieService();
  }

  @Bean
  public CustomerService customerService() {
    return new AxonCustomerService();
  }

  @Bean
  public RentalService rentalService() {
    return new AxonRentalService();
  }
}
