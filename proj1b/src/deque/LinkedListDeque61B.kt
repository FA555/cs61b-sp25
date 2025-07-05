package deque

class LinkedListDeque61B<T> : Deque61B<T>, MutableIterable<T> {
    inner class Node(val item: T?) {
        lateinit var next: Node
        lateinit var prev: Node
    }

    private var sentinel: Node = Node(null)
    private var size = 0

    init {
        sentinel.next = sentinel
        sentinel.prev = sentinel
    }

    inner class LinkedListDequeIterator(
        private var current: Node = sentinel.next,
        private val end: Node = sentinel
    ) : MutableIterator<T> {
        override fun hasNext(): Boolean = current != end

        override fun next(): T {
            if (!hasNext())
                throw NoSuchElementException("No more elements in the deque")
            val item = current.item!!
            current = current.next
            return item
        }

        override fun remove() {
            throw UnsupportedOperationException("Remove operation is not supported")
        }
    }

    override fun iterator() = LinkedListDequeIterator()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LinkedListDeque61B<*>) return false
        if (size != other.size) return false

        var currentThis = sentinel.next
        var currentOther = other.sentinel.next

        while (currentThis != sentinel) {
            if (currentThis.item != currentOther.item)
                return false

            currentThis = currentThis.next
            currentOther = currentOther.next
        }
        return true
    }

    override fun hashCode(): Int {
        var result = 1
        var current = sentinel.next
        while (current != sentinel) {
            result = 31 * result + current.item!!.hashCode()
            current = current.next
        }
        return result
    }

    override fun toString() = toList().toString()

    override fun addFirst(x: T) {
        val newNode = Node(x)
        newNode.next = sentinel.next
        newNode.prev = sentinel
        sentinel.next.prev = newNode
        sentinel.next = newNode
        ++size
    }

    override fun addLast(x: T) {
        val newNode = Node(x)
        newNode.prev = sentinel.prev
        newNode.next = sentinel
        sentinel.prev.next = newNode
        sentinel.prev = newNode
        ++size
    }

    override fun toList(): List<T> {
        val result = mutableListOf<T>()
        var current = sentinel.next
        while (current != sentinel) {
            result.add(current.item!!)
            current = current.next
        }
        return result
    }

    override fun isEmpty() = size == 0

    override fun size() = size

    override fun removeFirst(): T? {
        if (isEmpty)
            return null

        val firstNode = sentinel.next
        sentinel.next = firstNode.next
        firstNode.next.prev = sentinel
        --size
        return firstNode.item
    }

    override fun removeLast(): T? {
        if (isEmpty)
            return null

        val lastNode = sentinel.prev
        sentinel.prev = lastNode.prev
        lastNode.prev.next = sentinel
        --size
        return lastNode.item
    }

    override fun get(index: Int): T? {
        if (index < 0 || index >= size)
            return null

        var current = sentinel.next
        repeat(index) {
            current = current.next
        }
        return current.item
    }

    override fun getRecursive(index: Int): T? {
        if (index < 0 || index >= size)
            return null

        fun getRecursiveHelper(node: Node, index: Int): T =
            if (index == 0)
                node.item!!
            else
                getRecursiveHelper(node.next, index - 1)


        return getRecursiveHelper(sentinel.next, index)
    }
}
