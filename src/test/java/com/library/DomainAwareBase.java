package com.library;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;

@ContextConfiguration(locations = {"/persistence-beans.xml"})
public abstract class DomainAwareBase extends AbstractJUnit4SpringContextTests {
    private final String DELETE_SCRIPT = "src/main/resources/sql/cleanup.sql";
    private final String CREATE_SCRIPT = "src/main/resources/sql/create-data.sql";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void deleteAllDomainEntities() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(DELETE_SCRIPT), false);
        //JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(CREATE_SCRIPT), false);
    }
}
