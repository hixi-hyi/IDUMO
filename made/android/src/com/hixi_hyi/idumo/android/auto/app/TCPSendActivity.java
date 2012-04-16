package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.android.util.DeployUtil;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;
public class TCPSendActivity
 extends AbstractAndroidActivity {
  @Override
  public void init() {
		LogManager.DEBUG = DeployUtil.isDebuggable(this);
		LogManager.LOGGER = new AndroidLogger("IDUMO");

    setExecutionWithComponent(new TCPSendComponent());
  }
}
class TCPSendComponent
 extends AbstractAndroidExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
    StringProvider s = new StringProvider("IDUMO");
    add(s);
    SendTCPReceiptor r = new SendTCPReceiptor("192.168.12.4",10000);
    add(r);
    connect(s, r);
  }

  @Override
  public void onIdumoPrepare() {
    setLoopCount(-1);
    setSleepTime(1000);
  }
}