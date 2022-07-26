package requestprocessor

object RequestProcessor {

  private val createProcessor = new CreateProcessor


  def doPreliminaryAnalysis(request: String): String = {
    val arr = request.split(" ")
    val requestType = RequestType.valueOf(arr(0))
    val processedEntity = KeyEntity.valueOf(arr(1))

    if (requestType.equals(RequestType.CREATE)) {
      processedEntity match {
        case KeyEntity.STORAGE =>
          val updatedRequest = request.replace("CREATE STORAGE ", "")
          createProcessor.createStorage(updatedRequest).toString
        case KeyEntity.DOCUMENT =>
          val updatedRequest = request.replace("CREATE DOCUMENT ", "")
          createProcessor.createDocument(updatedRequest).toString
        case KeyEntity.RECORD =>
          val updatedRequest = request.replace("CREATE RECORD ", "")
          createProcessor.createRecord(updatedRequest).toString
        case null => ""  
      }
    } else null

  }



}
