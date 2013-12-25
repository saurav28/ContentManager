package org.saurav.contentmanager;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.saurav.contentmanager.model.CMISModel;
import org.saurav.contentmanager.util.ContentManagerListAdapter;
import org.saurav.contentmanager.util.FileUtils;
import org.saurav.contentmanager.util.ToastUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * An activity representing a list of Items. This activity has different presentations for handset and tablet-size
 * devices. On handsets, the activity presents a list of items, which when touched, lead to a {@link ItemDetailActivity}
 * representing item details. On tablets, the activity presents the list of items and item details side-by-side using
 * two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a {@link ItemListFragment} and the item details (if
 * present) is a {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class ItemListActivity extends FragmentActivity
	implements ItemListFragment.Callbacks
{

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
	 */
	private boolean mTwoPane;
	private ItemListFragment itemListFragment;
	List<CmisObject> cmisObjectList = CMISModel.getInstance().getFolderList();

	// =((ItemListFragment)getSupportFragmentManager().findFragmentById(R.id.item_list));

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
		itemListFragment = ((ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.item_list));
		itemListFragment.setActivateOnItemClick(true);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list_folder, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {

			case R.id.action_settings :
				openSettings();
				return true;
			case R.id.info :
				showInfo();
				return true;
			case R.id.open :
				openFiles();
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
	}

	private void openFiles()
	{

		// Intent intent = new Intent();
		ContentManagerListAdapter listAdatper = (ContentManagerListAdapter) itemListFragment.getListAdapter();
		Object[] objectArray = listAdatper.getObjectArray();
		boolean[] checkedArray = listAdatper.getChecked();

		CmisObject selectedObject = (CmisObject) objectArray[listAdatper.getCheckedPosition()];
		if (selectedObject instanceof Folder) {
			Context context = getApplicationContext();
			CharSequence text = "Selected object is Folder, hence can not be opened";
			ToastUtils.showText(context, text);
		}
		else if (selectedObject instanceof Document) {
			Document selectedDocument = (Document) selectedObject;
			ContentStream contentStream = selectedDocument.getContentStream();
			java.io.InputStream inputStream = contentStream.getStream();

		}
		// intent.setAction(android.content.Intent.ACTION_VIEW);
		// File file = new File("/sdcard/test.mp4");
		// intent.setDataAndType(Uri.fromFile(file), "video/*");
		// startActivity(intent);
		//
		// Intent intent = new Intent();
		// intent.setAction(android.content.Intent.ACTION_VIEW);
		// File file = new File("/sdcard/test.mp3");
		// intent.setDataAndType(Uri.fromFile(file), "audio/*");
		// startActivity(intent);

	}

	/**
	 * TODO: Right now doing it for learning purpose. But need to find out on how to show the info
	 */
	private void showInfo()
	{
		Bundle arguments = new Bundle();
		// arguments.putString(ItemDetailFragment.ARG_ITEM_ID,get);
		FolderDetailFragment fragment = new FolderDetailFragment();
		fragment.setArguments(arguments);
		fragment.show(getSupportFragmentManager(), "showdialog");
		// getSupportFragmentManager().beginTransaction().replace(android.R.id.list, fragment).commit();

	}

	private void openSettings()
	{
		Context context = getApplicationContext();
		CharSequence text = "Settings Clicked";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id)
	{
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();

		}
		else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.

			int parseInt = Integer.parseInt(id);
			CmisObject cmisObject = cmisObjectList.get(parseInt);
			if (cmisObject instanceof Document) {
				Document selectedDocument = (Document) cmisObject;
				// ContentStream contentStream = selectedDocument.getContentStream();
				FileUtils.writeAndOpenFile(selectedDocument, this, selectedDocument.getName());
			}
			else if (cmisObjectList.size() >= parseInt && cmisObject instanceof Folder) {

				cmisObjectList = itemListFragment.updateView(id);
			}
			// Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			// detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			// startActivity(detailIntent);

			// Trying to strt the same Item List fragment
			// http://developer.android.com/training/basics/fragments/communicating.html#Deliver

			// Bundle arguments = new Bundle();
			// arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			// itemListFragment.setArguments(arguments);
			// getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container,
			// itemListFragment).commit();
		}

	}
}
