package datastorage

import java.util.UUID
import scala.collection.mutable

class Document {

  //key - record identifier
  //value - record
  private final val documentContent = mutable.HashMap[UUID, String]()
  
  def addRecordToDocument(id: UUID, value: String): Option[String] = documentContent.put(id, value)

}
