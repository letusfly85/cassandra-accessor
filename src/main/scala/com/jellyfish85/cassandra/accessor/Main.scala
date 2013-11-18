package com.jellyfish85.cassandra.accessor

import com.jellyfish85.cassandra.accessor.manager.CassandraManager
import org.apache.cassandra.thrift.Cassandra

object Main {

  def main(args: Array[String]) {

    val manager: CassandraManager = new CassandraManager

    val client:  Cassandra.Client = manager.client

    client.set_keyspace("keyspace1")

    println(client.describe_version())
    println(client.describe_keyspace("keyspace1"))

  }

}
