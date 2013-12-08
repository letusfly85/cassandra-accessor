package com.jellyfish85.cassandra.accessor.bean.query.generate.tool

import scala.tools.scalap.scalax.rules.scalasig.ScalaSigPrinter._tf

/**
 * == CodeInfoBean ==
 *
 *
 * @author wada shunsuke
 * @since  2013/12/08
 *
 */
trait CodeInfoBeanTrait {

  var path:              String = _
  var fileName:          String = _

  var codeId:            String = _

  var logicalCodeName:   String = _
  var physicalCodeName:  String = _

  var ticketNumber:      String = _

  var physicalTableName: String = _
  var logicalTableName : String = _

  var logicalKeyName:    String = _
  var physicalKeyName:   String = _

  var codeValue:         String = _

  var shortName:         String = _
  var displayOrder:      Int = _

  var ignoreFlg:         String = "0"

  var revision:          Long = _

  var columnAttributeList: List[ColumnAttribute] = _

  def setColumnAttributeList(list: List[ColumnAttribute]) {

    list.foreach {attr: ColumnAttribute =>
      attr.columnAttributeMap = Map(attr.columnName -> List(attr.dataType, attr.dataLength))

      this.columnAttributeList ::= attr
    }

  }
}
