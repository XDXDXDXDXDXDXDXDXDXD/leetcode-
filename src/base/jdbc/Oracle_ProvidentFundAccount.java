package base.jdbc;

import base.jdbc.constant.Constant_DB_DML;
import base.jdbc.constant.Constant_SqlCommandType;
import base.jdbc.util.JdbcUtil;
import util.ValidateUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static base.jdbc.constant.Constant_SqlCommandType.*;

/**
 * @Author Yanghz
 * @Since 2022/7/27
 * @Description personal providentFundAccount manipulation
 */
public class Oracle_ProvidentFundAccount {

    public static void main(String[] args) {

        insertNewRecordIntoProvidentFundAccount();
        updateMonthsAndMaxLoanAfterModify();
    }

    public static void updateMonthsAndMaxLoanAfterModify() {
        Connection conn = JdbcUtil.connectToYhz();

        List<Map<String, Object>> records = getAllProvidentFundAccount();

        records.sort((i, j) -> {
            Integer issue1 = Integer.valueOf(i.get("ISSUE").toString());
            Integer issue2 = Integer.valueOf(j.get("ISSUE").toString());
            return issue1.compareTo(issue2);
        });

        BigDecimal maxLoan = BigDecimal.ZERO;
        List<Map<String, String>> updateMonthsList = new ArrayList<>();
        List<Map<String, String>> updateMaxLoanList = new ArrayList<>();
        for (int i = 0; i < records.size(); ++i) {
            Map<String, Object> t = records.get(i);
            // 重新设置缴存月数
            t.put("MONTHS", records.size() - i);

            // 重新计算可贷金额
            BigDecimal money = BigDecimal.valueOf(Double.parseDouble(t.get("MONEY").toString()));
            BigDecimal months = BigDecimal.valueOf(Integer.parseInt(t.get("MONTHS").toString()));
            // 可贷金额=Σ(当月缴存额 * 当月缴存月份数 * 贷款系数(当前为0.9))
            maxLoan = maxLoan.add(money.multiply(months).multiply(BigDecimal.valueOf(0.9)));
            t.put("MAXLOAN", maxLoan);

            Map<String, String> updateMonthsMap = new HashMap<>();
            Map<String, String> updateMaxLoanMap = new HashMap<>();
            updateMonthsMap.put("columnValue", t.get("MONTHS").toString());
            updateMonthsMap.put("condition", t.get("ISSUE").toString());
            updateMaxLoanMap.put("columnValue", t.get("MAXLOAN").toString());
            updateMaxLoanMap.put("condition", t.get("ISSUE").toString());

            updateMonthsList.add(updateMonthsMap);
            updateMaxLoanList.add(updateMaxLoanMap);
        }

        try {
            executeUpdateBatchProvidentFundAccount(
                    Constant_DB_DML.DML_ORACLE_ORCL_YHZ_UpdateProvidentFundAccountMonths.getType(),
                    conn,
                    Constant_DB_DML.DML_ORACLE_ORCL_YHZ_UpdateProvidentFundAccountMonths.getDml(),
                    updateMonthsList,
                    200
            );
            if (!ValidateUtil.isEmpty(updateMaxLoanList)) {
                executeUpdateBatchProvidentFundAccount(
                        Constant_DB_DML.DML_ORACLE_ORCL_YHZ_UpdateProvidentFundAccountMaxLoan.getType(),
                        conn,
                        Constant_DB_DML.DML_ORACLE_ORCL_YHZ_UpdateProvidentFundAccountMaxLoan.getDml(),
                        updateMaxLoanList,
                        200
                );
            }
        } catch (Exception e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("somethings wrong i can feel it");
            }
        }

