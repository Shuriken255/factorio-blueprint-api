package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class DeciderCombinator extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	@Deprecated
	public DeciderCombinator(float x, float y, int direction, Condition condition, String outputSignal) {
		super(x, y, direction);
		this.condition = condition;
		this.outputSignal = outputSignal;
	}

	@Deprecated
	public DeciderCombinator(float x, float y, Condition condition, String outputSignal) {
		super(x, y);
		this.condition = condition;
		this.outputSignal = outputSignal;
	}


	public DeciderCombinator(float x, float y, int direction, Condition condition, String outputSignal, boolean copyCountFromInput) {
		super(x, y, direction);
		this.condition = condition;
		this.outputSignal = outputSignal;
		this.copyCountFromInput = copyCountFromInput;
	}

	public DeciderCombinator(float x, float y, Condition condition, String outputSignal, boolean copyCountFromInput) {
		super(x, y);
		this.condition = condition;
		this.outputSignal = outputSignal;
		this.copyCountFromInput = copyCountFromInput;
	}


	
	@Override
	public String getName() {
		return Signals.Items.DECIDER_COMBINATOR;
	}
	
	
	private Condition condition;
	
	public Condition getCondition() {
		return condition;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
	private String outputSignal;
	
	public String getOutputSignal() {
		return outputSignal;
	}
	
	public void setOutputSignal(String signal) {
		outputSignal = signal;
	}
	
	
	private boolean copyCountFromInput = false;
	
	public boolean isCopyingCountFromInput() {
		return copyCountFromInput;
	}
	
	public void setCopyingCountFromInput(boolean copy) {
		copyCountFromInput = copy;
	}
	
	
	@Override
	protected String setupToString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"control_behavior\":{\"decider_conditions\":{\"first_signal\":{\"type\":\"");
		sb.append(Signals.getType(condition.getFirstSignal()));
		sb.append("\",\"name\":\"");
		sb.append(condition.getFirstSignal());
		sb.append("\"},");
		
		if(condition.isSecondSignalConstant()) {
			sb.append("\"constant\":");
			sb.append(condition.getConstant());
			sb.append(',');
		} else {
			sb.append("\"second_signal\":{\"type\":\"");
			sb.append(Signals.getType(condition.getSecondSignal()));
			sb.append("\",\"name\":\"");
			sb.append(condition.getSecondSignal());
			sb.append("\"},");
		}
		
		sb.append("\"comparator\":\"");
		sb.append(condition.getComparator());
		sb.append("\",\"output_signal\":{\"type\":\"");
		sb.append(Signals.getType(outputSignal));
		sb.append("\",\"name\":\"");
		sb.append(outputSignal);
		sb.append("\"},\"copy_count_from_input\":");
		sb.append(copyCountFromInput);
		
		
		sb.append("}}");
		
		return sb.toString();
	}
	
}
