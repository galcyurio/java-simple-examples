package com.github.galcyurio.advanced

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

abstract class CustomizedTypeAdapterFactory<C>(
    private val customizedClass: Class<C>
) : TypeAdapterFactory {

    @Suppress("UNCHECKED_CAST") // we use a runtime check to guarantee that 'C' and 'T' are equal
    override fun <T> create(
        gson: Gson,
        type: TypeToken<T>
    ): TypeAdapter<T>? {
        return if (type.rawType == customizedClass)
            customizeMyClassAdapter(gson, type as TypeToken<C>) as TypeAdapter<T>
        else
            null
    }

    private fun customizeMyClassAdapter(
        gson: Gson,
        type: TypeToken<C>
    ): TypeAdapter<C> {
        val delegate = gson.getDelegateAdapter(this, type)
        val elementAdapter = gson.getAdapter(JsonElement::class.java)
        return object : TypeAdapter<C>() {
            override fun write(out: JsonWriter, value: C) {
                val tree = delegate.toJsonTree(value)
                beforeWrite(value, tree)
                elementAdapter.write(out, tree)
            }

            override fun read(`in`: JsonReader): C {
                val tree = elementAdapter.read(`in`)
                afterRead(tree)
                return delegate.fromJsonTree(tree)
            }
        }
    }

    /**
     * Override this to muck with `toSerialize` before it is written to
     * the outgoing JSON stream.
     */
    protected open fun beforeWrite(source: C, toSerialize: JsonElement) {}

    /**
     * Override this to muck with `deserialized` before it parsed into
     * the application type.
     */
    protected open fun afterRead(deserialized: JsonElement) {}
}