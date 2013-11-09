package org.saurav.contentmanager.model;

public class Document extends CMISObject
{
	private Folder parentFolder;

	public Folder getParentFolder()
	{
		return parentFolder;
	}

	public void setParentFolder(Folder parentFolder)
	{
		this.parentFolder = parentFolder;
	}
	
	

}
