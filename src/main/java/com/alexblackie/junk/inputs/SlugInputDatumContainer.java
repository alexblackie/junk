package com.alexblackie.junk.inputs;

public class SlugInputDatumContainer extends StringInputDatumContainer {

	public String getSlug() {
		return this.getValue();
	}

	public void setSlug(String slug) {
		this.setValue(slug);
	}

}
