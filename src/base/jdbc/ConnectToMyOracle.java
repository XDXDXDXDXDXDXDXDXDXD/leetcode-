package base.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Yanghz
 * @Since 2022/7/27
 * @Description
 */
public class ConnectToMyOracle {

    public static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

    public static final String selectAll = """
        select issue as 期号, money as 缴纳金额, total as 账户余额, maxLoan as 可贷金额 from providentFundAccount
        """;

    public static void main(String[] args) {

        connectYhz();
    }

    public static void connectYhz() {

        try {
//            Class<?> driver = Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, "yhz", "007yhz?");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAll);
            List<Map<String, Object>> result = getListMapFromResultSet(resultSet);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将resultSet转为List<Map<String, Object>>
     * @param resultSet jdbc查询出的resultSet
     * @return 转化成为的list<Map<String, Object>>
     */
    public static List<Map<String, Object>> getListMapFromResultSet(ResultSet resultSet) {

        List<Map<String, Object>> list = new ArrayList<>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; ++i) {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                    list.add(temp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
