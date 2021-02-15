package com.alexblackie.junk;

import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexblackie.junk.data.PicDataService;
import com.alexblackie.junk.models.Pic;

@Controller
@RequestMapping("/")
public class PicController {

	@Autowired
	private PicDataService picDataService;

    @GetMapping
	@ResponseBody
    public Flux<String> index() {
		Flux<Pic> pics = picDataService.listPics();
		return pics.map((Pic p) -> p.getName() + "<br>");
    }

}
