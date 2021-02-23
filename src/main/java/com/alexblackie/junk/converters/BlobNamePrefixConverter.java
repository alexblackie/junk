package com.alexblackie.junk.converters;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.alexblackie.junk.inputs.BlobNameDataContainer;
import com.alexblackie.junk.inputs.BlobNameDataContainerFactory;
import com.alexblackie.junk.inputs.PrefixInputDatumContainer;
import com.alexblackie.junk.inputs.PrefixInputDatumContainerFactory;
import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.NameInputDatumContainerFactory;

@Component("blobNamePrefixConverter")
public class BlobNamePrefixConverter {

	@Autowired
	private NameInputDatumContainerFactory nameInputDatumContainerFactory;

	@Autowired
	private PrefixInputDatumContainerFactory prefixInputDatumContainerFactory;

	@Autowired
	private BlobNameDataContainerFactory blobNameDataContainerFactory;

	public BlobNameDataContainer parseBlobName(String blobName) {
		String[] segments = blobName.split("/");

		String nameSegment;
		String prefixSegment;

		if (segments.length > 1) {
			prefixSegment = segments[0];
			nameSegment = segments[1];
		} else {
			prefixSegment = null;
			nameSegment = segments[0];
		}

		NameInputDatumContainer nameInputDatumContainer =
			this.nameInputDatumContainerFactory.buildInputDatumContainer(nameSegment);

		PrefixInputDatumContainer prefixInputDatumContainer =
			this.prefixInputDatumContainerFactory.buildInputDatumContainer(prefixSegment);

		BlobNameDataContainer blobNameDataContainer = this.blobNameDataContainerFactory
				.buildBlobNameDataContainer(nameInputDatumContainer, prefixInputDatumContainer);

		return blobNameDataContainer;
	}

}
