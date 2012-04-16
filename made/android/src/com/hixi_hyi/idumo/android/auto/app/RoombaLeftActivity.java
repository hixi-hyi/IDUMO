package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class RoombaLeftActivity extends IDUMOAndroidWrapper {
  @Override
  public void init() {
    setExecutionWithComponent(new RoombaLeftComponent());
  }

}
class RoombaLeftComponent extends IDUMOAndroidComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
    StringProvider s = new StringProvider("LEFT");
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
        <activity android:name=".auto.app.RoombaLeftActivity"
       		  android:label="RoombaLeft">
           <intent-filter>
	            <action android:name="android.intent.action.MAIN" />
               <category android:name="android.intent.category.IDUMO_SAMPLES" />
               <category android:name="android.intent.category.LAUNCHER" />
           </intent-filter>
       </activity>
*/
