package com.alexblackie.junk.inputs;

public class NameInputDatumContainer extends StringInputDatumContainer {

	public String getName() {
		return this.getValue();
	}

	public void setName(String name) {
		this.setValue(name);
	}

}
