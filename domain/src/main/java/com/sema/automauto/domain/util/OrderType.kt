package com.sema.automauto.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
