package org.saurav.contentmanager.model;

import java.util.List;

public class Folder extends CMISObject
{
	private List<Document> documentList;

	public List<Document> getDocumentList()
	{
		return documentList;
	}

	public void setDocumentList(List<Document> documentList)
	{
		this.documentList = documentList;
	}

}
