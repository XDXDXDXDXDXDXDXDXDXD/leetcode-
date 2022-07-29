package base.jdbc.constant;

import static base.jdbc.constant.Constant_SqlCommandType.*;

/**
 * @Author Yanghz
 * @Since 2022/7/27
 * @Description Constant of DML
 */
public enum Constant_DB_DML {

    DML_ORACLE_ORCL_YHZ_GetAllProvidentFundAccount("select issue as issue, money, months, total, maxLoan from providentFundAccount order by issue desc", SELECT),
    DML_ORACLE_ORCL_YHZ_InsertProvidentFundAccount("insert into providentFundAccount(issue, money, months, total, maxLoan) values (?,?,?,?,?)", INSERT),
    DML_ORACLE_ORCL_YHZ_UpdateProvidentFundAccountMonths("update providentFundAccount set months = ? where issue = ?", UPDATE),
    DML_ORACLE_ORCL_YHZ_UpdateProvidentFundAccountMaxLoan("update providentFundAccount set maxLoan = ? where issue = ?", UPDATE),
    DML_ORACLE_ORCL_YHZ_DeleteProvidentFundAccount("delete from providentFundAccount where issue = ?", DELETE),
    ;

    private final String dml;
    private final Constant_SqlCommandType type;

    Constant_DB_DML(String dml, Constant_SqlCommandType type) {
        this.dml = dml;
        this.type = type;
    }

    public String getDml() {
        return dml;
    }

    public Constant_SqlCommandType getType() {
        return type;
    }
}
