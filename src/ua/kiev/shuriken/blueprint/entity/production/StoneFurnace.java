package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class StoneFurnace extends Entity {

	public StoneFurnace(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.STONE_FURNACE;
	}

}
