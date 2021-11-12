package task4_Matrix

class Matrix(private val initMatrix: Array<Array<Double>>) {
    private var matrixArray: Array<Array<Double>> = arrayOf()
    private val size: Array<Int>

    init {
        for (i in 1 until initMatrix.size)
            if (initMatrix[i - 1].size != initMatrix[i].size)
                throw IllegalArgumentException("Invalid argument")
        for (i in initMatrix.indices) {
            var array = arrayOf<Double>()
            for (j in initMatrix[i].indices)
                array += initMatrix[i][j]
            matrixArray += array
        }
        size = arrayOf(matrixArray.size, matrixArray[0].size)
    }

    operator fun set(i: Int, j: Int, value: Double) {
        if (i >= matrixArray.size || i < 0 || j >= matrixArray[0].size || j < 0)
            throw IllegalArgumentException("Invalid argument")
        matrixArray[i][j] = value
    }

    operator fun get(i: Int, j: Int): Double {
        if (i >= matrixArray.size || i < 0 || j >= matrixArray[0].size || j < 0)
            throw IllegalArgumentException("Invalid argument")
        return matrixArray[i][j]
    }

    operator fun plus(other: Matrix): Matrix {
        if (matrixArray.size != other.size[0] || matrixArray[0].size != other.size[1])
            throw IllegalArgumentException("Invalid argument")
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in initMatrix.indices) {
            var array = arrayOf<Double>()
            for (j in initMatrix[i].indices)
                array += (other[i, j] + matrixArray[i][j])
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun plusAssign(other: Matrix) {
        if (matrixArray.size != other.size[0] || matrixArray[0].size != other.size[1])
            throw IllegalArgumentException("Invalid argument")
        for (i in initMatrix.indices) {
            for (j in initMatrix[i].indices)
                matrixArray[i][j] += other[i, j]
        }
    }

    operator fun plusAssign(other: Double) {
        for (i in initMatrix.indices) {
            for (j in initMatrix[i].indices)
                matrixArray[i][j] += other
        }
    }

    operator fun plus(other: Double): Matrix {
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in initMatrix.indices) {
            var array = arrayOf<Double>()
            for (j in initMatrix[i].indices)
                array += (matrixArray[i][j] + other)
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun minus(other: Matrix): Matrix {
        if (matrixArray.size != other.size[0] || matrixArray[0].size != other.size[1])
            throw IllegalArgumentException("Invalid argument")
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in initMatrix.indices) {
            var array = arrayOf<Double>()
            for (j in initMatrix[i].indices)
                array += (other[i, j] - matrixArray[i][j])
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun minusAssign(other: Matrix) {
        if (matrixArray.size != other.size[0] || matrixArray[0].size != other.size[1])
            throw IllegalArgumentException("Invalid argument")
        for (i in initMatrix.indices) {
            for (j in initMatrix[i].indices)
                matrixArray[i][j] -= other[i, j]
        }
    }

    operator fun minusAssign(other: Double) {
        for (i in initMatrix.indices) {
            for (j in initMatrix[i].indices)
                matrixArray[i][j] -= other
        }
    }

    operator fun minus(other: Double): Matrix {
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in initMatrix.indices) {
            var array = arrayOf<Double>()
            for (j in initMatrix[i].indices)
                array += (matrixArray[i][j] - other)
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun times(other: Matrix): Matrix {
        if (matrixArray[0].size != other.size[0])
            throw IllegalArgumentException("Invalid argument")
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in 0 until this.size[0]) {
            var array = arrayOf<Double>()
            for (j in 0 until other.size[1]) {
                var sum = 0.0
                for (k in 0 until this.size[1])
                    sum += matrixArray[i][k] * other[k, j]
                array += sum
            }
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun timesAssign(other: Matrix) {
        if (matrixArray[0].size != other.size[0])
            throw IllegalArgumentException("Invalid argument")
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in 0 until this.size[0]) {
            var array = arrayOf<Double>()
            for (j in 0 until other.size[1]) {
                var sum = 0.0
                for (k in 0 until this.size[1])
                    sum += matrixArray[i][k] * other[k, j]
                array += sum
            }
            newMatrixArray += array
        }
        matrixArray = newMatrixArray
    }

    operator fun timesAssign(other: Double) {
        for (i in matrixArray.indices) {
            for (j in matrixArray[i].indices)
                matrixArray[i][j] *= other
        }
    }

    operator fun times(other: Double): Matrix {
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in matrixArray.indices) {
            var array = arrayOf<Double>()
            for (j in matrixArray[i].indices)
                array += (matrixArray[i][j] * other)
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun divAssign(other: Double) {
        for (i in matrixArray.indices) {
            for (j in matrixArray[i].indices)
                matrixArray[i][j] /= other
        }
    }

    operator fun div(other: Double): Matrix {
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in matrixArray.indices) {
            var array = arrayOf<Double>()
            for (j in matrixArray[i].indices)
                array += (matrixArray[i][j] / other)
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun unaryMinus(): Matrix {
        var newMatrixArray: Array<Array<Double>> = arrayOf()
        for (i in matrixArray.indices) {
            var array = arrayOf<Double>()
            for (j in matrixArray[i].indices)
                array += (-matrixArray[i][j])
            newMatrixArray += array
        }
        return Matrix(newMatrixArray)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    override fun toString(): String {
        var str = ""
        for (i in matrixArray.indices) {
            for (j in matrixArray[i].indices)
                str += matrixArray[i][j].toString() + " "
            str += System.lineSeparator()
        }
        return str
    }

    override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as Matrix
        for (i in matrixArray.indices) {
            for (j in matrixArray[i].indices)
                if (matrixArray[i][j] != other[i, j])
                    return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = initMatrix.contentDeepHashCode()
        result = 31 * result + matrixArray.contentDeepHashCode()
        result = 31 * result + size.contentHashCode()
        return result
    }
}