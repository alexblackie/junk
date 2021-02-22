package com.alexblackie.junk.converters;

import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.alexblackie.junk.inputs.BlobNameDataContainer;
import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.NameInputDatumContainerFactory;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainerFactory;
import com.alexblackie.junk.inputs.PrefixInputDatumContainer;
import com.alexblackie.junk.inputs.PrefixInputDatumContainerFactory;
import com.alexblackie.junk.models.Pic;

@Component("blobItemPicConverter")
public class BlobItemPicConverter {

	@Autowired
	private NameInputDatumContainerFactory nameInputDatumContainerFactory;

	@Autowired
	private SlugInputDatumContainerFactory slugInputDatumContainerFactory;

	@Autowired
	private PrefixInputDatumContainerFactory prefixInputDatumContainerFactory;

	@Autowired
	private BlobNamePrefixConverter blobNamePrefixConverter;

	public Pic blobItemToPic(BlobItem blob) {
		Pic pic = new Pic();

		SlugInputDatumContainer slugInputDatumContainer =
			this.slugInputDatumContainerFactory.buildInputDatumContainer(blob.getName());
		pic.setSlug(slugInputDatumContainer);

		BlobNameDataContainer blobNameDataContainer =
			this.blobNamePrefixConverter.parseBlobName(blob.getName());

		NameInputDatumContainer nameInputDatumContainer =
			this.nameInputDatumContainerFactory.buildInputDatumContainer(
					blobNameDataContainer.getName());
		pic.setName(nameInputDatumContainer);

		PrefixInputDatumContainer prefixInputDatumContainer =
			this.prefixInputDatumContainerFactory.buildInputDatumContainer(
					blobNameDataContainer.getPrefix());
		pic.setPrefix(prefixInputDatumContainer);

		return pic;
	}

}
