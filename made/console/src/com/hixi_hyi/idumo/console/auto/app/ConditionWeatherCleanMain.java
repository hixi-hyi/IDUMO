package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.common.handler.ConditionStringHandler;
import com.hixi_hyi.idumo.common.handler.SimpleRommbaCommandHandler;
import com.hixi_hyi.idumo.common.handler.StringHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider_Weather;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;
public class ConditionWeatherCleanMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new ConditionWeatherCleanComponent());
  }
  public static void main(String[] args){ 
    ConditionWeatherCleanMain main = new ConditionWeatherCleanMain(); 
    main.exec(); 
  }
}
class ConditionWeatherCleanComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
    LivedoorWeatherProvider_Weather w = new LivedoorWeatherProvider_Weather(63);
    add(w);
    ConditionStringHandler i = new ConditionStringHandler("晴れ");
    add(i);
    StringHandler s = new StringHandler("CLEAN");
    add(s);
    SimpleRommbaCommandHandler converter = new SimpleRommbaCommandHandler();
    add(converter);
    SerialSendReceiptor serial = new SerialSendReceiptor("/dev/tty.ESD200v117-0CC2EC-Gener");
    add(serial);

    connect(w, i);
    connect(i, s);
    connect(s, converter);
    connect(converter, serial);
  }
  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}
