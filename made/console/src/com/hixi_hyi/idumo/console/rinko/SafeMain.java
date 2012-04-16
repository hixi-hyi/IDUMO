package com.hixi_hyi.idumo.console.rinko;
import com.hixi_hyi.idumo.common.handler.SimpleRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
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
class SafeComponent extends IDUMOComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
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
