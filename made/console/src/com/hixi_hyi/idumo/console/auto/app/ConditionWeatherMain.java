package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.common.handler.ConditionStringHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider_Weather;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor_Bool;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
public class ConditionWeatherMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new ConditionWeatherComponent());
  }
  public static void main(String[] args){ 
    ConditionWeatherMain main = new ConditionWeatherMain(); 
    main.exec(); 
  }
}
class ConditionWeatherComponent extends IDUMOComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
    LivedoorWeatherProvider_Weather w = new LivedoorWeatherProvider_Weather(63);
    add(w);
    ConditionStringHandler s = new ConditionStringHandler("晴れ");
    add(s);
    ConsoleViewReceiptor_Bool console = new ConsoleViewReceiptor_Bool();
    add(console);

    connect(w, s);
    connect(s, console);
  }
  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}
