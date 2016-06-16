package com.jayrave.falkon.jdbc.h2

import com.jayrave.falkon.engine.Source
import java.sql.ResultSet

internal class ResultSetBackedSource(private val resultSet: ResultSet) : Source {

    override val position: Int
        get() = resultSet.row

    override fun move(offset: Int): Boolean = resultSet.relative(offset)
    override fun moveToPosition(position: Int): Boolean = resultSet.absolute(position)
    override fun moveToFirst(): Boolean = resultSet.first()
    override fun moveToLast(): Boolean = resultSet.last()
    override fun moveToNext(): Boolean = resultSet.next()
    override fun moveToPrevious(): Boolean = resultSet.previous()
    override fun getColumnIndex(columnName: String): Int = resultSet.findColumn(columnName)
    override fun getShort(columnIndex: Int): Short = resultSet.getShort(columnIndex)
    override fun getInt(columnIndex: Int): Int = resultSet.getInt(columnIndex)
    override fun getLong(columnIndex: Int): Long = resultSet.getLong(columnIndex)
    override fun getFloat(columnIndex: Int): Float = resultSet.getFloat(columnIndex)
    override fun getDouble(columnIndex: Int): Double = resultSet.getDouble(columnIndex)
    override fun getString(columnIndex: Int): String = resultSet.getString(columnIndex)

    override fun getBlob(columnIndex: Int): ByteArray {
        val blob = resultSet.getBlob(columnIndex)
        return blob.getBytes(1, blob.length().toInt())
    }

    override fun isNull(columnIndex: Int): Boolean {
        resultSet.getObject(columnIndex)
        return resultSet.wasNull()
    }
}