package task3_ShapeFactorImpl

import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(private val radius: Double) : Shape {
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
}

class Square(private val sideA: Double) : Shape {
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
}

class Rectangle(private val sideA: Double, private val sideB: Double) : Shape {
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
}

class Triangle(private val sideA: Double, private val sideB: Double, private val sideC: Double) : Shape {
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
}

interface ShapeFactory {
    fun createCircle(_Radius: Double): Circle
    fun createSquare(_sideA: Double): Square
    fun createRectangle(_sideA: Double, _sideB: Double): Rectangle
    fun createTriangle(_sideA: Double, _sideB: Double, _sideC: Double): Triangle
    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle
    fun createRandomShape(): Shape
}

class ShapeFactorImpl : ShapeFactory {
    private val maxValue = 999999.99999
    override fun createCircle(_Radius: Double): Circle {
        return Circle(_Radius)
    }

    override fun createRandomCircle(): Circle {
        return Circle(Random.nextDouble(0.1, maxValue))
    }

    override fun createSquare(_sideA: Double): Square {
        return Square(_sideA)
    }

    override fun createRandomSquare(): Square {
        return Square(Random.nextDouble(0.1, maxValue))
    }

    override fun createRectangle(_sideA: Double, _sideB: Double): Rectangle {
        return Rectangle(_sideA, _sideB)
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(Random.nextDouble(0.1, maxValue), Random.nextDouble(0.1, maxValue))
    }

    override fun createTriangle(_sideA: Double, _sideB: Double, _sideC: Double): Triangle {
        return Triangle(_sideA, _sideB, _sideC)
    }

    override fun createRandomTriangle(): Triangle {
        var sideA = Random.nextDouble(0.1, maxValue)
        var sideB = Random.nextDouble(0.1, maxValue)
        var sideC = Random.nextDouble(0.1, maxValue)
        while (sideA + sideB <= sideC ||
            sideA + sideC <= sideB ||
            sideB + sideC <= sideA
        ) {
            sideA = Random.nextDouble(0.1, maxValue)
            sideB = Random.nextDouble(0.1, maxValue)
            sideC = Random.nextDouble(0.1, maxValue)
        }
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