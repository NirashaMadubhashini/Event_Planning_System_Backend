package com.spring.eventPlaning.service;

import com.spring.eventPlaning.dto.OrderDTO;
import com.spring.eventPlaning.dto.VendorDTO;
import com.spring.eventPlaning.dto.VendorFilterDTO;
import org.hibernate.JDBCException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VendorService {
    String saveVendor(VendorDTO dto);

    String saveOrder(OrderDTO dto);

    String updateVendor(VendorDTO dto);

    VendorDTO searchVendor(Long vendorId);

    String deleteVendor(Long nic);

    List<VendorDTO> getAllVendors();

    Page<VendorDTO> filterVendorDetails(VendorFilterDTO pageableDto) throws JDBCException;
}
