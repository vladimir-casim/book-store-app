package com.library.integration;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class HibernateConfigurationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testHibernateConfiguration() {
        assertNotNull (sessionFactory); // Spring IOC container instantiated and prepared sessionFactory
    }
}
