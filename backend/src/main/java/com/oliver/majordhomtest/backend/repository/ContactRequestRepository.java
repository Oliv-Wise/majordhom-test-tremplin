package com.oliver.majordhomtest.backend.repository;

import com.oliver.majordhomtest.backend.dto.AvailabilityRequest;
import com.oliver.majordhomtest.backend.dto.CreateRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class ContactRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContactRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long saveContactRequest(CreateRequest request) {
        String sql = """
            INSERT INTO contact_requests (
                civility,
                last_name,
                first_name,
                email,
                phone,
                request_type,
                message
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, request.getCivility());
            statement.setString(2, request.getLastName());
            statement.setString(3, request.getFirstName());
            statement.setString(4, request.getEmail());
            statement.setString(5, request.getPhone());
            statement.setString(6, request.getRequestType());
            statement.setString(7, request.getMessage());
            return statement;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || keys.get("ID") == null) {
            throw new IllegalStateException("Impossible de récupérer l'identifiant créé.");
        }

        return ((Number) keys.get("ID")).longValue();
    }

    public void saveAvailabilities(Long contactRequestId, List<AvailabilityRequest> availabilities) {
        String sql = """
            INSERT INTO availabilities (
                contact_request_id,
                day_label,
                hour_value,
                minute_value
            ) VALUES (?, ?, ?, ?)
            """;

        for (AvailabilityRequest availability : availabilities) {
            jdbcTemplate.update(
                    sql,
                    contactRequestId,
                    availability.getDay(),
                    availability.getHour(),
                    availability.getMinute()
            );
        }
    }
}