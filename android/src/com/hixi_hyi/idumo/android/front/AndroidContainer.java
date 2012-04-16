package com.hixi_hyi.idumo.android.front;

import java.util.ArrayList;
import java.util.Collection;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.core.exec.IDUMOContainer;
import com.hixi_hyi.idumo.core.parts.IDUMOParts;

public class AndroidContainer extends IDUMOContainer {
	private ArrayList<AndroidController>	androidControllers	= new ArrayList<AndroidController>();

	@Override
	public void add(IDUMOParts item) {
		super.add(item);
		if (item instanceof AndroidController) {
			androidControllers.add((AndroidController) item);
		}
	}

	public Collection<AndroidController> getAndroidControllers() {
		return androidControllers;
	}

}
