package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class Beacon extends Entity {

	public Beacon(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.BEACON;
	}
	
	
	private ModuleRequest modules = new ModuleRequest();
	
	public ModuleRequest getModuleRequests() {
		return modules;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		return modules.toString();
	}
	
}
