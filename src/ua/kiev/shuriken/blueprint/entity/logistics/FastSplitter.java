package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class FastSplitter extends Splitter {

	public FastSplitter(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public FastSplitter(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.FAST_SPLITTER;
	}

}
