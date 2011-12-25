package com.hixi_hyi.idumo.console.rinko;
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
public class SafeMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new SafeComponent());
  }
  public static void main(String[] args){ 
    SafeMain main = new SafeMain(); 
    main.exec(); 
  }
}
class SafeComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    StringProvider s = new StringProvider("SAFE");
    add(s);
    SimpleRommbaCommandHandler converter = new SimpleRommbaCommandHandler();
    add(converter);
    SerialSendReceiptor serial = new SerialSendReceiptor("/dev/tty.ESD200v117-0CC2EC-Gener");
    add(serial);

    connect(s, converter);
    connect(converter, serial);
  }
  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}
