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

  it should "should create new record" in {
    testCreateRecord
  }

  it should "should update record" in {
    testUpdateRecord
  }

  it should "should delete record" in {
    testDeleteRecord
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

  def testCreateRecord: Assertion = {
    val testStorage= new Storage
    testStorage.createDocument(docName)

    val recordContent: String = "{name: name}"
    val id = testStorage.createRecord(docName, recordContent)

    val actualContent: String = testStorage.getRecord(docName, id)

    assert(!actualContent.isBlank && actualContent.equals(recordContent))
  }

  def testUpdateRecord: Assertion = {
    val testStorage= new Storage
    testStorage.createDocument(docName)

    val recordContent: String = "{name: name}"
    val id = testStorage.createRecord(docName, recordContent)

    val oldContent: String = testStorage.getRecord(docName, id)

    val newContent = "{some: some}"

    testStorage.updateRecord(docName, id, newContent)

    val actualContent = testStorage.getRecord(docName, id)

    assert(!actualContent.isBlank && !actualContent.equals(oldContent) && actualContent.equals(newContent))
  }

  def testDeleteRecord: Assertion = {

    val testStorage= new Storage
    testStorage.createDocument(docName)

    val recordContent: String = "{name: name}"
    val id = testStorage.createRecord(docName, recordContent)

    testStorage.deleteRecord(docName, id)

    testStorage.deleteRecord(docName, id)

    val actualContent = testStorage.getRecord(docName, id)
    assert(actualContent == null)

  }
}
