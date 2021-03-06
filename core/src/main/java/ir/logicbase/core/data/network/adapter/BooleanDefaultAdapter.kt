package ir.logicbase.core.data.network.adapter

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Created by Mahdi on 12/21/2017.
 * handle null and empty values in JSON element
 */

class BooleanDefaultAdapter : JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Boolean? {
        try {
            // Defined as Boolean type , If the background returns "" perhaps null, Then return false
            if (json.isJsonNull || json.asString == "" || json.asString == "null") {
                return false
            }
        } catch (ignore: Exception) {
        }

        return json.asBoolean
    }

    override fun serialize(src: Boolean?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src!!)
    }
}
