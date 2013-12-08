package com.jellyfish85.cassandra.accessor.bean.query.generate.tool

import java.math.BigDecimal

/**
 *
 *
 */
class ColumnAttribute {

  var columnName: String     = _
  var dataType:   String     = _
  var dataLength: BigDecimal = _

  var columnAttributeMap: Map[String, ColumnAttribute] = _ //Map(this.columnName -> List(this.dataType, this.dataLength))

}