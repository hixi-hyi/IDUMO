package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.*;
import com.hixi_hyi.idumo.android.exec.*;
import com.hixi_hyi.idumo.android.provider.*;
import com.hixi_hyi.idumo.android.handler.*;
import com.hixi_hyi.idumo.android.receiptor.*;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.android.util.DeployUtil;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.core.provider.*;
import com.hixi_hyi.idumo.core.handler.*;
import com.hixi_hyi.idumo.core.receiptor.*;
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
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s
     = new StringProvider("IDUMO");
    add(s);
    SendTCPReceiptor r
     = new SendTCPReceiptor("192.168.12.2",10000);
    add(r);
    connect(s, r);
  }

  @Override
  public void onIdumoPrepare() {
    setLoopCount(-1);
    setSleepTime(1000);
  }
}