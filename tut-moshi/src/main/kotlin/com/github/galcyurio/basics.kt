package com.github.galcyurio

import com.github.galcyurio.entity.BlackjackHand
import com.github.galcyurio.entity.Card
import com.github.galcyurio.entity.Suit
import com.squareup.moshi.Moshi
import org.intellij.lang.annotations.Language

@Language("JSON")
private val json = """
{
  "hidden_card": {
    "rank": "6",
    "suit": "SPADES"
  },
  "visible_cards": [
    {
      "rank": "4",
      "suit": "CLUBS"
    },
    {
      "rank": "A",
      "suit": "HEARTS"
    }
  ]
}
""".trimIndent()


/**
 * 코틀린이라 하더라도 필드명과 `json`의 키값이 같으면 별다른 코틀린 모듈 없이도 역직렬화가 잘 동작한다.
 * 하지만 실제 `json`의 키값과 필드명이 달라서 `@Json`을 통해서 일치시켜 주어야 하는 경우에는
 * 코틀린 관련 모듈 (reflection 또는 codegen)을 적용해야 한다.
 *
 * `reflection`의 경우에는 [KotlinJsonAdapterFactory]를 Moshi 빌더에 add 해주면 된다.
 * `codegen`의 경우에는 역직렬화하려는 해당 클래스에 `@JsonClass(generateAdapter = true)`를 명시해주어야 한다.
 * */
fun main() {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(BlackjackHand::class.java)
    val cardJsonAdapter = moshi.adapter(Card::class.java)

    /* 역직렬화 */
    val blackjackHand = jsonAdapter.fromJson(json)
    println(blackjackHand)

    /* 직렬화 */
//    val blackjackHand = BlackjackHand(
//        hidden_card = Card('6', Suit.SPADES),
//        visible_cards = listOf(Card('4', Suit.CLUBS), Card('A', Suit.HEARTS))
//    )
//    val json = jsonAdapter.toJson(blackjackHand)
//    println(json)
//     {"hidden_card":{"rank":"6","suit":"SPADES"},"visible_cards":[{"rank":"4","suit":"CLUBS"},{"rank":"A","suit":"HEARTS"}]}

    /* Card 역직렬화 */
//    @Language("JSON") val json = """
//        {"rank": "6","suit": "SPADES"}
//    """.trimIndent()
//    val card = cardJsonAdapter.fromJson(json)
//    println(card)
//    Card(rank=6, suit=SPADES)

    /* Card 직렬화 */
//    val card = Card('6', Suit.HEARTS)
//    val json = cardJsonAdapter.toJson(card)
//    println(json)
}