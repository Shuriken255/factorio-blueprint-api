package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class PassiveProviderChest extends Entity {
	
	public PassiveProviderChest(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.LOGISTIC_CHEST_PASSIVE_PROVIDER;
	}
	
}
