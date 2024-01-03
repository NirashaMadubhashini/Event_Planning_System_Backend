package com.spring.eventPlaning.entity;

import com.spring.eventPlaning.enums.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vendor extends SuperEntity<VendorDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  private Long vendorId;
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
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availability;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;


    @Override
    public VendorDTO toDto() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorId(this.vendorId);
        vendorDTO.setVendorNic(this.vendorNic);
        vendorDTO.setVendorName(this.vendorName);
        vendorDTO.setAddress(this.address);
        vendorDTO.setEmail(this.email);
        vendorDTO.setContactNo(this.contactNo);
        vendorDTO.setServiceName(this.serviceName);
        vendorDTO.setServiceType(this.serviceType);
        vendorDTO.setPrice(this.price);
        vendorDTO.setPortfolio(this.portfolio);
        vendorDTO.setCity(this.city);
        vendorDTO.setAvailability(this.availability.toString());
        vendorDTO.setStartDate(this.startDate);
        vendorDTO.setEndDate(this.endDate);

        return vendorDTO;
    }
}
