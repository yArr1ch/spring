package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.Parcel;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class ParcelRepository {

    private final DataSource dataSource;


    public List<Parcel> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM parcels", new BeanPropertyRowMapper<>(Parcel.class));
    }

    public Parcel find(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT * FROM parcels WHERE id=?", new BeanPropertyRowMapper<>(Parcel.class), new Object[]{id});
    }

    public int create(Parcel parcel) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO parcels (sender_id, receiver_id, origin_id, destination_id, description, " +
                "width, height, length, insured_price, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setInt(1, parcel.getSenderId());
                    ps.setInt(2, parcel.getReceiverId());
                    ps.setInt(3, parcel.getOriginId());
                    ps.setInt(4, parcel.getDestinationId());
                    ps.setString(5, parcel.getDescription());
                    ps.setInt(6, parcel.getWidth());
                    ps.setInt(7, parcel.getHeight());
                    ps.setInt(8, parcel.getLength());
                    ps.setInt(9, parcel.getInsuredPrice());
                    ps.setInt(10, parcel.getPrice());

                    return ps;
                }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public void delete(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM parcels WHERE id=?", id);
    }

    public void update(Integer id, Parcel parcel) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE parcels SET receiver_id=?, destination_id=? WHERE id=?", parcel.getReceiverId(), parcel.getDestinationId(), id);
    }
}
