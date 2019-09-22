package javastudywebapplication.app.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * H2Database接続クラス
 * 
 * @author iida
 *
 */
public class H2DriverAccessor {

	private final static String DRIVER_URL = "jdbc:h2:file:./src/main/resources/db/JavaStudyWebApplicationDB";
	private final static String DRIVER_NAME = "org.h2.Driver";
	private final static String USER_NAME = "admin";
	private final static String PASSWORD = "";

	/**
	 * コネクションを生成する
	 * 
	 * @return コネクション
	 */
	public Connection createConnection() {
		try {
			Class.forName(DRIVER_NAME);
			Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println("Can't Find H2 Driver.\n");
		} catch (SQLException e) {
			System.out.println("Connection Error.\n");
		}
		return null;
	}

	/**
	 * コネクションを破棄する
	 * 
	 * @param con コネクション
	 */
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
