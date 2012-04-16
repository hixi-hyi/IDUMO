package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.util.LogManager;
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
