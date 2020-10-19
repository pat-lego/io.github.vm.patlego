package io.github.vm.patlego.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;

public class TestHandler {

    @Test
    public void testGetJDBCProps() throws IOException {
        Handler handler = new Handler();
        Assertions.assertNotNull(handler.getPostgresJDBCProps());
    }

    @Test
    public void testHandler() throws DatabaseException, SQLException, LiquibaseException, Exception {
        InputStream in = IOUtils.toInputStream("hello world", "UTF-8");

        Handler handler = Mockito.mock(Handler.class);
        Context context = Mockito.mock(Context.class);
        LambdaLogger logger = Mockito.mock((LambdaLogger.class));

        Mockito.when(context.getLogger()).thenReturn(logger);
        Mockito.doNothing().when(logger).log(Mockito.anyString());
        Mockito.when(handler.handleRequest(Mockito.any(), Mockito.any())).thenCallRealMethod();

        Mockito.doThrow(new IOException()).when(handler).performUpdate(Mockito.anyString(), Mockito.any());

        Assertions.assertThrows(RuntimeException.class, () -> {
            handler.handleRequest(in, context);
        });

        Mockito.doThrow(new DatabaseException()).when(handler).performUpdate(Mockito.anyString(), Mockito.any());

        Assertions.assertThrows(RuntimeException.class, () -> {
            handler.handleRequest(in, context);
        });

        Mockito.doThrow(new SQLException()).when(handler).performUpdate(Mockito.anyString(), Mockito.any());

        Assertions.assertThrows(RuntimeException.class, () -> {
            handler.handleRequest(in, context);
        });

        Mockito.doThrow(new LiquibaseException()).when(handler).performUpdate(Mockito.anyString(), Mockito.any());

        Assertions.assertThrows(RuntimeException.class, () -> {
            handler.handleRequest(in, context);
        });

        Mockito.doThrow(new Exception()).when(handler).performUpdate(Mockito.anyString(), Mockito.any());

        Assertions.assertThrows(RuntimeException.class, () -> {
            handler.handleRequest(in, context);
        });
    }
}
