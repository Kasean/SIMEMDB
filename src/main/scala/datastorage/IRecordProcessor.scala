package datastorage

import java.util.UUID

trait IRecordProcessor {
  
  def createRecord(id: UUID, content: String): UUID
  
  def updateRecord(id: UUID, newContent: String): UUID
  
  def getRecord(id: UUID): String
  
  def deleteRecord(id: UUID): UUID

}
