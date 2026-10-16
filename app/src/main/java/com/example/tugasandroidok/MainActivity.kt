package com.example.tugasandroidok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugasandroidok.ui.theme.TugasandroidokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TugasandroidokTheme {
                FruitSaladApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitSaladApp() {

    var currentStep by remember {
        mutableIntStateOf(1)
    }

    var tapCount by remember {
        mutableIntStateOf((2..4).random())
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Fruit Salad",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val imageId = when (currentStep) {
                    1 -> R.drawable.buah_utuh
                    2 -> R.drawable.buah_potong
                    3 -> R.drawable.salad_mayones
                    else -> R.drawable.mangkok_kosong
                }

                val description = when (currentStep) {
                    1 -> "Berbagai buah segar"
                    2 -> "Buah sudah dipotong"
                    3 -> "Fruit salad dengan mayones"
                    else -> "Mangkuk fruit salad kosong"
                }

                Image(
                    painter = painterResource(imageId),
                    contentDescription = description,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clickable {

                            when (currentStep) {
                                1 -> {
                                    currentStep = 2
                                    tapCount = (2..4).random()
                                }

                                2 -> {
                                    tapCount--

                                    if (tapCount <= 0) {
                                        currentStep = 3
                                    }
                                }

                                3 -> {
                                    currentStep = 4
                                }

                                4 -> {
                                    currentStep = 1
                                    tapCount = (2..4).random()
                                }
                            }
                        }
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = when (currentStep) {
                        1 -> "Tekan untuk memotong buah!"
                        2 -> "Tekan lagi untuk memasukkan kedalam wadah"
                        3 -> "Fruit salad siap dinikmati!"
                        else -> "Yummy! Saladnya habis"
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FruitSaladPreview() {
    TugasandroidokTheme {
        FruitSaladApp()
    }
}