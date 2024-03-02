package com.brainycorp.tourism.domain

data class Criteria(val filtersOR: Filters, val filtersAND: Filters, val order: Order, val joins: List<Join> = emptyList()){

    companion object {
        fun fromPrimitives(filtersOR: List<FiltersPrimitives>, filtersAND: List<FiltersPrimitives>, orderBy: String?, orderType: String?, joins: List<Join> = emptyList()): Criteria{
            return Criteria(Filters.fromPrimitives(filtersOR), Filters.fromPrimitives(filtersAND), Order.fromPrimitives(orderBy, orderType), joins)
        }
    }

    fun hasOrder(): Boolean {
        return !order.isNone()
    }

    fun hasFilters(): Boolean {
        return filtersOR.isNotEmpty() || filtersAND.isNotEmpty()
    }
}

data class Filters(val filters: List<Filter>){

    companion object {
        fun fromPrimitives(filters: List<FiltersPrimitives>): Filters {
            return Filters(
                filters.map { filter -> Filter.fromPrimitives(filter.field, filter.operator, filter.value) }
            )
        }
    }

    fun toPrimitives(): List<FiltersPrimitives> {
        return filters.map { filter: Filter -> filter.toPrimitives() }
    }

    fun isEmpty(): Boolean {
        return filters.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return filters.isNotEmpty()
    }


}

data class FiltersPrimitives(val field: String, val operator: String, val value: String)

data class Filter(val filterField: FilterField, val filterOperator: FilterOperator, val filterValue: FilterValue){

    companion object {
        fun fromPrimitives(field: String, operator: String, value: String): Filter {
            return Filter(
                FilterField(field),
                FilterOperator(Operator.valueOf(operator)),
                FilterValue(value)
            )
        }
    }

    fun toPrimitives(): FiltersPrimitives {
        return FiltersPrimitives(filterField.field, filterOperator.operator.value, filterValue.value)
    }

}


data class FilterField(val field: String)
data class FilterOperator(val operator: Operator){
    fun isContains(): Boolean {
        return operator.value == Operator.CONTAINS.value
    }

    fun isNotContains(): Boolean {
        return operator.value == Operator.NOT_CONTAINS.value
    }
}
data class FilterValue(val value: String)

data class Order(val orderBy: OrderBy, val orderType: OrderType) {

    companion object {
        fun none(): Order {
            return Order(OrderBy(""), OrderType(OrderTypes.NONE))
        }

        fun fromPrimitives(orderBy: String?, orderType: String?): Order {
            return if(orderBy != null) Order(OrderBy(orderBy), OrderType(OrderTypes.valueOf(orderType!!))) else Order.none()
        }
    }

    fun isNone(): Boolean {
        return orderType.isNone()
    }
}

data class OrderBy(val param: String)

data class OrderType(val orderTypes: OrderTypes){
    fun isNone(): Boolean {
        return orderTypes == OrderTypes.NONE
    }
}
data class Join(val table: String, val joinType: JoinType, val on: String)

enum class JoinType(val sql: String) {
    INNER("INNER JOIN"),
    JOIN("JOIN"),
    LEFT("LEFT JOIN"),
    RIGHT("RIGHT JOIN"),
    FULL("FULL JOIN")
}

enum class OrderTypes(val value: String) {
    ASC("ASC") ,
    DESC("DESC"),
    NONE("NONE")
}
enum class Operator(val value: String) {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    NOT_CONTAINS("NOT_CONTAINS"),
    CONTAINS("CONTAINS")
}