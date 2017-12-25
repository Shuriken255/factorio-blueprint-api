package ua.kiev.shuriken.blueprint.combat;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Radar extends Entity {

	public Radar(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.RADAR;
	}

}
