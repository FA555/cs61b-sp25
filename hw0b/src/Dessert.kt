class Dessert (
    private val flavor: Int,
    private val price: Int,
) {
    companion object {
        private var totalDesserts: Int = 0

        @JvmStatic
        fun main(args: Array<String>) {
            println("I love dessert!")
        }
    }

    init {
        ++totalDesserts
    }

    fun printDessert() {
        println("$flavor $price $totalDesserts")
    }
}
