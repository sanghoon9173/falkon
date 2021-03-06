package com.jayrave.falkon.sqlBuilders

import com.jayrave.falkon.sqlBuilders.lib.WhereSection

interface UpdateSqlBuilder {

    /**
     * Builds a db specific statement similar to `UPDATE ...`
     *
     * @param [tableName] the table to update
     * @param [columns] A list of columns for which values will be bound later
     * @param [whereSections] A list of sections, applied in iteration order used to build
     * the optional SQL WHERE clause. Passing null denotes no WHERE in the built SQL
     */
    fun build(
            tableName: String,
            columns: Iterable<String>,
            whereSections: Iterable<WhereSection>?
    ): String
}