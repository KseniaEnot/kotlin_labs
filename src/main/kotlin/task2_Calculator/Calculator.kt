package task2_Calculator

import kotlin.math.pow

class Calculator(
    inStr: String,
) {

    private var pos = 0
    private var ex: String

    private enum class OpSigns(val opString: String) {
        PLU("+"),
        MIN("-"),
        MUL("*"),
        DIV("/"),
        DEGR("^"),
        OPBRAC("("),
        CLBRAC(")"),
    }

    init {
        if (inStr.isEmpty())
            throw IllegalArgumentException("Invalid argument")
        ex = inStr.replace(" ", "")
    }
    /*
    Priority:
    Num = (Exp) | Num
    SiNum = Num1^Num2 | Num1
    Fact = +- SiNum | SiNum
    Term = Fact1 *./ Fact2 | Fact1
    Express = Term1 +- Term2 | Term1
    */

    fun parser(): Double {
        val res = expression()
        if (pos != ex.length)
            throw IllegalArgumentException("Invalid argument")
        return res
    }

    private fun expression(): Double {
        var num1 = term()
        while (pos < ex.length) {
            val operator = ex[pos].toString()
            if ((operator != OpSigns.PLU.opString) && (operator != OpSigns.MIN.opString))
                break
            else
                pos++
            val num2 = term()
            if (operator == OpSigns.PLU.opString)
                num1 += num2
            else
                num1 -= num2
        }
        return num1
    }

    private fun term(): Double {
        var num1 = fact()
        while (pos < ex.length) {
            val operator = ex[pos].toString()
            if ((operator != OpSigns.MUL.opString) && (operator != OpSigns.DIV.opString))
                break
            else
                pos++
            val num2 = fact()
            if (operator == OpSigns.MUL.opString)
                num1 *= num2
            else
                num1 /= num2
        }
        return num1
    }

    private fun fact(): Double {
        val operator = ex[pos].toString()
        if (operator != OpSigns.PLU.opString && operator != OpSigns.MIN.opString)
            return signedNumber()
        pos++
        var num = signedNumber()
        if (operator == OpSigns.MIN.opString)
            num *= (-1)
        return num
    }

    private fun signedNumber(): Double {
        var num1 = num()
        while (pos < ex.length) {
            val operator = ex[pos].toString()
            if (operator != OpSigns.DEGR.opString)
                break
            else
                pos++
            val num2 = num()
            num1 = num1.pow(num2)
        }
        return num1
    }

    private fun num(): Double {
        val openingBrac = ex[pos].toString()
        val result: Double
        if (openingBrac == OpSigns.OPBRAC.opString) {
            pos++
            result = expression()
            val closingBrac: String
            if (pos < ex.length)
                closingBrac = ex[pos].toString()
            else
                throw IllegalArgumentException("')' expected")
            if ((pos < ex.length) && (closingBrac == OpSigns.CLBRAC.opString)) {
                pos++
                return result
            }
            throw IllegalArgumentException("')' expected but $closingBrac")
        }
        if (Regex("[0-9]+").find(ex, pos) == null)
            throw IllegalArgumentException("Invalid argument")
        else {
            val number = Regex("[0-9]+").find(ex, pos)!!.value
            pos += number.length
            return number.toDouble()
        }
    }
}