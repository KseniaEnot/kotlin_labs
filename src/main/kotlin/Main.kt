import task4_Matrix.Matrix

fun main() {
    val exampele1 = Matrix(arrayOf(arrayOf(2.0,1.0),arrayOf(9.0,3.0)))
    val exampele2 = Matrix(arrayOf(arrayOf(8.0,4.0),arrayOf(5.0,12.0)))
    print((exampele1*exampele2).toString() + System.lineSeparator())
    exampele2 += 1.2
    print(exampele2.toString() + System.lineSeparator())
    exampele2 -= 1.2
    print(exampele2.toString() + System.lineSeparator())
    exampele2 *= 2.0
    print(exampele2.toString() + System.lineSeparator())
    exampele2 /= 1.2
    print(exampele2.toString() + System.lineSeparator())
    exampele2 += exampele1
    print(exampele2.toString() + System.lineSeparator())
    exampele2 -= exampele1
    print(exampele2.toString() + System.lineSeparator())
    exampele2 *= exampele1
    print(exampele2.toString() + System.lineSeparator())
    val exampele3 = exampele1+exampele2*exampele2-exampele2
    print(exampele3.toString() + System.lineSeparator())
}