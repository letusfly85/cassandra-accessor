package com.jellyfish85.cassandra.accessor.dao

import java.nio.ByteBuffer

import org.apache.cassandra.thrift.{ConsistencyLevel, Cassandra, Column, ColumnParent, TimedOutException}
import org.apache.cassandra.exceptions.{InvalidRequestException, UnavailableException}
import org.apache.thrift.TException

/**
 * == SampleDao ==
 *
 *
 * @example
 *          create column family myColumnFammly
 *          with key_validation_class = 'UTF8Type'
 *          and comparator = 'UTF8Type'
 *          and default_validation_class = 'UTF8Type';
 * 
 * @author wada shunsuke
 * @since  2013/11/23
 *         
 */
class SampleDao {

  val COLUMN_FAMILY: String = "myColumnFammly"

  @throws(classOf[InvalidRequestException])
  @throws(classOf[UnavailableException])
  @throws(classOf[TimedOutException])
  @throws(classOf[TException])
  def insert(client: Cassandra.Client, rowKey: String, name: String, value: String) {
    val clock: Long = System.nanoTime()

    val column: Column = new Column()
    column.setName( name.getBytes("UTF8") )
    column.setValue( value.getBytes("UTF8") )
    column.setTimestamp( clock )

    val parent: ColumnParent = new ColumnParent( COLUMN_FAMILY )

    client.insert(
      ByteBuffer.wrap(rowKey.getBytes()), parent, column, ConsistencyLevel.ONE)
  }

}