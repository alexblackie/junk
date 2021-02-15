package com.alexblackie.junk.models;

public abstract interface AbstractPicDataEntity<N, S> {

	public String getName();
	public String getSlug();

	public void setName(N value);
	public void setSlug(S value);

}
