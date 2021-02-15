package com.alexblackie.junk.models;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;

public class Pic
	implements AbstractPicDataEntity<NameInputDatumContainer, SlugInputDatumContainer> {

	private String name;
	private String slug;

	public String getName() {
		return this.name;
	}

	public void setName(NameInputDatumContainer container) {
		this.name = container.getName();
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(SlugInputDatumContainer container) {
		this.slug = container.getSlug();
	}
}
