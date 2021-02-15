package com.alexblackie.junk.converters;

import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Component;

import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.models.Pic;

@Component("blobItemPicConverter")
public class BlobItemPicConverter {

	public Pic blobItemToPic(BlobItem blob) {
		Pic pic = new Pic();

		NameInputDatumContainer nameInputDatumContainer = new NameInputDatumContainer();
		nameInputDatumContainer.setName(blob.getName());
		pic.setName(nameInputDatumContainer);

		SlugInputDatumContainer slugInputDatumContainer = new SlugInputDatumContainer();
		slugInputDatumContainer.setSlug(blob.getName());
		pic.setSlug(slugInputDatumContainer);

		return pic;
	}

}
