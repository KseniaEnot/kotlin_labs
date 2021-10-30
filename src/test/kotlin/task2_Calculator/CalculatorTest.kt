package task2_Calculator

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CalculatorTest {

    @Test
    fun multiplication_check() {
        val str = Calculator("99*2")
        assertEquals(str.parser(), 198.0)
    }

    @Test
    fun division_check() {
        val str = Calculator("99/11")
        assertEquals(str.parser(), 9.0)
    }

    @Test
    fun addition_check() {
        val str = Calculator("99+11")
        assertEquals(str.parser(), 110.0)
    }

    @Test
    fun subtraction_check() {
        val str = Calculator("99-11")
        assertEquals(str.parser(), 88.0)
    }

    @Test
    fun brackets_check() {
        val str = Calculator("(11+99)-(11*1)")

        assertEquals(str.parser(), 99.0)
    }

    @Test
    fun brackets_exception1_check() {
        val str = Calculator("(11+99)-(11*1")
        try {
            str.parser()
        } catch (e: IllegalArgumentException) {
            assertEquals("')' expected", e.message)
        }
    }
    @Test
    fun brackets_exception2_check() {
        val str = Calculator("(11+99)-(11*1g")
        try {
            str.parser()
        } catch (e: IllegalArgumentException) {
            assertEquals("')' expected but g", e.message)
        }
    }

    @Test
    fun from_task1() {
        val str = Calculator("12+ 3")
        assertEquals(str.parser(), 15.0)
    }

    @Test
    fun from_task2() {
        val str = Calculator("-3+4^(+4)")
        assertEquals(str.parser(), 253.0)
    }

    @Test
    fun from_task3() {
        val str = Calculator("(4+3)*9/ -4")
        assertEquals(str.parser(), -15.75)
    }

    @Test
    fun from_task4() {
        val str = Calculator("((-4+3))*8/4")
        assertEquals(str.parser(), -2.0)
    }
    @Test
    fun from_task5() {
        val str = Calculator("(-3+9))*30")
        try {
            str.parser()
        } catch (e: IllegalArgumentException) {
            assertEquals("Invalid argument", e.message)
        }
    }
    @Test
    fun from_task6() {
        val str = Calculator("+4 */ 33")
        try {
            str.parser()
        } catch (e: IllegalArgumentException) {
            assertEquals("Invalid argument", e.message)
        }
    }
    @Test
    fun from_task7() {
        val str = Calculator("*3 + 3")
        try {
            str.parser()
        } catch (e: IllegalArgumentException) {
            assertEquals("Invalid argument", e.message)
        }
    }
    @Test
    fun from_task8() {
        val str = Calculator("3+alksdjfklsdjfkldj+asdfd+3")
        try {
            str.parser()
        } catch (e: IllegalArgumentException) {
            assertEquals("Invalid argument", e.message)
        }
    }

    @Test
    fun use_alm_operation() {
        val str = Calculator("1 + -10^(2+ 1 *2)")
        assertEquals(str.parser(), -9999.0)
    }
}