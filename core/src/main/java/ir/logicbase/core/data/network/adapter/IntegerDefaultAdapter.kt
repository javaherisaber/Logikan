package ir.logicbase.core.data.network.adapter

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Created by Mahdi on 12/21/2017.
 * handle null and empty values in JSON element
 */

class IntegerDefaultAdapter : JsonSerializer<Int>, JsonDeserializer<Int> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Int? {
        try {
            // Defined as int type , If the background returns "" perhaps null, Then return 0
            if (json.isJsonNull || json.asString == "" || json.asString == "null") {
                return 0
            }
        } catch (ignore: Exception) {
        }

        try {
            return json.asInt
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }

    }

    override fun serialize(src: Int?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src!!)
    }
}
