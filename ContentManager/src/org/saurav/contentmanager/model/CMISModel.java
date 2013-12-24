package org.saurav.contentmanager.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;

public class CMISModel
{
	private List<CmisObject> cmisObjectList = null;
	private static CMISModel instance;

	private CMISModel()
	{
		cmisObjectList = new ArrayList<CmisObject>();
	}

	public static CMISModel getInstance()
	{
		if (instance == null) {
			instance = new CMISModel();
		}
		return instance;
	}

	public List<CmisObject> getFolderList()
	{
		return cmisObjectList;
	}

	public void setFolderList(List<CmisObject> folderList)
	{
		this.cmisObjectList = folderList;
	}

}
