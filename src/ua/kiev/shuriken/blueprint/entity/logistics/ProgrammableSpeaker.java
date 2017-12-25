package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class ProgrammableSpeaker extends Entity {
	
	public static final int INSTRUMENT_ALARMS = 0;
	public static final int INSTRUMENT_MISCELLANEOUS = 1;
	public static final int INSTRUMENT_DRUMKIT = 2;
	public static final int INSTRUMENT_PIANO = 3;
	public static final int INSTRUMENT_BASS = 4;
	public static final int INSTRUMENT_LEAD = 5;
	public static final int INSTRUMENT_SAWTOOTH = 6;
	public static final int INSTRUMENT_SQUARE = 7;
	public static final int INSTRUMENT_CELESTA = 8;
	public static final int INSTRUMENT_VIBRAPHONE = 9;
	public static final int INSTRUMENT_PLUCKED_STRINGS = 10;
	public static final int INSTRUMENT_STEEL_DRUM = 11;
	
	public static final int ALARMS_ALARM_1 = 0;
	public static final int ALARMS_ALARM_2 = 1;
	public static final int ALARMS_BUZZER_1 = 2;
	public static final int ALARMS_BUZZER_2 = 3;
	public static final int ALARMS_BUZZER_3 = 4;
	public static final int ALARMS_RING = 5;
	public static final int ALARMS_SIREN = 6;
	
	public static final int MISCELLANEOUS_ACHIEVEMENT_UNLOCKED = 0;
	public static final int MISCELLANEOUS_ALERT_CONSTRUCTION = 1;
	public static final int MISCELLANEOUS_ALERT_DAMAGE = 2;
	public static final int MISCELLANEOUS_ARMOR_INSERT = 3;
	public static final int MISCELLANEOUS_ARMOR_REMOVE = 4;
	public static final int MISCELLANEOUS_CANNOT_BUILD = 5;
	public static final int MISCELLANEOUS_CONSOLE_MESSAGE = 6;
	public static final int MISCELLANEOUS_CRAFTING_FINISHED = 7;
	public static final int MISCELLANEOUS_GAME_LOST = 8;
	public static final int MISCELLANEOUS_GAME_WON = 9;
	public static final int MISCELLANEOUS_GUI_CLICK = 10;
	public static final int MISCELLANEOUS_GUI_CLICK_2 = 11;
	public static final int MISCELLANEOUS_INVENTORY_MOVE = 12;
	public static final int MISCELLANEOUS_NEW_OBJECTIVE = 13;
	public static final int MISCELLANEOUS_RESEARCH_COMPLETED = 14;
	public static final int MISCELLANEOUS_SCENARIO_MESSAGE = 15;
	
	public static final int DRUMKIT_KICK_1 = 0;
	public static final int DRUMKIT_KICK_2 = 1;
	public static final int DRUMKIT_SNARE_1 = 2;
	public static final int DRUMKIT_SNARE_2 = 3;
	public static final int DRUMKIT_SNARE_3 = 4;
	public static final int DRUMKIT_HI_HAT_1 = 5;
	public static final int DRUMKIT_HI_HAT_2 = 6;
	public static final int DRUMKIT_FX = 7;
	public static final int DRUMKIT_HIGH_Q = 8;
	public static final int DRUMKIT_PERCUSSION_1 = 9;
	public static final int DRUMKIT_PERCUSSION_2 = 10;
	public static final int DRUMKIT_REVERSE_CYMBAL = 11;
	public static final int DRUMKIT_CLAP = 12;
	public static final int DRUMKIT_SHAKER = 13;
	public static final int DRUMKIT_COWBELL = 14;
	public static final int DRUMKIT_TRIANGLE = 15;
	

	public ProgrammableSpeaker(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.PROGRAMMABLE_SPEAKER;
	}
	
	
	private Condition enableCondition;
	
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	private boolean signalValueIsPitch;
	
	public boolean isSignalValueIsPitchMode() {
		return signalValueIsPitch;
	}
	
	public void setSignalValueIsPitchMode(boolean mode) {
		signalValueIsPitch = mode;
	}
	
	
	public String getPitchSignal() {
		if(enableCondition == null) {
			return null;
		} else {
			return enableCondition.getFirstSignal();
		}
	}
	
	public void setPitchSignal(String signal) {
		enableCondition = new Condition(signal, Condition.MORE_THAN, 0);
	}
	
	
	private int instrumentID;
	
	public int getInstrumentID() {
		return instrumentID;
	}
	
	public void setInstrumentID(int id) {
		instrumentID = id;
	}
	
	
	private int noteID;
	
	public int getNoteID() {
		return noteID;
	}
	
	public void setNoteID(int id) {
		noteID = id;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("\"control_behavior\":{");
		
		if(enableCondition != null) {
			sb.append(enableCondition.toString());
			sb.append(',');
		}
		
		sb.append("\"circuit_parameters\":{\"signal_value_is_pitch\":");
		sb.append(signalValueIsPitch);
		sb.append(",\"instrument_id\":");
		sb.append(instrumentID);
		sb.append(",\"note_id\":");
		sb.append(noteID);
		
		sb.append("}}");
		return sb.toString();
	}
	
	
	private float playbackVolume = 1f;
	
	public float getVolume() {
		return playbackVolume;
	}
	
	public void setVolume(float volume) {
		playbackVolume = volume;
	}
	
	
	private boolean playGlobally;
	
	public boolean isPlayingGlobally() {
		return playGlobally;
	}
	
	public void setGlobalPlay(boolean play) {
		playGlobally = play;
	}
	
	
	private boolean allowPolyphony;
	
	public boolean isAllowingPolyphony() {
		return allowPolyphony;
	}
	
	public void setAllowingPolyphony(boolean allow) {
		allowPolyphony = allow;
	}
	
	
	public static class Alert {
		
		private String icon;
		
		public String getIcon() {
			return icon;
		}
		
		public void setIcon(String icon) {
			this.icon = icon;
		}
		
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		private boolean showOnMap = true;
		
		public boolean isShowingOnMap() {
			return showOnMap;
		}
		
		public void setShowingOnMap(boolean show) {
			showOnMap = show;
		}
		
	}
	
	private Alert alert;
	
	public Alert getAlert() {
		return alert;
	}
	
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"parameters\":{\"playback_volume\":");
		sb.append(playbackVolume);
		sb.append(",\"playback_globally\":");
		sb.append(playGlobally);
		sb.append(",\"allow_polyphony\":");
		sb.append(allowPolyphony);
		sb.append('}');
		
		if(alert == null) {
			sb.append(",\"alert_parameters\":{\"show_alert\":false,\"show_on_map\":true,\"alert_message\":\"\"}");
		} else {
			sb.append(",\"alert_parameters\":{\"show_alert\":true,\"show_on_map\":");
			sb.append(alert.showOnMap);
			if(alert.getIcon() != null) {
				sb.append(",\"icon_signal_id\":{\"type\":\"");
				sb.append(Signals.getType(alert.getIcon()));
				sb.append("\",\"name\":\"");
				sb.append(alert.getIcon());
				sb.append("\"}");
			}
			sb.append(",\"alert_message\":\"");
			if(alert.message != null) {
				sb.append(alert.message);
			}
			sb.append("\"}");
		}
		
		return sb.toString();
	}

}
