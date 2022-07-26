package requestprocessor

import datastorage.Storage
import datastorage.Document
import requestprocessor.StorageBuffer

import java.util.UUID

class CreateProcessor {

  def createStorage(request: String): Storage =

    val name = request.replace("WITH NAME '", "")
      .replace("'", "")
    StorageBuffer.createStorage(name)

  def createDocument(request: String): Document = {

    val names = request.split(" IN STORAGE WITH NAME ")
    val storageName = names(1).replace("'", "").replace("'", "")
    val storage = StorageBuffer.getStorage(storageName)
    val docName = names(0).replace("WITH NAME ", "").replace("'", "").replace("'", "")
    storage.createDocument(docName)
  }

  def createRecord(request: String): UUID = {
    
    val startContentIndex = request.indexOf("WITH CONTENT '")
    val endContentIndex = request.indexOf("' IN DOCUMENT")
    val recordContent = request.substring(startContentIndex, endContentIndex)
      .replace(RequestKeyWords.WITH.name() + " " + RequestKeyWords.CONTENT.name() + " '", "")

    val startDocNameIndex = request.indexOf("DOCUMENT WITH NAME '")
    val endDocNameIndex = request.indexOf("' IN STORAGE")
    val docName = request.substring(startDocNameIndex, endDocNameIndex)
      .replace(RequestKeyWords.DOCUMENT.name + " " + RequestKeyWords.WITH.name() + " " + RequestKeyWords.NAME.name() + " '", "")

    val startStorageNameIndex = request.indexOf("IN STORAGE WITH NAME '")
    val endStorageNameIndex = request.length - 1
    val storageName = request.substring(startStorageNameIndex, endStorageNameIndex)
      .replace(RequestKeyWords.IN.name() + " " + RequestKeyWords.STORAGE.name() + " " + RequestKeyWords.WITH.name() + " " + RequestKeyWords.NAME.name() + " '", "")

    StorageBuffer.getStorage(storageName)
      .getDocument(docName)
      .createRecord(UUID.randomUUID(), recordContent)
  }
}
