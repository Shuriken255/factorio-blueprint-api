package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class WoodenChest extends Entity {

	public WoodenChest(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.WOODEN_CHEST;
	}

}