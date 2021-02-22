package com.alexblackie.junk.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.NameInputDatumContainerFactory;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainerFactory;
import com.alexblackie.junk.inputs.PrefixInputDatumContainer;
import com.alexblackie.junk.inputs.PrefixInputDatumContainerFactory;
import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicPresenter;
import com.alexblackie.junk.models.PicPresenterFactory;

@Component("picPresenterConverter")
public class PicPresenterConverter {

	@Autowired
	private NameInputDatumContainerFactory nameInputDatumContainerFactory;

	@Autowired
	private SlugInputDatumContainerFactory slugInputDatumContainerFactory;

	@Autowired
	private PrefixInputDatumContainerFactory prefixInputDatumContainerFactory;

	@Autowired
	private PicPresenterFactory picPresenterFactory;

	public PicPresenter picToPicPresenter(Pic pic) {
		NameInputDatumContainer nameInputDatumContainer =
			this.nameInputDatumContainerFactory.buildInputDatumContainer(pic.getName());

		SlugInputDatumContainer slugInputDatumContainer =
			this.slugInputDatumContainerFactory.buildInputDatumContainer(pic.getSlug());

		PrefixInputDatumContainer prefixInputDatumContainer =
			this.prefixInputDatumContainerFactory.buildInputDatumContainer(pic.getPrefix());

		return this.picPresenterFactory
			.buildPicPresenter(
				nameInputDatumContainer,
				slugInputDatumContainer,
				prefixInputDatumContainer);
	}

}
