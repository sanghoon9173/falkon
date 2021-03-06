package com.jayrave.falkon.engine

/**
 * Exposes methods to interface with the database. Implementations must hold up these rules
 *
 *      - Only one transaction can be active per thread at a time
 *      - Transactions mustn't be shared across threads
 *      - Should throw on trying to nest transactions
 */
interface EngineCore {

    /**
     * All changes made to the database inside a transaction will be ACID (Atomic, Consistent,
     * Isolated, and Durable). Behaviour of database resources passed passed outside of
     * transaction is implementation dependent. Examples of such resources are
     * [CompiledStatement], [Source] etc.
     *
     * Implementations must make sure of the following
     *      - only one transaction can be active per thread
     *      - transactions can't span multiple threads
     *      - Should throw on trying to nest transactions
     *
     * Implementations needn't worry about nested transactions. It will taken care by the
     * [Engine] implementations.
     */
    fun <R> executeInTransaction(operation: () -> R): R

    /**
     * @return `true` if there is an active transaction in the current thread
     */
    fun isInTransaction(): Boolean

    /**
     * @param rawSql raw SQL statement
     */
    fun compileSql(rawSql: String): CompiledStatement<Unit>

    /**
     * @param rawSql raw INSERT statement
     * @return number of rows inserted [0, [Int.MAX_VALUE]]
     */
    fun compileInsert(rawSql: String): CompiledStatement<Int>

    /**
     * @param rawSql raw UPDATE statement
     * @return number of rows updated [0, [Int.MAX_VALUE]]
     */
    fun compileUpdate(rawSql: String): CompiledStatement<Int>

    /**
     * @param rawSql raw DELETE statement
     * @return number of rows deleted [0, [Int.MAX_VALUE]]
     */
    fun compileDelete(rawSql: String): CompiledStatement<Int>

    /**
     * @param rawSql raw statement that performs insert or replace
     * @return number of rows inserted or replaced [0, [Int.MAX_VALUE]]
     */
    fun compileInsertOrReplace(rawSql: String): CompiledStatement<Int>

    /**
     * @param rawSql raw SELECT statement
     * @return source that contains the data produced by the query
     */
    fun compileQuery(rawSql: String): CompiledStatement<Source>
}