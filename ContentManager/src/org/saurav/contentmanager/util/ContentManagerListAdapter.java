package org.saurav.contentmanager.util;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.saurav.contentmanager.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Acting as the array adapter for the Content Manager application
 * @author I054564
 *
 */
public class ContentManagerListAdapter extends BaseAdapter
{
	
	private Object[] objectArray;
	private boolean[] checked;
	private CheckBox[] checkBoxArray;
	private final Context context;
	private int checkedPosition;
	
	public int getCheckedPosition()
	{
		return checkedPosition;
	}

	public ContentManagerListAdapter(Context context, int resource, int showsometext, String[] objects)
	{
		
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public ContentManagerListAdapter(Context context, int resource, int text1, Object[] array)
	{
		
		objectArray =array;
		this.context = context;
		checked = new boolean[objectArray.length];
	}

	public Object[] getObjectArray()
	{
		return objectArray;
	}

	

	public boolean[] getChecked()
	{
		return checked;
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		View rowView = convertView;
		if(rowView == null){
		 LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		}
		
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
		  checkBox.setOnCheckedChangeListener(null);			
		  checkBox.setChecked(checked[position]);
		  checkBox.setTag(Integer.valueOf(position));
		  checkBox.setOnCheckedChangeListener(checkedListener);
		  
		return rowView;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return objectArray.length;
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	OnCheckedChangeListener checkedListener = new OnCheckedChangeListener()
	{
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		{
			checked[(Integer)buttonView.getTag()] = isChecked;
			if(isChecked){
				checkedPosition = (Integer)buttonView.getTag();
			}
			
		}
	};
}
