package com.example.dietsnap.dietSnap

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dietsnap.R
import com.example.dietsnap.api.DisplayNutrition
import com.example.dietsnap.api.FoodInfoResponse


@Composable
fun FoodPageScreen(navController: NavController,viewModel: DietsnapViewModel,modifier: Modifier = Modifier){

    LaunchedEffect(Unit){
        viewModel.getFoodInfo() }
    val foodInfo = viewModel.foodData.value

    Scaffold { innerPadding ->
        if (foodInfo.isNotEmpty()) {
        Text(text = "hello")
        Column(modifier = modifier.padding(innerPadding)){
            LazyColumn {
                items(foodInfo.size){index ->
                    val food = foodInfo[index]
                    FoodPage(food,navController)

                }

            }

        }}
        else {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Loading...")
            }
        }
    }
}


@Composable
fun FoodPage(foodInfoResponse: FoodInfoResponse,navController: NavController,modifier:Modifier = Modifier) {

        Column(modifier = modifier.fillMaxSize()){
            val selectedNutrientsOne = foodInfoResponse.data.serving_sizes.filter { it.name in listOf(
                "Energy", "Protein", "Trans Fat", "Saturated Fat", "Carbohydrates", "Fibre"
            )}
            val selectedNutrientsTwo = foodInfoResponse.data.nutrition_info_scaled.filter { it.name in listOf(
                "Energy", "Protein", "Trans Fat", "Saturated Fat", "Carbohydrates", "Fibre"
            )}
            val displayNutritionOne = selectedNutrientsOne.map{ nutrient ->
                DisplayNutrition(
                    name = nutrient.name,
                    perServe = "${DisplayNutrition.formatValue(nutrient.value)} ${nutrient.units}",
                    per250gm = "${DisplayNutrition.formatValue(nutrient.value * (250.0 / 100.0))} ${nutrient.units}"
                )
            }
            val displayNutritionTwo = selectedNutrientsTwo.map{ nutrient ->
                DisplayNutrition(
                    name = nutrient.name,
                    perServe = "${DisplayNutrition.formatValue(nutrient.value)} ${nutrient.units}",
                    per250gm = "${DisplayNutrition.formatValue(nutrient.value * (250.0 / 100.0))} ${nutrient.units}"
                )
            }
            Log.d("NutritionInfo", "nutrition_info_scaled: ${foodInfoResponse.data.serving_sizes}")

            Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.biryani_1), // Replace with your image resource
                contentDescription = "Image",
                modifier = modifier.size(500.dp).fillMaxWidth().padding(bottom = 200.dp)


            )
            Row(modifier = modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.Start){
                IconButton(
                    onClick = {navController.navigate("home")},

                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = modifier.size(40.dp)
                    )
                }


            Text(
                text = "Back",
                color = Color.White,
                fontSize = 24.sp,
                modifier = modifier.padding(top = 10.dp)

            )}
            Row(modifier =modifier.padding(0.dp) ){
            // Text composable to overlay custom text on the image
                Column() {
                    Text(
                        text = "Food Information",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(top = 200.dp, start = 16.dp)

                    )
                    Text(
                        text = "Biryani",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(top = 10.dp, start = 16.dp)

                    )
                }
                Card(modifier = modifier.padding(start = 135.dp, top = 170.dp).size(80.dp)
                    ){
                    Column(modifier = modifier.fillMaxSize().background(Color(0xFF707070).copy(alpha = 0.5f))
                        .clip(RoundedCornerShape(16.dp))){
                        Text(text = "71",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier  = modifier.padding(start = 26.dp, top = 16.dp))
                        Text(
                            text = "out of 100",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier.padding(top = 10.dp, start = 16.dp)

                        )
                    }

                }}
            Column() {
                Text(
                    text = "Description",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(top = 330.dp, start = 16.dp)

                )
                Spacer(modifier = modifier.padding(top = 8.dp))
                Text(
                    text =  "Chicken biryani is a popular Indian dish made with fragrant basmati rice, tender chicken, and a blend of aromatic spices.",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = modifier.padding( start = 16.dp, end = 16.dp)
                )}}
            Column(){
                Text(
                    text = "Macro Nutrients",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(start = 16.dp)

                )
                Spacer(modifier = modifier.padding(top = 12.dp))

                Card(modifier = modifier.fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    //elevation = CardDefaults.elevatedCardElevation(4.dp)
                ){
                    Column(modifier = modifier.fillMaxSize().
                    padding(8.dp)){
                        Row(modifier = modifier.fillMaxWidth()
                            .padding( 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Text(
                                text = "Nutrient",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Per Serve",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                            Text(
                                text = "Per 250 gm",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )

                        }
                        Divider(
                            color = Color.Black,
                            modifier = modifier.padding(4.dp, 0.dp, 0.dp, 0.dp),
                            thickness = 1.dp // Thickness of the line
                        )

                        displayNutritionOne.forEach { item ->
                            Row(modifier = modifier.fillMaxWidth()
                                .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,){
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = item.perServe,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                                Text(
                                    text = item.per250gm,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }

                        }
                        displayNutritionTwo.forEach { item ->
                            Row(modifier = modifier.fillMaxWidth()
                                .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,){
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = item.perServe,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                                Text(
                                    text = item.per250gm,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }

                        }


                    }


                }
                Spacer(modifier = modifier.padding(top = 20.dp))
                Text(text = "Facts ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(start = 16.dp)
                )
                Spacer(modifier = modifier.padding(top = 16.dp))
                Card(modifier = modifier.fillMaxWidth().size(200.dp).padding(start = 16.dp, end = 16.dp)
                    .background(color = Color(0xFFF8B944),
                        shape = RoundedCornerShape(12.dp)

                    )) {

                    val doYouKnow = foodInfoResponse.data.generic_facts[3]
                    Column(modifier = modifier.fillMaxSize()
                        .background(color = Color(0xFFF8B944))
                       ) {
                        Spacer(modifier = modifier.padding(top = 16.dp))
                        Text(text = "DidYouKnow",
                            color = Color.White,
                            fontSize = 22.sp,
                            modifier = modifier.padding(start = 50.dp))
                        Spacer(modifier = modifier.padding(top = 16.dp))
                        Text(text = doYouKnow,
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = modifier.padding(start = 16.dp, end = 16.dp))
                    }

                }
                Spacer(modifier = modifier.padding(top = 20.dp))
                Text(text = "Similar Items",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(start = 16.dp)
                )
                Spacer(modifier = modifier.padding(top = 16.dp))

                Column(modifier = modifier.padding(bottom = 30.dp)) {

                }



            }






    }
}





