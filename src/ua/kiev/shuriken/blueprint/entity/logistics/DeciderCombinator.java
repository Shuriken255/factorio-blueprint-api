package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class DeciderCombinator extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public DeciderCombinator(float x, float y, int direction, Condition condition, String outputSignal) {
		super(x, y, direction);
		this.condition = condition;
		this.outputSignal = outputSignal;
	}
	
	public DeciderCombinator(float x, float y, Condition condition, String outputSignal) {
		super(x, y);
		this.condition = condition;
		this.outputSignal = outputSignal;
	}
	
	@Override
	public String getName() {
		return Signals.Items.DECIDER_COMBINATOR;
	}
	
	
	private Condition condition;
	
	/**
	 * Returns condition that will be used in this decider combinator.
	 * @return Condition that will be used in this decider combinator.
	 */
	public Condition getCondition() {
		return condition;
	}
	
	/**
	 * Allows you to set condition for this decider combinator.
	 * @param condition condition you want to set for this decider combinator.
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
	private String outputSignal;
	
	/**
	 * Returns output signal of this decider combinator.
	 * @return Output signal of this decider combinator.
	 */
	public String getOutputSignal() {
		return outputSignal;
	}
	
	/**
	 * Allows you to set signal that this decider combinator will use for output
	 * @param signal signal, you want this decider combinator to use for output
	 */
	public void setOutputSignal(String signal) {
		outputSignal = signal;
	}
	
	
	private boolean copyCountFromInput = false;
	
	/**
	 * Returns "true" if decider copies signal's value from input to output
	 * and "false" if output should be "1".
	 * @return "True" if decider copies signal's value from input to output
	 * and "false" if output should be "1".
	 */
	public boolean isCopyingCountFromInput() {
		return copyCountFromInput;
	}
	
	/**
	 * Sets if decider should copy output to input or not.
	 * @param copy "true" if it should and "false" if output should be "1".
	 */
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
