package com.spring.eventPlaning.service.impl;

import com.spring.eventPlaning.exception.RecordAlreadySubmittedException;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.repo.CustomerRepo;
import com.spring.eventPlaning.repo.OrderRepo;
import com.spring.eventPlaning.repo.RatingsRepo;
import com.spring.eventPlaning.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.spring.eventPlaning.constant.AppConstant.*;
import static com.spring.eventPlaning.constant.ExceptionMessageConstant.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final RatingsRepo ratingsRepo;
    private final CustomerRepo repo;
    private final ModelMapper mapper;
    private final OrderRepo orderRepository;

    public CustomerServiceImpl(CustomerRepo repo, ModelMapper mapper, RatingsRepo ratingsRepo, OrderRepo orderRepository) {
        this.repo = repo;
        this.mapper = mapper;
        this.ratingsRepo = ratingsRepo;
        this.orderRepository = orderRepository;
    }

    @Override
    public String saveCustomer(CustomerDTO dto) throws RecordAlreadySubmittedException {
        if (!repo.existsById(dto.getNic())) {
            Customer c = mapper.map(dto, Customer.class);
            repo.save(c);
            return SAVE_SUCCESSFUL;
        } else {
            throw new RecordAlreadySubmittedException(CUSTOMER_ALREADY_SUBMITTED);
        }
    }

    @Override
    public String updateCustomer(CustomerDTO dto) throws RecordNotFoundException {
        if (repo.existsById(dto.getNic())) {
            Customer c = mapper.map(dto, Customer.class);
            repo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public CustomerDTO searchCustomer(String nic) throws RecordNotFoundException {
        Optional<Customer> customer = repo.findById(nic);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + nic);
        }
    }

    @Override
    public String deleteCustomer(String nic) throws RecordNotFoundException {
        if (repo.existsById(nic)) {
            repo.deleteById(nic);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + nic);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public String saveCustomerRatings(RatingsDTO ratingsDTO) {
        Ratings c = mapper.map(ratingsDTO, Ratings.class);
        ratingsRepo.save(c);
        return SAVE_SUCCESSFUL;
    }

    @Override
    public List<Ratings> searchCustomerRatings(String vendorNic) {
        if (!vendorNic.trim().isEmpty()) {
            return ratingsRepo.findByVendorNic(vendorNic);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + vendorNic);
        }
    }


    @Override
    public Orders saveOrder(OrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setStatus(orderDTO.getStatus());
        orders.setTotalPrice(orderDTO.getTotalPrice());

        List<PlaceOrder> placeOrders = orderDTO.getPlaceOrders();
        if (placeOrders != null) {
            for (PlaceOrder placeOrder : placeOrders) {
                placeOrder.setOrders(orders);
            }
            orders.setPlaceOrders(placeOrders);
        }
        return orderRepository.save(orders);
    }
}


