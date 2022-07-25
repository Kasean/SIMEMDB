package requestprocessor

import datastorage.Storage

class CreateProcessor {
  def createStorage(request: String): Storage =
    StorageBuffer.createStorage(name = request.replace("WITH NAME '", "")
      .replace("'", ""))
}
