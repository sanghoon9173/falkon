package com.jayrave.falkon.dao

import com.jayrave.falkon.dao.delete.DeleteBuilder
import com.jayrave.falkon.dao.insert.InsertBuilder
import com.jayrave.falkon.dao.query.QueryBuilder
import com.jayrave.falkon.dao.update.UpdateBuilder
import com.jayrave.falkon.mapper.Table

/**
 * Provides builders for the following operations
 *
 *      - Insert
 *      - Update
 *      - Delete
 *      - Query
 *
 * All the provided builders have the following qualities
 *
 *      - are type-safe
 *      - prevent SQL syntax errors when used as a fluent interface
 */
interface Dao<T : Any, ID : Any> {

    /**
     * The [Table] this DAO works for
     */
    val table: Table<T, ID>

    fun insertBuilder(): InsertBuilder<T>
    fun updateBuilder(): UpdateBuilder<T>
    fun deleteBuilder(): DeleteBuilder<T>
    fun queryBuilder(): QueryBuilder<T>
}