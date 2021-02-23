package com.alexblackie.junk.inputs;

public class PrefixInputDatumContainer extends StringInputDatumContainer {

	public String getPrefix() {
		return this.getValue();
	}

	public void setPrefix(String prefix) {
		this.setValue(prefix);
	}

}
