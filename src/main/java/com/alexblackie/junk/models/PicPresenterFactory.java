package com.alexblackie.junk.models;

import org.springframework.stereotype.Component;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;

@Component("picPresenterFactory")
public class PicPresenterFactory {

	public PicPresenter buildPicPresenter(
			NameInputDatumContainer nameInputDatumContainer,
			SlugInputDatumContainer slugInputDatumContainer) {
		PicPresenter picPresenter = new PicPresenter();

		picPresenter.setName(nameInputDatumContainer.getName());
		picPresenter.setSlug(slugInputDatumContainer.getSlug());

		return picPresenter;
	}

}
