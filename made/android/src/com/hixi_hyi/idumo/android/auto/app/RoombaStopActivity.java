package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.*;
import com.hixi_hyi.idumo.android.exec.*;
import com.hixi_hyi.idumo.android.provider.*;
import com.hixi_hyi.idumo.android.handler.*;
import com.hixi_hyi.idumo.android.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;
public class RoombaStopActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new RoombaStopComponent());
  }

}
class RoombaStopComponent extends AbstractAndroidExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s = new StringProvider("STOP");
    add(s);
    SendTCPReceiptor r = new SendTCPReceiptor("192.168.11.4",10000);
    add(r);

    connect(s, r);
  }

  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}

/*
        <activity android:name=".auto.app.RoombaStopActivity"
       		  android:label="RoombaStop">
           <intent-filter>
	            <action android:name="android.intent.action.MAIN" />
               <category android:name="android.intent.category.IDUMO_SAMPLES" />
               <category android:name="android.intent.category.LAUNCHER" />
           </intent-filter>
       </activity>
*/
