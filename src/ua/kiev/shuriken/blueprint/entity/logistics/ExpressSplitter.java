package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class ExpressSplitter extends FastSplitter{
	
	public ExpressSplitter(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public ExpressSplitter(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.EXPRESS_SPLITTER;
	}
	
}
