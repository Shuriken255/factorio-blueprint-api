package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class BurnerInserter extends Inserter {
	
	public BurnerInserter(float x, float y, int direction) {
		super(x, y, direction);
	}

	public BurnerInserter(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.BURNER_INSERTER;
	}
	
}
