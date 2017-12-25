package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class StackInserter extends Inserter {
	
	public StackInserter(float x, float y, int direction) {
		super(x, y, direction);
	}

	public StackInserter(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.INSERTER;
	}
	
}
