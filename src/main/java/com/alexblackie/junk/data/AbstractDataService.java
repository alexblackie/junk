package com.alexblackie.junk.data;

import reactor.core.publisher.Flux;
import com.alexblackie.junk.inputs.SlugInputDatumContainer;

public interface AbstractDataService<C> {

	public Flux<C> listAll();
	public C getBySlug(SlugInputDatumContainer s);

}
