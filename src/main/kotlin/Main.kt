import task6_ShapeCollector.*

fun main() {
    println("Task6")
    val collector = ShapeCollector<Shape>()
    collector.add(Circle(5.0))
    collector.add(Square(4.0))
    collector.add(Triangle(3.0, 4.0, 5.0))
    collector.add(Circle(10.0))
    collector.add(Circle(8.0))
    collector.addAll(listOf(Circle(8.0), Triangle(3.0, 4.0, 3.0)))
    println("All Collector: ")
    for (it in collector.getAll())
        println(it.toString())
    println("Sorted Collector: ")
    for (it in collector.getAllSorted(ShapeComparators.byDescendPerimeter))
        println(it.toString())
    val collector2 = ShapeCollector<Circle>()
    collector2.addAll(listOf(Circle(8.0), Circle(12.0), Circle(5.0), Circle(3.3)))
    println("Sorted Circle Collector: ")
    for (it in collector2.getAllSorted(ShapeComparators.byDescendRadius))
        println(it.toString())
}
