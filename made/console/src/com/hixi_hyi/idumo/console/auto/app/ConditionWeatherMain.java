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
class ConditionWeatherComponent extends AbstractExecutionComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IdumoException {
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
