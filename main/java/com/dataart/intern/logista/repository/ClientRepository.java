package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.Client;
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
public class ClientRepository {

    private final DataSource dataSource;

    public List<Client> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM clients", new BeanPropertyRowMapper<>(Client.class));

    }

    public Client findById(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT * FROM clients WHERE id = ?", new BeanPropertyRowMapper<>(Client.class), new Object[]{id});

    }

    public int create(Client client) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO clients(first_name, last_name, phone_number) values(?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, client.getFirstName());
                    ps.setString(2, client.getLastName());
                    ps.setString(3, client.getPhoneNumber());
                    return ps;

                }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void delete(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM clients WHERE id=?", id);
    }

    public void update(Integer id, Client client) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE clients SET first_name=?, last_name=?, phone_number=? WHERE id=?",
                client.getFirstName(), client.getLastName(), client.getPhoneNumber(), id);
    }
}
