package task7_Serialization

import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import kotlinx.serialization.json.Json
import task3_ShapeFactorImpl.*

object SerializersModule {
    private val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
            }
        }
    }

    fun encode(value: List<Shape>) = json.encodeToString(value)


    fun decode(string: String) = json.decodeFromString<List<Shape>>(string)

}