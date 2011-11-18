package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.*;
import com.hixi_hyi.idumo.android.exec.*;
import com.hixi_hyi.idumo.android.provider.*;
import com.hixi_hyi.idumo.android.handler.*;
import com.hixi_hyi.idumo.android.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;
public class UPSendActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new UPSendComponent());
  }

}
class UPSendComponent extends AbstractAndroidExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
	  LogManager.log();
	StringProvider s = new StringProvider("CLEAN");
    add(s);
    LogManager.debug("string ok");
    SendTCPReceiptor r = new SendTCPReceiptor("192.168.12.4",10000);
    add(r);
    LogManager.debug("tcp ok");
    connect(s, r);
    LogManager.debug("connection ok");
  }

  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}

/*
        <activity android:name=".auto.app.UPSendActivity"
       		  android:label="UPSend">
           <intent-filter>
	            <action android:name="android.intent.action.MAIN" />
               <category android:name="android.intent.category.IDUMO_SAMPLES" />
               <category android:name="android.intent.category.LAUNCHER" />
           </intent-filter>
       </activity>
*/
