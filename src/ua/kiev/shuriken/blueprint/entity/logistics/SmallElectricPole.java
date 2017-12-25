package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class SmallElectricPole extends Entity {

	public SmallElectricPole(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.SMALL_ELECTRIC_POLE;
	}

}
