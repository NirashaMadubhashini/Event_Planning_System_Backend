package com.spring.eventPlaning.controller;

import com.spring.eventPlaning.exception.BaseException;
import com.spring.eventPlaning.exception.RecordAlreadySubmittedException;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
@Slf4j
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //    @RolesAllowed("event-add")
    @PostMapping("/add/event")
    public ResponseEntity<?> addEvent(@RequestBody EventDTO dto) {
        try {
            return ResponseEntity.ok(adminService.addEvent(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("service-add")
    @PostMapping("/add/service")
    public ResponseEntity<?> addService(@RequestBody ServicesDTO dto) {
        try {
            return ResponseEntity.ok(adminService.addService(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("package-add")
    @PostMapping("/add/package")
    public ResponseEntity<?> addPackages(@RequestBody PackagesDTO dto) {
        try {
            return ResponseEntity.ok(adminService.addPackage(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("events-view-all")
    @GetMapping("/view-all/events")
    public ResponseEntity<?> getAllEventDetails() {
        try {
            return ResponseEntity.ok(adminService.getAllEvents());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("services-view-all")
    @GetMapping("/view-all/services")
    public ResponseEntity<?> getAllServiceDetails() {
        try {
            return ResponseEntity.ok(adminService.getAllServices());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("packages-view-all")
    @GetMapping("/view-all/packages")
    public ResponseEntity<?> getAllPackageDetails() {
        try {
            return ResponseEntity.ok(adminService.getAllPackages());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("events-search")
    @GetMapping(path = "/{eventId}")
    public ResponseEntity<?> searchEventDetail(@PathVariable Long eventId) {
        try {
            return ResponseEntity.ok(adminService.searchEvent(eventId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("services-search")
    @GetMapping(path = "/{serviceId}")
    public ResponseEntity<?> searchServiceDetail(@PathVariable Long serviceId) {
        try {
            return ResponseEntity.ok(adminService.searchService(serviceId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("packages-search")
    @GetMapping(path = "/{packageId}")
    public ResponseEntity<?> searchPackageDetail(@PathVariable Long packageId) {
        try {
            return ResponseEntity.ok(adminService.searchPackage(packageId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("event-delete")
    @DeleteMapping("/delete/event")
    public ResponseEntity<?> deleteEvent(@RequestParam Long eventId) {
        try {
            return ResponseEntity.ok(adminService.deleteEvent(eventId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("service-delete")
    @DeleteMapping("/delete/service")
    public ResponseEntity<?> deleteService(@RequestParam Long serviceId) {
        try {
            return ResponseEntity.ok(adminService.deleteService(serviceId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("package-delete")
    @DeleteMapping("/delete/package")
    public ResponseEntity<?> deletePackage(@RequestParam Long packageId) {
        try {
            return ResponseEntity.ok(adminService.deletePackage(packageId));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("event-update")
    @PutMapping("/update/event")
    public ResponseEntity<?> updateEvents(@RequestBody EventDTO dto) {
        try {
            return ResponseEntity.ok(adminService.updateEvent(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("event-service")
    @PutMapping("/update/service")
    public ResponseEntity<?> updateServices(@RequestBody ServicesDTO dto) {
        try {
            return ResponseEntity.ok(adminService.updateService(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("event-Package")
    @PutMapping("/update/package")
    public ResponseEntity<?> updatePackage(@RequestBody PackagesDTO dto) {
        try {
            return ResponseEntity.ok(adminService.updatePackage(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @RolesAllowed("status-change-admin")
    @PutMapping("/change/status/admin")
    public ResponseEntity<?> statusChange(@RequestBody OrderDTO dto) {
        try {
            return ResponseEntity.ok(adminService.changeStatusAdmin(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
