package io.github.vm.patlego.lambda.db;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.vm.patlego.lambda.db.impl.LiquibaseImpl;

public class TestLiquibaseImpl {

    @Test
    public void testEmptyNullChangeLog() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new LiquibaseImpl(StringUtils.EMPTY);
        });
    }

    @Test
    public void testGetProperty() {
        Properties props = new Properties();
        props.setProperty("url", "https://www.google.com");

        LiquibaseImpl liquibase = new LiquibaseImpl("my_path.xml");
        Assertions.assertEquals("https://www.google.com", liquibase.getProperty(props, "url"));
    }

    @Test
    public void testGetEmptyProperty() {
        Properties props = new Properties();
        props.setProperty("web", "https://www.google.com");

        LiquibaseImpl liquibase = new LiquibaseImpl("my_path.xml");
        Assertions.assertEquals(null, liquibase.getProperty(props, "url"));
    }

    @Test
    public void testGetPropertyNullProps() {
        LiquibaseImpl liquibase = new LiquibaseImpl("my_path.xml");
        Assertions.assertEquals(null, liquibase.getProperty(null, "url"));
    }

    
}
