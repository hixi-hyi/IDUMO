package com.hixi_hyi.idumo.common.component;

import java.util.HashMap;
import java.util.Map;

public class _ConvertRoombaCommand {
	private static Map<String, Byte> command;
	static {
		command = new HashMap<String, Byte>();
		// Geting Started Commands
		command.put("START", (byte) 128);
		command.put("BAUD", (byte) 129);
		// Mode Commands
		command.put("SAFE", (byte) 131);
		command.put("FULL", (byte) 132);
		// Cleaning Commands
		command.put("CLEAN", (byte) 135);
		command.put("MAX", (byte) 136);
		command.put("SPOT", (byte) 134);
		command.put("SEEK_DOCK", (byte) 143);
		command.put("SCHEDULE", (byte) 167);
		command.put("SET_DAY_TIME", (byte) 168);
		command.put("POWER", (byte) 133);
		// Actuator Commands
		command.put("DRIVE", (byte) 137);
		command.put("DRIVE_DIRECT", (byte) 145);
		command.put("DRIVE_PWM", (byte) 146);
		command.put("MOTORS", (byte) 138);
		command.put("PWM_MOTORS", (byte) 144);
		command.put("LEDS", (byte) 139);
		command.put("SCHEDULLING_LEDS", (byte) 162);
		command.put("DIGIT_LEDS_RAW", (byte) 163);
		command.put("DIGIT_LEDS_ASCII", (byte) 164);
		command.put("BUTTONS", (byte) 165);
		command.put("SONG", (byte) 140);
		command.put("PLAY", (byte) 141);
		// Input Command
		command.put("SENSORS", (byte) 142);
		command.put("QUERY_LIST", (byte) 149);
		command.put("STREAM", (byte) 148);
		command.put("PAUSE_RESUME_STREAM", (byte) 150);
	}

	public static boolean containsKey(Object key) {
		return command.containsKey(key);
	}

	public static Byte getCommand(String key) {
		return command.get(key);
	}

}
