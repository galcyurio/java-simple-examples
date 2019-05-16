package com.github.galcyurio

import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type

data class User(
    val id: Int,

    @JsonAdapter(OpenTypeTypeAdapter::class)
    val openType: OpenType
)

enum class OpenType(val raw: Int) {
    MAIN(1),
    FRIEND(2),
    CHANNEL(3)
}

class OpenTypeDeserializer : JsonDeserializer<OpenType> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): OpenType? {
        val raw = json.asJsonPrimitive.asInt
        return OpenType.values().find { raw == it.raw }
    }
}

class OpenTypeTypeAdapter : TypeAdapter<OpenType>() {
    override fun write(out: JsonWriter, value: OpenType) {
        out.value(value.raw)
    }

    override fun read(`in`: JsonReader): OpenType? {
        val raw = `in`.nextInt()
        return OpenType.values().find { raw == it.raw }
    }
}

fun main() {
    val gson = Gson()
    val json = """
    {"id":222,"openType":2}
    """.trimIndent()

//    Gson()
//        .fromJson(json, User::class.java)
//        .also { println(it) }

    gson.toJson(User(1, OpenType.MAIN))
        .also { println(it) }
}