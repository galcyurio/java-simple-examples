package com.github.galcyurio

import com.google.gson.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.reflect.TypeToken
import org.intellij.lang.annotations.Language
import java.lang.reflect.Type

data class UserSimple(
    val name: String,
    val email: String,
    val isDeveloper: Boolean,
    val age: Int
)

data class Merchant(
    val id: Int,
    val name: String
)

data class UserSubscription(
    val name: String,
    val email: String,
    val age: Int,
    val isDeveloper: Boolean,

    @JsonAdapter(MerchantIdsDeserializer::class)
    val merchantIds: List<Int>
)

class MerchantSerializer : JsonSerializer<Merchant> {
    override fun serialize(
        src: Merchant,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonObject().apply {
            addProperty("id", src.id)
        }
    }
}

class MerchantIdsDeserializer : JsonDeserializer<List<Int>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<Int> {
        val array = json.asJsonArray
        return listOf()
    }
}

val json = """
{
  "name": "Norman",
  "email": "norman@fs.io",
  "age": 26,
  "isDeveloper": true,
  "merchantList": [
    {
      "id": 23,
      "name": "Hello world"
    },
    {
      "id": 42,
      "name": "Coffee Shop"
    }
  ]
}
""".trimIndent()

fun main() {
    GsonBuilder()
        .registerTypeAdapter(object: TypeToken<List<Int>>() {}.type, MerchantIdsDeserializer())
        .create()
        .fromJson(json, UserSubscription::class.java)
        .also { println(it) }
}