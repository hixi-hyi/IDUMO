/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 * 
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jp.idumo.android.manifest;

import jp.idumo.core.doclet.json.EnumAnnotation;

/**
 * @author Hiroyoshi HOUCHI
 */
public enum AndroidFeature implements EnumAnnotation{
	NONE(""),
	/*-- Hardware --*/
	//Audio
	AUDIO_LOW_LATENCY("android.hardware.audio.low_latency"),
	//Bluetooth
	BLUETOOTH("android.hardware.bluetooth"),
	//Camera
	CAMERA("android.hardware.camera"),
	CAMERA_AUTO_FOCUS("android.hardware.camera.autofocus"),
	CAMERA_FLASH("android.hardware.camera.flash"),
	CAMERA_FRONT("android.hardware.camera.front"),
	//Location
	LOCATION("android.hardware.location"),
	LOCATION_NETWORK("android.hardware.location.network"),
	LOCATION_GPS("android.hardware.location.gps"),
	//Microphone
	MICROPHONE("android.hardware.microphone"),
	//NFC
	NFC("android.hardware.nfc"),
	//Sensor
	SENSOR_ACCELEROMETOR("android.hardware.sensor.accelerometer"),
	SENSOR_BAROMETOR("android.hardware.sensor.barometer"),
	SENSOR_COMPASS("android.hardware.sensor.compass"),
	SENSOR_GYROSCOPE("android.hardware.sensor.gyroscope"),
	SENSOR_LIGHT("android.hardware.sensor.light"),
	SENSOR_PROXIMITY("android.hardware.sensor.proximity"),
	//Screen
	SCREEN_LANDSCOPE("android.hardware.screen.landscape"),
	SCREEN_PORTRAIT("android.hardware.screen.portrait"),
	//Telephony
	TELEPHONY("android.hardware.telephony"),
	TELEPHONY_CDMA("android.hardware.telephony.cdma"),
	TELEPHONY_GSM("android.hardware.telephony.gsm"),
	//Television
	TELEVISION("android.hardware.type.television"),
	//Touchscreen
	FAKETOUCH("android.hardware.faketouch"),
	FAKETOUCH_MULTITOUCH_DISTINCT("android.hardware.faketouch.multitouch.distinct"),
	FAKETOUCH_MULTITOUCH_JAZZHAND("android.hardware.faketouch.multitouch.jazzhand"),
	TOUCHSCREEN("android.hardware.touchscreen"),
	TOUCHSCREEN_MULTITOUCH("android.hardware.touchscreen.multitouch"),
	TOUCHSCREEN_MULTITOUCH_DISTINCT("android.hardware.touchscreen.multitouch.distinct"),
	TOUCHSCREEN_MULTITOUCH_JAZZHAND("android.hardware.touchscreen.multitouch.jazzhand"),
	//USB
	USB_HOST("android.hardware.usb.host"),
	USB_ACCESSORY("android.hardware.usb.accessory"),
	//WIFI
	WIFI("android.hardware.wifi"),
	
	;
	private String feature;
	
	private AndroidFeature(String feature){
		this.feature = feature;
	}
	
	public String getValue(){
		return feature;
	}
	
}
