package com.alexblackie.junk.inputs;

import org.springframework.stereotype.Component;

@Component("blobNameDataContainerFactory")
public class BlobNameDataContainerFactory {

	public BlobNameDataContainer buildBlobNameDataContainer(
			NameInputDatumContainer nameInputDatumContainer,
			PrefixInputDatumContainer prefixInputDatumContainer) {
		BlobNameDataContainer container = new BlobNameDataContainer();

		container.setName(nameInputDatumContainer.getName());
		container.setPrefix(prefixInputDatumContainer.getPrefix());

		return container;
	}

}
