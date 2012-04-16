package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.MagneticFieldProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.IdumoException;

public class MagneticActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new MagneticComponent());
	}

	public class MagneticComponent extends AbstractAndroidExecutionComponent {

		@Override
		public void onIdumoMakeFlowChart() throws IdumoException {

			MagneticFieldProvider mag1 = new MagneticFieldProvider(activity);
			mag1.setOption(MagneticFieldProvider.Type.X);
			add(mag1);

			MagneticFieldProvider mag2 = new MagneticFieldProvider(activity);
			mag2.setOption(MagneticFieldProvider.Type.Y);
			add(mag2);

			MagneticFieldProvider mag3 = new MagneticFieldProvider(activity);
			mag3.setOption(MagneticFieldProvider.Type.Z);
			add(mag3);

			StringConcatHandler s1 = new StringConcatHandler("X:");
			add(s1);

			StringConcatHandler s2 = new StringConcatHandler("Y:");
			add(s2);

			StringConcatHandler s3 = new StringConcatHandler("Z:");
			add(s3);

			TextViewReceiptor textView = new TextViewReceiptor(activity);
			add(textView);

			connect(mag1, s1);
			connect(mag2, s2);
			connect(mag3, s3);

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
