package com.jashmerchant.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
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

    val conversionFactor = rememberSaveable { mutableStateOf(0.01) }

    Column(
        modifier = Modifier.fillMaxSize(), // Tells Column (a composable) how to behave
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        inputUnit = "Centimeters"
                        iExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        inputUnit = "Meters"
                        iExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        inputUnit = "Feet"
                        iExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        inputUnit = "Millimeters"
                        iExpanded = false
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        oExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        outputUnit = "Meters"
                        oExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        outputUnit = "Feet"
                        oExpanded = false
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        outputUnit = "Millimeters"
                        oExpanded = false
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue" )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}
