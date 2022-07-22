package datastorage

import java.util.UUID
import scala.collection.mutable
import scala.collection.mutable.HashMap

class Storage extends ICrudDocument {

  private final val documentBucket = mutable.HashMap[String, Document]()

  override def createDocument(documentName: String, documentContent: String): Document = {
    val oldDocument: Document = documentBucket(documentName)
    if (oldDocument != null) {
      printDocExistsException(documentName)
      null
    } else {
      val newDocument: Document = new Document()
      newDocument.addRecordToDocument(UUID.randomUUID(), documentContent)
      documentBucket.put(documentName, newDocument)
      newDocument
    }
  }

  override def readDocument(documentName: String): Document = ???

  override def updateDocument(documentName: String, newDocumentContent: String): Document = ???

  override def deleteDocument(documentName: String): String = ???

  private def printDocExistsException(docName: String): Unit = println("Document with name: " + docName + " already exists")

}
