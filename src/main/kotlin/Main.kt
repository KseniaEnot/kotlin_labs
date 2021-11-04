import task3_ShapeFactorImpl.Shape
import task3_ShapeFactorImpl.ShapeFactorImpl

fun main() {
    var exampele: List<Shape> = listOf()
    val factory = ShapeFactorImpl()
    exampele = exampele.plus(factory.createCircle(10.0))
    exampele = exampele.plus(factory.createSquare(18.3))
    exampele = exampele.plus(factory.createRectangle(20.0, 12.0))
    exampele = exampele.plus(factory.createTriangle(20.0, 12.0, 9.3))
    exampele = exampele.plus(factory.createRandomCircle())
    exampele = exampele.plus(factory.createRandomRectangle())
    exampele = exampele.plus(factory.createRandomSquare())
    exampele = exampele.plus(factory.createRandomTriangle())
    exampele = exampele.plus(factory.createRandomShape())

    var sumAr = 0.0
    for (item in exampele)
        sumAr += item.calcArea()
    println("sum Area = $sumAr")
    var sumPr = 0.0
    for (item in exampele)
        sumPr += item.calcPerimeter()
    println("sum Perimeter = $sumPr")
    var maxArShape: Shape = exampele[0]
    for (item in exampele)
        if (item.calcArea() > maxArShape.calcArea())
            maxArShape = item
    sumAr = maxArShape.calcArea()
    println("max Area = $sumAr")
    var maxPrShape: Shape = exampele[0]
    for (item in exampele)
        if (item.calcPerimeter() > maxPrShape.calcPerimeter())
            maxPrShape = item
    sumAr = maxPrShape.calcArea()
    println("max Perimeter = $sumAr")
}