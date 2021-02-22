package com.alexblackie.junk;

import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexblackie.junk.converters.PicPresenterConverter;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainerFactory;
import com.alexblackie.junk.data.PicDataService;
import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicPresenter;

@Controller
public class PicController {

	@Autowired
	private PicDataService picDataService;

	@Autowired
	private SlugInputDatumContainerFactory slugInputDatumContainerFactory;

	@Autowired
	private PicPresenterConverter picPresenterConverter;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(Model model) {
		Flux<PicPresenter> presentablePics = picDataService
			.listAll()
			.map((Pic p) -> this.picPresenterConverter.picToPicPresenter(p));

		model.addAttribute("pics", presentablePics);

		return "index";
	}

	@ResponseBody
	@RequestMapping(path = "/{slug}", method = RequestMethod.GET)
	public ResponseEntity<Flux<ByteBuffer>> show(@PathVariable("slug") String slug) {
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		SlugInputDatumContainer slugInputDatumContainer =
			this.slugInputDatumContainerFactory.buildInputDatumContainer(slug);

		Pic pic = this.picDataService.getBySlug(slugInputDatumContainer);

		MediaType mediaType = MediaTypeFactory
			.getMediaType(slugInputDatumContainer.getSlug())
			.orElse(MediaType.APPLICATION_OCTET_STREAM);

		headers.setContentType(mediaType);

		ResponseEntity<Flux<ByteBuffer>> responseEntity =
			new ResponseEntity<>(pic.getImage(), headers, HttpStatus.OK);

		return responseEntity;
	}

}
