package javastudywebapplication.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 動作確認用の疎通コントローラー
 * 
 * @author iida
 *
 */
@RestController
@RequestMapping("/aliveCheck")
public class AliveCheckController {

	public String aliveCheck() {
		return "Its alive!!!";
	}
}
