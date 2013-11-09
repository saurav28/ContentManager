package org.saurav.contentmanager.util;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.saurav.contentmanager.R;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Acting as the array adapter for the Content Manager application
 * @author I054564
 *
 */
public class ContentManagerListAdapter extends ArrayAdapter<Object>
{
	
	private Object[] objectArray;
	private final Context context;
	
	public ContentManagerListAdapter(Context context, int resource, int showsometext, String[] objects)
	{
		super(context, resource,showsometext,objects);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public ContentManagerListAdapter(Context context, int resource, int text1, Object[] array)
	{
		super(context, resource,text1,array);
		objectArray =array;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		
		 LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		
		
//		TextView view = (TextView) super.getView(position, convertView, parent);
//		view.setText(((CmisObject)objectArray[position]).getName());
		
		
		
		 TextView textView = (TextView) rowView.findViewById(R.id.label);
		    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		    textView.setText(((CmisObject)objectArray[position]).getName());
		    // Change the icon for Windows and iPhone
		    CmisObject cmisObject = ((CmisObject)objectArray[position]);
		    if (cmisObject instanceof Folder) {
		      imageView.setImageResource(R.drawable.folder);
		    } else {
		      imageView.setImageResource(R.drawable.file);
		    }
		  CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
		  
		return rowView;
	}

	
}
