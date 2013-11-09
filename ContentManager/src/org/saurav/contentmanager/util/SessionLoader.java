package org.saurav.contentmanager.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.saurav.contentmanager.model.CMISModel;

import android.os.AsyncTask;
import android.util.Log;

public class SessionLoader extends AsyncTask<String, Void, Session> {

	private Session session;
	private static SessionLoader dataLoader = null;

	public SessionLoader() {
		
	}
	
	public Session getSessionObject(String path, String username, String pass) {
		
			execute(path,username,pass);
			try {
				session = get();
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return session;
	}
	
	
	
	@Override
	protected Session doInBackground(String... params) {
		// TODO Auto-generated method stub
		if (session != null) {
			return session;
		}
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// connection settings
		parameter.put(SessionParameter.ATOMPUB_URL,
				params[0]);
		// user credentials
		parameter.put(SessionParameter.USER, params[1]);
		parameter.put(SessionParameter.PASSWORD, params[2]);

		// parameter.put(SessionParameter.REPOSITORY_ID,
		// "9eebbd0d-1bf4-4f4d-b905-ca1318f49404");

		parameter.put(SessionParameter.BINDING_TYPE,
				BindingType.ATOMPUB.value());
		parameter.put(SessionParameter.AUTH_HTTP_BASIC, "true");
		parameter.put(SessionParameter.COOKIES, "true");
		try {
		Repository repository = factory.getRepositories(parameter).get(0);
		session = repository.createSession();
		CMISModel model = CMISModel.getInstance();
		if(session!=null){
			System.out.println("Welcome buddy");
			Folder root = session.getRootFolder();
			

			ItemIterable<CmisObject> children = root.getChildren();

			for (CmisObject o : children) {
			  System.out.println(o.getName());
			  Folder folderTobeAdded = (Folder)o;
			  model.getFolderList().add(folderTobeAdded);
			 
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
			//Log.e("SessionLoader", e.getLocalizedMessage());
		}
		// create session
		
		return session;
	}

}
