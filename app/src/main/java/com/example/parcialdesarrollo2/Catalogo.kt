package com.example.parcialdesarrollo2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parcialdesarrollo2.ui.theme.*

@Composable
fun Catalogo(
    navController: NavController,
    productos: List<Producto>,
    carrito: List<Producto>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
            .padding(16.dp)
    ) {
        Text(
            "Catálogo de Productos",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { navController.navigate("detalle/${producto.id}") }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        AsyncImage(
                            model = producto.imagen,
                            contentDescription = producto.nombre,
                            modifier = Modifier.size(80.dp),
                            error = painterResource(id = R.drawable.error)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                producto.nombre,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                "Precio: $${String.format("%.f", producto.precio)}",
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }

        Text(
            "Total en carrito: $${String.format("%.f", carrito.sumOf { it.precio })}",
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("registro") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Agregar Producto")
            }
            Button(
                onClick = { navController.navigate("carrito") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Ver Carrito")
            }
        }
    }
}
