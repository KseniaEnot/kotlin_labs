package task6_ShapeCollector
import task3_ShapeFactorImpl.Shape
import task3_ShapeFactorImpl.Circle

object ShapeComparators {
    val byAscendPerimeter = compareBy<Shape> { it.calcPerimeter() }
    val byDescendPerimeter = compareByDescending<Shape> { it.calcPerimeter() }
    val byAscendArea = compareBy<Shape> { it.calcArea() }
    val byDescendArea = compareByDescending<Shape> { it.calcArea() }
    val byAscendRadius = compareBy<Circle> { it.radius }
    val byDescendRadius = compareByDescending<Circle> { it.radius }
}