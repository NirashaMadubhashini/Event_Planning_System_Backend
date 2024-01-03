package com.spring.eventPlaning.dto;

import com.spring.eventPlaning.enums.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO extends SuperDto<Vendor> {
    private Long vendorId;
    private String vendorNic;
    private String vendorName;
    private String address;
    private String email;
    private String contactNo;
    private String serviceName;
    private String serviceType;
    private Long price;
    private String portfolio;
    private String city;
    private String availability;
    private Date startDate;
    private Date endDate;

    @Override
    public Vendor toEntity() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(this.vendorId);
        vendor.setVendorNic(this.vendorNic);
        vendor.setVendorName(this.vendorName);
        vendor.setAddress(this.address);
        vendor.setEmail(this.email);
        vendor.setContactNo(this.contactNo);
        vendor.setServiceName(this.serviceName);
        vendor.setServiceType(this.serviceType);
        vendor.setPrice(this.price);
        vendor.setPortfolio(this.portfolio);
        vendor.setCity(this.city);
        vendor.setAvailability(null == this.availability ? AvailabilityStatus.AVAILABLE : AvailabilityStatus.getAvailabilityStatus(this.availability));
        vendor.setStartDate(this.startDate);
        vendor.setEndDate(this.endDate);
        return vendor;
    }
}
