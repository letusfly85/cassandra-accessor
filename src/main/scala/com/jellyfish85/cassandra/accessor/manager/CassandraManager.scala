package com.jellyfish85.cassandra.accessor.manager

import org.apache.cassandra.thrift.Cassandra
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocol
import org.apache.thrift.transport.{TTransportException, TFramedTransport, TSocket, TTransport}
import org.apache.cassandra.exceptions.InvalidRequestException
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
  @throws(classOf[TTransportException])
  @throws(classOf[TException])
  @throws(classOf[InvalidRequestException])
  def client: Cassandra.Client = {
    var client:   Cassandra.Client = null

    try {
      val port:     TTransport        = new TSocket("localhost", 9160)
      val ttr:      TFramedTransport  = new TFramedTransport(port)
      val protocol: TProtocol         = new TBinaryProtocol(ttr)

      client = new Cassandra.Client(protocol)
      port.open()

    } catch {
      case ex: Exception =>
        ex.printStackTrace()

    }

    client
  }

}
