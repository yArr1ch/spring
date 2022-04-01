package com.dataart.intern.logista.RowMapper;

import com.dataart.intern.logista.model.Depot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepotMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Depot depots = new Depot();
        depots.setId(rs.getInt("id"));
        depots.setNumber(rs.getInt("number"));
        depots.setAddress(rs.getString("address"));
        depots.setCityId(rs.getInt("city_id"));
        return depots;
    }
}
