package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ThermostatScreen()
                }
            }
        }
    }
}

// PADRE: es el único que guarda el estado (State Hoisting)
@Composable
fun ThermostatScreen() {
    var temperatura by remember { mutableIntStateOf(20) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TemperatureDisplay(temperatura = temperatura)

        Spacer(modifier = Modifier.height(32.dp))

        TemperatureControls(
            onSubir = { temperatura++ },
            onBajar = { temperatura-- }
        )
    }
}

// HIJO 1: sin estado propio, solo recibe el valor y lo muestra
@Composable
fun TemperatureDisplay(temperatura: Int) {
    val esCalor = temperatura >= 25
    val color = if (esCalor) Color.Red else Color.Blue
    val icono = if (esCalor) Icons.Filled.WbSunny else Icons.Filled.AcUnit

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icono,
            contentDescription = if (esCalor) "Calor" else "Frío",
            tint = color,
            modifier = Modifier.size(96.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$temperatura °C",
            color = color,
            fontSize = 56.sp
        )
    }
}

// HIJO 2: sin lógica propia, ejecuta las lambdas que recibe del padre
@Composable
fun TemperatureControls(
    onSubir: () -> Unit,
    onBajar: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onBajar) {
            Text("Bajar")
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(onClick = onSubir) {
            Text("Subir")
        }
    }
}