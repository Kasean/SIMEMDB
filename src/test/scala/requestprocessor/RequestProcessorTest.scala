package requestprocessor

import org.scalatest.compatible.Assertion
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class RequestProcessorTest extends AnyFlatSpec{
//
  it should "create new storage" in {
    val request = "CREATE STORAGE WITH NAME 'TEST_DB'"
    RequestProcessor.doPreliminaryAnalysis(request)

    val actual = StorageBuffer.getStorage("TEST_DB")

    assert(actual != null)
  }

  it should "create new document in storage" in {
    val requestForCreateStorage = "CREATE STORAGE WITH NAME 'TEST_DB_1'"
    RequestProcessor.doPreliminaryAnalysis(requestForCreateStorage)

    val requestForCreateDocument = "CREATE DOCUMENT WITH NAME 'DOC_NAME' IN STORAGE WITH NAME 'TEST_DB_1'"
    RequestProcessor.doPreliminaryAnalysis(requestForCreateDocument)

    val actualStorage = StorageBuffer.getStorage("TEST_DB_1")

    val actualDoc = actualStorage.getDocument("DOC_NAME")

    assert(actualDoc != null)

  }

  it should "create new record in doc in storage" in {
    val requestForCreateStorage = "CREATE STORAGE WITH NAME 'TEST_DB_2'"
    RequestProcessor.doPreliminaryAnalysis(requestForCreateStorage)
    val actualStorage = StorageBuffer.getStorage("TEST_DB_2")

    val requestForCreateDocument = "CREATE DOCUMENT WITH NAME 'DOC_NAME' IN STORAGE WITH NAME 'TEST_DB_2'"
    RequestProcessor.doPreliminaryAnalysis(requestForCreateDocument)
    val actualDoc = actualStorage.getDocument("DOC_NAME")

    val requestForCreateRecord = "CREATE RECORD WITH CONTENT '{\"SOME\" : \"SOME\"}' IN DOCUMENT WITH NAME 'DOC_NAME' IN STORAGE WITH NAME 'TEST_DB_2'"
    val id = RequestProcessor.doPreliminaryAnalysis(requestForCreateRecord)
    val actualRecord = actualDoc.getRecord(UUID.fromString(id))

    assert(actualRecord != null && actualRecord.equals("{\"SOME\" : \"SOME\"}"))
  }
}
