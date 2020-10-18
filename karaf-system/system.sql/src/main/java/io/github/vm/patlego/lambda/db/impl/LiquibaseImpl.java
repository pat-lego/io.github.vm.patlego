package io.github.vm.patlego.lambda.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import io.github.vm.patlego.lambda.db.DBManager;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class LiquibaseImpl implements DBManager {

    final private String URL = "url";
    final private String USER = "user";
    final private String PASSWORD = "password";

    private String changeLog;

    public LiquibaseImpl(String changeLog) {
        if (changeLog == null || changeLog.isEmpty()) {
            throw new RuntimeException("Cannot have an empty or null change log path, please refer to a local class path");
        }
        this.changeLog = changeLog;
    }

    @Override
    public void update(Properties props) throws Exception {
        if (props == null) {
            throw new RuntimeException("Cannot have a null property object passed to the update function");
        }

        if (getProperty(props, URL) == null) {
            throw new RuntimeException(String.format("The properties file is missing the %s key and value", URL));
        }

        if (getProperty(props, USER) == null) {
            throw new RuntimeException(String.format("The properties file is missing the %s key and value", USER));
        }

        if (getProperty(props, PASSWORD) == null) {
            throw new RuntimeException(String.format("The properties file is missing the %s key and value", PASSWORD));
        }

        // Get connection to the DB
        Connection conn = DriverManager.getConnection(getProperty(props, URL), props);
        
        // Create a database object
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
        
        // Liquibase object that will perform the migration
        Liquibase liquibase = new Liquibase(this.changeLog, new ClassLoaderResourceAccessor(),
                db);

        // Perform the migration
        liquibase.update(new Contexts());
        if (liquibase != null) {
            liquibase.close();
        }
    }

    public String getProperty(Properties props, String key) {
        if (props == null) {
            return null;
        }

        if (key == null) {
            return null;
        }

        return props.getProperty(key);
    }

}
