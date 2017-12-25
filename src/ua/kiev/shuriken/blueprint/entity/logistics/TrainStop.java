package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class TrainStop extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	public TrainStop(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public TrainStop(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.TRAIN_STOP;
	}
	
	
	private Condition enableCondition;
	
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	private boolean sendSignalsToTrain = true;
	
	public boolean isSendingSignalsToTrain() {
		return sendSignalsToTrain;
	}
	
	public void setSendingSignalsToTrain(boolean send) {
		sendSignalsToTrain = send;
	}
	
	
	private boolean readTrainsContents = false;
	
	public boolean isReadingTrainsContents() {
		return readTrainsContents;
	}
	
	public void setReadingTrainContents(boolean read) {
		readTrainsContents = read;
	}
	
	
	private String trainStoppedSignal;
	
	public String getTrainStoppedSignal() {
		return trainStoppedSignal;
	}
	
	public void setTrainStoppedSignal(String signal) {
		trainStoppedSignal = signal;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"control_behavior\":{");
		boolean firstPartPassed = false;
		
		if(enableCondition != null) {
			sb.append(enableCondition.toString());
			sb.append(",\"circuit_enable_disable\":true");
			firstPartPassed = true;
		}
		
		if(!sendSignalsToTrain) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"send_to_train\":false");
		}
		
		if(readTrainsContents) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"read_from_train\":true");
		}
		
		if(trainStoppedSignal != null) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"read_stopped_train\":true,");
			sb.append("\"train_stopped_signal\":{\"type\":\"");
			sb.append(Signals.getType(trainStoppedSignal));
			sb.append("\",\"name\":\"");
			sb.append(trainStoppedSignal);
			sb.append("\"}");
		}
		
		sb.append('}');
		if(sb.length() < 25) {
			return null;
		} else {
			return sb.toString();
		}
	}
	
	
	private String stationName = "No name";
	
	public String getStationName() {
		return stationName;
	}
	
	public void setStationName(String name) {
		this.stationName = name;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"station\":\"");
		sb.append(stationName);
		sb.append("\"");
		
		return sb.toString();
	}
	
}