        JdbcUtil.closeConnectToYhz(conn);
    }

    public static void insertNewRecordIntoProvidentFundAccount() {

        Map<String, Object> newRecord = new HashMap<>();
        newRecord.put("ISSUE", "202207");
        newRecord.put("MONEY", "1140");
        newRecord.put("MONTHS", "1");
        newRecord.put("TOTAL", "22807.35");
        newRecord.put("MAXLOAN", "");
        int count = insertProvidentFundAccount(newRecord);
        System.out.println(count + "record has been inserted into providentFundAccount");
    }

    public static void deleteOneRecordFromProvidentFundAccount(String issue) {

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("issue", issue);
        list.add(map);

        Connection conn = JdbcUtil.connectToYhz();
        executeUpdateBatchProvidentFundAccount(
                Constant_DB_DML.DML_ORACLE_ORCL_YHZ_DeleteProvidentFundAccount.getType(),
                conn,
                Constant_DB_DML.DML_ORACLE_ORCL_YHZ_DeleteProvidentFundAccount.getDml(),
                list,
                200
        );
    }

    public static List<Map<String, Object>> getAllProvidentFundAccount() {

        Connection conn = JdbcUtil.connectToYhz();
        ResultSet resultSet = executeQueryProvidentFundAccount(conn, Constant_DB_DML.DML_ORACLE_ORCL_YHZ_GetAllProvidentFundAccount.getDml());
        JdbcUtil.closeConnectToYhz(conn);
        return JdbcUtil.getListMapFromResultSet(resultSet);
    }

    public static int insertProvidentFundAccount(Map<String, Object> map) {

        Connection conn = JdbcUtil.connectToYhz();
        return executeInsertProvidentFundAccount(conn, Constant_DB_DML.DML_ORACLE_ORCL_YHZ_InsertProvidentFundAccount.getDml(), map);
    }

    public static ResultSet executeQueryProvidentFundAccount(Connection conn, String dml) {

        if (conn == null) {
            return null;
        }

        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(dml);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int executeUpdateProvidentFundAccount(Connection conn, String dml) {

        if (conn == null) {
            return -1;
        }

        try {
            Statement statement = conn.createStatement();
            return statement.executeUpdate(dml);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int executeInsertProvidentFundAccount(Connection conn, String dml, Map<String ,Object> map) {

        if (conn == null) {
            return -1;
        }

        try {
            PreparedStatement preStatement = conn.prepareStatement(dml);
            preStatement.setObject(1, map.get("ISSUE"));
            preStatement.setObject(2, map.get("MONEY"));
            preStatement.setObject(3, map.get("MONTHS"));
            preStatement.setObject(4, map.get("TOTAL"));
            preStatement.setObject(5, map.get("MAXLOAN"));

            return preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Execute sql in batch mode.Including insert and update
     * @param conn connection to db_yhz
     * @param dml sql
     * @param list data to be inserted or updated
     * @param limit maximum data that can be inserted/updated at one time
     */
    public static void executeUpdateBatchProvidentFundAccount(Constant_SqlCommandType type, Connection conn, String dml, List<Map<String, String>> list, int limit) {

        if (conn == null) {
            return;
        }

        try {
            PreparedStatement preStatement = conn.prepareStatement(dml);
            for (int i = 0; i < list.size(); ++i) {
                Map<String, String> map = list.get(i);

                switch (type) {
                    case INSERT -> {
                        preStatement.setObject(1, map.get("issue"));
                        preStatement.setObject(2, map.get("money"));
                        preStatement.setObject(3, map.get("months"));
                        preStatement.setObject(4, map.get("total"));
                        preStatement.setObject(5, map.get("maxLoan"));
                    }
                    case UPDATE -> {
                        preStatement.setObject(1, map.get("columnValue"));
                        preStatement.setObject(2, map.get("condition"));
                    }
                    case DELETE -> preStatement.setObject(1, map.get("issue"));
                    default -> {
                        System.out.println("sqlCommandType[" + type +"]is not supported, return null");
                        return;
                    }
                }

                preStatement.addBatch();
                // when records size is larger than 200, insert/update 200 record once
                if (i % limit == 0) {
                    preStatement.executeBatch();
                    preStatement.clearBatch();
                }
            }
            preStatement.executeBatch();
            preStatement.clearBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
