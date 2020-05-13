package ir.logicfan.core.data.network.adapter

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Created by Mahdi on 12/21/2017.
 * handle null and empty values in JSON element
 */

class StringDefaultAdapter : JsonSerializer<String>, JsonDeserializer<String> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): String {
        try {
            // Defined as String type , If the background returns "" perhaps null, Then return ""
            if (json.isJsonNull || json.asString == "" || json.asString == "null") {
                return ""
            }
        } catch (ignore: Exception) {
        }

        return json.asString
    }

    override fun serialize(src: String, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src)
    }
}
