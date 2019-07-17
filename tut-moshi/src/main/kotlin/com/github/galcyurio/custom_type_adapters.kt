package com.github.galcyurio

import com.github.galcyurio.entity.BlackjackHand
import com.github.galcyurio.entity.Card
import com.github.galcyurio.entity.Suit
import com.squareup.moshi.ToJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi

/**
 * With Moshi, it’s particularly easy to customize how values are converted to and from JSON.
 * A type adapter is any class that has methods annotated [ToJson] and [FromJson].
 *
 * For example, Moshi’s default encoding of a playing card is verbose:
 * the JSON defines the rank and suit in separate fields: `{"rank":"A","suit":"HEARTS"}`.
 * With a type adapter, we can change the encoding to something more compact: "4H" for the four of hearts or "JD" for the jack of diamonds:
 * */
fun main() {
    val moshi = Moshi.Builder()
        .add(CardAdapter())
        .build()
    val jsonAdapter = moshi.adapter(BlackjackHand::class.java)
    val blackjackHand = BlackjackHand(
        hiddenCard = Card('6', Suit.SPADES),
        visibleCards = listOf(Card('4', Suit.CLUBS), Card('A', Suit.HEARTS))
    )
    val json = jsonAdapter.toJson(blackjackHand)
    println(json)
    // {"hidden_card":"6S","visible_cards":["4C","AH"]}
}

class CardAdapter {
    @ToJson
    fun toJson(card: Card): String =
        card.rank + card.suit.name.substring(0, 1)

    @FromJson
    fun fromJson(card: String): Card {
        if (card.length != 2) throw JsonDataException("Unknown card: $card")

        val rank = card[0]
        return when (card[1]) {
            'C' -> Card(rank, Suit.CLUBS)
            'D' -> Card(rank, Suit.DIAMONDS)
            'H' -> Card(rank, Suit.HEARTS)
            'S' -> Card(rank, Suit.SPADES)
            else -> throw JsonDataException("unknown suit: $card")
        }
    }
}