package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
public class RoombaCleanActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new RoombaCleanComponent());
  }

}
class RoombaCleanComponent extends AbstractAndroidExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s = new StringProvider("CLEAN");
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
        <activity android:name=".auto.app.RoombaCleanActivity"
       		  android:label="RoombaClean">
           <intent-filter>
	            <action android:name="android.intent.action.MAIN" />
               <category android:name="android.intent.category.IDUMO_SAMPLES" />
               <category android:name="android.intent.category.LAUNCHER" />
           </intent-filter>
       </activity>
*/
