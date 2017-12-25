package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class FastUndergroundBelt extends UndergroundBelt {

	public FastUndergroundBelt(float x, float y, int direction, int type) {
		super(x, y, direction, type);
	}
	
	public FastUndergroundBelt(float x, float y, int type) {
		super(x, y, type);
	}
	
	@Override
	public String getName() {
		return Signals.Items.FAST_UNDERGROUND_BELT;
	}

}
