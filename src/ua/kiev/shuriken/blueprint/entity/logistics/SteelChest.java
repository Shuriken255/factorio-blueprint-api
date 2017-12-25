package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class SteelChest extends Entity {

	public SteelChest(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.STEEL_CHEST;
	}

}