
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import data.Item
import org.intellij.lang.annotations.Language
import support.ItemDeserializer

fun main(args: Array<String>) {
    @Language("JSON")
    val json = """
        {
          "id": 1,
          "itemName": "theItem",
          "owner": {
            "id": 2,
            "name": "theUser"
          }
        }
    """.trimIndent()

    @Language("JSON")
    val json2 = """
        {
            "id": 1,
            "itemName": "theItem",
            "createdBy": 2
        }
    """.trimIndent()

    val objectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(SimpleModule().addDeserializer(Item::class.java, ItemDeserializer()))

//    val item = objectMapper.readValue(json, Item::class.java)
    val item = objectMapper.readValue(json2, Item::class.java)

    println(item)
}