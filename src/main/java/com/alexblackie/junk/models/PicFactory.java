package com.alexblackie.junk.models;

import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Component;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.PrefixInputDatumContainer;

@Component("picFactory")
public class PicFactory {

	public Pic buildPic(
			NameInputDatumContainer nameInputDatumContainer,
			SlugInputDatumContainer slugInputDatumContainer,
			PrefixInputDatumContainer prefixInputDatumContainer) {
		Pic pic = new Pic();

		pic.setName(nameInputDatumContainer);
		pic.setSlug(slugInputDatumContainer);
		pic.setPrefix(prefixInputDatumContainer);

		return pic;
	}

	public Pic buildPic(
			NameInputDatumContainer nameInputDatumContainer,
			SlugInputDatumContainer slugInputDatumContainer,
			PrefixInputDatumContainer prefixInputDatumContainer,
			Flux<ByteBuffer> image) {
		Pic pic = buildPic(nameInputDatumContainer, slugInputDatumContainer, prefixInputDatumContainer);
		pic.setImage(image);
		return pic;
	}

}
