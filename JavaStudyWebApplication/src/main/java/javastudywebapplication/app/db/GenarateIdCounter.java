package javastudywebapplication.app.core.db;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

/**
 * ID採番クラス<br>
 * 暫定用。後々はH2の機能で採番すること
 * 
 * @author iida
 *
 */
@Component
public class GenarateIdCounter {

	public AtomicInteger userIdCounter = new AtomicInteger();

	public int issueUserId() {
		return userIdCounter.incrementAndGet();
	}
}
