package com.spring.eventPlaning.repo.impl;

import com.spring.eventPlaning.dto.VendorFilterDTO;
import com.spring.eventPlaning.entity.Vendor;
import com.spring.eventPlaning.exception.JdbcException;
import com.spring.eventPlaning.exception.RecordNotFoundException;
import com.spring.eventPlaning.repo.VendorFilterRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLDataException;
import java.util.List;

import static com.spring.eventPlaning.constant.ExceptionMessageConstant.CANNOT_FIND_DATA;
import static com.spring.eventPlaning.constant.ExceptionMessageConstant.NO_RECORD_FOUND;

@Repository
public class VendorFilterRepoImpl implements VendorFilterRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public VendorFilterRepoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String FILTER_DETAIL_SQL = "SELECT \n" +
            "vendor_nic,\n" +
            "vendor_name,\n" +
            "address,\n" +
            "email,\n" +
            "contact_no,\n" +
            "company_name,\n" +
            "service_type,\n" +
            "price,\n" +
            "portfolio,\n" +
            "city,\n" +
            "availability\n" +
            " FROM vendor \n" +
            " WHERE record_status = 'ACTIVE' AND availability = 'AVAILABLE' ";

    private static final String FILTER_DETAIL_COUNT = "SELECT count(*) FROM vendor where record_status = 'ACTIVE'";

    @Override
    public Page<Vendor> getFilterVendorData(Pageable page, MapSqlParameterSource mapSqlParameterSource, VendorFilterDTO pageableFilterDto) throws JdbcException {
        try {
            Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("created_date");

            String SQL = getSearchingSql(pageableFilterDto, mapSqlParameterSource, FILTER_DETAIL_SQL)
                    .concat(" ORDER BY ").concat(order.getProperty()).concat(" ").concat(order.getDirection().name())
                    .concat(" LIMIT ").concat(String.valueOf(page.getOffset()))
                    .concat(" , ").concat(String.valueOf(page.getPageSize()));

            List<Vendor> list = namedParameterJdbcTemplate.query(SQL, mapSqlParameterSource, BeanPropertyRowMapper.newInstance(Vendor.class));

            return new PageImpl<>(list, page, getSelectedCount(mapSqlParameterSource, pageableFilterDto));
        } catch (JdbcException | SQLDataException | BadSqlGrammarException ex) {
            throw new JdbcException(CANNOT_FIND_DATA);
        } catch (Exception ex) {
            throw new RecordNotFoundException(NO_RECORD_FOUND, ex);
        }
    }

    private Long getSelectedCount(MapSqlParameterSource mapSqlParameterSource, VendorFilterDTO searchDto) throws Exception {
        return namedParameterJdbcTemplate.queryForObject(getSearchingSql(searchDto, mapSqlParameterSource, FILTER_DETAIL_COUNT), mapSqlParameterSource, Long.class);
    }

    private String getSearchingSql(VendorFilterDTO searchDto, MapSqlParameterSource mapSqlParameterSource, String sql) throws Exception {

        if (StringUtils.isNotBlank(searchDto.getCity())) {
            sql = sql.concat(" AND city = :city");
            mapSqlParameterSource.addValue("city", searchDto.getCity());
        }

        if (StringUtils.isNotBlank(searchDto.getServiceType())) {
            sql = sql.concat(" AND service_type = :serviceType");
            mapSqlParameterSource.addValue("serviceType", searchDto.getServiceType());
        }
        return sql;
    }
}
