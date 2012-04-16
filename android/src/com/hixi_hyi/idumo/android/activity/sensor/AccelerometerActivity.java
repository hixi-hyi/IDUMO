package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.IdumoException;

public class AccelerometerActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new AccelerometerComponent());
	}

	public class AccelerometerComponent extends AbstractAndroidExecutionComponent {

		@Override
		public void onIdumoMakeFlowChart() throws IdumoException {

			AccelerometerProvider accelerometer = new AccelerometerProvider(activity);
			accelerometer.setOption(AccelerometerProvider.Type.X);
			add(accelerometer);

			AccelerometerProvider accelerometer2 = new AccelerometerProvider(activity);
			accelerometer2.setOption(AccelerometerProvider.Type.Y);
			add(accelerometer2);

			AccelerometerProvider accelerometer3 = new AccelerometerProvider(activity);
			accelerometer3.setOption(AccelerometerProvider.Type.Z);
			add(accelerometer3);

			StringConcatHandler concat = new StringConcatHandler("X:");
			add(concat);

			StringConcatHandler concat2 = new StringConcatHandler("Y:");
			add(concat2);

			StringConcatHandler concat3 = new StringConcatHandler("Z:");
			add(concat3);

			TextViewReceiptor textView = new TextViewReceiptor(activity);
			add(textView);

			connect(accelerometer, concat);
			connect(accelerometer2, concat2);
			connect(accelerometer3, concat3);

			connect(concat, textView);
			connect(concat2, textView);
			connect(concat3, textView);

		}

		@Override
		public void onIdumoPrepare() {
			setLoopCount(-1);
			setSleepTime(1000);

		}

	}

}
