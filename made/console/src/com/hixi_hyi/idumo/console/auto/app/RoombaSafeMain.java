package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.console.*;
import com.hixi_hyi.idumo.console.exec.*;
import com.hixi_hyi.idumo.console.provider.*;
import com.hixi_hyi.idumo.console.handler.*;
import com.hixi_hyi.idumo.console.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;
public class RoombaSafeMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new RoombaSafeComponent());
  }
  public static void main(String[] args){ 
    RoombaSafeMain main = new RoombaSafeMain(); 
    main.exec(); 
  }
}
class RoombaSafeComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s = new StringProvider("SAFE");
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
