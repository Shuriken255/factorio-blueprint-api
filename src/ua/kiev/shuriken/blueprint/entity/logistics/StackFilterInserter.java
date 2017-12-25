package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class StackFilterInserter extends FilterInserter {
	
	public StackFilterInserter(float x, float y, int direction) {
		super(x, y, direction);
	}

	public StackFilterInserter(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.STACK_FILTER_INSERTER;
	}
	
}
