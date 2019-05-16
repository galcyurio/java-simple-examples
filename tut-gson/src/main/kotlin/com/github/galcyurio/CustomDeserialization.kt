package com.github.galcyurio

import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type
import java.util.*
import java.io.IOException
import com.google.gson.TypeAdapter

data class BigBook(
    val id: Int,

    @JsonAdapter(BookTypeAdapter::class)
    val book: Book
)

data class Book(
    val authors: List<String>,
    val isbn: String,
    val title: String
)

class BookTypeAdapter : TypeAdapter<Book>() {

    override fun read(`in`: JsonReader): Book {
        `in`.beginObject()
        var isbn: String? = null
        var title: String? = null
        var authors: List<String>? = null

        while (`in`.hasNext()) {
            when (`in`.nextName()) {
                "isbn" -> isbn = `in`.nextString()
                "title" -> title = `in`.nextString()
                "authors" -> authors = `in`.nextString().split(";")
            }
        }
        `in`.endObject()

        return Book(authors!!, isbn!!, title!!)
    }

    override fun write(out: JsonWriter, book: Book) {
        out.beginObject()
        out.name("isbn").value(book.isbn)
        out.name("title").value(book.title)
        out.name("authors").value(book.authors.joinToString(";"))
        out.endObject()
    }
}

val bookJson = """
{"isbn":"12398ji12j9f21","title":"Hello, world!","authors":"AA;BB;CC"}
""".trimIndent()

fun main() {
    GsonBuilder()
        .create()
        .toJson(BigBook(
            id = 8888,
            book = Book(
                authors = listOf("AA", "BB", "CC"),
                isbn = "jo1j928f9j128jf1",
                title = "Hello, world!"
            )
        ))
        .also { println(it) }
}