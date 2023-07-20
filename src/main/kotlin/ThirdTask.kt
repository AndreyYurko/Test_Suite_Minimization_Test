import kotlin.math.abs
import kotlin.math.ceil

data class SetWithSum(val sum: Int, val set: Set<String>)

class Example {
    // We expect that you will return a list of size 2
    companion object {

        // Splits a set with even size according to the task
        fun split(set: Map<String, Int>): List<Map<String, Int>> {
            // Split the set into two parts: left and right and calculate the total sum.
            val totalSum = set.values.sum()
            val nLeft = set.size / 2
            val nRight = ceil(set.size / 2.0).toInt()
            val itemList = set.map { it }

            // Calculate sums for all possible subsets for both parts
            val setOfSums1 = computePossibleSums(itemList.subList(0, nLeft))
            val setOfSums2 = computePossibleSums(itemList.subList(nLeft, set.size))

            // Find the set with minimum diff score using two subsets, one from each part, with size nRight
            // diff score = split1 - split2 = totalSum - split2 - split2 = totalSum - split2 * 2
            var bestDiff = Int.MAX_VALUE
            var bestSet: Set<String> = setOf()
            for (i in 0..nLeft) {
                val (diff, newSet) = getMinDiff(setOfSums1[i], setOfSums2[nRight-i], totalSum)
                if (diff < bestDiff) {
                    bestDiff = diff
                    bestSet = newSet
                }
            }

            // We need to return both sets, not only one, with prices
            return listOf(set.filter { bestSet.contains(it.key) }, set.filter { !bestSet.contains(it.key) })
        }

        // Chooses a set with best diff score
        private fun getMinDiff(leftPart: List<SetWithSum>, rightPart: List<SetWithSum>, totalSum: Int):
                Pair<Int, Set<String>> {
            var i = 0
            var j = rightPart.size - 1
            var bestDiff = Int.MAX_VALUE
            var bestSet: Set<String> = setOf()

            while (i < leftPart.size && j >= 0) {
                val leftSet = leftPart[i]
                val rightSet = rightPart[j]
                val diff = totalSum - (leftSet.sum + rightSet.sum) * 2
                if (abs(diff) < bestDiff) {
                    bestDiff = abs(diff)
                    bestSet = leftSet.set + rightSet.set
                }

                if (diff > 0) i++
                else j--

            }

            return Pair(bestDiff, bestSet)
        }

//       Computes all possible sums that can we get from the given set and sorts it for each size.
//       example: input = mutableMapOf(Pair("1", 1), Pair("2", 2), Pair("3", 3))
//       result:
//         i: List
//         0: [SetWithSum(sum=0, set=[])]
//         1: [SetWithSum(sum=1, set=[1]), SetWithSum(sum=2, set=[2]), SetWithSum(sum=3, set=[3])]
//         2: [SetWithSum(sum=3, set=[1, 2]), SetWithSum(sum=4, set=[1, 3]), SetWithSum(sum=5, set=[2, 3])]
//         3: [SetWithSum(sum=6, set=[1, 2, 3])]
        private fun computePossibleSums(set: List<Map.Entry<String, Int>>): List<List<SetWithSum>> {
            val sums: MutableMap<Int, MutableMap<Int, Set<String>>> = mutableMapOf()
            val n = set.size
            for (mask in 0 until (1 shl n)) {
                var sum = 0
                val setOfItems: MutableSet<String> = mutableSetOf()
                for (i in 0 until n) {
                    if ((mask shr i) and 1 == 1) {
                        sum += set[i].value
                        setOfItems.add(set[i].key)
                    }
                }
                sums.computeIfAbsent(Integer.bitCount(mask)) { mutableMapOf() }[sum] = setOfItems
            }
            return sums
                .entries
                .sortedBy { it.key }
                .map { (_, sumsToSets) ->
                    sumsToSets
                        .entries
                        .sortedBy { it.key }
                        .map { (k, v) -> SetWithSum(k, v) }
                }
        }
    }
}

fun main() {
    val test = mutableMapOf(Pair("3", 3), Pair("9", 9), Pair("7", 7), Pair("2", 2), Pair("10", 10))
    println(Example.split(test))
}