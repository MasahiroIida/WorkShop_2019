package javastudywebapplication.app.domain;

/**
 * ドメインのインターフェース
 * 
 * @author iida
 *
 */
public interface Domain {
	
	public String get(int i);

	public void set(Object obj, int i);

	/**
	 * カラム件数を取得する
	 * 
	 * @return カラム件数
	 */
	public int columnSize();

}
