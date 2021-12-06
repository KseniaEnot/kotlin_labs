package task3_ShapeFactorImpl
import kotlinx.serialization.Serializable
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}
@Serializable
class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Invalid argument")
    }

    override fun calcArea(): Double {
        return Math.PI * radius * radius
    }

    override fun calcPerimeter(): Double {
        return 2 * Math.PI * radius
    }

    override fun toString(): String {
        return "Circle(radius=$radius)"
    }
}
@Serializable
class Square(val sideA: Double) : Shape {
    init {
        if (sideA <= 0)
            throw IllegalArgumentException("Invalid argument")
    }

    override fun calcArea(): Double {
        return sideA * sideA
    }

    override fun calcPerimeter(): Double {
        return 4 * sideA
    }

    override fun toString(): String {
        return "Square(sideA=$sideA)"
    }
}
@Serializable
class Rectangle(val sideA: Double, val sideB: Double) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0)
            throw IllegalArgumentException("Invalid argument")
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return 2 * (sideA + sideB)
    }

    override fun toString(): String {
        return "Rectangle(sideA=$sideA, sideB=$sideB)"
    }
}
@Serializable
class Triangle(val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0 ||
            sideA + sideB <= sideC ||
            sideA + sideC <= sideB ||
            sideB + sideC <= sideA
        )
            throw IllegalArgumentException("Invalid argument")
    }

    override fun calcArea(): Double {
        val p = calcPerimeter() / 2
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }

    override fun toString(): String {
        return "Triangle(sideA=$sideA, sideB=$sideB, sideC=$sideC)"
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(sideA: Double): Square
    fun createRectangle(sideA: Double, sideB: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle
    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle
    fun createRandomShape(): Shape
}

class ShapeFactorImpl : ShapeFactory {
    private val maxValue = 999999.99999
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createRandomCircle(): Circle {
        return Circle(Random.nextDouble(0.1, maxValue))
    }

    override fun createSquare(sideA: Double): Square {
        return Square(sideA)
    }

    override fun createRandomSquare(): Square {
        return Square(Random.nextDouble(0.1, maxValue))
    }

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle {
        return Rectangle(sideA, sideB)
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(Random.nextDouble(0.1, maxValue), Random.nextDouble(0.1, maxValue))
    }

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle {
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomTriangle(): Triangle {
        val sideA = Random.nextDouble(Double.MIN_VALUE, maxValue)
        val sideB = Random.nextDouble(Double.MIN_VALUE, maxValue)
        val sideC = Random.nextDouble(abs(sideB - sideA), sideA + sideB - Double.MIN_VALUE)
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomShape(): Shape {
        return when ((0..3).random()) {
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            3 -> createRandomTriangle()
            else -> throw IllegalArgumentException("Invalid argument")
        }
    }
}