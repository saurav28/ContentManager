package org.saurav.contentmanager.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Util methods for Toast related functionalities
 * @author I054564
 *
 */
public class ToastUtils
{

	public static void showText(Context context, CharSequence text){
		
//		Context context = getApplicationContext();
//		CharSequence text = "Selected object is Folder, hence can not be opened";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
