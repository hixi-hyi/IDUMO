package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.ProximityProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.IdumoException;
public class ProximityActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new ProximityComponent());
  }

  public class ProximityComponent extends AbstractAndroidExecutionComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IdumoException {
      ProximityProvider l1 = new ProximityProvider(activity);
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
