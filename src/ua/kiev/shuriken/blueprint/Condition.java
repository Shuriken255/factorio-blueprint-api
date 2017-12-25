package ua.kiev.shuriken.blueprint;

public class Condition {
	
	public static final char LESS_THAN = '<';
	public static final char MORE_THAN = '>';
	public static final char EQUALS = '=';
	
	
	public Condition(String firstSignal, char comporator, String secondSignal) {
		this.firstSignal = firstSignal;
		this.comparator = comporator;
		secondSignalString = secondSignal;
		isConstant = false;
	}
	
	public Condition(String firstSignal, char comporator, int constant) {
		this.firstSignal = firstSignal;
		this.comparator = comporator;
		secondSignalInteger = constant;
		isConstant = true;
	}
	
	
	private String firstSignal;
	
	public String getFirstSignal() {
		return firstSignal;
	}
	
	public void setFirstSignal(String signal) {
		firstSignal = signal; 
	}
	
	
	private String secondSignalString;
	private int secondSignalInteger;
	private boolean isConstant;
	
	public String getSecondSignal() {
		return secondSignalString;
	}
	
	public int getConstant() {
		return secondSignalInteger;
	}
	
	public void setSecondSignal(String signal) {
		secondSignalString = signal;
		isConstant = false;
	}
	
	public void setSecondSignal(int constant) {
		secondSignalInteger = constant;
		isConstant = true;
	}
	
	public boolean isSecondSignalConstant() {
		return isConstant;
	}
	
	
	private char comparator = LESS_THAN;
	
	public char getComparator() {
		return comparator;
	}
	
	
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
