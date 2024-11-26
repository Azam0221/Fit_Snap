package com.example.dietsnap

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.dietsnap.dietSnap.DietSnap
import com.example.dietsnap.dietSnap.DietsnapViewModel
import com.example.dietsnap.dietSnap.FoodPageScreen
import com.example.dietsnap.ui.theme.DietSnapTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

       AppNavigation()


        }
    }
}
