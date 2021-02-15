package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;

import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.converters.BlobItemPicConverter;

@Component("picDataService")
public class PicDataService {

	@Autowired
	private BlobServiceClientBuilder blobServiceClientBuilder;

	@Autowired
	private BlobItemPicConverter blobItemPicConverter;

	public Flux<Pic> listPics() {
		BlobServiceAsyncClient blobServiceAsyncClient = blobServiceClientBuilder.buildAsyncClient();
		BlobContainerAsyncClient blobContainerAsyncClient = blobServiceAsyncClient.getBlobContainerAsyncClient("pics");

		return (Flux<Pic>) blobContainerAsyncClient
			.listBlobs()
			.map(blob -> this.blobItemPicConverter.blobItemToPic(blob));
	}

//	public Pic getPic(NameInputDatumContainer nameContainer) {
//	}

}
