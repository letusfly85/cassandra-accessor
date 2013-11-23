package com.jellyfish85.cassandra.accessor

import com.jellyfish85.cassandra.accessor.manager.CassandraManager
import org.apache.cassandra.thrift.Cassandra
import com.jellyfish85.cassandra.accessor.dao.SampleDao

object Main {

  def main(args: Array[String]) {

    val manager: CassandraManager = new CassandraManager

    val client:  Cassandra.Client = manager.client

    client.set_keyspace("keyspace1")

    println(client.describe_version())
    println(client.describe_keyspace("keyspace1"))

    val dao: SampleDao = new SampleDao
    dao.insert(client, "abc", "def", "aaz")

  }

}
