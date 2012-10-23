package jp.idumo.android.core.exec;

import java.util.ArrayList;
import java.util.Collection;

import jp.idumo.android.core.AndroidController;
import jp.idumo.core.exec.CoreContainer;
import jp.idumo.core.parts.Connectable;


public class AndroidContainer extends CoreContainer {
	private ArrayList<AndroidController>	androidControllers	= new ArrayList<AndroidController>();
	
	@Override
	public void add(Connectable item) {
		super.add(item);
		if (item instanceof AndroidController) {
			androidControllers.add((AndroidController) item);
		}
	}
	
	public Collection<AndroidController> getAndroidControllers() {
		return androidControllers;
	}
	
}
