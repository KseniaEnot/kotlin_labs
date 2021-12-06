import task7_Serialization.*
import task3_ShapeFactorImpl.*
import task6_ShapeCollector.*

fun main() {
    println("Task7")
    val pathIn = "src\\main\\kotlin\\task7_Serialization\\TextSerializer\\in.json"
    val pathOut = "src\\main\\kotlin\\task7_Serialization\\TextSerializer\\out.json"
    val collector = ShapeCollector<Shape>()
    val shapeImpl = ShapeFactorImpl()
    collector.addAll(SerializersModule.decode(FileIO.fileReader(pathIn)))
    collector.add(shapeImpl.createRandomCircle())
    collector.add(shapeImpl.createRandomTriangle())
    collector.add(shapeImpl.createRandomTriangle())
    collector.add(shapeImpl.createRandomSquare())
    FileIO.fileWriter(SerializersModule.encode(collector.getAll()), pathOut)
}