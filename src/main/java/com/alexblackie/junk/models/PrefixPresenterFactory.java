package com.alexblackie.junk.models;

import org.springframework.stereotype.Component;

@Component("prefixPresenterFactory")
public class PrefixPresenterFactory {

	public PrefixPresenter buildPrefixPresenter(String value) {
		PrefixPresenter prefixPresenter = new PrefixPresenter();
		prefixPresenter.setPrefix(value);
		return prefixPresenter;
	}
}
