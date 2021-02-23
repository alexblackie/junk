package com.alexblackie.junk.inputs;

import org.springframework.stereotype.Component;

@Component("slugInputDatumContainerFactory")
public class SlugInputDatumContainerFactory
	implements AbstractInputDatumContainerFactory<SlugInputDatumContainer, String> {

	public SlugInputDatumContainer buildInputDatumContainer(String value) {
		SlugInputDatumContainer slugInputContainer = new SlugInputDatumContainer();
		slugInputContainer.setSlug(value);
		return slugInputContainer;
	}

}
