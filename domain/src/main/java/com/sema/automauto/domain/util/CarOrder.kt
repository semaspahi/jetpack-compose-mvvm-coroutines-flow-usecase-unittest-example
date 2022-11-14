package com.sema.automauto.domain.util

sealed class CarOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): CarOrder(orderType)
    class Date(orderType: OrderType): CarOrder(orderType)
    class Color(orderType: OrderType): CarOrder(orderType)
    class Price(orderType: OrderType): CarOrder(orderType)

    fun copy(orderType: OrderType): CarOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
            is Price -> Price(orderType)
        }
    }
}
