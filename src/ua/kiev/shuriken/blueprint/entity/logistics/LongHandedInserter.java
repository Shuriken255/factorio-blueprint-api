package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class LongHandedInserter extends Inserter {
	
	public LongHandedInserter(float x, float y, int direction) {
		super(x, y, direction);
	}

	public LongHandedInserter(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.LONG_HANDED_INSERTER;
	}
	
}
