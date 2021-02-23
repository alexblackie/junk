package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;

import com.azure.storage.blob.BlobAsyncClient;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.ListBlobsOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicFactory;

@Component("picDataService")
public class PicDataService {

	@Autowired
	private BlobServiceClientBuilder blobServiceClientBuilder;

	@Autowired
	private PicFactory picFactory;

	@Cacheable("picDataListAll")
	public Flux<Pic> listAll() {
		return (Flux<Pic>) getBlobContainerAsyncClient()
			.listBlobs()
			.map(blob -> this.picFactory.buildPic(blob.getName()));
	}

	@Cacheable(value = "picDataListAllWithPrefix", key = "#prefix")
	public Flux<Pic> listAll(String prefix) {
		ListBlobsOptions options = new ListBlobsOptions().setPrefix(prefix);

		return (Flux<Pic>) getBlobContainerAsyncClient()
			.listBlobs(options)
			.map(blob -> this.picFactory.buildPic(blob.getName()));
	}

	@Cacheable("picDataListAllPrefixes")
	public Flux<String> listAllPrefixes() {
		return getBlobContainerAsyncClient()
			.listBlobsByHierarchy("")
			.filter(blob -> blob.isPrefix() != null) // what the fuck azure
			.map(blob -> blob.getName().replace("/", ""));
	}

	public Pic getBySlug(String slug) {
		BlobAsyncClient blobClient = getBlobContainerAsyncClient().getBlobAsyncClient(slug);

		return this.picFactory.buildPic(slug, blobClient.download());
	}

	private BlobContainerAsyncClient getBlobContainerAsyncClient() {
		return getBlobContainerAsyncClient("pics");
	}

	private BlobContainerAsyncClient getBlobContainerAsyncClient(String containerName) {
		BlobServiceAsyncClient blobServiceAsyncClient = blobServiceClientBuilder.buildAsyncClient();
		return blobServiceAsyncClient.getBlobContainerAsyncClient(containerName);
	}
}
