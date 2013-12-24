package org.saurav.contentmanager;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.saurav.contentmanager.model.CMISModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Item detail screen. This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment
{
	/**
	 * The fragment argument representing the item ID that this fragment represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	// private DummyContent.DummyItem mItem;

	private CmisObject cmisObject;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon screen orientation
	 * changes).
	 */
	public ItemDetailFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			cmisObject = CMISModel.getInstance().getFolderList()
					.get(Integer.parseInt((getArguments().getString(ARG_ITEM_ID))));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

		// Show the dummy content as text in a TextView.
		if (cmisObject != null) {
			((TextView) rootView.findViewById(R.id.item_detail)).setText(cmisObject.toString());
		}

		return rootView;
	}
}
