package com.jellyfish85.cassandra.accessor.manager

import org.apache.cassandra.thrift.Cassandra
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocol
import org.apache.thrift.transport.TSocket
import org.apache.thrift.transport.TTransport
import org.apache.cassandra.exceptions.CassandraException
import org.apache.thrift.TException

/**
 * == CassandraManager ==
 *
 * control connection to cassandra servers
 *
 */
class CassandraManager {

  /**
   * == client ==
   *
   * @return
   */
  def client: Cassandra.Client = {
    var client:   Cassandra.Client = null

    try {
      val port:     TTransport        = new TSocket("localhost", 9160)
      val protocol: TProtocol         = new TBinaryProtocol(port)
      client = new Cassandra.Client(protocol)
      port.open()

    } catch {
      case e0: CassandraException =>
        e0.printStackTrace()

      case e1: TException =>
        e1.printStackTrace()

      case ex: Exception =>
        ex.printStackTrace()

    }

    client
  }

}
