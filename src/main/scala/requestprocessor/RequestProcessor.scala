package requestprocessor

object RequestProcessor {

  private val storageBuffer = StorageBuffer

  private val createProcessor = new CreateProcessor


  def doPreliminaryAnalysis(request: String): Any = {
    val arr = request.split(" ")
    val requestType = RequestType.valueOf(arr(0))
    val processedEntity = KeyEntity.valueOf(arr(1))

    requestType match
      case RequestType.CREATE =>
        processedEntity match
          case KeyEntity.STORAGE =>
            val updatedRequest = request.replace("CREATE STORAGE ", "")
            createProcessor.createStorage(updatedRequest)


  }



}
