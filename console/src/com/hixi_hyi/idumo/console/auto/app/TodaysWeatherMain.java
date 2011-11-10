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
class TodaysWeatherComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
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
