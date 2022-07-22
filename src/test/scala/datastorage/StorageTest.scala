package datastorage

import org.scalatest.compatible.Assertion
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class StorageTest extends AnyFlatSpec {

  val docName: String = "test doc name"
  val recordContent: String = "test content"

  it should "should create new Document with name " + docName + "and content" + recordContent in {
    testCreateDocument
  }

  it should "should read doc correctly" in {
    testReadDocument
  }

  it should "should update doc correctly" in {
    testUpdateDocument
  }

  it should "should delete doc correctly" in {
    testDeleteDocument
  }

  def testCreateDocument: Assertion = {
    val testStorage: Storage = new Storage
    testStorage.createDocument(docName)
    val doc: Document = testStorage.readDocument(docName)

    assert(doc != null)
  }

  def testReadDocument: Assertion = {
    val testStorage: Storage = new Storage
    val expectedDoc: Document = testStorage.createDocument(docName)
    val actualDoc: Document = testStorage.readDocument(docName)

    assert(actualDoc == expectedDoc)
  }

  def testUpdateDocument: Assertion = {
    val testStorage: Storage = new Storage
    testStorage.createDocument(docName)

    val recordId: UUID = UUID.randomUUID()
    testStorage.updateDocument(recordId, docName, recordContent)

    val actualDoc: Document = testStorage.readDocument(docName)

    assert(actualDoc != null &&
      actualDoc.getRecordsById(recordId) != null
      && actualDoc.getRecordsById(recordId).equals(recordContent))
  }

  def testDeleteDocument: Assertion = {
    val testStorage: Storage = new Storage
    testStorage.createDocument(docName)

    val docCreated: Boolean = testStorage.readDocument(docName) != null

    val removedDocName: String = testStorage.deleteDocument(docName)

    val actualDoc: Document = testStorage.readDocument(removedDocName)

    assert(actualDoc == null && docCreated)
  }

}
