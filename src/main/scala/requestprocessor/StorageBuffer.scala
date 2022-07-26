package requestprocessor

import scala.collection.mutable
import datastorage.Storage

object StorageBuffer {

  private val storageCluster = mutable.HashMap[String, Storage]()

  def createStorage(name: String): Storage = {
    if (storageCluster.keysIterator.contains(name)) {
      null
    } else {
      storageCluster.put(name, new Storage)
      storageCluster(name)
    }
  }

  def getStorage(name: String): Storage = {
    if (storageCluster.keysIterator.contains(name)) {
      storageCluster(name)
    } else null
  }

}
