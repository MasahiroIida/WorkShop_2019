package javastudywebapplication.app.core;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基底クラス
 * 
 * @author iida
 *
 */
@SpringBootApplication
public class JavaStudyWebApplication {

	private static Logger logger = Logger.getLogger(JavaStudyWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaStudyWebApplication.class, args);
		logger.info("application:start");
	}
}
