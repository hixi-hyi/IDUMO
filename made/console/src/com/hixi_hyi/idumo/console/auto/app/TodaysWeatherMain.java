package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
public class TodaysWeatherMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new TodaysWeatherComponent());
  }
  public static void main(String[] args){ 
    TodaysWeatherMain main = new TodaysWeatherMain(); 
    main.exec(); 
  }
}
class TodaysWeatherComponent extends IDUMOComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
    LivedoorWeatherProvider w1 = new LivedoorWeatherProvider(63);
    w1.setOption(LivedoorWeatherProvider.Type.WEATHER);
    add(w1);
    ConsoleViewReceiptor console = new ConsoleViewReceiptor();
    add(console);

    connect(w1, console);
  }
  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}
