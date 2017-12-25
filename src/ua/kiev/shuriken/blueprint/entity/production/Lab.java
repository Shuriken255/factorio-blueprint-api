package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class Lab extends Entity {

	public Lab(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.LAB;
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
