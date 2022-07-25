package requestprocessor

import org.scalatest.compatible.Assertion
import org.scalatest.flatspec.AnyFlatSpec

class RequestProcessorTest extends AnyFlatSpec{

  it should "create new storage" in {
    val request = "CREATE STORAGE WITH NAME 'TEST_DB'"
    RequestProcessor.doPreliminaryAnalysis(request)

    val actual = StorageBuffer.getStorage("TEST_DB")

    assert(actual != null)
  }

}
