package org.saurav.contentmanager.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;

public class CMISModel
{
	private List<Folder> folderList =null;
	private static CMISModel instance;
	
	private CMISModel(){
		folderList = new ArrayList<Folder>();
	}
	
	public static CMISModel getInstance(){
		if(instance ==null){
			instance = new CMISModel();
		}
		return instance;
	}

	public List<Folder> getFolderList()
	{
		return folderList;
	}

	public void setFolderList(List<Folder> folderList)
	{
		this.folderList = folderList;
	}
	
}
