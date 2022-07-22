package datastorage

trait ICrudDocument {
  
  def createDocument(documentName: String, documentContent: String): Document
  
  def readDocument(documentName: String): Document
  
  def updateDocument(documentName: String, newDocumentContent: String): Document
  
  def deleteDocument(documentName: String): String

}
