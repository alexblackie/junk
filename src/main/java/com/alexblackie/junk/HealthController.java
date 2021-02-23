package com.alexblackie.junk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthController {

	@ResponseBody
	@GetMapping("/healthz")
	public String healthz() {
		return "OK!";
	}

}
