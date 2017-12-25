package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class ChemicalPlant extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	public ChemicalPlant(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public ChemicalPlant(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.CHEMICAL_PLANT;
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
