package org.saurav.contentmanager.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
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

public class SessionLoader extends AsyncTask<String, Void, Session>
{

	private Session session;

	public SessionLoader()
	{

	}

	public Session getSessionObject(String path, String username, String pass)
	{

		execute(path, username, pass);
		try {
			session = get();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		return session;
	}

	@Override
	protected Session doInBackground(String... params)
	{
		if (session != null) {
			return session;
		}
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// connection settings
		parameter.put(SessionParameter.ATOMPUB_URL, params[0]);
		// user credentials
		parameter.put(SessionParameter.USER, params[1]);
		parameter.put(SessionParameter.PASSWORD, params[2]);
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		parameter.put(SessionParameter.AUTH_HTTP_BASIC, "true");
		parameter.put(SessionParameter.COOKIES, "true");
		try {
			Repository repository = factory.getRepositories(parameter).get(0);
			session = repository.createSession();
			CMISModel model = CMISModel.getInstance();
			if (session != null) {
				System.out.println("Welcome buddy");
				Folder root = session.getRootFolder();

				ItemIterable<CmisObject> children = root.getChildren();

				for (CmisObject o : children) {
					System.out.println(o.getName());
					if (o instanceof Document || o instanceof Folder) {
						model.getFolderList().add(o);
					}

				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

}
