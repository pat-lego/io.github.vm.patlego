package patlego.vm.github.io.datasource.utils;

import java.sql.SQLException;
import java.util.List;

public interface DataResource {

    public <T> List<T> executeSQL(String sql, String columnName) throws SQLException;
    
}