package jp.idumo.android.core.exec;

import java.util.Collection;

import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.core.AndroidController;
import jp.idumo.core.exec.CoreComponent;
import jp.idumo.core.parts.Connectable;

public abstract class AndroidComponent extends CoreComponent {
	
	private AndroidActivityResource	activity;
	
	public Collection<AndroidController> getAndroidControllers() {
		AndroidContainer container = (AndroidContainer) getContainer();
		return container.getAndroidControllers();
	}
	
	@Override
	public void add(Connectable item) {
		super.add(item);
		if (item instanceof AndroidActivityController) {
			((AndroidActivityController) item).registActivity(activity);
		}
	}
	
	public void setActivity(AndroidActivityResource activity) {
		this.activity = activity;
	}
	
}
