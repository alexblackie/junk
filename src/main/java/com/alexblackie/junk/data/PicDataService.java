package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;
import com.alexblackie.junk.models.Pic;

public interface PicDataService {
	public Flux<Pic> listAll();
	public Flux<Pic> listAll(String prefix);
	public Flux<String> listAllPrefixes();
	public Pic getBySlug(String slug);
}
