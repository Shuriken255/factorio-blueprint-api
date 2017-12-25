package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class ExpressUndegroundBelt extends FastUndergroundBelt{

	public ExpressUndegroundBelt(float x, float y, int direction, int type) {
		super(x, y, direction, type);
	}
	
	public ExpressUndegroundBelt(float x, float y, int type) {
		super(x, y, type);
	}
	
	@Override
	public String getName() {
		return Signals.Items.EXPRESS_UNDERGROUND_BELT;
	}

}
