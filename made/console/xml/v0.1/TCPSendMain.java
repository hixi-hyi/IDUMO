package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.console.*;
import com.hixi_hyi.idumo.console.core.exec.*;
import com.hixi_hyi.idumo.console.provider.*;
import com.hixi_hyi.idumo.console.handler.*;
import com.hixi_hyi.idumo.console.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.core.provider.*;
import com.hixi_hyi.idumo.core.handler.*;
import com.hixi_hyi.idumo.core.receiptor.*;
public class TCPSendMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new TCPSendComponent());
  }
  public static void main(String[] args){ 
    TCPSendMain main = new TCPSendMain(); 
    main.exec(); 
  }
}
class TCPSendComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s = new StringProvider("IDUMO");
    add(s);
    SendTCPReceiptor r = new SendTCPReceiptor("192.168.11.1",10000);
    add(r);

    connect(s, r);
  }
  @Override
  public void onIdumoPrepare() {
    setLoopCount(-1);
    setSleepTime(1000);
  }
}
