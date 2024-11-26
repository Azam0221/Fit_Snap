package com.example.dietsnap.api

import kotlinx.coroutines.flow.MutableStateFlow

data class HomePageResponse(
    val success: Boolean,
    val data: List<HomePageData>
)

data class HomePageData(
    val _id: String,
    val image_url: String,
    val name: String,
    val description: String,

    val health_rating: Int
)

data class FoodInfoResponse(
    val success: Boolean,
    val data: FoodInfoData
)

data class FoodInfoData(
    val image_url: String = " http://52.25.229.242:8000/mastermenu/10470.png",
    val description: String,
    val generic_facts: List<String>,
    val serving_sizes : List<NutritionInfo>,
   val  nutrition_info_scaled: List<NutritionInfo>,
    val health_rating: Int,
    val similar_items: List<SimilarItem>
)

data class NutritionInfo(
    val name: String,
    val value: Double,
    val units: String
)
data class DisplayNutrition(
    val name: String,
    val perServe: String,
    val per250gm: String
){
    companion object {
        // Function to format numbers to show 1 decimal place
        fun formatValue(value: Double): String {
            return String.format("%.1f", value)  // Limits to 1 decimal place
        }
    }
}
data class SimilarItem(
    val id: String,
    val name: String,
    val image: String,


)

