package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;

import com.azure.storage.blob.BlobAsyncClient;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.ListBlobsOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicFactory;


@Component
@ConditionalOnProperty(prefix = "junk", value = "picDataService", havingValue = "azureblob")
public class AzurePicDataService implements PicDataService {
	@Autowired
	private BlobServiceClientBuilder blobServiceClientBuilder;

	@Autowired
	private PicFactory picFactory;

	@Cacheable("picDataListAll")
	public Flux<Pic> listAll() {
		return getBlobContainerAsyncClient()
			.listBlobs()
			.map(blob -> this.picFactory.buildPic(blob.getName()));
	}

	@Cacheable(value = "picDataListAllWithPrefix", key = "#prefix")
	public Flux<Pic> listAll(String prefix) {
		ListBlobsOptions options = new ListBlobsOptions().setPrefix(prefix);

		return getBlobContainerAsyncClient()
			.listBlobs(options)
			.map(blob -> this.picFactory.buildPic(blob.getName()));
	}

	@Cacheable("picDataListAllPrefixes")
	public Flux<String> listAllPrefixes() {
		return getBlobContainerAsyncClient()
			.listBlobsByHierarchy("")
			.filter(blob -> blob.isPrefix())
			.map(blob -> blob.getName().replace("/", ""));
	}

	public Pic getBySlug(String slug) {
		BlobAsyncClient blobClient = getBlobContainerAsyncClient().getBlobAsyncClient(slug);

		return this.picFactory.buildPic(slug, blobClient.downloadStream());
	}

	private BlobContainerAsyncClient getBlobContainerAsyncClient() {
		return getBlobContainerAsyncClient("pics");
	}

	private BlobContainerAsyncClient getBlobContainerAsyncClient(String containerName) {
		BlobServiceAsyncClient blobServiceAsyncClient = blobServiceClientBuilder.buildAsyncClient();
		return blobServiceAsyncClient.getBlobContainerAsyncClient(containerName);
	}
}
