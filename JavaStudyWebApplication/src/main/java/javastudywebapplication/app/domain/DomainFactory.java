package javastudywebapplication.app.domain;

/**
 * ドメインのファクトリー
 * 
 * @author iida
 *
 * @param <T>
 */
public interface DomainFactory<T extends Domain> {
	public T newInstance();
}
