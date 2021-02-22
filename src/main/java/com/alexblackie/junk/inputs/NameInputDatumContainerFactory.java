package com.alexblackie.junk.inputs;

import org.springframework.stereotype.Component;

@Component("nameInputDatumContainerFactory")
public class NameInputDatumContainerFactory
	implements AbstractInputDatumContainerFactory<NameInputDatumContainer, String> {

	public NameInputDatumContainer buildInputDatumContainer(String value) {
		NameInputDatumContainer nameInputContainer = new NameInputDatumContainer();
		nameInputContainer.setName(value);
		return nameInputContainer;
	}

}
