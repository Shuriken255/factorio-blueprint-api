package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class ArithmeticCombinator extends Entity {

	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	public static final String OPERATION_PLUS = "+";
	public static final String OPERATION_MINUS = "-";
	public static final String OPERATION_DIVIDE = "/";
	public static final String OPERATION_MULTIPLY = "*";
	public static final String OPERATION_POWER = "^";
	public static final String OPERATION_MOD = "%";
	
	public static final String OPERATION_BIT_SHIFT_RIGHT = ">>";
	public static final String OPERATION_BIT_SHIFT_LEFT = "<<";
	public static final String OPERATION_OR = "OR";
	public static final String OPERATION_AND = "AND";
	public static final String OPERATION_XOR = "XOR";
	

	public ArithmeticCombinator(float x, float y, int direction,
			Term firstTerm, String operation, Term secondTerm, String outputSignal) {
		super(x, y, direction);
		this.firstTerm = firstTerm;
		this.operation = operation;
		this.secondTerm = secondTerm;
		this.outputSignal = outputSignal;
	}
	
	public ArithmeticCombinator(float x, float y,
			Term firstTerm, String operation, Term secondTerm, String outputSignal) {
		super(x, y);
		this.firstTerm = firstTerm;
		this.operation = operation;
		this.secondTerm = secondTerm;
		this.outputSignal = outputSignal;
	}
	
	@Override
	public String getName() {
		return Signals.Items.ARITHMETIC_COMBINATOR;
	}
	
	
	public static class Term {
		
		private boolean isConstant;
		
		public boolean isConstant() {
			return isConstant;
		}
		
		
		private String signal;
		
		public String getSignal() {
			return signal;
		}
		
		public void setSignal(String signal) {
			this.signal = signal;
			isConstant = false;
		}
		
		
		private int constant;
		
		public int getConstant() {
			return constant;
		}
		
		public void setConstant(int constant) {
			this.constant = constant;
			isConstant = true;
		}
		
		public Term(String signal) {
			this.signal = signal;
			isConstant = false;
		}
		
		public Term(int constant) {
			this.constant = constant;
			isConstant = true;
		}
		
	}
	
	
	private Term firstTerm;
	
	public Term getFirstTerm() {
		return firstTerm;
	}
	
	public void setFirstTerm(Term term) {
		firstTerm = term;
	}
	
	
	private Term secondTerm;
	
	public Term getSecondTerm() {
		return secondTerm;
	}
	
	public void setSecondTerm(Term term) {
		secondTerm = term;
	}
	
	
	private String operation = OPERATION_PLUS;
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	private String outputSignal;
	
	public String getOutputSignal() {
		return outputSignal;
	}
	
	public void setOutputSignal(String signal) {
		outputSignal = signal;
	}
	
	
	@Override
	protected String setupToString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"control_behavior\":{\"arithmetic_conditions\":{");
		boolean passedFirst = false;
		
		if(firstTerm != null) {
			passedFirst = true;
			if(firstTerm.isConstant()) {
				sb.append("\"first_constant\":");
				sb.append(firstTerm.getConstant());
			} else {
				sb.append("\"first_signal\":{\"type\":\"");
				sb.append(Signals.getType(firstTerm.getSignal()));
				sb.append("\",\"name\":\"");
				sb.append(firstTerm.getSignal());
				sb.append("\"}");
			}
		}
		
		if(secondTerm != null) {
			if(passedFirst) {
				sb.append(',');
			} else {
				passedFirst = true;
			}
			if(secondTerm.isConstant()) {
				sb.append("\"second_constant\":");
				sb.append(secondTerm.getConstant());
			} else {
				sb.append("\"second_signal\":{\"type\":\"");
				sb.append(Signals.getType(secondTerm.getSignal()));
				sb.append("\",\"name\":\"");
				sb.append(secondTerm.getSignal());
				sb.append("\"}");
			}
		}
		
		if(passedFirst) {
			sb.append(',');
		}
		sb.append("\"operation\":\"");
		sb.append(operation);
		sb.append('"');
		
		if(outputSignal != null) {
			sb.append(",\"output_signal\":{\"type\":\"");
			sb.append(Signals.getType(outputSignal));
			sb.append("\",\"name\":\"");
			sb.append(outputSignal);
			sb.append("\"}");
		}
		
		sb.append("}}");
		
		return sb.toString();
	}

}
