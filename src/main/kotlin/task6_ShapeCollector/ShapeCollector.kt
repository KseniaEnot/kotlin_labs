package task6_ShapeCollector

import task3_ShapeFactorImpl.Shape

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
        return allShapes.toList()
    }


    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

}