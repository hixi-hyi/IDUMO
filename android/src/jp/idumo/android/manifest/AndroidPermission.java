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

import jp.idumo.core.doclet.element.EnumAnnotation;

/**
 * @author Hiroyoshi HOUCHI
 */
public enum AndroidPermission implements EnumAnnotation{
	NONE(""),
	ACCESS_CHECKIN_PROPERTIES(android.Manifest.permission.ACCESS_CHECKIN_PROPERTIES),
	ACCESS_COARSE_LOCATION(android.Manifest.permission.ACCESS_COARSE_LOCATION),
	ACCESS_FINE_LOCATION(android.Manifest.permission.ACCESS_FINE_LOCATION),
	ACCESS_LOCATION_EXTRA_COMMANDS(android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS),
	ACCESS_MOCK_LOCATION(android.Manifest.permission.ACCESS_MOCK_LOCATION),
	ACCESS_NETWORK_STATE(android.Manifest.permission.ACCESS_NETWORK_STATE),
	ACCESS_SURFACE_FLINGER(android.Manifest.permission.ACCESS_SURFACE_FLINGER),
	ACCESS_WIFI_STATE(android.Manifest.permission.ACCESS_WIFI_STATE),
//	ACCOUNT_MANAGER(android.Manifest.permission.ACCOUNT_MANAGER),
//	ADD_VOICEMAIL(android.Manifest.permission.ADD_VOICEMAIL),
//	AUTHENTICATE_ACCOUNTS(android.Manifest.permission.AUTHENTICATE_ACCOUNTS),
	BATTERY_STATS(android.Manifest.permission.BATTERY_STATS),
//	BIND_ACCESSIBILITY_SERVICE(android.Manifest.permission.BIND_ACCESSIBILITY_SERVICE),
	BIND_APPWIDGET(android.Manifest.permission.BIND_APPWIDGET),
//	BIND_DEVICE_ADMIN(android.Manifest.permission.BIND_DEVICE_ADMIN),
	BIND_INPUT_METHOD(android.Manifest.permission.BIND_INPUT_METHOD),
//	BIND_REMOTEVIEWS(android.Manifest.permission.BIND_REMOTEVIEWS),
//	BIND_TEXT_SERVICE(android.Manifest.permission.BIND_TEXT_SERVICE),
//	BIND_VPN_SERVICE(android.Manifest.permission.BIND_VPN_SERVICE),
//	BIND_WALLPAPER(android.Manifest.permission.BIND_WALLPAPER),
	BLUETOOTH(android.Manifest.permission.BLUETOOTH),
	BLUETOOTH_ADMIN(android.Manifest.permission.BLUETOOTH_ADMIN),
	BRICK(android.Manifest.permission.BRICK),
	BROADCAST_PACKAGE_REMOVED(android.Manifest.permission.BROADCAST_PACKAGE_REMOVED),
	BROADCAST_SMS(android.Manifest.permission.BROADCAST_SMS),
	BROADCAST_STICKY(android.Manifest.permission.BROADCAST_STICKY),
	BROADCAST_WAP_PUSH(android.Manifest.permission.BROADCAST_WAP_PUSH),
	CALL_PHONE(android.Manifest.permission.CALL_PHONE),
	CALL_PRIVILEGED(android.Manifest.permission.CALL_PRIVILEGED),
	CAMERA(android.Manifest.permission.CAMERA),
	CHANGE_COMPONENT_ENABLED_STATE(android.Manifest.permission.CHANGE_COMPONENT_ENABLED_STATE),
	CHANGE_CONFIGURATION(android.Manifest.permission.CHANGE_CONFIGURATION),
	CHANGE_NETWORK_STATE(android.Manifest.permission.CHANGE_NETWORK_STATE),
//	CHANGE_WIFI_MULTICAST_STATE(android.Manifest.permission.CHANGE_WIFI_MULTICAST_STATE),
	CHANGE_WIFI_STATE(android.Manifest.permission.CHANGE_WIFI_STATE),
	CLEAR_APP_CACHE(android.Manifest.permission.CLEAR_APP_CACHE),
	CLEAR_APP_USER_DATA(android.Manifest.permission.CLEAR_APP_USER_DATA),
	CONTROL_LOCATION_UPDATES(android.Manifest.permission.CONTROL_LOCATION_UPDATES),
	DELETE_CACHE_FILES(android.Manifest.permission.DELETE_CACHE_FILES),
	DELETE_PACKAGES(android.Manifest.permission.DELETE_PACKAGES),
	DEVICE_POWER(android.Manifest.permission.DEVICE_POWER),
	DIAGNOSTIC(android.Manifest.permission.DIAGNOSTIC),
	DISABLE_KEYGUARD(android.Manifest.permission.DISABLE_KEYGUARD),
	DUMP(android.Manifest.permission.DUMP),
	EXPAND_STATUS_BAR(android.Manifest.permission.EXPAND_STATUS_BAR),
	FACTORY_TEST(android.Manifest.permission.FACTORY_TEST),
	FLASHLIGHT(android.Manifest.permission.FLASHLIGHT),
	FORCE_BACK(android.Manifest.permission.FORCE_BACK),
	GET_ACCOUNTS(android.Manifest.permission.GET_ACCOUNTS),
	GET_PACKAGE_SIZE(android.Manifest.permission.GET_PACKAGE_SIZE),
	GET_TASKS(android.Manifest.permission.GET_TASKS),
//	GLOBAL_SEARCH(android.Manifest.permission.GLOBAL_SEARCH),
	HARDWARE_TEST(android.Manifest.permission.HARDWARE_TEST),
	INJECT_EVENTS(android.Manifest.permission.INJECT_EVENTS),
//	INSTALL_LOCATION_PROVIDER(android.Manifest.permission.INSTALL_LOCATION_PROVIDER),
	INSTALL_PACKAGES(android.Manifest.permission.INSTALL_PACKAGES),
	INTERNAL_SYSTEM_WINDOW(android.Manifest.permission.INTERNAL_SYSTEM_WINDOW),
	INTERNET(android.Manifest.permission.INTERNET),
//	KILL_BACKGROUND_PROCESSES(android.Manifest.permission.KILL_BACKGROUND_PROCESSES),
//	MANAGE_ACCOUNTS(android.Manifest.permission.MANAGE_ACCOUNTS),
	MANAGE_APP_TOKENS(android.Manifest.permission.MANAGE_APP_TOKENS),
	MASTER_CLEAR(android.Manifest.permission.MASTER_CLEAR),
	MODIFY_AUDIO_SETTINGS(android.Manifest.permission.MODIFY_AUDIO_SETTINGS),
	MODIFY_PHONE_STATE(android.Manifest.permission.MODIFY_PHONE_STATE),
	MOUNT_FORMAT_FILESYSTEMS(android.Manifest.permission.MOUNT_FORMAT_FILESYSTEMS),
	MOUNT_UNMOUNT_FILESYSTEMS(android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS),
//	NFC(android.Manifest.permission.NFC),
	PERSISTENT_ACTIVITY(android.Manifest.permission.PERSISTENT_ACTIVITY),
	PROCESS_OUTGOING_CALLS(android.Manifest.permission.PROCESS_OUTGOING_CALLS),
	READ_CALENDAR(android.Manifest.permission.READ_CALENDAR),
//	READ_CALL_LOG(android.Manifest.permission.READ_CALL_LOG),
	READ_CONTACTS(android.Manifest.permission.READ_CONTACTS),
//	READ_EXTERNAL_STORAGE(android.Manifest.permission.READ_EXTERNAL_STORAGE),
	READ_FRAME_BUFFER(android.Manifest.permission.READ_FRAME_BUFFER),
//	READ_HISTORY_BOOKMARKS(android.Manifest.permission.READ_HISTORY_BOOKMARKS),
	READ_INPUT_STATE(android.Manifest.permission.READ_INPUT_STATE),
	READ_LOGS(android.Manifest.permission.READ_LOGS),
	READ_PHONE_STATE(android.Manifest.permission.READ_PHONE_STATE),
//	READ_PROFILE(android.Manifest.permission.READ_PROFILE),
	READ_SMS(android.Manifest.permission.READ_SMS),
//	READ_SOCIAL_STREAM(android.Manifest.permission.READ_SOCIAL_STREAM),
	READ_SYNC_SETTINGS(android.Manifest.permission.READ_SYNC_SETTINGS),
	READ_SYNC_STATS(android.Manifest.permission.READ_SYNC_STATS),
//	READ_USER_DICTIONARY(android.Manifest.permission.READ_USER_DICTIONARY),
	REBOOT(android.Manifest.permission.REBOOT),
	RECEIVE_BOOT_COMPLETED(android.Manifest.permission.RECEIVE_BOOT_COMPLETED),
	RECEIVE_MMS(android.Manifest.permission.RECEIVE_MMS),
	RECEIVE_SMS(android.Manifest.permission.RECEIVE_SMS),
	RECEIVE_WAP_PUSH(android.Manifest.permission.RECEIVE_WAP_PUSH),
	RECORD_AUDIO(android.Manifest.permission.RECORD_AUDIO),
	REORDER_TASKS(android.Manifest.permission.REORDER_TASKS),
	RESTART_PACKAGES(android.Manifest.permission.RESTART_PACKAGES),
	SEND_SMS(android.Manifest.permission.SEND_SMS),
	SET_ACTIVITY_WATCHER(android.Manifest.permission.SET_ACTIVITY_WATCHER),
//	SET_ALARM(android.Manifest.permission.SET_ALARM),
	SET_ALWAYS_FINISH(android.Manifest.permission.SET_ALWAYS_FINISH),
	SET_ANIMATION_SCALE(android.Manifest.permission.SET_ANIMATION_SCALE),
	SET_DEBUG_APP(android.Manifest.permission.SET_DEBUG_APP),
	SET_ORIENTATION(android.Manifest.permission.SET_ORIENTATION),
//	SET_POINTER_SPEED(android.Manifest.permission.SET_POINTER_SPEED),
	SET_PREFERRED_APPLICATIONS(android.Manifest.permission.SET_PREFERRED_APPLICATIONS),
	SET_PROCESS_LIMIT(android.Manifest.permission.SET_PROCESS_LIMIT),
//	SET_TIME(android.Manifest.permission.SET_TIME),
	SET_TIME_ZONE(android.Manifest.permission.SET_TIME_ZONE),
	SET_WALLPAPER(android.Manifest.permission.SET_WALLPAPER),
	SET_WALLPAPER_HINTS(android.Manifest.permission.SET_WALLPAPER_HINTS),
	SIGNAL_PERSISTENT_PROCESSES(android.Manifest.permission.SIGNAL_PERSISTENT_PROCESSES),
	STATUS_BAR(android.Manifest.permission.STATUS_BAR),
	SUBSCRIBED_FEEDS_READ(android.Manifest.permission.SUBSCRIBED_FEEDS_READ),
	SUBSCRIBED_FEEDS_WRITE(android.Manifest.permission.SUBSCRIBED_FEEDS_WRITE),
	SYSTEM_ALERT_WINDOW(android.Manifest.permission.SYSTEM_ALERT_WINDOW),
	UPDATE_DEVICE_STATS(android.Manifest.permission.UPDATE_DEVICE_STATS),
//	USE_CREDENTIALS(android.Manifest.permission.USE_CREDENTIALS),
//	USE_SIP(android.Manifest.permission.USE_SIP),
	VIBRATE(android.Manifest.permission.VIBRATE),
	WAKE_LOCK(android.Manifest.permission.WAKE_LOCK),
	WRITE_APN_SETTINGS(android.Manifest.permission.WRITE_APN_SETTINGS),
	WRITE_CALENDAR(android.Manifest.permission.WRITE_CALENDAR),
//	WRITE_CALL_LOG(android.Manifest.permission.WRITE_CALL_LOG),
	WRITE_CONTACTS(android.Manifest.permission.WRITE_CONTACTS),
//	WRITE_EXTERNAL_STORAGE(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
	WRITE_GSERVICES(android.Manifest.permission.WRITE_GSERVICES),
//	WRITE_HISTORY_BOOKMARKS(android.Manifest.permission.WRITE_HISTORY_BOOKMARKS),
//	WRITE_PROFILE(android.Manifest.permission.WRITE_PROFILE),
	WRITE_SECURE_SETTINGS(android.Manifest.permission.WRITE_SECURE_SETTINGS),
	WRITE_SETTINGS(android.Manifest.permission.WRITE_SETTINGS),
	WRITE_SMS(android.Manifest.permission.WRITE_SMS),
//	WRITE_SOCIAL_STREAM(android.Manifest.permission.WRITE_SOCIAL_STREAM),
	WRITE_SYNC_SETTINGS(android.Manifest.permission.WRITE_SYNC_SETTINGS),
//	WRITE_USER_DICTIONARY(android.Manifest.permission.WRITE_USER_DICTIONARY),

	;
	
	private String permission;
	
	private AndroidPermission(String permission){
		this.permission = permission;
	}
	
	public String getValue(){
		return permission;
	}
	
}
