package datastorage

import java.util.UUID
import scala.::
import scala.collection.mutable

class Document extends IRecordProcessor {

  //key - record identifier
  //value - record
  private final val recordBucket = mutable.HashMap[UUID, String]()

  def createRecord(id: UUID, content: String): UUID = {
    recordBucket.put(id, content)
    id

  }

  def updateRecord(id: UUID, newContent: String): UUID = {
    recordBucket.put(id, newContent)
    id
  }

  def getRecord(id: UUID): String = {
    recordBucket(id)
  }

  def deleteRecord(id: UUID): UUID = {
    recordBucket.remove(id)
    id
  }
}
