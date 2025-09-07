package com.example.demo.Repository;

import com.example.demo.Models.UserInfModel;
import com.example.demo.queries.SqlQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserInfRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Integer addUser(UserInfModel userInfModel){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("identityNumber", userInfModel.getIdentity_number());
        params.addValue("name", userInfModel.getName());
        params.addValue("surname", userInfModel.getSurname());
        params.addValue("isAdmin", userInfModel.getIsAdmin());
        params.addValue("cityCode", userInfModel.getCityCode());
        params.addValue("institutionId", userInfModel.getInstitution_id());

        return namedParameterJdbcTemplate.queryForObject(SqlQuery.INSERT_USERINF, params, Integer.class);

    }


}
