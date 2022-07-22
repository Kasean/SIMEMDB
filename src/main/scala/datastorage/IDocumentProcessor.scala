package datastorage

import java.util.UUID

trait IDocumentProcessor {

  def createDocument(documentName: String): Document

  def getDocument(documentName: String): Document

  def renameDocument(documentOldName: String, documentNewName: String): Document

  def deleteDocument(documentName: String): String

}
