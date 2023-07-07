package com.salvador.artapp.ui.screens.home_screen

import android.util.Log
import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.ConfigModel
import com.salvador.artapp.domain.domain_models.list.PaginationModel
import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.domain.use_cases.GetArtworksUseCase
import com.salvador.artapp.domain.use_cases.GetRandomArtUseCase
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val getArtworksUseCase: GetArtworksUseCase,
    private val getRandomArtUseCase: GetRandomArtUseCase,
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState(isLoading = true))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()


    private val _randomArt = MutableStateFlow(listOf<RandomImageModel>())
    val randomArt: StateFlow<List<RandomImageModel>> = _randomArt

    val getAllImages = artworkRepository.getAllImagesModelsRemoteMediator()

//    val art: Flow<PagingData<ArtworkModel>> = Pager(PagingConfig(pageSize = 20)) {
//        ArtSource(getArtworksUseCase)
//    }.flow


    var artPage = 1

    var arr1 = intArrayOf(15, 2, 4, 8, 9, 5, 10, 23 )
    var arr2 = intArrayOf(1, 2, 3, 4, 5, 99, -5)
    var arr3 = charArrayOf('a', 'b', 'c', 'd')


    var multiArr1 = arrayOf(
        arrayOf('a', 'b', 'c'),
        arrayOf('d', 'e', 'f'),
        arrayOf('g', 'h', 'i')
    )

    var newNumber = 999

    val stocks = intArrayOf(7, 1, 5, 3, 6, 4, 4, 7, 7, 7)
    val stocks2 = intArrayOf(1,1,1,2,2,3)
    val arrWithDups = intArrayOf(0,1,2,2,3,0,4,2)
    val twosomarr = intArrayOf(3,2,4)
    init {
        loadAllArtworks()
        topKFrequent(stocks2, 2)
    }


    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        val returnArray = IntArray(k)

        for( i in nums.indices) {
            val bool = map.containsKey(nums[i])
            if(!bool) {
                map.put(nums[i], 1)
            } else {
                map.put(nums[i], map.getValue(nums[i]) + 1)
            }
        }
        map.onEachIndexed { index, entry ->  }
        for (i in 1 ..k){
            println("i = $i, entry = ${map.getValue(i)}")
            returnArray[i-1] = map.getValue(i)
        }

        println(map.size)
        println(map.get(k))
        println("RETURN ARRAY ${returnArray.contentToString()}")
        return returnArray
    }

    fun removeDupes3(nums: IntArray,): Int {
        var counter = 0
        for (i in 1 until nums.size){
            if (nums[counter] != nums[i]){
                counter++
                nums[counter] = nums[i]
            }
        }
        println(nums.contentToString())
        return counter +1
    }

    fun removeDuplicates2(nums: IntArray, `val`: Int): Int {
        var counter = 0
        for(i in 0 until nums.size){
            if(nums[i] != `val`){

                nums[counter] = nums[i]
                counter++
            }
        }
        println(nums.contentToString())
        println(counter)
        return counter
    }


    fun plusOne(digits: IntArray): IntArray {
        var newArray = IntArray(digits.size)
        var hold = digits.last() + 1
        println(hold)
        return newArray
    }


    fun negAndPos2(arr: IntArray ): IntArray {
        val answer = IntArray(arr.size)
        var left = 0
        var right = arr.size - 1
        while (left <= right ){
            if(arr[left] < 0 && arr[right] < 0) {
                left++
            }
        }


        println(answer.contentToString())
        return answer
    }

    fun negAndPos(arr: IntArray ): IntArray {
        val answer = arr.sortedArray()
        println(answer.contentToString())
        return answer
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right =1
        var currentSum = nums[left]
        while (right > left && right < nums.size){
            currentSum += nums[right]
            right++
            while (currentSum > target){
                currentSum -= nums[left]
                left++
            }
        }
        val answer = intArrayOf(left, right)
        println(answer.contentToString())
        return answer
    }

    fun findSubArrWithGivnSum_slidingWindow(arr: IntArray, sum: Int): IntArray {
        var left = 0
        var right = 1
        var currentCount = arr[left]

        while(right > left && right< arr.size){
            currentCount += arr[right]
            right++
            while(currentCount > sum) {
                currentCount -= arr[left]
                left++
            }
        }
        println("Sum $sum, left index ${left - 1} right index ${right - 1}")
        println("left - ${left - 1}, right - ${right - 1}")

        return intArrayOf(currentCount)
    }

    fun findSubArrWithGivnSum(arr: IntArray, sum: Int): IntArray {

        var currentSum = 0

       for(i in arr.indices){
           currentSum = arr[i]
           if (currentSum == sum) {
               println("Sum found at index $i")
           } else {
               for (j in i+ 1 until arr.size) {
                   currentSum += arr[j]
                   if (currentSum == sum) {
                       println("Sum $sum found at index $i and $j")
                   }
               }
           }
       }

        return intArrayOf(currentSum)

    }

    fun kThSmallestNumber(arr: IntArray, k: Int): Int {
        val sortedArr = arr.sortedArray()
        println(sortedArr[k-1])
        return sortedArr[k-1]
    }

    fun freqInArr(key: Int, arr: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        for(i in arr.indices){
            if(map.containsKey(key)){
                map.put(arr[i], map[key]!! + 1)
            }else{
                map.put(key, 1)
            }
        }
        println(map.get(key))
        return map.get(key)!!
    }

    fun containsDuplicate(nums: IntArray): Boolean {

        val map = mutableMapOf<Int, Int>()
        var bool = false
       for( i in nums.indices) {
           if(map.containsKey(nums[i])) {
               bool = true
           } else {
               map.put(nums[i], i)
               bool = false
           }
       }
        return bool
    }

    fun maxProfit2(prices: IntArray): Int {

        var left = 0
        var right = 1
        var maxProfit = 0

        while (right < prices.size) {
            if (prices[right] > prices[left]) {
                val temp = prices[right] - prices[left]
                if (temp > maxProfit) {
                    maxProfit = temp
                }
                right++
            } else {
                left = right
                right++
            }
        }

        return maxProfit
    }

    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        var lowest = prices[0]
        val buyDay = 0
        var highest = 0
        var sellDay = 0

        for (i in prices.indices) {
            if (lowest > prices[i]) {
                lowest = prices[i]
            }
        }
        for (j in buyDay until prices.size) {
            if (highest < prices[j]) {
                highest = prices[j]
                sellDay = j
            }
        }
        val maxProfit = highest - lowest - 1
        if (buyDay == prices.indexOf(lowest)) {
            println("max profit is 0 , bought on last day")
            return 0
        } else {
            println("max profit is $${maxProfit}")

            return maxProfit

        }
    }

    fun reverseWithPointers(arr: CharArray): CharArray {
        var left = 0
        var right = arr.size - 1

        while (left < right) {
            val temp = arr[left]
            arr[left] = arr[right]
            arr[right] = temp
            left++
            right--
        }
        println(arr.contentToString())
        return arr
    }

    fun reverseArray(arr: CharArray): CharArray {
        val reversedArray = CharArray(arr.size)
        for (i in arr.indices) {
            reversedArray[i] = arr[arr.size - 1 - i]
        }
        println(reversedArray.contentToString())
        return reversedArray
    }

    fun getCoordsForChar(multiArr1: Array<Array<Char>>, key: Char): IntArray {
        var returnArray: IntArray = intArrayOf()
        for (row in multiArr1) {
            for (element in row) {
                if (element == key) {
                    returnArray = intArrayOf(multiArr1.indexOf(row), row.indexOf(element))
                    println(returnArray.contentToString())
                }
            }
        }
        return returnArray
    }

    fun printCoords(arr: Array<Array<Char>>) {
        for (row in arr) {
            println(row.contentToString())
        }
    }

    fun lowestAndHighest(arr: IntArray): IntArray {
        var lowest = 0
        var higest = 0

        for (i in arr) {
            if (i > higest) {
                higest = i
            }
            if (i < lowest) {
                lowest = i
            }
        }
        val answer = intArrayOf(higest, lowest)
        println(answer.contentToString())
        return answer
    }

    fun areEqualWithHash2(arr1: IntArray, arr2: IntArray): Boolean {
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (i in arr1.indices) {
            map.put(arr1[i], i)
        }
        for (i in arr2.indices) {
            if (map.containsKey(arr2[i])) {
                map.remove(arr2[i])
            }
        }
        return map.isEmpty()
    }

    fun areEqualWithHash(arr1: IntArray, arr2: IntArray): Boolean {
        var count = 0
        val map: MutableMap<Int, Int> = mutableMapOf()

        if (arr1.size != arr2.size) return false

        for (i in arr1.indices) {
            if (!map.containsKey(arr1[i])) {
                map.put(arr1[i], 1)
            } else {
                count = map.get(arr1[i])!!
                count++
                map.put(arr1[i], count)
            }
        }
        for (i in arr2.indices) {
            if (!map.containsKey(arr2[i])) {
                return false
            }
            if (map.get(arr2[i]) == 0) {
                return false
            }
            count = map.get(arr2[i])!!
            --count
            map.put(arr2[i], count)
        }
        return true
    }

    fun areTheyTheSame(arr1: IntArray, arr2: IntArray): Boolean {
        if (arr1.size != arr2.size) return false

        Arrays.sort(arr1)
        Arrays.sort(arr2)

        for (i in arr1.indices) {
            if (arr1[i] != arr2[i]) return false
        }
        return true
    }


    private fun loadAllArtworks() = viewModelScope.launch {

        try {
            val response = getArtworksUseCase(FIELD_TERMS, artPage)
            val artworks = response.artWorks
            val config = response.config
            val pagination = response.pagination
            if (artworks.isNotEmpty()) {
                setListUiState(
                    artworks = _listUiState.value.currentList + artworks,
                    isLoading = false,
                    pagination = pagination,
                    config = config,
                    currentPage = pagination.currentPage,
                    totalPages = pagination.totalPages
                )


                val randomArt = getRandomArtUseCase(1)
                _randomArt.emit(randomArt)

            }
        }
        catch (e: Exception) {
            Log.e("VM_LOAD_ERROR", e.stackTraceToString())
        }
    }

    private fun setListUiState(
        artworks: List<ArtworkModel>,
        isLoading: Boolean,
        config: ConfigModel,
        pagination: PaginationModel,
        currentPage: Int,
        totalPages: Int,
    ) {
        _listUiState.update {
            it.copy(
                currentList = artworks,
                isLoading = isLoading,
                config = config,
                pagination = pagination,
                currentPage = currentPage,
                totalPages = totalPages
            )
        }
    }

}