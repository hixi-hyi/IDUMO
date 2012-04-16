/**
 *
 */
package com.hixi_hyi.idumo.android;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hixi_hyi.idumo.android.core.util.AndroidLogger;
import com.hixi_hyi.idumo.android.core.util.DeployUtil;
import com.hixi_hyi.idumo.core.util.LogManager;

//import com.hixi_hyi.idumo.android.core.util.AndroidLogger;
//import com.hixi_hyi.idumo.android.core.util.DeployUtil;
//import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public class Idumo extends ListActivity {

	public static String		TAG				= "Idumo";
	private final static String	MY_CATEGORY		= "android.intent.category.IDUMO_SAMPLES";
	private final static String	CATEGORY_PATH	= "com.hixi_hyi.idumo.android.Path";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// AndroidLogger
		LogManager.DEBUG = DeployUtil.isDebuggable(this);
		LogManager.LOGGER = new AndroidLogger(TAG);
		// AndroidLogger.isDebug = DeployUtil.isDebuggable(this);
		// AndroidLogger.TAG = TAG;

		Intent intent = getIntent();
		String path = intent.getStringExtra(CATEGORY_PATH);

		if (path == null) {
			path = "";
		}
//		LogManager.debug(path);
		List<Action> data = getData(path);
		ListAdapter adapter = new ArrayAdapter<Action>(this, android.R.layout.simple_list_item_1, data);
		setListAdapter(adapter);
		getListView().setTextFilterEnabled(true);
	}

	protected List<Action> getData(String prefix) {
		List<Action> ActionData = new ArrayList<Action>();

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(MY_CATEGORY);

		PackageManager pm = getPackageManager();
		List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

		if (null == list) {
			return ActionData;
		}

		String[] prefixPath;

		if (prefix.equals("")) {
			prefixPath = null;
		} else {
			prefixPath = prefix.split("/");
		}

		Map<String, Boolean> entries = new HashMap<String, Boolean>();

		for (ResolveInfo info : list) {
			CharSequence labelSeq = info.loadLabel(pm);
			String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

			if ((prefix.length() == 0) || label.startsWith(prefix)) {

				String[] labelPath = label.split("/");

				String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

				if ((prefixPath != null ? prefixPath.length : 0) == (labelPath.length - 1)) {
					// 終点の場合
					addItem(ActionData, nextLabel, activityIntent(info.activityInfo.applicationInfo.packageName, info.activityInfo.name));
				} else {
					// 下位層があるばあい
					if (entries.get(nextLabel) == null) {
						addItem(ActionData, "[" + nextLabel + "]/", browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
						entries.put(nextLabel, true);
					}
				}
			}
		}

		Collections.sort(ActionData, sDisplayNameComparator);

		return ActionData;
	}

	private final static Comparator<Action>	sDisplayNameComparator	= new Comparator<Action>() {
																		private final Collator	collator	= Collator.getInstance();

																		@Override
																		public int compare(Action map1, Action map2) {
																			return collator.compare(map1.getTitle(), map2.getTitle());
																		}
																	};

	protected Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();
		result.setClassName(pkg, componentName);
		return result;
	}

	protected Intent browseIntent(String path) {
		Intent result = new Intent();
		result.setClass(this, Idumo.class);
		result.putExtra(CATEGORY_PATH, path);
		return result;
	}

	protected void addItem(List<Action> data, String name, Intent intent) {
		data.add(new Action(name, intent));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Action action = (Action) l.getItemAtPosition(position);
		startActivity(action.getIntent());
	}

	private class Action {
		private String	title;
		private Intent	intent;

		Action(String title, Intent intent) {
			this.title = title;
			this.intent = intent;
		}

		@Override
		public String toString() {
			return title;
		}

		public String getTitle() {
			return title;
		}

		/**
		 * @return intent
		 */
		public Intent getIntent() {
			return intent;
		}
	}

}
