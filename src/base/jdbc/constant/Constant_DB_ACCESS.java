package base.jdbc.constant;

/**
 * @Author Yanghz
 * @Since 2022/7/27
 * @Description Constant for database authentication(url, username, password)
 *
 */
public enum Constant_DB_ACCESS {

    ACCESS_ORACLE_ORCL_YHZ("jdbc:oracle:thin:@127.0.0.1:1521:orcl?rewriteBatchedStatements=true", "yhz", "007yhz?"),
    ;

    private final String url;
    private final String username;
    private final String password;

    Constant_DB_ACCESS(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
