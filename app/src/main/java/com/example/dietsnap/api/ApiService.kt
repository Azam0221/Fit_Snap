package com.example.dietsnap.api

import retrofit2.http.GET

interface ApiService {
    @GET("homepage_v3/")
    suspend fun getHomeData() :HomePageResponse

    @GET("food_info/")
    suspend fun getFoodInfoData(): FoodInfoResponse
}