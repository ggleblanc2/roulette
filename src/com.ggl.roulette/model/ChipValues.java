package com.ggl.roulette.model;

import java.util.ArrayList;
import java.util.List;

public class ChipValues {
	
	private final List<ChipValue> values;
	
	public ChipValues() {
		this.values = new ArrayList<>();
		values.add(new ChipValue(1));
		values.add(new ChipValue(5));
		values.add(new ChipValue(25));
	}

	public List<ChipValue> getValues() {
		return values;
	}
	
}
