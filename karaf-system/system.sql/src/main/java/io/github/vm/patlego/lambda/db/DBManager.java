package io.github.vm.patlego.lambda.db;

import java.sql.SQLException;
import java.util.Properties;

import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;

public interface DBManager {
    /**
     * Function used to perform migration
     * @throws SQLException
     * @throws DatabaseException
     * @throws LiquibaseException
     * @throws Exception
     */
    public void update(Properties props) throws SQLException, DatabaseException, LiquibaseException, Exception;
}
