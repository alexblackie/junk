package com.alexblackie.junk.converters;

import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.NameInputDatumContainerFactory;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainerFactory;
import com.alexblackie.junk.models.Pic;

@Component("blobItemPicConverter")
public class BlobItemPicConverter {

	@Autowired
	private NameInputDatumContainerFactory nameInputDatumContainerFactory;

	@Autowired
	private SlugInputDatumContainerFactory slugInputDatumContainerFactory;

	public Pic blobItemToPic(BlobItem blob) {
		Pic pic = new Pic();

		NameInputDatumContainer nameInputDatumContainer =
			this.nameInputDatumContainerFactory.buildInputDatumContainer(blob.getName());
		pic.setName(nameInputDatumContainer);

		SlugInputDatumContainer slugInputDatumContainer =
			this.slugInputDatumContainerFactory.buildInputDatumContainer(blob.getName());
		pic.setSlug(slugInputDatumContainer);

		return pic;
	}

}
