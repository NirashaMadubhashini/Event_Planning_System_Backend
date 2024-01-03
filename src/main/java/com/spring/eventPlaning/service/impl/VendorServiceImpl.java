package com.spring.eventPlaning.service.impl;

import com.spring.eventPlaning.entity.Orders;
import com.spring.eventPlaning.entity.SuperEntity;
import com.spring.eventPlaning.entity.Vendor;
import com.spring.eventPlaning.exception.RecordAlreadySubmittedException;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.repo.OrderRepo;
import com.spring.eventPlaning.repo.VendorFilterRepo;
import com.spring.eventPlaning.repo.VendorRepo;
import com.spring.eventPlaning.service.VendorService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.JDBCException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.spring.eventPlaning.constant.AppConstant.*;
import static com.spring.eventPlaning.constant.ExceptionMessageConstant.RECORD_NOT_FOUND;
import static com.spring.eventPlaning.constant.ExceptionMessageConstant.VENDOR_NOT_FOUND;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {
    private final OrderRepo orderRepo;
    private final VendorRepo repo;
    private final ModelMapper mapper;
    private final VendorFilterRepo vendorFilterRepo;

    public VendorServiceImpl(VendorRepo repo, ModelMapper mapper, VendorFilterRepo vendorFilterRepo, OrderRepo orderRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.vendorFilterRepo = vendorFilterRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public String saveVendor(VendorDTO dto) throws RecordAlreadySubmittedException {
        Vendor c = mapper.map(dto, Vendor.class);
        repo.save(c);
        return SAVE_SUCCESSFUL;

    }

    @Override
    public String updateVendor(VendorDTO dto) throws RecordNotFoundException {
        if (repo.existsById(dto.getVendorId())) {
            Vendor c = mapper.map(dto, Vendor.class);
            repo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(VENDOR_NOT_FOUND);
        }
    }

    @Override
    public VendorDTO searchVendor(Long vendorId) throws RecordNotFoundException {
        Optional<Vendor> vendor = repo.findById(vendorId);
        if (vendor.isPresent()) {
            return mapper.map(vendor.get(), VendorDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + vendorId);
        }
    }


    @Override
    public String deleteVendor(Long id) throws RecordNotFoundException {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + id);
        }
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        List<Vendor> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<VendorDTO>>() {
        }.getType());
    }

    @Override
    public Page<VendorDTO> filterVendorDetails(VendorFilterDTO pageableDto) throws JDBCException {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        PageRequest nativePageable = getNativePageable(pageableDto, Vendor.class);

        return vendorFilterRepo.getFilterVendorData(nativePageable, mapSqlParameterSource, pageableDto).map(Vendor::toDto);
    }

    protected PageRequest getNativePageable(PageableDTO pageDto, Class... classes) {
        Sort defaultSort = Sort.by(Sort.Direction.DESC, "modified_date");
        if (StringUtils.isBlank(pageDto.getSortField())) {
            return PageRequest.of(pageDto.getPage(), pageDto.getSize(), defaultSort);
        }
        for (Class aClass : classes) {
            for (Field field : aClass.getDeclaredFields()) {
                if (pageDto.getSortField().equals(field.getName())) {
                    Sort sort = Sort.by(getDirection(pageDto.getSortOrder()), this.underscoreName(field.getName()));
                    return PageRequest.of(pageDto.getPage(), pageDto.getSize(), sort);
                }
            }
        }
        for (Field field : SuperEntity.class.getFields()) {
            if (pageDto.getSortField().equals(field.getName())) {
                Sort sort = Sort.by(getDirection(pageDto.getSortOrder()), this.underscoreName(field.getName()));
                return PageRequest.of(pageDto.getPage(), pageDto.getSize(), sort);
            }
        }
        return PageRequest.of(pageDto.getPage(), pageDto.getSize(), defaultSort);
    }

    protected Sort.Direction getDirection(String direction) {
        if (StringUtils.isBlank(direction)) {
            return Sort.Direction.ASC;
        }
        return "ascend".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    protected String underscoreName(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            result.append(name.substring(0, 1).toLowerCase(Locale.US));

            for (int i = 1; i < name.length(); ++i) {
                String s = name.substring(i, i + 1);
                String slc = s.toLowerCase(Locale.US);
                if (!s.equals(slc)) {
                    result.append("_").append(slc);
                } else {
                    result.append(s);
                }
            }

            return result.toString();
        }
    }

    @Override
    public String saveOrder(OrderDTO dto) throws RecordAlreadySubmittedException {
        Orders orders = mapper.map(dto, Orders.class);
        orderRepo.save(orders);
        return SAVE_SUCCESSFUL;
    }

    @Override
    public List<VendorDTO> searchServices(String serviceType) {
        List<Vendor> searchByServiceType = repo.findByServiceName(serviceType);

        List<VendorDTO> vendorDTOs = new ArrayList<>();
        for (Vendor vendor : searchByServiceType) {
            VendorDTO vendorDTO = vendor.toDto();
            vendorDTOs.add(vendorDTO);
        }

        return vendorDTOs;
    }
}

