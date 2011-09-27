package com.hixi_hyi.idumo.android.sensor;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraSensor extends SurfaceView implements Callback, PictureCallback {
	private Camera	camera;
	private Bitmap	picture;
	private boolean	isReady;
	
	public CameraSensor(Context context) {
		super(context);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	/**
	 * @return picture
	 */
	public Bitmap getPicture() {
		return picture;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		picture = BitmapFactory.decodeByteArray(data, 0, data.length, null);
		// camera.startPreview();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Parameters p = camera.getParameters();
		p.setPreviewSize(width, height);
		camera.startPreview();
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		camera = Camera.open();
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
	}
	
	public void takePicture() {
		camera.takePicture(null, null, this);
		isReady = true;
	}
	
}
