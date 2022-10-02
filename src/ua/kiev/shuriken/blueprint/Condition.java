package ua.kiev.shuriken.blueprint;

/**
 * This class is used to describe default circuit condition.
 */
public class Condition {
	
	public static final char COMPARATOR_LESS_THAN = '<';
	public static final char COMPARATOR_MORE_THAN = '>';
	public static final char COMPARATOR_EQUALS = '=';
	public static final char COMPARATOR_LESS_THAN_EQUAL = '\u2264';
	public static final char COMPARATOR_MORE_THAN_EQUAL = '\u2265';
	public static final char COMPARATOR_NOT_EQUALS = '\u2260';
	
	/**
	 * Creates new Condition class object with signal as second part in this condition.
	 * @param firstSignal first signal that will be used for comparison
	 * @param comparator comparator that will be used for comparison
	 * @param secondSignal second signal that will be used for comparison
	 */
	public Condition(String firstSignal, char comparator, String secondSignal) {
		this.firstSignal = firstSignal;
		this.comparator = comparator;
		secondSignalString = secondSignal;
		isConstant = false;
	}
	
	/**
	 * Creates new Condition class object with constant as second part in this condition.
	 * @param firstSignal first signal that will be used for comparison
	 * @param comparator comparator that will be used for comparison
	 * @param constant constant that will be used for comparison instead of secondSignal
	 */
	public Condition(String firstSignal, char comparator, int constant) {
		this.firstSignal = firstSignal;
		this.comparator = comparator;
		secondSignalInteger = constant;
		isConstant = true;
	}
	
	
	private String firstSignal;
	
	/**
	 * Gets first signal in this condition.
	 * @return Name of first signal. Signals class's constants should be used to compare.
	 */
	public String getFirstSignal() {
		return firstSignal;
	}
	
	/**
	 * Sets first signal in condition.
	 * @param signal Signal you want to be first in this condition. Signals class constant's should be used.
	 */
	public void setFirstSignal(String signal) {
		firstSignal = signal; 
	}
	
	
	private String secondSignalString;
	private int secondSignalInteger;
	private boolean isConstant;
	
	/**
	 * Returns name of second signal. May return null if second signal is not set. 
	 * @return Name of second signal. May return null if second signal is not set.
	 * Signals class's constants should be use to compare.
	 */
	public String getSecondSignal() {
		return secondSignalString;
	}
	
	/**
	 * Gets constant that is used as second part of this condition
	 * @return Constant used as second part.
	 */
	public int getConstant() {
		return secondSignalInteger;
	}
	
	/**
	 * Sets second signal in condition. After calling this method, signal will be used
	 * as second part in condition.
	 * @param signal signal you want to be second in this condition. Signals class constant's should be used.
	 */
	public void setSecondSignal(String signal) {
		secondSignalString = signal;
		isConstant = false;
	}
	
	/**
	 * Sets second signal in condition. After calling this method, constant will be used
	 * as second part in condition.
	 * @param constant Constant you want to be second in this condition.
	 */
	public void setSecondSignal(int constant) {
		secondSignalInteger = constant;
		isConstant = true;
	}
	
	/**
	 * Method, that allows you to check if constant will be used in this condition.
	 * @return "True" if second part of this condition is constant, "false" if second part of this condition is signal
	 */
	public boolean isSecondSignalConstant() {
		return isConstant;
	}
	
	
	private char comparator = COMPARATOR_LESS_THAN;
	
	/**
	 * Return comparator that will be used in this condition.
	 * @return Condition's comparator.
	 */
	public char getComparator() {
		return comparator;
	}
	
	/** 
	 * Sets comparator that will be used in this condition.
	 * @param comparator comparator that will be used in this condition
	 */
	public void setComparator(char comparator) {
		this.comparator = comparator;
	}
	
	/** 
	 * Returns JSON representation of this condition.
	 * @return JSON representation of this condition.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"circuit_condition\":{\"first_signal\":{\"type\":\"");
		sb.append(Signals.getType(firstSignal));
		sb.append("\",\"name\":\"");
		sb.append(firstSignal);
		sb.append("\"},");
		
		if(isConstant) {
			sb.append("\"constant\":");
			sb.append(secondSignalInteger);
			sb.append(',');
		} else {
			sb.append("\"second_signal\":{\"type\":\"");
			sb.append(Signals.getType(secondSignalString));
			sb.append("\",\"name\":\"");
			sb.append(secondSignalString);
			sb.append("\"},");
		}
		
		sb.append("\"comparator\":\"");
		sb.append(comparator);
		sb.append("\"}");
		
		return sb.toString();
	}
	
}
