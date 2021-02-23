package com.alexblackie.junk;

import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.alexblackie.junk.models.PrefixPresenter;
import com.alexblackie.junk.models.PrefixPresenterFactory;
import com.alexblackie.junk.converters.PicPresenterConverter;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainerFactory;
import com.alexblackie.junk.inputs.PrefixInputDatumContainer;
import com.alexblackie.junk.inputs.PrefixInputDatumContainerFactory;
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
	private PrefixInputDatumContainerFactory prefixInputDatumContainerFactory;

	@Autowired
	private PrefixPresenterFactory prefixPresenterFactory;

	@Autowired
	private PicPresenterConverter picPresenterConverter;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(Model model) {
		Flux<PicPresenter> presentablePics = this.picDataService
			.listAll()
			.map((Pic p) -> this.picPresenterConverter.picToPicPresenter(p));

		Flux<PrefixPresenter> prefixes = this.picDataService
			.listAllPrefixes()
			.map((String p) -> this.prefixPresenterFactory.buildPrefixPresenter(p));

		model.addAttribute("pageTitle", "everything");
		model.addAttribute("pics", presentablePics);
		model.addAttribute("prefixes", prefixes);

		return "index";
	}

	@RequestMapping(path = "/{prefix:[a-z]+}", method = RequestMethod.GET)
	public String indexPrefix(@PathVariable("prefix") String prefix, Model model) {
		PrefixInputDatumContainer prefixInputDatumContainer =
			this.prefixInputDatumContainerFactory.buildInputDatumContainer(prefix);

		Flux<PicPresenter> presentablePics = picDataService
			.listAll(prefixInputDatumContainer)
			.map((Pic p) -> this.picPresenterConverter.picToPicPresenter(p));

		Flux<PrefixPresenter> prefixes = this.picDataService
			.listAllPrefixes()
			.map((String p) -> this.prefixPresenterFactory.buildPrefixPresenter(p));

		model.addAttribute("pageTitle", prefixInputDatumContainer.getPrefix());
		model.addAttribute("pics", presentablePics);
		model.addAttribute("prefixes", prefixes);

		return "index";
	}

	@ResponseBody
	@RequestMapping(
		path = {"/{slug:.*\\.(?:jpe?g|gif|png)}", "/{prefix}/{slug:.*\\.(?:jpe?g|gif|png)}"},
		method = RequestMethod.GET
	)
	public ResponseEntity<Flux<ByteBuffer>> show(@PathVariable(value = "prefix", required = false) String prefix,
			@PathVariable("slug") String slug, Model model) {
		String fullName;

		if (prefix == null)
			fullName = slug;
		else
			fullName = prefix + "/" + slug;

		SlugInputDatumContainer slugInputDatumContainer =
			this.slugInputDatumContainerFactory.buildInputDatumContainer(fullName);

		Pic pic = this.picDataService.getBySlug(slugInputDatumContainer);

		MediaType mediaType = MediaTypeFactory
			.getMediaType(slugInputDatumContainer.getSlug())
			.orElse(MediaType.APPLICATION_OCTET_STREAM);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);

		ResponseEntity<Flux<ByteBuffer>> responseEntity =
			new ResponseEntity<>(pic.getImage(), headers, HttpStatus.OK);

		return responseEntity;
	}

	// Avoid favicon requests raising errors.
	@ResponseBody
	@RequestMapping(path = "/favicon.ico", method = RequestMethod.GET)
	public String favicon() {
		return "";
	}
}
