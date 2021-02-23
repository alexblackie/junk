package com.alexblackie.junk.models;

import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;

public class Pic {

	private String name;
	private String prefix;
	private String slug;
	private Flux<ByteBuffer> image;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String value) {
		this.slug = value;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String value) {
		this.prefix = value;
	}

	public void setImage(Flux<ByteBuffer> image) {
		this.image = image;
	}

	public Flux<ByteBuffer> getImage() {
		return this.image;
	}
}
