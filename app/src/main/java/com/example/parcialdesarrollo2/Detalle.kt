package com.example.parcialdesarrollo2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Detalle(
    navController: NavController,
    productoId: Int?,
    productos: List<Producto>,
    carrito: MutableList<Producto>
) {
    val producto = productos.find { it.id == productoId }

    if (producto == null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Producto no encontrado")
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = producto.imagen,
            contentDescription = producto.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            error = painterResource(id = R.drawable.error)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
        Text("Precio: $${String.format("%.2f", producto.precio)}")
        Text("Descripción: ${producto.descripcion}")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            carrito.add(producto)
            navController.popBackStack()
        }) {
            Text("Agregar al carrito")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}
