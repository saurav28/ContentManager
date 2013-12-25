package org.saurav.contentmanager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.chemistry.opencmis.client.api.Document;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.MimeTypeMap;

/**
 * Utility class for Android stroage related operations
 * 
 * @author I054564
 * 
 */
public class FileUtils
{
	/**
	 * Write to the file
	 * 
	 * @param content
	 * @param context
	 * @param fileName
	 */
	public static void writeAndOpenFile(final Document selectedDocument, final Context context, final String fileName)
	{

		// String FILENAME = "hello_file";
		// String string = "hello world!";
		AsyncTask<Void, Void, Void> contentReadTask = new AsyncTask<Void, Void, Void>()
		{

			@Override
			protected Void doInBackground(Void... arg0)
			{
				InputStream content = selectedDocument.getContentStream().getStream();
				FileOutputStream fos;
				try {
					fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
					// The below code is hanging the application
					fos.write(content.read());

					fos.close();
					File fileToBeOpened = context.getFileStreamPath(fileName);
					openFile(fileToBeOpened, context);
					// FileInputStream inputStream = context.openFileInput(fileName);
					// System.out.println("file input stream");
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		};

		contentReadTask.execute();

	}

	public static void readFile(Context context, String fileName)
	{
		try {
			FileInputStream inputStream = context.openFileInput(fileName);

		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void openFile(File fileTobeOpened, Context context)
	{

		MimeTypeMap map = MimeTypeMap.getSingleton();
		String ext = MimeTypeMap.getFileExtensionFromUrl(fileTobeOpened.getName());
		String type = map.getMimeTypeFromExtension(ext);

		if (type == null) {
			type = "*/*";
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.fromFile(fileTobeOpened);

		intent.setDataAndType(data, type);

		context.startActivity(intent);
	}

}
