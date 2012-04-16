package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidVirtualMachine;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class GPSActivity extends IDUMOAndroidVirtualMachine {
  @Override
  public void init() {
    setExecutionWithComponent(new GPSComponent());
  }

  public class GPSComponent extends IDUMOAndroidComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IDUMOException {
      GPSProvider g1 = new GPSProvider(activity);
      g1.setOption(GPSProvider.Type.ACCURARY);
      add(g1);
      GPSProvider g2 = new GPSProvider(activity);
      g2.setOption(GPSProvider.Type.ALTITUDE);
      add(g2);
      GPSProvider g3 = new GPSProvider(activity);
      g3.setOption(GPSProvider.Type.BEARING);
      add(g3);
      GPSProvider g4 = new GPSProvider(activity);
      g4.setOption(GPSProvider.Type.LATITUDE);
      add(g4);
      GPSProvider g5 = new GPSProvider(activity);
      g5.setOption(GPSProvider.Type.LONGITUDE);
      add(g5);
      GPSProvider g6 = new GPSProvider(activity);
      g6.setOption(GPSProvider.Type.SPEED);
      add(g6);
      GPSProvider g7 = new GPSProvider(activity);
      g7.setOption(GPSProvider.Type.TIME);
      add(g7);
      StringConcatHandler s1 = new StringConcatHandler("Accurary:");
      add(s1);
      StringConcatHandler s2 = new StringConcatHandler("Altitude:");
      add(s2);
      StringConcatHandler s3 = new StringConcatHandler("Bearing:");
      add(s3);
      StringConcatHandler s4 = new StringConcatHandler("Latitude:");
      add(s4);
      StringConcatHandler s5 = new StringConcatHandler("Longitude:");
      add(s5);
      StringConcatHandler s6 = new StringConcatHandler("Speed:");
      add(s6);
      StringConcatHandler s7 = new StringConcatHandler("Time:");
      add(s7);
      TextViewReceiptor textView = new TextViewReceiptor(activity);
      add(textView);

      connect(g1, s1);
      connect(g2, s2);
      connect(g3, s3);
      connect(g4, s4);
      connect(g5, s5);
      connect(g6, s6);
      connect(g7, s7);
      connect(s1, textView);
      connect(s2, textView);
      connect(s3, textView);
      connect(s4, textView);
      connect(s5, textView);
      connect(s6, textView);
      connect(s7, textView);
    }

    @Override
    public void onIdumoPrepare() {
      setLoopCount(-1);
      setSleepTime(1000);
    }
  }

}
