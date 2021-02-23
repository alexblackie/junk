package com.alexblackie.junk.models;

public class PicPresenter {

	public String name;
	public String slug;

	public String prefix;
	public String prefixPath;

	public void setName(String name) {
		this.name = name;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public void setPrefix(String prefix) {
		if (prefix == null) {
			this.prefix = "::";
			this.prefixPath = "/";
		} else {
			this.prefix = prefix;
			this.prefixPath = "/" + prefix;
		}
	}

}
