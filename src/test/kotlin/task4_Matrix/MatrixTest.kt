package task4_Matrix

import kotlin.test.Test
import kotlin.test.assertEquals

internal class MatrixTest {

    @Test
    fun set() {
        val exampele = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        exampele[0, 0] = 1.0
        assertEquals(1.0, exampele[0, 0])
    }

    @Test
    fun get() {
        val exampele = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        assertEquals(2.0, exampele[0, 0])
    }

    @Test
    fun plus() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0))), exampele1 + exampele2)
    }

    @Test
    fun plus_exception() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0), arrayOf(9.0, 3.0)))
        try {
            exampele1 + exampele2
        } catch (e: IllegalArgumentException) {
            assertEquals("Incorrect matrix size for the operation", e.message)
        }
    }

    @Test
    fun plusAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        exampele1 += exampele2
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0))), exampele1)
    }

    @Test
    fun plusAssign_exception() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0), arrayOf(9.0, 3.0)))
        try {
            exampele1 += exampele2
        } catch (e: IllegalArgumentException) {
            assertEquals("Incorrect matrix size for the operation", e.message)
        }
    }

    @Test
    fun testPlusAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        exampele1 += 2.0
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 3.0), arrayOf(11.0, 5.0))), exampele1)
    }

    @Test
    fun testPlus() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0))) + 2.0
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 3.0), arrayOf(11.0, 5.0))), exampele1)
    }

    @Test
    fun minus() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(3.0, 2.0), arrayOf(10.0, 4.0)))
        assertEquals(Matrix(arrayOf(arrayOf(1.0, 1.0), arrayOf(1.0, 1.0))), exampele1 - exampele2)
    }

    @Test
    fun minusAssign_exception() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0), arrayOf(9.0, 3.0)))
        try {
            exampele1 -= exampele2
        } catch (e: IllegalArgumentException) {
            assertEquals("Incorrect matrix size for the operation", e.message)
        }
    }

    @Test
    fun minusAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(3.0, 2.0), arrayOf(10.0, 4.0)))
        exampele1 -= exampele2
        assertEquals(Matrix(arrayOf(arrayOf(-1.0, -1.0), arrayOf(-1.0, -1.0))), exampele1)
    }

    @Test
    fun testMinusAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        exampele1 -= 2.0
        assertEquals(Matrix(arrayOf(arrayOf(0.0, -1.0), arrayOf(7.0, 1.0))), exampele1)
    }

    @Test
    fun testMinus() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0))) - 2.0
        assertEquals(Matrix(arrayOf(arrayOf(0.0, -1.0), arrayOf(7.0, 1.0))), exampele1)
    }

    @Test
    fun times() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0, 3.0), arrayOf(9.0, 3.0, 1.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(3.0, 2.0), arrayOf(10.0, 4.0), arrayOf(3.0, 2.0)))
        assertEquals(Matrix(arrayOf(arrayOf(25.0, 14.0), arrayOf(60.0, 32.0))), exampele1 * exampele2)
    }

    @Test
    fun timesAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0, 3.0), arrayOf(9.0, 3.0, 1.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(3.0, 2.0), arrayOf(10.0, 4.0), arrayOf(3.0, 2.0)))
        exampele1 *= exampele2
        assertEquals(Matrix(arrayOf(arrayOf(25.0, 14.0), arrayOf(60.0, 32.0))), exampele1)
    }

    @Test
    fun timesAssign_exception() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0, 3.0), arrayOf(9.0, 3.0, 1.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(3.0, 2.0), arrayOf(10.0, 4.0)))
        try {
            exampele1 *= exampele2
        } catch (e: IllegalArgumentException) {
            assertEquals("Incorrect matrix size for the operation", e.message)
        }
    }

    @Test
    fun testTimesAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
        exampele1 *= 2.0
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0))), exampele1)
    }

    @Test
    fun testTimes() {
        val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0))) * 2.0
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0))), exampele1)
    }

    @Test
    fun divAssign() {
        val exampele1 = Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0)))
        exampele1 /= 2.0
        assertEquals(Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0))), exampele1)
    }

    @Test
    fun div() {
        val exampele1 = Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0))) / 2.0
        assertEquals(Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0))), exampele1)
    }

    @Test
    operator fun unaryMinus() {
        val exampele1 = -Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0)))
        assertEquals(Matrix(arrayOf(arrayOf(-4.0, -2.0), arrayOf(-18.0, -6.0))), exampele1)
    }

    @Test
    operator fun unaryPlus() {
        val exampele1 = Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0)))
        assertEquals(Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0))), exampele1)
    }


    @Test
    fun testEquals() {
        val exampele1 = Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0)))
        val exampele2 = Matrix(arrayOf(arrayOf(4.0, 2.0), arrayOf(18.0, 6.0)))
        assertEquals(true, exampele1 == exampele2)
    }
}