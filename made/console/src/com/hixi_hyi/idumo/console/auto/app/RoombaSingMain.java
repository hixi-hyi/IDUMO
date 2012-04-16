package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;
public class RoombaSingMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new RoombaSingComponent());
  }
  public static void main(String[] args){ 
    RoombaSingMain main = new RoombaSingMain(); 
    main.exec(); 
  }
}
class RoombaSingComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s = new StringProvider("SING");
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
