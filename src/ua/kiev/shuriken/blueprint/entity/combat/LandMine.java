package ua.kiev.shuriken.blueprint.entity.combat;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class LandMine extends Entity {
	
	public LandMine(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.LAND_MINE;
	}
	
}
