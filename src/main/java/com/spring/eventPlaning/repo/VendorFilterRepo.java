package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.dto.VendorFilterDTO;
import com.spring.eventPlaning.exception.JdbcException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface VendorFilterRepo {
    Page<Vendor> getFilterVendorData(Pageable page, MapSqlParameterSource mapSqlParameterSource, VendorFilterDTO pageableFilterDto) throws JdbcException;
}
