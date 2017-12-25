package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class IronChest extends Entity {

	public IronChest(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.IRON_CHEST;
	}

}