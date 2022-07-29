package base.jdbc.util;

import base.jdbc.constant.Constant_DB_ACCESS;
import util.ValidateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Yanghz
 * @Since 2022/7/28
 * @Description jdbc util(connect, covert resultSet to ..,etc)
 */
public class JdbcUtil {

    /**
     * connect to db_yhz
     * @return jdbc connection to db_yhz
     */
    public static Connection connectToYhz() {
        try {
            return DriverManager.getConnection(
                    Constant_DB_ACCESS.ACCESS_ORACLE_ORCL_YHZ.getUrl(),
                    Constant_DB_ACCESS.ACCESS_ORACLE_ORCL_YHZ.getUsername(),
                    Constant_DB_ACCESS.ACCESS_ORACLE_ORCL_YHZ.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * close connection to db_yhz
     * @param conn jdbc connection to db_yhz
     */
    public static void closeConnectToYhz(Connection conn) {

        if (ValidateUtil.isEmpty(conn)) {
            return;
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.exit(0);
        }
    }

    /**
     * Transfer resultSet to ListMap
     * @param resultSet resultSet from db
     * @return a List contains several Map(ColumnName, ColumnValue)
     */
    public static List<Map<String, Object>> getListMapFromResultSet(ResultSet resultSet) {


        List<Map<String, Object>> list = new ArrayList<>();

        if (ValidateUtil.isEmpty(resultSet)) {
            return list;
        }

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> temp = new HashMap<>();
                for (int i = 1; i <= columnCount; ++i) {
                    temp.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                }
                list.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
