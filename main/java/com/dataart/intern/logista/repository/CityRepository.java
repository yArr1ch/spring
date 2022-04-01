package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.City;
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
public class CityRepository {

    private final DataSource dataSource;

    public List<City> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM cities", new BeanPropertyRowMapper<>(City.class));
    }

    public City findById(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT * FROM cities WHERE id=?", new BeanPropertyRowMapper<>(City.class), new Object[]{id});
    }

    public int create(City city) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO cities(name) values(?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, city.getName());
                    return ps;

                }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void delete(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM cities WHERE id=?", id);
    }

    public void update(Integer id, City city) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE cities SET name=? WHERE id=?", city.getName(), id);
    }
}
