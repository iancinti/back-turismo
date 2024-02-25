package com.brainycorp.tourism.domain

data class Criteria(val filters: Filters, val order: Order){

    companion object {
        fun fromPrimitives(filters: List<FiltersPrimitives>, orderBy: String?, orderType: String?): Criteria{
            return Criteria(Filters.fromPrimitives(filters), Order.fromPrimitives(orderBy, orderType))
        }
    }

    fun hasOrder(): Boolean {
        return !order.isNone()
    }

    fun hasFilters(): Boolean {
        return !filters.isEmpty()
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
data class FilterOperator(val operator: Operator)
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

enum class OrderTypes(val value: String) {
    ASC("ASC") ,
    DESC("DESC"),
    NONE("NONE")
}
enum class Operator(val value: String) {
    EQUAL("="),
    NOT_EQUAL("!="),
    LIKE("LIKE"),
    GT(">"),
    LT("<"),
    NOT_CONTAINS("CONTAINS"),
    CONTAINS("NOT_CONTAINS")
}