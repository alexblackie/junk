package com.alexblackie.junk.models;

import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Component;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;

@Component("picFactory")
public class PicFactory {

	public Pic buildPic(
			NameInputDatumContainer nameInputDatumContainer,
			SlugInputDatumContainer slugInputDatumContainer) {
		Pic pic = new Pic();

		pic.setName(nameInputDatumContainer);
		pic.setSlug(slugInputDatumContainer);

		return pic;
	}

	public Pic buildPic(
			NameInputDatumContainer nameInputDatumContainer,
			SlugInputDatumContainer slugInputDatumContainer,
			Flux<ByteBuffer> image) {
		Pic pic = buildPic(nameInputDatumContainer, slugInputDatumContainer);
		pic.setImage(image);
		return pic;
	}

}
