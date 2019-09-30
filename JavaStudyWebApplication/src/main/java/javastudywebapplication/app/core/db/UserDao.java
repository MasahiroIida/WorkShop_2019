package javastudywebapplication.app.core.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javastudywebapplication.app.domain.User;

/**
 * UserDAO
 * 
 * @author iida
 *
 */
@Component
public class UserDao {

	@Autowired
	H2DBManager dbManager;

	@Autowired
	GenarateIdCounter idCounter;

	private final String SELECT = "select * from user where userid = ?";
	private final String SELECT_ALL = "select * from user";
	private final String INSERT = "insert into user values(?,?,?,?,?)";

	/**
	 * 検索を行う
	 * 
	 * @param userId ユーザID
	 * @return 検索結果
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<User> select(String userId) throws SQLException {
		List<String> binds = new ArrayList<String>();
		binds.add(userId);
		return (List<User>) dbManager.executeSelect(SELECT, () -> new User(), binds);
	}

	/**
	 * 全件検索を行う
	 * 
	 * @return 検索結果
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<User> selectAll() throws SQLException {
		return (List<User>) dbManager.executeSelect(SELECT_ALL, () -> new User());
	}

	/**
	 * レコードを挿入する
	 * 
	 * @param user
	 *            ユーザ
	 * @return 実行結果
	 * @throws SQLException
	 *             エラー
	 */
	public int insert(User user) throws SQLException {
		List<String> binds = new ArrayList<String>();
		binds.add(Integer.toString(idCounter.issueUserId()));
		binds.add(user.getUserNameKana());
		binds.add(user.getUserNameKanji());
		binds.add(user.getUserNameEng());
		binds.add(user.getPassword());
		return dbManager.executeSql(INSERT, binds);
	}
}
