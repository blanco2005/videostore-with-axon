package com.fb.videostore.adapter;

import com.fb.query.customer.CustomerSummary;
import com.fb.videostore.AllCustomersSummaryQuery;
import com.fb.videostore.CreateCustomerCommand;
import com.fb.videostore.service.CustomerService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf;

public class AxonCustomerService implements CustomerService {

  @Autowired private CommandGateway commandGateway;
  @Autowired private QueryGateway queryGateway;

  @Override
  public void create(String customerName) {
    commandGateway.sendAndWait(new CreateCustomerCommand(customerName));
  }

  @Override
  public List<CustomerSummary> getCustomers() {
    try {
      return queryGateway.query(new AllCustomersSummaryQuery(), multipleInstancesOf(CustomerSummary.class)).get();
    } catch (Exception e) {
      throw new RuntimeException("Problem with future");
    }
  }
}
