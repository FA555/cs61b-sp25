package deque

class ArrayDeque61B<T> : Deque61B<T>, MutableIterable<T> {
    // Make precondition test happy
    private val INITIAL_CAPACITY = 8
    private val RESIZE_FACTOR = 2
    private val SHRINK_FACTOR = 0.25

    private var capacity = INITIAL_CAPACITY
    private var data = arrayOfNulls<Any>(capacity) as Array<T>
    private var size = 0
    private var nextFirst = 0
    private var nextLast = 1

    inner class ArrayDequeIterator(
        private var currentIndex: Int = 0,
        private val endIndex: Int = size,
        private val deque: ArrayDeque61B<T> = this,
    ) : MutableIterator<T> {
        override fun next(): T {
            if (!hasNext())
                throw NoSuchElementException("No more elements in the deque")

            val item = deque.get(currentIndex)
            currentIndex++
            return item!!
        }

        override fun hasNext(): Boolean = currentIndex < endIndex

        override fun remove() {
            throw UnsupportedOperationException("Remove operation is not supported")
        }
    }

    override fun iterator() = ArrayDequeIterator()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ArrayDeque61B<*>) return false
        if (size != other.size) return false

        for (i in 0 until size)
            if (this.get(i) != other.get(i))
                return false
        return true
    }

    override fun hashCode(): Int {
        var result = capacity
        result = 31 * result + size
        result = 31 * result + nextFirst
        result = 31 * result + nextLast
        for (item in toList())
            result = 31 * result + (item.hashCode())
        return result
    }

    override fun toString() = toList().toString()

    private fun resize(newCapacity: Int) {
        val newData = arrayOfNulls<Any>(newCapacity) as Array<T>
        for (i in 0 until size)
            newData[i] = data[(nextFirst + 1 + i) % capacity]
        data = newData
        nextFirst = newCapacity - 1
        nextLast = size
        capacity = newCapacity
    }

    private fun checkResize() {
        if (size == capacity)
            resize(RESIZE_FACTOR * size)
        else if (size < capacity * SHRINK_FACTOR && capacity > INITIAL_CAPACITY)
            resize(RESIZE_FACTOR * size)
    }

    private fun indexInc(index: Int, offset: Int = 1) = (index + offset + capacity) % capacity

    override fun addFirst(x: T) {
        checkResize()
        data[nextFirst] = x
        nextFirst = indexInc(nextFirst, -1)
        ++size
    }

    override fun addLast(x: T) {
        checkResize()
        data[nextLast] = x
        nextLast = indexInc(nextLast)
        ++size
    }

    override fun toList() = List(size) { get(it)!! }

    override fun isEmpty() = size == 0

    override fun size() = size

    override fun removeFirst(): T? {
        if (isEmpty)
            return null

        nextFirst = indexInc(nextFirst)
        --size
        val result = data[nextFirst]
        checkResize()
        return result
    }

    override fun removeLast(): T? {
        if (isEmpty)
            return null

        nextLast = indexInc(nextLast, -1)
        --size
        val result = data[nextLast]
        checkResize()
        return result
    }

    override fun get(index: Int): T? {
        if (index < 0 || index >= size)
            return null

        return data[indexInc(nextFirst, index + 1)]
    }

    override fun getRecursive(index: Int): T? =
        throw UnsupportedOperationException("No need to implement getRecursive for proj 1b")

}
