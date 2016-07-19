package com.jayrave.falkon.dao.update

import com.jayrave.falkon.dao.where.WhereBuilder
import com.jayrave.falkon.engine.CompiledUpdate
import com.jayrave.falkon.mapper.Column
import com.jayrave.falkon.mapper.Table

interface UpdateBuilder<T : Any> {

    val table: Table<T, *>

    /**
     * Sets the value a column should have after update. This method can be called multiple times
     * to set values for multiple columns. Behaviour on calling this method again for a column
     * that has already been set is implementation dependent
     */
    fun <C> set(column: Column<T, C>, value: C): AdderOrEnder<T>
}


interface AdderOrEnder<T : Any> {

    /**
     * @see [set]
     */
    fun <C> set(column: Column<T, C>, value: C): AdderOrEnder<T>

    /**
     * Use to build the WHERE clause of UPDATE SQL statement. Each call would erase the
     * previously configured WHERE clause and start creating a new one
     */
    fun where(): WhereBuilder<T, PredicateAdderOrEnder<T>>

    /**
     * @return [Update] for this [UpdateBuilder]
     */
    fun build(): Update

    /**
     * @return [CompiledUpdate] for this [UpdateBuilder]
     */
    fun compile(): CompiledUpdate
}


/**
 * To access some [AdderOrEnder] methods conveniently after chaining calls on [WhereBuilder]
 */
interface PredicateAdderOrEnder<T : Any> :
        com.jayrave.falkon.dao.where.AdderOrEnder<T, PredicateAdderOrEnder<T>> {

    fun build(): Update
    fun compile(): CompiledUpdate
}