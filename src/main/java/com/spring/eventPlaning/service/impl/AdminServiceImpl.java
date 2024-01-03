package com.spring.eventPlaning.service.impl;

import com.spring.eventPlaning.dto.EventDTO;
import com.spring.eventPlaning.dto.OrderDTO;
import com.spring.eventPlaning.dto.PackagesDTO;
import com.spring.eventPlaning.dto.ServicesDTO;
import com.spring.eventPlaning.entity.*;
import com.spring.eventPlaning.enums.AvailabilityStatus;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.repo.*;
import com.spring.eventPlaning.service.AdminService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.spring.eventPlaning.constant.AppConstant.*;
import static com.spring.eventPlaning.constant.ExceptionMessageConstant.*;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final ModelMapper mapper;
    private final EventsRepo eventsRepo;
    private final ServicesRepo servicesRepo;
    private final PackagesRepo packagesRepo;
    private final VendorRepo vendorRepo;
    private final OrderRepo orderRepo;

    public AdminServiceImpl(ModelMapper mapper, EventsRepo eventsRepo, ServicesRepo servicesRepo, PackagesRepo packagesRepo, VendorRepo vendorRepo, OrderRepo orderRepo) {
        this.mapper = mapper;
        this.eventsRepo = eventsRepo;
        this.servicesRepo = servicesRepo;
        this.packagesRepo = packagesRepo;
        this.vendorRepo = vendorRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public String addEvent(EventDTO dto) {
        Event c = mapper.map(dto, Event.class);
        eventsRepo.save(c);
        return SAVE_SUCCESSFUL;
    }

    @Override
    public String addPackage(PackagesDTO dto) {
        Packages c = mapper.map(dto, Packages.class);
        packagesRepo.save(c);
        return SAVE_SUCCESSFUL;
    }

    @Override
    public String addService(ServicesDTO dto) {
        Services c = mapper.map(dto, Services.class);
        servicesRepo.save(c);
        return SAVE_SUCCESSFUL;
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> all = eventsRepo.findAll();
        return mapper.map(all, new TypeToken<List<EventDTO>>() {
        }.getType());
    }

    @Override
    public List<ServicesDTO> getAllServices() {
        List<Services> all = servicesRepo.findAll();
        return mapper.map(all, new TypeToken<List<ServicesDTO>>() {
        }.getType());
    }

    @Override
    public List<PackagesDTO> getAllPackages() {
        List<Packages> all = packagesRepo.findAll();
        return mapper.map(all, new TypeToken<List<PackagesDTO>>() {
        }.getType());
    }

    @Override
    public EventDTO searchEvent(Long eventId) {
        Optional<Event> event = eventsRepo.findById(eventId);
        if (event.isPresent()) {
            return mapper.map(event.get(), EventDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + event);
        }
    }

    @Override
    public ServicesDTO searchService(Long serviceId) {
        Optional<Services> event = servicesRepo.findById(serviceId);
        if (event.isPresent()) {
            return mapper.map(event.get(), ServicesDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + serviceId);
        }
    }

    @Override
    public PackagesDTO searchPackage(Long packageId) {
        Optional<Packages> event = packagesRepo.findById(packageId);
        if (event.isPresent()) {
            return mapper.map(event.get(), PackagesDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + packageId);
        }
    }

    @Override
    public String deleteEvent(Long id) {
        if (eventsRepo.existsById(id)) {
            eventsRepo.deleteById(id);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + id);
        }
    }

    @Override
    public String deleteService(Long serviceId) {
        if (servicesRepo.existsById(serviceId)) {
            servicesRepo.deleteById(serviceId);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + serviceId);
        }
    }

    @Override
    public String deletePackage(Long packageId) {
        if (packagesRepo.existsById(packageId)) {
            packagesRepo.deleteById(packageId);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + packageId);
        }
    }

    @Override
    public String updateEvent(EventDTO dto) {
        if (eventsRepo.existsById(dto.getEventId())) {
            Event c = mapper.map(dto, Event.class);
            eventsRepo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(EVENT_NOT_FOUND);
        }
    }

    @Override
    public String updateService(ServicesDTO dto) {
        if (servicesRepo.existsById(dto.getServiceId())) {
            Services c = mapper.map(dto, Services.class);
            servicesRepo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(SERVICE_NOT_FOUND);
        }
    }

    @Override
    public String updatePackage(PackagesDTO dto) {
        if (packagesRepo.existsById(dto.getPackageId())) {
            Packages c = mapper.map(dto, Packages.class);
            packagesRepo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(PACKAGE_NOT_FOUND);
        }
    }

    // Scheduler runs at 1.00 PM every day of the year
    @Scheduled(cron = "0 * 1 * * ?")
    public void cronJobSch() {
        Date currentDate = new Date();
        List<Vendor> vendors = vendorRepo
                .findByAvailability(AvailabilityStatus.AVAILABLE);

        scheduledData(currentDate, vendors);
    }

    private void scheduledData(Date currentDate, List<Vendor> vendors) {
        vendors
                .forEach(vendorDetail -> {
                    if (currentDate.after(vendorDetail.getEndDate())) {
                        vendorDetail.setAvailability(AvailabilityStatus.UNAVAILABLE);
                    }
                    if (currentDate.before(vendorDetail.getStartDate())) {
                        vendorDetail.setAvailability(AvailabilityStatus.UNAVAILABLE);
                    } else {
                        vendorDetail.setAvailability(AvailabilityStatus.AVAILABLE);
                    }
                });
    }

    @Override
    public Orders changeStatusAdmin(OrderDTO orderDTO) throws RecordNotFoundException {
        Orders existingOrders = orderRepo.findById(orderDTO.orderId).orElse(null);
        if (null == existingOrders) {
            throw new RecordNotFoundException(EVENT_NOT_FOUND);
        }

        existingOrders.setStatus(orderDTO.getStatus());
        existingOrders.setTotalPrice(orderDTO.getTotalPrice());
        existingOrders.setPlaceOrders(orderDTO.getPlaceOrders());

        return orderRepo.save(existingOrders);
    }
}


