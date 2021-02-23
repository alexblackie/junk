package com.alexblackie.junk.models;

import java.nio.ByteBuffer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component("picFactory")
public class PicFactory {

	public Pic buildPic(String slug, Flux<ByteBuffer> image) {
		Pic pic = buildPic(slug);
		pic.setImage(image);
		return pic;
	}

	public Pic buildPic(String slug) {
		String[] segments = slug.split("/");

		String name;
		String prefix;

		if (segments.length > 1) {
			prefix = segments[0];
			name = segments[1];
		} else {
			prefix = null;
			name = segments[0];
		}

		Pic pic = new Pic();

		pic.setName(name);
		pic.setPrefix(prefix);
		pic.setSlug(slug);

		return pic;
	}
}
