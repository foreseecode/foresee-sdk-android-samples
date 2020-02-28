package com.foresee.advancedsample.customInvite

enum class DataType {
    PRODUCT_TYPE, INVITE_TYPE;

    companion object {
        private val map = values().associateBy(DataType::ordinal)
        fun fromInt(type: Int) = map[type]
    }
}

abstract class CardData {
    abstract val type: Int
}

class ProductCardData(val title: String, val image: Int) : CardData() {

    override val type: Int
        get() = DataType.PRODUCT_TYPE.ordinal
}

class InviteCardData(val title: String, val description: String) : CardData() {

    override val type: Int
        get() = DataType.INVITE_TYPE.ordinal
}