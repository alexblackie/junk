package com.alexblackie.junk.inputs;

public class StringInputDatumContainer implements AbstractInputDatumContainer<String> {

	protected String value;

	public String getValue() {
		return this.value;
	}

	public void setValue(String newValue) {
		this.value = newValue;
	}

}
