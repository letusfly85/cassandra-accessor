package com.jellyfish85.cassandra.accessor

import com.jellyfish85.cassandra.accessor.manager.CassandraManager
import org.apache.cassandra.thrift._
import com.jellyfish85.cassandra.accessor.dao.SampleDao

object Main {

  def main(args: Array[String]) {

    val manager: CassandraManager = new CassandraManager

    val client:  Cassandra.Client = manager.client

    client.set_keyspace("keyspace1")

    println(client.describe_version())
    println(client.describe_keyspace("keyspace1"))

    val dao: SampleDao = new SampleDao
    dao.insert(client, "0001", "NAME", "JELLYFISH85")
    dao.insert(client, "0002", "NAME", "LETUSFLY85")

    dao.insert(client, "id", "0001", "")
    dao.insert(client, "id", "0002", "")

    val predicate: SlicePredicate = new SlicePredicate()
    val sliceRange: SliceRange    = new SliceRange()

    sliceRange.setStart("id".getBytes())
    sliceRange.setFinish("id".getBytes())

    predicate.setSlice_range(sliceRange)

    val keyRange: KeyRange = new KeyRange()
    keyRange.setStart_key("".getBytes())
    keyRange.setEnd_key("".getBytes())

    val COLUMN_FAMILY: String = "myColumnFammly"
    val parent: ColumnParent = new ColumnParent( COLUMN_FAMILY )
    val list: java.util.List[KeySlice] = client.get_range_slices(parent, predicate, keyRange, ConsistencyLevel.ONE)

    for (i <- 0 until list.size()) {
      val item = list.get(i)

      println(new String(item.getKey, "UTF8") + item.columns.size().toString)

      val columns = item.columns
      for (k <- 0 until columns.size) {
        val col = columns.get(k)
        val sc  = col.super_column
        println("\t" + sc.getName)

        val _columns = sc.columns
        for (j <- 0 until _columns.size()) {
          println("\t" + _columns.get(j).getName)
        }

        println("aaa")
        println(new String((columns.get(k).asInstanceOf[Column]).getName, "UTF8"))
      }
    }


  }

}
