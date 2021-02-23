package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;
import com.alexblackie.junk.inputs.PrefixInputDatumContainer;

public interface AbstractDataService<C> {

	public Flux<C> listAll();
	public Flux<C> listAll(PrefixInputDatumContainer p);

	public Flux<String> listAllPrefixes();

	public C getBySlug(SlugInputDatumContainer s);

}
