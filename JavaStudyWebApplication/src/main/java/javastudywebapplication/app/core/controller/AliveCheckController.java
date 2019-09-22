package javastudywebapplication.app.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 動作確認用の疎通コントローラー
 * 
 * @author iida
 *
 */
@RestController("/aliveCheck")
public class AliveCheckController {

	@RequestMapping
	public String aliveCheck() {
		return "Its alive!!!";
	}
}
