package com.example.demo.Service;

import com.example.demo.Models.UserInfPasswordModel;
import com.example.demo.queries.SqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String tcNo) throws UsernameNotFoundException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tcNo", tcNo);

        UserInfPasswordModel user =  namedParameterJdbcTemplate.queryForObject(SqlQuery.USER_DETAILS_SQL, params,  new BeanPropertyRowMapper<>(UserInfPasswordModel.class));
        return user;
    }
}
