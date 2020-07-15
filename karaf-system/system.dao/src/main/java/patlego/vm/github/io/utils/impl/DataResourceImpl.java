package patlego.vm.github.io.utils.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import patlego.vm.github.io.utils.DataResource;

public class DataResourceImpl implements DataResource {

    private DataSource ds;

    public DataResourceImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public <T> List<T> executeSQL(String sql, String columnName) throws SQLException {
        Connection connection = null;
        List<T> list = null;

        try {
            connection = this.ds.getConnection();
            list = new QueryRunner().execute(connection, sql, new ScalarHandler<T>(columnName));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }

}