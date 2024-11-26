package com.example.dietsnap.dietSnap

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dietsnap.R
import com.example.dietsnap.data.NavItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun DietSnap(navController: NavController,viewModel: DietsnapViewModel, modifier: Modifier = Modifier){

    val navItemList = listOf(
        NavItem("home", R.drawable.activity, modifier= modifier.size(60.dp)),
        NavItem("Goals", R.drawable.goals, modifier= modifier.size(60.dp)),
        NavItem("Camera", R.drawable.camera, modifier= modifier.size(35.dp)),
        NavItem("Feed", R.drawable.feed, modifier= modifier.size(60.dp)),
        NavItem("profile",R.drawable.profile, modifier= modifier.size(60.dp)),
    )
    var selectedIndex by remember {
        mutableIntStateOf(5)
    }
    when(selectedIndex){
        0 ->{}
        1 ->{}
        2 ->{}
        3 ->{}
        4 ->{}
    }
    Scaffold (
        topBar = {
            Box(modifier = modifier.fillMaxWidth()
                .shadow(
                    elevation = 8.dp
                )){
            TopAppBar(title = {
               Row(modifier = modifier.padding(top = 8.dp)){
                   Text(text = "Dietsnap",
                       color = Color(0xFFF8B645),
                       fontWeight = FontWeight.W500,
                       modifier = modifier.padding(start = 4.dp)
                   )
                   Spacer(modifier.weight(1f))
                   Icon(
                       painter = painterResource(R.drawable.notification),
                       contentDescription = "notification",
                       modifier = modifier.size(25.dp)
                   )
                   Spacer(modifier.padding(start = 8.dp, end = 6.dp))
                   Icon(
                       painter = painterResource(R.drawable.star),
                       contentDescription = "star",
                       modifier = modifier.size(25.dp)
                   )
                   Spacer(modifier.padding(start = 8.dp, end = 6.dp))

                   Icon(
                       painter = painterResource(R.drawable.message),
                       contentDescription = "message",
                       modifier = modifier.size(25.dp)
                   )
                   Spacer(modifier.padding(start = 8.dp, end = 6.dp))

               }
            })}

        },
        bottomBar = {

            Box (modifier = modifier.padding(top =12.dp)) {
                NavigationBar(modifier = Modifier.height(90.dp)) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {selectedIndex = index},
                            icon = {
                                Icon(
                                    painter = painterResource(navItem.icon), contentDescription = navItem.label,
                                    modifier = navItem.modifier

                                )
                            },
                            label = {
                                navItem.label
                            }
                        )
                    }
                }
            }
        }
    ){innerPadding ->
        Column(modifier = modifier.padding(innerPadding)
            .verticalScroll(rememberScrollState())

        ) {
            val pageCount = 2
            val coroutineScope = rememberCoroutineScope()


            val pagerState = rememberPagerState(
                pageCount = { pageCount },
                initialPage = 0
            )

            val  progress  = 0.01f
            val  progressExercise = 0.01f
            Column(modifier = modifier.padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Today",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = viewModel.formatDay(LocalDate.now()),
                    color = Color.Gray
                )
                Spacer(modifier = modifier.padding(bottom = 24.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = modifier.padding(0.dp)
                ) { page->
                    when(page){
               0 -> { Column(modifier = modifier.padding(0.dp).height(325.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){


                   Row(){
                       Spacer(modifier = modifier.padding(start = 95.dp))
                Canvas(modifier = Modifier.size(200.dp)) {
                    val ringWidthDiet = 22f
                    val ringWidthExercise = 22f
                    val sweepAngleDiet = 360f * progress
                    val sweepAngleExercise =
                        360f * progressExercise



                    drawArc(
                        color = Color(0xFFFAA057).copy(alpha = 0.5f),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = ringWidthDiet),
                        size = size
                    )
                    drawArc(
                        color = Color(0xFFFAA057),
                        startAngle = -90f,
                        sweepAngle = sweepAngleDiet,
                        useCenter = false,
                        style = Stroke(
                            width = ringWidthDiet + 4f,
                            cap = StrokeCap.Round
                        )
                    )
                    drawArc(
                        color = Color(0xFF9013FE).copy(0.5f),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = ringWidthExercise),
                        size = size.copy(
                            width = size.width - 50.dp.toPx(),
                            height = size.height - 50.dp.toPx()
                        ),
                        topLeft = Offset(67f, 70f)
                    )
                  // Change 1

                    drawContext.canvas.nativeCanvas.apply {

                        drawContext.canvas.nativeCanvas.apply {
                            val centerX = size.width / 2
                            val centerY = size.height / 2
                            val text = "SET GOAL"
                            val textSize = 48f

                            val paint = android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textAlign = android.graphics.Paint.Align.CENTER
                                this.textSize = textSize
                                isAntiAlias = true
                                typeface = android.graphics.Typeface.DEFAULT_BOLD
                            }
                            val verticalOffset = (paint.descent() + paint.ascent()) / 2


                            drawText(
                                text,
                                centerX,
                                centerY - verticalOffset,
                                paint
                            )
                        }
                    }


                    drawArc(
                        color = Color(0xFF9013FE),
                        startAngle = -90f,
                        sweepAngle = sweepAngleExercise,
                        useCenter = false,
                        style = Stroke(
                            width = ringWidthExercise + 4f,
                            cap = StrokeCap.Round
                        ),
                        size = size.copy(
                            width = size.width - 50.dp.toPx(),
                            height = size.height - 50.dp.toPx()
                        ),
                        topLeft = Offset(67f, 70f) // Center th
                    )

                }
                       Spacer(modifier = modifier.padding(start = 45.dp))
                   IconButton(
                       onClick = {
                           coroutineScope.launch {
                               delay(100L)
                               if (pagerState.currentPage < pagerState.pageCount - 1) {
                                   pagerState.animateScrollToPage(pagerState.currentPage + 1)
                               }
                           }
                       },
                       modifier = modifier.padding(top = 80.dp)
                   ) {
                       Icon(
                           imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                           contentDescription = "Next",
                           tint = Color.Gray
                       )
                   }}
                Spacer(modifier = modifier.padding(bottom = 12.dp))

                Row(modifier = modifier) {

                    Image(
                        painter = painterResource(R.drawable.apple),
                        contentDescription = "apple",
                        modifier = modifier.size(24.dp)
                    )
                    Spacer(modifier = modifier.padding(start = 4.dp))
                    Text(
                        text = "Diet Pts",
                        modifier = modifier.padding(top = 8.dp)
                    )
                    Spacer(modifier = modifier.padding(start = 20.dp))

                    Image(
                        painter = painterResource(R.drawable.dumble),
                        contentDescription = "dumble",
                        modifier = modifier.padding(top = 4.dp).size(24.dp)
                    )
                    Spacer(modifier = modifier.padding(start = 4.dp))
                    Text(
                        text = "Exercise Pts",
                        modifier = modifier.padding(top = 8.dp)
                    )
                }
                Spacer(modifier = modifier.padding(top = 12.dp))
                Row(modifier = modifier.fillMaxWidth().padding(start = 90.dp)) {
                    Column {
                        Text(
                            text = "1500",
                            color = Color(0xFFF8B645)
                        )
                        Spacer(modifier = modifier.padding(top = 4.dp))
                        Text(
                            text = "Cal",
                            modifier = modifier.padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = modifier.padding(start = 55.dp))

                    Column {
                        Text(
                            text = "3/5",
                            color = Color(0xFFF8B645),
                            modifier = modifier.padding(start = 6.dp)
                        )
                        Spacer(modifier = modifier.padding(top = 4.dp))
                        Text(
                            text = "Days",
                            modifier = modifier.padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = modifier.padding(start = 50.dp))

                    Column {
                        Text(
                            text = "88",
                            color = Color(0xFFF8B645),
                            modifier = modifier.padding(start = 12.dp)
                        )
                        Spacer(modifier = modifier.padding(top = 8.dp))
                        Text(
                            text = "Health Score",
                            modifier = modifier.padding(end = 10.dp)
                        )
                    }
                }

            }}

                    1 ->{
                        Column(modifier = modifier.height(325.dp)){

                            Row(modifier = modifier.fillMaxWidth().padding(start = 16.dp)){
                                Column(modifier = modifier.padding(0.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally) {

                                    IconButton(
                                        onClick = {
                                            coroutineScope.launch {
                                                delay(100L)
                                                if (pagerState.currentPage > 0) {
                                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                                }
                                            }
                                        },
                                        modifier = modifier.padding(top = 80.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                            contentDescription = "Previous",
                                            tint = Color.Black
                                        )
                                    }
                                    //Change 1

                                    }
                                TextButton(onClick = {navController.navigate("food")}) {
                                    Text(text = "Today's Special Diet",
                                        fontSize = 24.sp,
                                        modifier = modifier.padding(start = 30.dp, end = 30.dp)
                                    )
                                }
                                

                            }

                        }
                    }
                    }
            }}
            Row(
                modifier = modifier.padding(start = 180.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pageCount) { index ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                if (pagerState.currentPage == index) Color(0xFFFAA057)
                                else Color.Gray.copy(alpha = 0.2f)
                            )
                            .padding(horizontal = 4.dp)

                    )
                    Spacer(modifier = modifier.padding(4.dp))
                }

            }
            Spacer(
                modifier = modifier.padding(top = 24.dp)
            )
            Text(text = " Your Goals",
                fontSize = 24.sp,
            modifier = modifier.padding(start = 12.dp)
            )

            Spacer(
                modifier = modifier.padding(top = 24.dp)
            )
            Card(modifier = modifier.fillMaxWidth().height(80.dp).padding(start = 10.dp, end = 10.dp)){
    Column(modifier.padding(8.dp)) {
        Row(modifier = modifier.fillMaxWidth()){
    Image(
        painter = painterResource(R.drawable._bitmapworkout),
        contentDescription = "workout",
        modifier = modifier.size(65.dp)
    )
    Row(modifier = modifier.padding(start = 8.dp)){
        Column {
            Text(
                "Keto Weight loss plan",
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(text = "Current Major Goal",
                fontSize = 16.sp,
                color = Color.Gray)
        }
    }
            Spacer(modifier = modifier.padding(start =30.dp))
            Column(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)){
            val progressWorkout = 0.73f
            Canvas(modifier = Modifier.size(55.dp).padding(end = 8.dp)) {

                val ringWidth = 12f
                val sweepAngle = 360f * progressWorkout

                // Background ring
                drawArc(
                    color = Color(0xFFFAA057).copy(alpha = 0.1f),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = ringWidth)
                )

                // Foreground ring
                drawArc(
                    color = Color(0xFFFAA057),
                    startAngle = -90f, // Starts at the top
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(
                        width = ringWidth + 4f,
                        cap = StrokeCap.Round
                    )
                )
                drawContext.canvas.nativeCanvas.apply {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val text = "73%"
                    val textSize = 38f // Adjust as needed

                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK // Text color
                        textAlign = android.graphics.Paint.Align.CENTER
                        this.textSize = textSize // Directly using textSize value
                        isAntiAlias = true

                    }


                    val verticalOffset = (paint.descent() + paint.ascent()) / 2

                    // Draw text at the center
                    drawText(
                        text,
                        centerX,
                        centerY - verticalOffset,
                        paint
                    )
                }
            }

            }

}}
            }
            Text(text = "Explore",
                fontSize = 24.sp,
                modifier = modifier.padding(start = 14.dp,top = 24.dp, bottom = 24.dp)
              )
            Column {
                Row (modifier.fillMaxWidth().padding(start = 8.dp)){
                    Image(
                        painter = painterResource(R.drawable.player),
                        contentDescription = "player",
                        modifier = modifier.size(65.dp)
                    )
                    Column{
                    Text(text = "Find Diets",
                       fontSize = 18.sp)
                        Spacer(
                            modifier = modifier.padding(top = 12.dp)
                        )

                    Text(text = "Find premade diets according to your cuisine",
                        color = Color.Gray)
                    }
                }
                Spacer(
                    modifier = modifier.padding(top = 16.dp)
                )
                Row (modifier.fillMaxWidth().padding(start = 8.dp)){
                    Image(
                        painter = painterResource(R.drawable.nutrition),
                        contentDescription = "nutrition",
                        modifier = modifier.size(65.dp)
                    )
                    Column{
                        Text(text = "Find Nutritionist",
                            fontSize = 18.sp)
                        Spacer(
                            modifier = modifier.padding(top = 12.dp)
                        )

                        Text(text = "Get customized diets to achieve your health \n" +
                                "goal",
                            color = Color.Gray)
                    }
                    Spacer(modifier.padding(bottom = 50.dp))
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DietSnapPreview(){
    DietSnap(navController = rememberNavController(), viewModel = DietsnapViewModel())
}

