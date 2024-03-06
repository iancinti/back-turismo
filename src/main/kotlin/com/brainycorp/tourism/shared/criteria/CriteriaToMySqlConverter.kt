package com.brainycorp.tourism.shared.criteria

class CriteriaToMySqlConverter {

    companion object {
        private var op = " AND "
        fun convert(fieldsToSelect: Map<String, String>, tableName: String, criteria: Criteria): String {
            var query: String = "SELECT ${fieldsToSelect.map { it.key + " AS " + it.value }.joinToString( ", ") } FROM $tableName"

            criteria.joins.forEach { join ->
                query += " ${join.joinType.sql} ${join.table} ON ${join.on}"
            }

            println(query)

            if (criteria.hasFilters()) {
                query += " WHERE "
                val whereQueryOR = criteria.filtersOR.filters.map { filter: Filter -> generateWhereQuery(filter) }
                val whereQueryAND = criteria.filtersAND.filters.map { filter: Filter -> generateWhereQuery(filter) }
                query += if (whereQueryAND.isEmpty()) whereQueryOR.joinToString(" OR ") else if(whereQueryOR.isNotEmpty()) "(" + whereQueryOR.joinToString(" OR ") + ")" else ""
                query += if (whereQueryOR.isEmpty() || whereQueryAND.isEmpty()) whereQueryAND.joinToString(" AND ") else " AND " + whereQueryAND.joinToString(" AND ")

            }

            if (criteria.hasOrder()){
                query += " ORDER BY ${criteria.order.orderBy.param} ${criteria.order.orderType.orderTypes.value}"
            }
            println(query)

            return "$query;"
        }

        private fun generateWhereQuery(filter: Filter): String {
            if (filter.filterOperator.isContains()) {
                op = " OR "
                return "${filter.filterField.field} LIKE '%${filter.filterValue.value}%'";
            }

            if (filter.filterOperator.isNotContains()) {
                op = " OR "
                return "${filter.filterField.field} NOT LIKE '%${filter.filterValue.value}%'";
            }
            op = " AND "

            if (filter.filterValue.value.isEmpty()){
                return "${filter.filterField.field} IS NULL"
            }

            return "${filter.filterField.field} ${filter.filterOperator.operator.value} '${filter.filterValue.value}'"
        }
    }

}