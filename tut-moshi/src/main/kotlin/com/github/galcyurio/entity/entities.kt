package com.github.galcyurio.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlackjackHand(
    @Json(name = "hidden_card")
    val hiddenCard: Card,

    @Json(name = "visible_cards")
    val visibleCards: List<Card>
)

data class Card(
    val rank: Char,
    val suit: Suit
)

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}