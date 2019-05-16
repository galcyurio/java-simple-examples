package com.github.galcyurio.advanced

import com.google.gson.JsonElement

class MyClassTypeAdapterFactory : CustomizedTypeAdapterFactory<MyClass>(MyClass::class.java) {

    override fun beforeWrite(source: MyClass, toSerialize: JsonElement) {
        /* Do nothing - default */
    }

    override fun afterRead(deserialized: JsonElement) {
        val trueMap = deserialized.asJsonObject["trueMap"].asJsonObject
        trueMap.keySet().toSet()
            .forEach { trueMap.addProperty(it, true) }

        val falseMap = deserialized.asJsonObject["falseMap"].asJsonObject
        falseMap.keySet().toSet()
            .forEach { falseMap.addProperty(it, false) }

        val customMap = deserialized.asJsonObject["customIntMap"].asJsonObject
        val foo = customMap["foo"].asString.replace("something-", "").toInt()
        val bar = customMap["bar"].asString.replace("something-", "").toInt()

        customMap.keySet().toSet()
            .forEach { customMap.remove(it) }

        customMap.addProperty("foo", foo)
        customMap.addProperty("baz", bar)
    }
}