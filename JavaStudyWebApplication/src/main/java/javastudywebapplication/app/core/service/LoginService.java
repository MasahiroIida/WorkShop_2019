package javastudywebapplication.app.core.service;

import javastudywebapplication.app.domain.User;
import javastudywebapplication.app.response.LoginResponse;

/**
 * ログイン処理を行うサービス
 * 
 * @author iida
 *
 */
public interface LoginService {

	/**
	 * ログイン処理を行う
	 * 
	 * @param user
	 *            ユーザ
	 * @return ログイン結果
	 */
	public LoginResponse login(User user);
}
