package task6_ShapeCollector

class ShapeCollector<T>
        where T : Shape {
    private val allShapes = mutableListOf<T>()
    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: List<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        val returnAllShapes = mutableListOf<T>()
        for (it in allShapes)
            returnAllShapes.add(it)
        return returnAllShapes
    }

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

}