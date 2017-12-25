package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class SolarPanel extends Entity {

	public SolarPanel(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.SOLAR_PANEL;
	}

}
