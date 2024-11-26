package com.example.dietsnap

import com.example.dietsnap.api.FoodInfoResponse
import com.example.dietsnap.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DietsnapRepository {

    suspend fun getFoodData():FoodInfoResponse{
        return withContext(Dispatchers.IO){
            RetrofitInstance.retrofit.getFoodInfoData()
        }
    }
}