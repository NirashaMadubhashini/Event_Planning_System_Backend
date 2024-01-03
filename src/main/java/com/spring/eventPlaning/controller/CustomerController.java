package com.spring.eventPlaning.controller;

import com.spring.eventPlaning.exception.BaseException;
import com.spring.eventPlaning.exception.RecordAlreadySubmittedException;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //    @RolesAllowed("customer-add")
    @PostMapping("/")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO dto) {
        try {
            return ResponseEntity.ok(customerService.saveCustomer(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("customer-delete")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteCustomer(@RequestParam String nic) {
        try {
            return ResponseEntity.ok(customerService.deleteCustomer(nic));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("customer-update")
    @PutMapping("/")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO dto) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("customer-search")
    @GetMapping(path = "/{nic}")
    public ResponseEntity<?> searchCustomerDetail(@PathVariable String nic) {
        try {
            return ResponseEntity.ok(customerService.searchCustomer(nic));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("customer-view-all")
    @GetMapping("/view-all")
    public ResponseEntity<?> getAllCustomerDetails() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/rating")
    public ResponseEntity<?> addRatings(@RequestBody RatingsDTO ratingsDTO) {
        try {
            return ResponseEntity.ok(customerService.saveCustomerRatings(ratingsDTO));
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/view-all/ratings/{vendorNic}")
    public ResponseEntity<?> getAllCustomerRatingDetails(@PathVariable String vendorNic) {
        try {
            return ResponseEntity.ok(customerService.searchCustomerRatings(vendorNic));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("order-add")
    @PostMapping("/orders")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(customerService.saveOrder(orderDTO));
    }
}
