class LinkedListDeque61B<T> : Deque61B<T> {
    private inner class Node(val item: T?) {
        lateinit var next: Node
        lateinit var prev: Node
    }

    private var sentinel: Node = Node(null)
    private var size = 0

    init {
        sentinel.next = sentinel
        sentinel.prev = sentinel
    }

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

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun size(): Int {
        return size
    }

    override fun removeFirst(): T? {
        if (isEmpty) {
            return null
        }

        val firstNode = sentinel.next
        sentinel.next = firstNode.next
        firstNode.next.prev = sentinel
        --size
        return firstNode.item
    }

    override fun removeLast(): T? {
        if (isEmpty) {
            return null
        }

        val lastNode = sentinel.prev
        sentinel.prev = lastNode.prev
        lastNode.prev.next = sentinel
        --size
        return lastNode.item
    }

    override fun get(index: Int): T? {
        if (index < 0 || index >= size) {
            return null
        }

        var current = sentinel.next
        repeat(index) {
            current = current.next
        }
        return current.item
    }

    override fun getRecursive(index: Int): T? {
        if (index < 0 || index >= size) {
            return null
        }

        fun getRecursiveHelper(node: Node, index: Int): T {
            return if (index == 0) {
                node.item!!
            } else {
                getRecursiveHelper(node.next, index - 1)
            }
        }

        return getRecursiveHelper(sentinel.next, index)
    }
}
