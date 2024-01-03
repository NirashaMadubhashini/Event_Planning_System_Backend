package com.spring.eventPlaning.service;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDTO dto);

    String updateCustomer(CustomerDTO dto);

    CustomerDTO searchCustomer(String nic);

    String deleteCustomer(String nic);

    List<CustomerDTO> getAllCustomers();

    String saveCustomerRatings(RatingsDTO ratingsDTO);

    List<Ratings> searchCustomerRatings(String vendorNic);

    Orders saveOrder(OrderDTO orderDTO);
}
