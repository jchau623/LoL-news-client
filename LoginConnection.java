import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * You must include the driver for this to work.
 *
 * On IntelliJ: Project Structure (ctrl+alt+shift+s) -> Modules -> Dependencies -> Add Library
 * On Eclipse: http://www.ugrad.cs.ubc.ca/~cs304/2015S1/tutorials/JDBC/OracleFromEclipse.htm
 */
public class LoginConnection {
    public void logIn(String user, String password) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug",
                "ora_"+user, "a"+password);

    }

}
