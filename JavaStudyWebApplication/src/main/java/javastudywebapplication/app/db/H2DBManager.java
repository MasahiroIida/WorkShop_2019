package javastudywebapplication.app.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import javastudywebapplication.app.domain.Domain;
import javastudywebapplication.app.domain.DomainFactory;

/**
 * DBアクセス管理クラス<br>
 * 原則DAO以外からはアクセスしないこと
 * 
 * @author iida
 *
 */
@Component
public class H2DBManager extends H2DriverAccessor {

	private Connection con = null;

	/**
	 * DB生成処理<br>
	 * DBファイルがある場合にはそれを読み込む
	 * 
	 */
	@PostConstruct
	public void createDB() {
		con = createConnection();
		createUserTable();
	}

	/**
	 * コネクション破棄
	 * 
	 */
	@PreDestroy
	public void closeDB() {
		closeConnection(con);
	}

	/**
	 * SQLを実行する
	 * 
	 * @param sql
	 *            SQL
	 * @return 実行結果
	 * @throws SQLException
	 *             エラー
	 */
	public int executeSql(String sql) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sql);
		int result = stmt.executeUpdate();
		stmt.close();
		return result;
	}

	/**
	 * SQLを実行する
	 * 
	 * @param sql
	 *            SQL
	 * @param binds
	 *            バインド文字列
	 * @return 実行結果
	 * @throws SQLException
	 *             エラー
	 */
	public int executeSql(String sql, List<String> binds) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sql);
		int bindIdx = 1;
		for (String bind : binds) {
			stmt.setString(bindIdx++, bind);
		}
		int result = stmt.executeUpdate();
		stmt.close();
		return result;
	}

	/**
	 * SQL(select)を実行する
	 * 
	 * @param sql
	 *            SQL
	 * @param schema
	 *            スキーマクラス
	 * @return 取得結果
	 * @throws SQLException
	 *             エラー
	 */
	public List<?> executeSelect(String sql, DomainFactory<?> schema) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Domain> resultList = new ArrayList<Domain>();
		Domain obj = schema.newInstance();
		while (rs.next()) {
			for (int i = 0; i < obj.columnSize(); i++) {
				obj.set(rs.getString(obj.get(i)), i);
			}
			resultList.add(obj);
		}
		return resultList;

	}

	/**
	 * ユーザテーブルがなければ生成する
	 * 
	 */
	private void createUserTable() {
		String sql = "create table if not exists user(" + "userid varchar(16) PRIMARY KEY NOT NULL,"
				+ "usernamekana varchar(64) NOT NULL," + "usernamekanji varchar(64) NOT NULL,"
				+ "usernameeng varchar(64) NOT NULL," + "password text NOT NULL)";
		try {
			executeSql(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
