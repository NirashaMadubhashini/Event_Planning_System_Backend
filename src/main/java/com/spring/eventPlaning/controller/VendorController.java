package com.spring.eventPlaning.controller;

import com.spring.eventPlaning.constant.ExceptionMessageConstant;
import com.spring.eventPlaning.exception.BaseException;
import com.spring.eventPlaning.exception.JdbcException;
import com.spring.eventPlaning.exception.RecordAlreadySubmittedException;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendor")
@CrossOrigin
@Slf4j
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    //    @RolesAllowed("vendor-add")
    @PostMapping("/")
    public ResponseEntity<?> addVendor(@RequestBody VendorDTO dto) {
        try {
            return ResponseEntity.ok(vendorService.saveVendor(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("vendor-delete")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteVendor(@RequestParam Long vendorId) {
        try {
            return ResponseEntity.ok(vendorService.deleteVendor(vendorId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("vendor-update")
    @PutMapping("/")
    public ResponseEntity<?> updateVendor(@RequestBody VendorDTO dto) {
        try {
            return ResponseEntity.ok(vendorService.updateVendor(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("vendor-search")
    @GetMapping(path = "/{vendorId}")
    public ResponseEntity<?> searchVendorDetail(@PathVariable Long vendorId) {
        try {
            return ResponseEntity.ok(vendorService.searchVendor(vendorId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("vendor-view-all")
    @GetMapping("/view-all")
    public ResponseEntity<?> getAllVendorDetails() {
        try {
            return ResponseEntity.ok(vendorService.getAllVendors());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("filter-vendor-details")
    @PostMapping("/filter/details")
    public ResponseEntity<?> filterMyRequestDetailsAdmin(@RequestBody VendorFilterDTO pageableDto) {
        try {
            return ResponseEntity.ok(vendorService.filterVendorDetails(pageableDto));
        } catch (JdbcException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(ExceptionMessageConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("order-add")
    @PostMapping("/add/order")
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO dto) {
        try {
            return ResponseEntity.ok(vendorService.saveOrder(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("order-type-wise")
    @GetMapping(path ="/filter/order/typeWise/{serviceName}")
    public ResponseEntity<?> getServicesTypeWise(@PathVariable String serviceName) {
        try {
            return ResponseEntity.ok(vendorService.searchServices(serviceName));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
