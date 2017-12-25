package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class BigElectricPole extends Entity {

	public BigElectricPole(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.BIG_ELECTRIC_POLE;
	}

}
