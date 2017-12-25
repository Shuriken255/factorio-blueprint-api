package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class AssemblingMachine1 extends Entity {

	public AssemblingMachine1(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.ASSEMBLING_MACHINE_1;
	}
	
	
	private String recipe;
	
	public String getRecipe() {
		return recipe;
	}
	
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	
	
	@Override
	protected String setupToString() {
		if(recipe == null) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("\"recipe\":\"");
			sb.append(recipe);
			sb.append('"');
			return sb.toString();
		}
	}

}
