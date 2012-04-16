package com.hixi_hyi.idumo.console.rinko;
import com.hixi_hyi.idumo.common.handler.SimpleRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
public class SingMain extends IDUMOConsoleWrapper {
  @Override
  public void init() {
    setExecutionWithComponent(new SingComponent());
  }
  public static void main(String[] args){ 
    SingMain main = new SingMain(); 
    main.exec(); 
  }
}
class SingComponent extends IDUMOComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
    StringProvider s = new StringProvider("SING");
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
