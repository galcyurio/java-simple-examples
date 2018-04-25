package support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import data.Item
import data.User

/**
 * @author galcyurio
 */
class ItemDeserializer : StdDeserializer<Item>(Item::class.java) {
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Item {
        val jsonNode = jp.codec.readTree<JsonNode>(jp)

        val id = jsonNode.get("id").intValue()
        val itemName = jsonNode.get("itemName").textValue()
        val ownerId = jsonNode.get("createdBy").intValue()

        return Item(id, itemName, User(ownerId, ""))
    }
}