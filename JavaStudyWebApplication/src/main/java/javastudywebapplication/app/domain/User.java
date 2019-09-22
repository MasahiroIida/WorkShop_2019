package javastudywebapplication.app.domain;

/**
 * ユーザオブジェクト
 * 
 * @author iida
 *
 */
public class User implements Domain {

	/** ユーザID */
	private String userId;
	/** ユーザ名(かな) */
	private String userNameKana;
	/** ユーザ名(漢字) */
	private String userNameKanji;
	/** ユーザ名(英語) */
	private String userNameEng;
	/** パスワード */
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNameKana() {
		return userNameKana;
	}

	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
	}

	public String getUserNameKanji() {
		return userNameKanji;
	}

	public void setUserNameKanji(String userNameKanji) {
		this.userNameKanji = userNameKanji;
	}

	public String getUserNameEng() {
		return userNameEng;
	}

	public void setUserNameEng(String userNameEng) {
		this.userNameEng = userNameEng;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String get(int i) {
		switch (i) {
		case 0:
			return "userid";
		case 1:
			return "userNameKana";
		case 2:
			return "userNameKanji";
		case 3:
			return "userNameEng";
		case 4:
			return "password";
		default:
			return null;
		}
	}

	@Override
	public void set(Object obj, int i) {
		switch (i) {
		case 0:
			setUserId((String) obj);
		case 1:
			setUserNameKana((String) obj);
		case 2:
			setUserNameKanji((String) obj);
		case 3:
			setUserNameEng((String) obj);
		case 4:
			setPassword((String) obj);
		default:
			//
		}
	}

	@Override
	public int columnSize() {
		return 5;

	}
}
