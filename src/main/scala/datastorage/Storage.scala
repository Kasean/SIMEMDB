package datastorage

import java.util.UUID
import scala.collection.mutable
import scala.collection.mutable.HashMap

class Storage extends IDocumentProcessor {

  private final val documentBucket = mutable.HashMap[String, Document]()

  override def createDocument(documentName: String): Document = {
    if (documentBucket.keysIterator.contains(documentName)) {
      printDocExistsException(documentName)
      null
    } else {
      val newDocument: Document = new Document()
      documentBucket.put(documentName, newDocument)
      newDocument
    }
  }

  override def getDocument(documentName: String): Document = {
    if (documentBucket.keysIterator.contains(documentName)) {
      documentBucket(documentName)
    } else {
      null
    }
  }

  override def renameDocument(documentOldName: String, documentNewName: String): Document = {
    if (!documentBucket.keysIterator.contains(documentOldName)) {
      printDocNotExistsException(documentOldName)
      null
    } else {
      val updatedDocument: Document = documentBucket(documentOldName)
      documentBucket.put(documentNewName, updatedDocument)
      documentBucket.remove(documentOldName)
      updatedDocument
    }
  }

  override def deleteDocument(documentName: String): String = {
    if (!documentBucket.keysIterator.contains(documentName)) {
      printDocNotExistsException(documentName)
      null
    } else {
      documentBucket.remove(documentName)
      documentName
    }
  }

  private def printDocExistsException(docName: String): Unit = println("Document with name: " + 
    docName + " already exists")
  private def printDocNotExistsException(docName: String): Unit = println("Document with name: " +
    docName + " not exists")

}
