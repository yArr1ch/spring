package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.Depot;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@AllArgsConstructor
public class DepotRepository {

    private final DataSource dataSource;

    public List<Depot> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM depots", new BeanPropertyRowMapper<>(Depot.class));
    }

    public Depot findById(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT * FROM depots WHERE id=?", new BeanPropertyRowMapper<>(Depot.class), new Object[]{id});
    }

    public int create(Depot depot) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO depots(number, address, city_id) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setInt(1, depot.getNumber());
                    ps.setString(2, depot.getAddress());
                    ps.setInt(3, depot.getCityId());
                    return ps;
                }, keyHolder );

        return keyHolder.getKey().intValue();
    }

    public void delete(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM depots WHERE id=?", id);
    }

    public void update(Integer id, Depot depot) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE depots SET number=?, address=? WHERE id=?", depot.getNumber(), depot.getAddress(), id);
    }
}
