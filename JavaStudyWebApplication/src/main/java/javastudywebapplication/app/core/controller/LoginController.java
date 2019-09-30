package javastudywebapplication.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javastudywebapplication.app.domain.User;
import javastudywebapplication.app.response.LoginResponse;
import javastudywebapplication.app.service.LoginService;

/**
 * 動作確認用の疎通コントローラー
 * 
 * @author iida
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * ログイン処理
	 * 
	 * @param user
	 *            ユーザ
	 * @return 結果
	 */
	public ResponseEntity<LoginResponse> login(@RequestBody User user) {
		LoginResponse loginResponse = loginService.login(user);
		return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
	}
}
