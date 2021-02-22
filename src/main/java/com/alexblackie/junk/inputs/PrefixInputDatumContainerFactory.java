package com.alexblackie.junk.inputs;

import org.springframework.stereotype.Component;

@Component("prefixInputDatumContainerFactory")
public class PrefixInputDatumContainerFactory
	implements AbstractInputDatumContainerFactory<PrefixInputDatumContainer, String> {

	public PrefixInputDatumContainer buildInputDatumContainer(String value) {
		PrefixInputDatumContainer prefixInputContainer = new PrefixInputDatumContainer();
		prefixInputContainer.setPrefix(value);
		return prefixInputContainer;
	}

}
