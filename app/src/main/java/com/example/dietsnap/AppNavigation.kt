package com.example.dietsnap

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dietsnap.dietSnap.DietSnap
import com.example.dietsnap.dietSnap.DietsnapViewModel
import com.example.dietsnap.dietSnap.FoodPageScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(modifier: Modifier = Modifier){

    val navController = rememberNavController()
    val viewModel = DietsnapViewModel()

    NavHost(
        navController = navController,
        startDestination = "home",
        builder = {
            composable("home"){
                DietSnap(navController,viewModel)
            }
            composable("food"){
                FoodPageScreen(navController,viewModel)
            }
        }
    )

}