package com.salvador.artapp.ui.screens.home_screen

import android.util.Log
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
import kotlin.collections.HashMap
import kotlin.math.abs


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

    var arr1 = intArrayOf(1, 3, 2, 4, 5, 6, 0, -1)
    var arr2 = intArrayOf(1, 2, 3, 4, 5, 99, -5)
    var arr3 = charArrayOf('a', 'b', 'c', 'd')

    var multiArr1 = arrayOf(
        arrayOf('a','b','c'),
        arrayOf('d','e','f'),
        arrayOf('g','h','i')
    )

    var newNumber = 999



    init {
        loadAllArtworks()
        reverseWithPointers(arr3)

    }





    fun reverseWithPointers(arr: CharArray): CharArray {
        var left = 0
        var right = arr.size - 1

        while (left < right ){
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
        for(i in arr.indices){
            reversedArray[i] = arr[arr.size - 1 - i]
        }
        println(reversedArray.contentToString())
        return reversedArray
    }

    fun getCoordsForChar(multiArr1: Array<Array<Char>>, key: Char): IntArray {
        var returnArray: IntArray  = intArrayOf()
        for(row in multiArr1){
            for (element in row){
                if(element == key) {
                    returnArray = intArrayOf(multiArr1.indexOf(row), row.indexOf(element), )
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

        for(i in arr){
            if(i > higest){
                higest = i
            }
            if (i < lowest){
                lowest = i
            }
        }

        return intArrayOf(higest, lowest)
    }

    fun areEqualWithHash2(arr1: IntArray, arr2: IntArray): Boolean {
        val map:MutableMap<Int, Int> = mutableMapOf()
        for (i in arr1.indices){
            map.put(arr1[i], i, )
        }
        for (i in arr2.indices) {
            if (map.containsKey(arr2[i])){
                map.remove(arr2[i])
            }
        }
        return map.isEmpty()
    }

        fun areEqualWithHash(arr1: IntArray, arr2: IntArray): Boolean {
        var count = 0
        val map: MutableMap<Int, Int>  = mutableMapOf()

        if (arr1.size != arr2.size) return false

        for (i in arr1.indices){
            if (!map.containsKey(arr1[i])){
                map.put(arr1[i], 1)
            } else {
                count = map.get(arr1[i])!!
                count++
                map.put(arr1[i], count)
            }
        }
        for(i in arr2.indices) {
            if(!map.containsKey(arr2[i])){
                return false
            }
            if (map.get(arr2[i]) == 0){
                return false
            }
            count = map.get(arr2[i])!!
            --count
            map.put(arr2[i], count)
        }
        return true
    }

   fun areTheyTheSame(arr1: IntArray, arr2: IntArray): Boolean {
       if(arr1.size != arr2.size) return false

       Arrays.sort(arr1)
       Arrays.sort(arr2)

       for (i in arr1.indices) {
           if(arr1[i] != arr2[i]) return false
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