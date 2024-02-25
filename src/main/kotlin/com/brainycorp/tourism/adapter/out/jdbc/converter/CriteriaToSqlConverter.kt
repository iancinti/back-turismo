package com.brainycorp.tourism.adapter.out.jdbc.converter

import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Filter

class CriteriaToSqlConverter {

    companion object {
        fun convert(fieldsToSelect: List<String>, tableName: String, criteria: Criteria): String {
            var query: String = "SELECT ${fieldsToSelect.joinToString( ", ") } FROM $tableName"
            println(query)

            if (criteria.hasFilters()) {
                query += " WHERE "
                var op = " AND "
                val whereQuery = criteria.filters.filters.map { filter: Filter ->
                    if (filter.filterOperator.operator.value == "LIKE"){
                        op = " OR "
                        "${filter.filterField.field} ${filter.filterOperator.operator.value} '%${filter.filterValue.value}%'"
                    }else{
                        op = " AND "
                        "${filter.filterField.field} ${filter.filterOperator.operator.value} '${filter.filterValue.value}'"
                    }
                }
                query += whereQuery.joinToString(op)
            }

            if (criteria.hasOrder()){
                query += " ORDER BY ${criteria.order.orderBy.param} ${criteria.order.orderType.orderTypes.value}"
            }
            println(query)

            return "$query;"
        }

    }

}