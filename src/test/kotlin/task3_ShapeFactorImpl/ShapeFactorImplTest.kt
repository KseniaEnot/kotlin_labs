package task3_ShapeFactorImpl

import kotlin.math.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail


internal class ShapeFactorImplTest {
    @Test
    fun createCircleTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createCircle(10.0)
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun createRandomCircleTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createRandomCircle()
        } catch (e: Exception) {
            fail()
        }

    }

    @Test
    fun circleAreaTest() {
        val factory = ShapeFactorImpl()
        assertEquals(10.0 * 10.0 * Math.PI, factory.createCircle(10.0).calcArea())
    }

    @Test
    fun circlePerimeterTest() {
        val factory = ShapeFactorImpl()
        assertEquals(2 * 10.0 * Math.PI, factory.createCircle(10.0).calcPerimeter())
    }

    @Test
    fun createSquareTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createSquare(18.3)
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun createRandomSquareTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createRandomSquare()
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun squareAreaTest() {
        val factory = ShapeFactorImpl()
        assertEquals(18.3 * 18.3, factory.createSquare(18.3).calcArea())
    }

    @Test
    fun squarePerimeterTest() {
        val factory = ShapeFactorImpl()
        assertEquals(4 * 18.3, factory.createSquare(18.3).calcPerimeter())
    }

    @Test
    fun createRectangleTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createRectangle(20.0, 12.0)
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun createRandomRectangleTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createRandomRectangle()
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun rectangleAreaTest() {
        val factory = ShapeFactorImpl()
        assertEquals(20.0 * 12.0, factory.createRectangle(20.0, 12.0).calcArea())
    }

    @Test
    fun rectanglePerimeterTest() {
        val factory = ShapeFactorImpl()
        assertEquals(2 * (20.0 + 12.0), factory.createRectangle(20.0, 12.0).calcPerimeter())
    }

    @Test
    fun createTriangleTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createTriangle(20.0, 12.0, 9.3)
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun createRandomTriangleTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createRandomTriangle()
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun triangleAreaTest() {
        val factory = ShapeFactorImpl()
        val p = (20.0 + 12.0 + 9.3) / 2
        assertEquals(sqrt(p * (p - 20.0) * (p - 12.0) * (p - 9.3)), factory.createTriangle(20.0, 12.0, 9.3).calcArea())
    }

    @Test
    fun trianglePerimeterTest() {
        val factory = ShapeFactorImpl()
        assertEquals(20.0 + 12.0 + 9.3, factory.createTriangle(20.0, 12.0, 9.3).calcPerimeter())
    }

    @Test
    fun createRandomShapeTest() {
        try {
            val factory = ShapeFactorImpl()
            factory.createRandomShape()
        } catch (e: Exception) {
            fail()
        }
    }
}