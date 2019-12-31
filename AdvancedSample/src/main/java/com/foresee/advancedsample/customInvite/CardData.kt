package com.foresee.advancedsample.customInvite

enum class DataType {
    PRODUCT_TYPE, INVITE_TYPE;

    companion object {
        private val map = DataType.values().associateBy(DataType::ordinal)
        fun fromInt(type: Int) = map[type]
    }
}
abstract class CardData {
    abstract val type: Int
}

class ProductCardData(name: String, val image: Int) : CardData() {
    val title: String = name

    override val type: Int
        get() = DataType.PRODUCT_TYPE.ordinal
}

class InviteCardData(name: String, image: String) : CardData() {
    val title: String = name
    val description: String = image

    override val type: Int
        get() = DataType.INVITE_TYPE.ordinal
}