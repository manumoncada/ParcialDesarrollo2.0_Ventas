package com.example.parcialdesarrollo2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Detalle(
    navController: NavController,
    productoId: Int?,
    productos: List<Producto>,
    Carrito: MutableList<Producto>
) {
    val producto = productos.find { it.id == productoId }

    if (producto == null) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(FondoSuave)
            .padding(16.dp)) {
            Text("Producto no encontrado", fontWeight = FontWeight.Bold)
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver", fontWeight = FontWeight.Bold)
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoSuave)
            .padding(16.dp)
    ) {
        AsyncImage(
            model = producto.imagen,
            contentDescription = producto.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            error = painterResource(id = R.drawable.error)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Verde)
                .padding(8.dp)
        ) {
            Text(producto.nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("ID: ${producto.id}", fontWeight = FontWeight.Bold)
        Text("Precio: $${producto.precio}", fontWeight = FontWeight.Bold)
        Text("Descripción: ${producto.descripcion}", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Carrito.add(producto)
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Verde)
        ) {
            Text("Agregar al carrito", fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver", fontWeight = FontWeight.Bold)
        }
    }
}
