package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.BlueprintException;
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
	
	public static final int DRUMKIT_KICK_1 = 1;
	public static final int DRUMKIT_KICK_2 = 2;
	public static final int DRUMKIT_SNARE_1 = 3;
	public static final int DRUMKIT_SNARE_2 = 4;
	public static final int DRUMKIT_SNARE_3 = 5;
	public static final int DRUMKIT_HI_HAT_1 = 6;
	public static final int DRUMKIT_HI_HAT_2 = 7;
	public static final int DRUMKIT_FX = 8;
	public static final int DRUMKIT_HIGH_Q = 9;
	public static final int DRUMKIT_PERCUSSION_1 = 10;
	public static final int DRUMKIT_PERCUSSION_2 = 11;
	public static final int DRUMKIT_CRASH = 12;
	public static final int DRUMKIT_REVERSE_CYMBAL = 13;
	public static final int DRUMKIT_CLAP = 14;
	public static final int DRUMKIT_SHAKER = 15;
	public static final int DRUMKIT_COWBELL = 16;
	public static final int DRUMKIT_TRIANGLE = 17;
	

	public ProgrammableSpeaker(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.PROGRAMMABLE_SPEAKER;
	}
	
	
	private Condition enableCondition;
	
	/**
	 * Returns enable condition or "null" if enable condition wasn't set before.
	 * @return enable condition or "null" if enable condition wasn't set before.
	 */
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	/**
	 * Sets enable condition for this programmable speaker.
	 * @param condition enable condition you want to set for this programmable speaker.
	 */
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	private boolean signalValueIsPitch;
	
	/**
	 * Returns "true" if "Signal value is pitch" mode is on and "false" if not.
	 * @return "true" if "Signal value is pitch" mode is on and "false" if not.
	 */
	public boolean isSignalValueIsPitchMode() {
		return signalValueIsPitch;
	}
	
	/**
	 * Enables/disables "Signal value is pitch" mode.
	 * @param mode "true" if you want to enable it or "false" if you want to disable it.
	 */
	public void setSignalValueIsPitchMode(boolean mode) {
		signalValueIsPitch = mode;
	}
	
	/**
	 * Returns signal that will be used for "Signal value is pitch" mode or "null"
	 * if signal wasn't set yet.
	 * @return signal used for "Signal value is pitch" or "null".
	 */
	public String getPitchSignal() {
		if(enableCondition == null) {
			return null;
		} else {
			return enableCondition.getFirstSignal();
		}
	}
	
	/**
	 * Sets signal for "Signal value is pitch" mode. Overrides enable condition with
	 * next condition: "your signal > 0".
	 * @param signal
	 */
	public void setPitchSignal(String signal) {
		enableCondition = new Condition(signal, Condition.COMPARATOR_MORE_THAN, 0);
	}
	
	
	private int instrumentID;
	
	/**
	 * Returns instrument's id. Use "INSTRUMENT_" constants of this class.
	 * @return Instrument's id
	 */
	public int getInstrumentID() {
		return instrumentID;
	}
	
	/**
	 * Sets instrument's id. You can use "INTRUMENT_" constants of this class.
	 * @param id instrument's id.
	 */
	public void setInstrumentID(int id) {
		instrumentID = id;
	}
	
	
	private int noteID;
	
	/**
	 * Returns note's id that will be used for this programmable speaker.
	 * @return Note's id.
	 */
	public int getNoteID() {
		return noteID;
	}
	
	/**
	 * Sets note's id that will be used for this programmable speaker.
	 * @param id note's id.
	 */
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
	
	/**
	 * Gets volume of current speaker.
	 * @return Volume value from "0" to "1"
	 */
	public float getVolume() {
		return playbackVolume;
	}
	
	/**
	 * Sets volume of current speaker. Throws BlueprintException if value will be
	 * higher than "1" and lower than "0".
	 * @param volume
	 * @throws BlueprintException
	 */
	public void setVolume(float volume) {
		if(volume < 0) {
			throw new BlueprintException("Programmable speaker's volume can't be lower than \"0\"");
		} else if(volume > 1) {
			throw new BlueprintException("Programmable speaker's volume can't be higher than \"1\"");
		}
		playbackVolume = volume;
	}
	
	
	private boolean playGlobally;
	
	/**
	 * Returns state of "playing globally" flag.
	 * @return "true" if "playing globally" flag is toggled and "false" if not.
	 */
	public boolean isPlayingGlobally() {
		return playGlobally;
	}
	
	/**
	 * Sets "playing globally" flag.
	 * @param play "true" to enable and "false" to disable.
	 */
	public void setGlobalPlay(boolean play) {
		playGlobally = play;
	}
	
	
	private boolean allowPolyphony;
	
	/**
	 * Returns state of "allow polyphony" flag.
	 * @return "true" if "allow polyphony" flag is toggled and "false" if not.
	 */
	public boolean isAllowingPolyphony() {
		return allowPolyphony;
	}
	
	/**
	 * Sets "polyphony" flag.
	 * @param play "true" to enable and "false" to disable.
	 */
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
	
	/**
	 * Returns alert used by this programmable speaker or "null" if alert is disabled
	 * @return Alert used by this programmable speaker or "null" if alert is disabled.
	 */
	public Alert getAlert() {
		return alert;
	}
	
	/**
	 * Sets alert used by this programmable speaker or disables it.
	 * @param alert alert you want to use or "null" if you want to disable it.
	 */
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
