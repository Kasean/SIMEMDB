package datastorage

import java.util.UUID
import scala.::
import scala.collection.mutable

class Document {

  //key - record identifier
  //value - record
  private final val documentContent = mutable.HashMap[UUID, String]()

  def addRecordToDocument(id: UUID, value: String): Option[String] = documentContent.put(id, value)

  def getRecordsById(id: UUID): String = documentContent(id)
  
  def getAllRecords: mutable.Map[UUID, String] = documentContent
  
}
