package org.saurav.contentmanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.saurav.contentmanager.model.CMISModel;
import org.saurav.contentmanager.util.ContentManagerListAdapter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class ContentListFragment extends ListFragment
{
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	// {
	// // TODO Auto-generated method stub
	// View contentListView = inflater.inflate(R.layout.fragment_listcontent, container, false);
	// ListView listView = (ListView) contentListView
	// .findViewById(android.R.id.list);
	// //ListView listView =getListView();
	// setAdapter(listView);
	// return contentListView;
	// }

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(getAdapter());
	}

	private ArrayAdapter<Object> getAdapter()
	{

		String[] values = new String[] { "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu",
										"Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X",
										"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Android",
										"iPhone", "WindowsMobile" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		List<CmisObject> cmisObjectsList = CMISModel.getInstance().getFolderList();
		// ContentManagerListAdapter listAdapter = new ContentManagerListAdapter(this.getActivity(),
		// android.R.layout.simple_list_item_activated_1,android.R.id.text1,list.toArray(new String[] {}));

		ContentManagerListAdapter listAdapter = new ContentManagerListAdapter(this.getActivity(),
				android.R.layout.simple_list_item_activated_1, android.R.id.text1, cmisObjectsList.toArray());
		// listView.setAdapter(listAdapter);
		return null;

	}

	// @Override
	// public void onActivityCreated(Bundle savedInstanceState)
	// {
	// // TODO Auto-generated method stub
	// super.onActivityCreated(savedInstanceState);
	//
	// ListView listView = (ListView)getView()
	// .findViewById(android.R.id.list);
	// //ListView listView =getListView();
	// setAdapter(listView);
	// }

}
