package com.example.demo.queries;

public class SqlQuery {

    public static  final String USER_DETAILS_SQL = """
    SELECT u."ID" as userId, p."PASSWORD" as password
    FROM "USERINF" u
    JOIN "USERINFPASSWORD" p ON u."ID" = p."USER_ID"
    WHERE u."IDENTITY_NUMBER" = :tcNo
""";

    public static  final String INSERT_USERINF = """
    INSERT INTO "USERINF"
    ("IDENTITY_NUMBER", "NAME", "SURNAME", "IS_ADMIN", "CITY_CODE", "INSTITUTION_ID")
    VALUES (:identityNumber, :name, :surname, :isAdmin, :cityCode, :institutionId)
    RETURNING "ID"
""";

    public static  final String INSERT_USERINFPASSWORD = """
        INSERT INTO "USERINFPASSWORD" ("USER_ID", "PASSWORD")
        VALUES (:userId, :password)
    """;




}
