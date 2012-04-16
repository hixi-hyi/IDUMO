package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidVirtualMachine;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.util.AndroidLogger;
import com.hixi_hyi.idumo.android.core.util.DeployUtil;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;
public class TCPSendActivity
 extends IDUMOAndroidVirtualMachine {
  @Override
  public void init() {
		LogManager.DEBUG = DeployUtil.isDebuggable(this);
		LogManager.LOGGER = new AndroidLogger("IDUMO");

    setExecutionWithComponent(new TCPSendComponent());
  }
}
class TCPSendComponent
 extends IDUMOAndroidComponent {
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