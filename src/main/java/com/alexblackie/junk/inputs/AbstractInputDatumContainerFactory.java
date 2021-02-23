package com.alexblackie.junk.inputs;

public interface AbstractInputDatumContainerFactory<C, V> {

	public C buildInputDatumContainer(V value);

}
