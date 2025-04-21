package com.example.trafficlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.trafficlight.ui.theme.TrafficLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[TrafficLightViewModel::class.java]
        setContent {
            TrafficLightTheme {
                TrafficLightScreen(viewModel)
            }
        }
    }
}

@Composable
fun TrafficLightScreen(viewModel: TrafficLightViewModel) {
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Red Light
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = if (state == TrafficLightState.RED) Color.Red else Color.Gray,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Yellow Light
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = if (state == TrafficLightState.YELLOW) Color.Yellow else Color.Gray,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Green Light
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = if (state == TrafficLightState.GREEN) Color.Green else Color.Gray,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { viewModel.changeLight() }) {
            Text("Switch Light")
        }
    }
}