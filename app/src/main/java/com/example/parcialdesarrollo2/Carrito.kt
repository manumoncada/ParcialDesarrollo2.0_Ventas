package com.example.parcialdesarrollo2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Carrito(
    navController: NavController,
    Carrito: MutableList<Producto>
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    val total = Carrito.sumOf { it.precio }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoSuave)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Verde)
                .padding(8.dp)
        ) {
            Text("Carrito de Compras", fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(Carrito) { producto ->
                Text("${producto.nombre} - $${producto.precio}", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Total a pagar: $${"%.2f".format(total)}", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Verde)
        ) {
            Text("Finalizar Compra", fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver", fontWeight = FontWeight.Bold)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Compra Finalizada", fontWeight = FontWeight.Bold) },
            text = { Text("¡Gracias por tu compra!") },
            confirmButton = {
                Button(onClick = {
                    Carrito.clear()
                    showDialog = false
                    navController.popBackStack()
                }) {
                    Text("OK")
                }
            }
        )
    }
}

