package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.*;
import com.hixi_hyi.idumo.android.exec.*;
import com.hixi_hyi.idumo.android.provider.*;
import com.hixi_hyi.idumo.android.handler.*;
import com.hixi_hyi.idumo.android.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;
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
