package datastorage

import java.util.UUID

trait ICrudDocument {

  def createDocument(documentName: String): Document

  def readDocument(documentName: String): Document

  def updateDocument(recordId: UUID, documentName: String, recordContent: String): Document

  def deleteDocument(documentName: String): String

}
