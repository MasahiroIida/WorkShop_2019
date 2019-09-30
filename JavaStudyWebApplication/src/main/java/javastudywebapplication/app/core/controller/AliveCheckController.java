package javastudywebapplication.app.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 動作確認用の疎通コントローラー
 * 
 * @author iida
 *
 */
@RestController
public class AliveCheckController {

	@RequestMapping("checkAlive")
	public String aliveCheck() {
		return "Its alive!!!";
	}
}
