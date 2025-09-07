package com.example.demo.Repository;

import com.example.demo.Models.UserInfPasswordModel;
import com.example.demo.queries.SqlQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfPasswordRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserInfPasswordRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int insertPassword(UserInfPasswordModel userPass) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userPass.getUserId());
        params.addValue("password", userPass.getPassword());

        return namedParameterJdbcTemplate.update(SqlQuery.INSERT_USERINFPASSWORD, params);
    }
}
