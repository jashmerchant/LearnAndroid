package com.jashmerchant.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.jashmerchant.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable // Composables are the building blocks of a Jetpack Compose UI
fun UnitConverter() {
    var inputValue by rememberSaveable { mutableStateOf("") }
    var outputValue by rememberSaveable { mutableStateOf("") }

    var inputUnit by rememberSaveable { mutableStateOf("Select") }
    var outputUnit by rememberSaveable { mutableStateOf("Select") }

    var iExpanded by rememberSaveable { mutableStateOf(false) }
    var oExpanded by rememberSaveable { mutableStateOf(false) }

    val conversionFactor = rememberSaveable { mutableStateOf(1.00) }
    val oConversionFactor = rememberSaveable { mutableStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value / oConversionFactor.value * 100).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier // Tells Column (a composable) how to behave
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Pick units and type a value to convert.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box {
                Button(
                    onClick = { iExpanded = true },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        inputUnit = "Centimeters"
                        iExpanded = false
                        conversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        inputUnit = "Meters"
                        iExpanded = false
                        conversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        inputUnit = "Feet"
                        iExpanded = false
                        conversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        inputUnit = "Millimeters"
                        iExpanded = false
                        conversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            Box {
                Button(
                    onClick = { oExpanded = true },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        oExpanded = false
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        outputUnit = "Meters"
                        oExpanded = false
                        oConversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        outputUnit = "Feet"
                        oExpanded = false
                        oConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        outputUnit = "Millimeters"
                        oExpanded = false
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Result",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (outputUnit == "Select") {
                        outputValue.ifEmpty { "0.0" }
                    } else {
                        "$outputValue $outputUnit"
                    },
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}
