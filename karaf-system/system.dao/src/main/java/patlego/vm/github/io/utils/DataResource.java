package patlego.vm.github.io.utils;

import java.sql.SQLException;
import java.util.List;

public interface DataResource {

    public <T> List<T> executeSQL(String sql, String columnName) throws SQLException;
    
}