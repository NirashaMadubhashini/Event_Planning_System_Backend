package com.spring.eventPlaning.service;

import com.spring.eventPlaning.dto.EventDTO;
import com.spring.eventPlaning.dto.OrderDTO;
import com.spring.eventPlaning.dto.PackagesDTO;
import com.spring.eventPlaning.dto.ServicesDTO;
import com.spring.eventPlaning.entity.Orders;

import java.util.List;

public interface AdminService {
    String addEvent(EventDTO dto);

    String addPackage(PackagesDTO dto);

    String addService(ServicesDTO dto);

    List<EventDTO> getAllEvents();

    List<ServicesDTO> getAllServices();

    List<PackagesDTO> getAllPackages();

    EventDTO searchEvent(Long eventId);

    ServicesDTO searchService(Long serviceId);

    PackagesDTO searchPackage(Long packageId);

    String deleteEvent(Long eventId);

    String deleteService(Long serviceId);

    String deletePackage(Long packageId);

    String updateEvent(EventDTO dto);

    String updateService(ServicesDTO dto);

    String updatePackage(PackagesDTO dto);

    Orders changeStatusAdmin(OrderDTO dto);


}
