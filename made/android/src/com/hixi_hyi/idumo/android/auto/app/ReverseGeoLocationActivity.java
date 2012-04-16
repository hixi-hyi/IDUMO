package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidVirtualMachine;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.ReversedGeocordingHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class ReverseGeoLocationActivity extends IDUMOAndroidVirtualMachine {
  @Override
  public void init() {
    setExecutionWithComponent(new ReverseGeoLocationComponent());
  }

  public class ReverseGeoLocationComponent extends IDUMOAndroidComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IDUMOException {
      GPSProvider gps1 = new GPSProvider(activity);
      gps1.setOption(GPSProvider.Type.LATITUDE);
      add(gps1);
      GPSProvider gps2 = new GPSProvider(activity);
      gps2.setOption(GPSProvider.Type.LONGITUDE);
      add(gps2);
      ReversedGeocordingHandler rgh = new ReversedGeocordingHandler();
      add(rgh);
      TextViewReceiptor textview = new TextViewReceiptor(activity);
      add(textview);

      connect(gps1, rgh);
      connect(gps2, rgh);
      connect(rgh, textview);
    }

    @Override
    public void onIdumoPrepare() {
      setLoopCount(-1);
      setSleepTime(1000);
    }
  }

}
