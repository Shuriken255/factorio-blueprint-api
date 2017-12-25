package ua.kiev.shuriken.blueprint.combat;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class RocketSilo extends Entity {

	public RocketSilo(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.ROCKET_SILO;
	}
	
	
	private ModuleRequest modules = new ModuleRequest();
	
	public ModuleRequest getModuleRequests() {
		return modules;
	}
	
	
	@Override
	public String setupToString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"recipe\":\"rocket-part\"");
		String moduleRequest = modules.toString();
		if(moduleRequest != null) {
			sb.append(',');
			sb.append(moduleRequest);
		}
		
		return sb.toString();
	}

}
