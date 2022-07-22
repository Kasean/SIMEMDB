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
    val doc: Document = testStorage.getDocument(docName)

    assert(doc != null)
  }

  def testReadDocument: Assertion = {
    val testStorage: Storage = new Storage
    val expectedDoc: Document = testStorage.createDocument(docName)
    val actualDoc: Document = testStorage.getDocument(docName)

    assert(actualDoc == expectedDoc)
  }

  def testUpdateDocument: Assertion = {
    val testStorage: Storage = new Storage
    testStorage.createDocument(docName)

    val docCreated: Boolean = testStorage.getDocument(docName) != null

    testStorage.renameDocument(docName, "new Name")

    val docRenamed = testStorage.getDocument(docName) == null
      && testStorage.getDocument("new Name") != null

    assert(docRenamed && docCreated)

  }

  def testDeleteDocument: Assertion = {
    val testStorage: Storage = new Storage
    testStorage.createDocument(docName)

    val docCreated: Boolean = testStorage.getDocument(docName) != null

    val removedDocName: String = testStorage.deleteDocument(docName)

    val actualDoc: Document = testStorage.getDocument(removedDocName)

    assert(actualDoc == null && docCreated)
  }

}
