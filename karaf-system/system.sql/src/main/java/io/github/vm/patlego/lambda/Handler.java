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
    final String pg_ChangeLog = "/org/postgres/db.changelog-1.0.xml";

    @Override
    public String handleRequest(InputStream json, Context context) {
        LambdaLogger logger = context.getLogger();
        try {

            InputStream jdbcProps = this.getClass().getResourceAsStream("/org/postgres/jdbc.properties");

            Properties props = new Properties();
            props.load(jdbcProps);

            DBManager manager = new LiquibaseImpl("/db-changelog-master.xml");

            manager.update(props);
        } catch (IOException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s", e.getClass().getName(), e.getMessage()));
           throw new RuntimeException(String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (DatabaseException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s", e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (SQLException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s", e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (LiquibaseException e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s", e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        } catch (Exception e) {
            logger.log(String.format("Caught a %s exception while processing the transaction, msg is the following %s", e.getClass().getName(), e.getMessage()));
            throw new RuntimeException(String.format("Caught a %s exception while processing the transaction", e.getClass().getName()));
        }

        return OK;
    }
}