package io.github.vm.patlego.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import io.github.vm.patlego.lambda.db.DBManager;
import io.github.vm.patlego.lambda.db.impl.LiquibaseImpl;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;

public class Handler implements RequestHandler<InputStream, String> {

    final String UTF_8 = StandardCharsets.UTF_8.name();
    final String OK = "200 OK";
    final String pg_ChangeLog = "/db-changelog-master.xml";

    @Override
    public String handleRequest(InputStream json, Context context) {
        LambdaLogger logger = context.getLogger();
        try {
            Properties props = this.getPostgresJDBCProps();

            this.performUpdate(this.pg_ChangeLog, props);
        } catch (IOException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s",
                    e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(
                    String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (DatabaseException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s",
                    e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(
                    String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (SQLException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s",
                    e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(
                    String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (LiquibaseException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s",
                    e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(
                    String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (Exception e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s",
                    e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(
                    String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        }

        return OK;
    }

    /**
     * Used to retrieve the Postgres JDBC properties file in the resource folder of
     * the maven project
     * 
     * @return Properties
     * @throws IOException - Failed to load the file
     */
    public Properties getPostgresJDBCProps() throws IOException {
        InputStream jdbcProps = this.getClass().getResourceAsStream("/org/postgres/jdbc.properties");

        Properties props = new Properties();
        props.load(jdbcProps);

        return props;
    }

    /**
     * Performs the DB migration based off of the parameters supplied in the properties and the changelog location
     * @param changLog - The liquibase changelog location
     * @param props - The Properties containing user, password and url for the JDBC connection
     * @throws DatabaseException
     * @throws SQLException
     * @throws LiquibaseException
     * @throws Exception
     */
    public void performUpdate(String changeLog, Properties props) throws DatabaseException, SQLException, LiquibaseException, Exception {
        DBManager manager = new LiquibaseImpl(changeLog);
        manager.update(props);
    }
}