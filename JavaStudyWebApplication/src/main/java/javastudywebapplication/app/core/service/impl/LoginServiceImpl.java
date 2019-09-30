package javastudywebapplication.app.core.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javastudywebapplication.app.core.db.UserDao;
import javastudywebapplication.app.core.service.LoginService;
import javastudywebapplication.app.domain.User;
import javastudywebapplication.app.response.LoginResponse;

/**
 * ログイン処理を行うサービスの実装クラス
 * 
 * @author iida
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserDao userDao;

	@Override
	public LoginResponse login(User user) {
		LoginResponse loginResponse = new LoginResponse();
		if(user.getUserId() == null || user.getPassword() == null) {
			loginResponse.setMessage("ログインID及びパスワードを入力してください");
			return loginResponse;
		}
		List<User> userList = null;
		try {
			userList = userDao.select(user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
			loginResponse.setMessage("システムエラーが発生しています。");
		}
		if(userList == null || userList.size() == 0) {
			loginResponse.setMessage("正しいログインIDを入力してください");
			return loginResponse;
		}
		if(!userList.get(0).getPassword().equals(user.getPassword())) {
			loginResponse.setMessage("ログインIDあるいはパスワードが間違っています。");
			return loginResponse;		
		}
		loginResponse.setResult(true);
		return loginResponse;
	}

}
