package requestprocessor

import datastorage.Storage
import datastorage.Document
import requestprocessor.StorageBuffer

import java.util.UUID

class CreateProcessor {

  def createStorage(request: String): Storage = {
    
    val storageName = request.replace(RequestKeyWords.WITH.name() + " "
      + RequestKeyWords.NAME.name() + " '", "")
    StorageBuffer.createStorage(storageName)
  }
  
  def createDocument(request: String): Document = {
    
    val names = request.split(" " + RequestKeyWords.IN.name() + " " 
      + RequestKeyWords.STORAGE.name() + " " 
      + RequestKeyWords.WITH.name() + " " 
      + RequestKeyWords.NAME.name() + " ")
    val storageName = names(1).replace("'", "").replace("'", "")
    val storage = StorageBuffer.getStorage(storageName)
    val docName = names(0)
      .replace(RequestKeyWords.WITH.name() + " " + RequestKeyWords.NAME.name() + " ", "")
      .replace("'", "")
      .replace("'", "")
    storage.createDocument(docName)
  }

  def createRecord(request: String): UUID = {

    val startContentIndex = request.indexOf(RequestKeyWords.WITH.name() + " " + RequestKeyWords.CONTENT.name() + " '")
    val endContentIndex = request.indexOf("' " + RequestKeyWords.IN.name() + " " +  RequestKeyWords.DOCUMENT.name())
    val recordContent = request.substring(startContentIndex, endContentIndex)
      .replace(RequestKeyWords.WITH.name() + " " + RequestKeyWords.CONTENT.name() + " '", "")

    val startDocNameIndex = request.indexOf(RequestKeyWords.DOCUMENT.name + " "
      + RequestKeyWords.WITH.name() + " "
      + RequestKeyWords.NAME.name() + " '")
    val endDocNameIndex = request.indexOf("' " + RequestKeyWords.IN.name() + " " +  RequestKeyWords.STORAGE.name())
    val docName = request.substring(startDocNameIndex, endDocNameIndex)
      .replace(RequestKeyWords.DOCUMENT.name + " " 
        + RequestKeyWords.WITH.name() + " " 
        + RequestKeyWords.NAME.name() + " '", "")

    val startStorageNameIndex = request.indexOf(RequestKeyWords.IN.name() + " "
      + RequestKeyWords.STORAGE.name() + " "
      + RequestKeyWords.WITH.name() + " "
      + RequestKeyWords.NAME.name() + " '")
    val endStorageNameIndex = request.length - 1
    val storageName = request.substring(startStorageNameIndex, endStorageNameIndex)
      .replace(RequestKeyWords.IN.name() + " " 
        + RequestKeyWords.STORAGE.name() + " " 
        + RequestKeyWords.WITH.name() + " " 
        + RequestKeyWords.NAME.name() + " '", "")

    StorageBuffer.getStorage(storageName)
      .getDocument(docName)
      .createRecord(UUID.randomUUID(), recordContent)
  }
}
