package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class BufferChest extends RequesterChest {

	public BufferChest(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.LOGISTIC_CHEST_BUFFER;
	}
	
}
