package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Pipe extends Entity {

	public Pipe(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.PIPE;
	}
	
}
