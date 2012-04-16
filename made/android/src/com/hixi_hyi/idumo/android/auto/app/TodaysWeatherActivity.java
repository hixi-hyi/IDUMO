package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class TodaysWeatherActivity extends IDUMOAndroidWrapper {
  @Override
  public void init() {
    setExecutionWithComponent(new TodaysWeatherComponent());
  }

  public class TodaysWeatherComponent extends IDUMOAndroidComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IDUMOException {
      LivedoorWeatherProvider weather1 = new LivedoorWeatherProvider(63);
      weather1.setOption(LivedoorWeatherProvider.Type.DATE);
      add(weather1);
      LivedoorWeatherProvider weather2 = new LivedoorWeatherProvider(63);
      weather2.setOption(LivedoorWeatherProvider.Type.LOCATION);
      add(weather2);
      LivedoorWeatherProvider weather3 = new LivedoorWeatherProvider(63);
      weather3.setOption(LivedoorWeatherProvider.Type.MAX_TEMP);
      add(weather3);
      LivedoorWeatherProvider weather4 = new LivedoorWeatherProvider(63);
      weather4.setOption(LivedoorWeatherProvider.Type.MIN_TEMP);
      add(weather4);
      LivedoorWeatherProvider weather5 = new LivedoorWeatherProvider(63);
      weather5.setOption(LivedoorWeatherProvider.Type.WEATHER);
      add(weather5);
      LivedoorWeatherProvider weather6 = new LivedoorWeatherProvider(63);
      weather6.setOption(LivedoorWeatherProvider.Type.DESCRIPTION);
      add(weather6);
      StringConcatHandler s1 = new StringConcatHandler("DATE:");
      add(s1);
      StringConcatHandler s2 = new StringConcatHandler("Location:");
      add(s2);
      StringConcatHandler s3 = new StringConcatHandler("Max:");
      add(s3);
      StringConcatHandler s4 = new StringConcatHandler("Min:");
      add(s4);
      StringConcatHandler s5 = new StringConcatHandler("Weather:");
      add(s5);
      StringConcatHandler s6 = new StringConcatHandler("Desc:");
      add(s6);
      TextViewReceiptor textView = new TextViewReceiptor(activity);
      add(textView);

      connect(weather1, s1);
      connect(weather2, s2);
      connect(weather3, s3);
      connect(weather4, s4);
      connect(weather5, s5);
      connect(weather6, s6);
      connect(s1, textView);
      connect(s2, textView);
      connect(s3, textView);
      connect(s4, textView);
      connect(s5, textView);
      connect(s6, textView);
    }

    @Override
    public void onIdumoPrepare() {
      setLoopCount(1);
      setSleepTime(1000);
    }
  }

}
