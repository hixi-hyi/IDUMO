package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.LightProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class LightActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new LightComponent());
  }

  public class LightComponent extends AbstractAndroidExecutionComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IDUMOException {
      LightProvider l1 = new LightProvider(activity);
      add(l1);
      StringConcatHandler s1 = new StringConcatHandler("Accurary:");
      add(s1);
      TextViewReceiptor textView = new TextViewReceiptor(activity);
      add(textView);

      connect(l1, s1);
      connect(s1, textView);
    }

    @Override
    public void onIdumoPrepare() {
      setLoopCount(-1);
      setSleepTime(1000);
    }
  }

}
