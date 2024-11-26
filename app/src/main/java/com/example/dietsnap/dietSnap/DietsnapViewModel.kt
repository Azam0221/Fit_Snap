package com.example.dietsnap.dietSnap

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dietsnap.DietsnapRepository
import com.example.dietsnap.api.FoodInfoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class DietsnapViewModel: ViewModel() {

    private val dietsnapRepository = DietsnapRepository()

    private  var _foodData = mutableStateOf<List<FoodInfoResponse>>(emptyList())
     val foodData: MutableState<List<FoodInfoResponse>> = _foodData


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDay(date: LocalDate): String{
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayOfMonth = date.dayOfMonth
        val month = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val trimMonth = month.substring(0,3)
        val ordinalSuffix = getOrdinalSuffix(dayOfMonth)
        return "$dayOfWeek, $dayOfMonth$ordinalSuffix $trimMonth"
    }

    fun getOrdinalSuffix(day:Int): String{
        return when{
            day in 11..13 -> "th"
            day in 21..23 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th" }
    }

    fun getFoodInfo(){
        viewModelScope.launch {
            val foodInfo = dietsnapRepository.getFoodData()
            _foodData.value = listOf(foodInfo)

        }
    }





}