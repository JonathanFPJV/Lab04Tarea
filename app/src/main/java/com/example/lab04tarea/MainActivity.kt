package com.example.lab04tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab04tarea.ui.theme.Lab04TareaTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab04TareaTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    WarehouseManager()
                }
            }
        }
    }
}

@Composable
fun WarehouseManager(modifier: Modifier = Modifier) {
    var productName by rememberSaveable { mutableStateOf("") }
    var productList by rememberSaveable { mutableStateOf(listOf<String>()) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inventario Alamacen", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (productName.isNotBlank()) {
                productList = productList + productName
                productName = ""
            }
        }) {
            Text("Add Product")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (productList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                items(productList) { product ->
                    ProductItem(product) { selectedProduct ->
                        productList = productList - selectedProduct
                    }
                }
            }
        } else {
            Text(text = "No products in the inventory")
        }
    }
}

@Composable
fun ProductItem(product: String, onRemove: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = product)
        IconButton(onClick = { onRemove(product) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWarehouseManager() {
    Lab04TareaTheme {
        WarehouseManager()
    }
}
