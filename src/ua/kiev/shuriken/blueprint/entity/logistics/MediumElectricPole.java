package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class MediumElectricPole extends Entity {

	public MediumElectricPole(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.MEDIUM_ELECTRIC_POLE;
	}

}
