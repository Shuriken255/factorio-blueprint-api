package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class NuclearReactor extends Entity {

	public NuclearReactor(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.NUCLEAR_REACTOR;
	}

}
