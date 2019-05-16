package com.github.galcyurio.advanced

import com.google.gson.GsonBuilder

fun main() {
    val json = """
    {
      "falseMap": {
        "foo": "something-false",
        "bar": "something-false"
      },
      "trueMap": {
        "foo": "something-true",
        "bar": "something-true"
      },
      "customIntMap": {
        "foo": "something-55",
        "bar": "something-2"
      }
    }
    """.trimIndent()


    GsonBuilder()
        .registerTypeAdapterFactory(MyClassTypeAdapterFactory())
        .create()
        .fromJson(json, MyClass::class.java)
        .also { println(it) }
}