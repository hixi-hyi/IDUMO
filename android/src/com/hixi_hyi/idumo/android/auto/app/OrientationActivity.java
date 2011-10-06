package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.*;
import com.hixi_hyi.idumo.android.exec.*;
import com.hixi_hyi.idumo.android.provider.*;
import com.hixi_hyi.idumo.android.handler.*;
import com.hixi_hyi.idumo.android.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.core.provider.*;
import com.hixi_hyi.idumo.core.handler.*;
import com.hixi_hyi.idumo.core.receiptor.*;
public class OrientationActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new OrientationComponent());
  }

  public class OrientationComponent extends AbstractAndroidExecutionComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IdumoException {
      OrientationProvider g1 = new OrientationProvider(activity);
      g1.setOption(OrientationProvider.Type.AZMUTH);
      add(g1);
      OrientationProvider g2 = new OrientationProvider(activity);
      g2.setOption(OrientationProvider.Type.PITCH);
      add(g2);
      OrientationProvider g3 = new OrientationProvider(activity);
      g3.setOption(OrientationProvider.Type.ROLL);
      add(g3);
      StringConcatHandler s1 = new StringConcatHandler("AZMUTH:");
      add(s1);
      StringConcatHandler s2 = new StringConcatHandler("PITCH:");
      add(s2);
      StringConcatHandler s3 = new StringConcatHandler("ROLL:");
      add(s3);
      TextViewReceiptor textView = new TextViewReceiptor(activity);
      add(textView);

      connect(g1, s1);
      connect(g2, s2);
      connect(g3, s3);
      connect(s1, textView);
      connect(s2, textView);
      connect(s3, textView);
    }

    @Override
    public void onIdumoPrepare() {
      setLoopCount(-1);
      setSleepTime(1000);
    }
  }

}
