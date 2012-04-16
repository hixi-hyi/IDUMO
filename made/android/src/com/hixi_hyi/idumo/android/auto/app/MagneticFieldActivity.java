package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.MagneticFieldProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class MagneticFieldActivity extends AbstractAndroidActivity {
  @Override
  public void init() {
    setExecutionWithComponent(new MagneticFieldComponent());
  }

  public class MagneticFieldComponent extends AbstractAndroidExecutionComponent {
    @Override
    public void onIdumoMakeFlowChart() throws IDUMOException {
      MagneticFieldProvider g1 = new MagneticFieldProvider(activity);
      g1.setOption(MagneticFieldProvider.Type.X);
      add(g1);
      MagneticFieldProvider g2 = new MagneticFieldProvider(activity);
      g2.setOption(MagneticFieldProvider.Type.Y);
      add(g2);
      MagneticFieldProvider g3 = new MagneticFieldProvider(activity);
      g3.setOption(MagneticFieldProvider.Type.Z);
      add(g3);
      StringConcatHandler s1 = new StringConcatHandler("X:");
      add(s1);
      StringConcatHandler s2 = new StringConcatHandler("Y:");
      add(s2);
      StringConcatHandler s3 = new StringConcatHandler("Z:");
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
