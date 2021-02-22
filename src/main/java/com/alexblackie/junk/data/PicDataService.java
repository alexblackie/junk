package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;

import com.azure.storage.blob.BlobAsyncClient;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexblackie.junk.converters.BlobItemPicConverter;
import com.alexblackie.junk.inputs.NameInputDatumContainer;
import com.alexblackie.junk.inputs.NameInputDatumContainerFactory;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.SlugInputDatumContainerFactory;
import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicFactory;

@Component("picDataService")
public class PicDataService implements AbstractDataService<Pic> {

	@Autowired
	private BlobServiceClientBuilder blobServiceClientBuilder;

	@Autowired
	private BlobItemPicConverter blobItemPicConverter;

	@Autowired
	private NameInputDatumContainerFactory nameInputDatumContainerFactory;

	@Autowired
	private SlugInputDatumContainerFactory slugInputDatumContainerFactory;

	@Autowired
	private PicFactory picFactory;

	public Flux<Pic> listAll() {
		return (Flux<Pic>) getBlobContainerAsyncClient()
			.listBlobs()
			.map(blob -> this.blobItemPicConverter.blobItemToPic(blob));
	}

	public Pic getBySlug(SlugInputDatumContainer slugContainer) {
		BlobAsyncClient blobClient = getBlobContainerAsyncClient().getBlobAsyncClient(
				slugContainer.getSlug());

		NameInputDatumContainer nameInputContainer =
			this.nameInputDatumContainerFactory.buildInputDatumContainer(
				blobClient.getBlobName());

		SlugInputDatumContainer slugInputContainer =
			this.slugInputDatumContainerFactory.buildInputDatumContainer(
				blobClient.getBlobName());

		return this.picFactory.buildPic(nameInputContainer, slugInputContainer, blobClient.download());
	}

	private BlobContainerAsyncClient getBlobContainerAsyncClient() {
		return getBlobContainerAsyncClient("pics");
	}

	private BlobContainerAsyncClient getBlobContainerAsyncClient(String containerName) {
		BlobServiceAsyncClient blobServiceAsyncClient = blobServiceClientBuilder.buildAsyncClient();
		return blobServiceAsyncClient.getBlobContainerAsyncClient(containerName);
	}
}
