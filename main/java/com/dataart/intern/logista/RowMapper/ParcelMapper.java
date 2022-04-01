package com.dataart.intern.logista.RowMapper;

import com.dataart.intern.logista.model.Parcel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapper implements RowMapper<Parcel> {
    @Override
    public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parcel parcel = new Parcel();
        parcel.setId(rs.getInt("id"));
        parcel.setSenderId(rs.getInt("sender_id"));
        parcel.setReceiverId(rs.getInt("receiver_id"));
        parcel.setOriginId(rs.getInt("origin_id"));
        parcel.setDestinationId(rs.getInt("destination_id"));
        parcel.setDescription(rs.getString("description"));
        parcel.setWidth(rs.getInt("width"));
        parcel.setHeight(rs.getInt("height"));
        parcel.setLength(rs.getInt("length"));
        parcel.setInsuredPrice(rs.getInt("insured_price"));
        parcel.setPrice(rs.getInt("price"));
        return parcel;
    }
}
