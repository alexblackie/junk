package com.alexblackie.junk.converters;

import org.springframework.stereotype.Component;

import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicPresenter;

@Component("picPresenterConverter")
public class PicPresenterConverter {

	public PicPresenter build(Pic pic) {
		PicPresenter presenter = new PicPresenter();

		presenter.setName(pic.getName());
		presenter.setPrefix(pic.getPrefix());
		presenter.setSlug(pic.getSlug());

		return presenter;
	}

}
