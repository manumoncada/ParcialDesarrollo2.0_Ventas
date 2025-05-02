package com.example.parcialdesarrollo2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

val Verde = Color(0xFF78BE20)
val GrisOscuro = Color(0xFF4A4A4A)
val FondoSuave = Color(0xFFF8F8F8)

@Composable
fun Catalogo(
    navController: NavController,
    productos: List<Producto>,
    Carrito: List<Producto>
) {
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
            Text(
                "Catálogo de Productos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
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
                                color = GrisOscuro
                            )
                            Text(
                                "Precio: $${producto.precio}",
                                fontWeight = FontWeight.Bold,
                                color = GrisOscuro
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("registro") },
                colors = ButtonDefaults.buttonColors(containerColor = Verde)
            ) {
                Text("Agregar Producto", fontWeight = FontWeight.Bold, color = Color.White)
            }

            IconButton(onClick = { navController.navigate("Carrito") }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito", tint = Verde)
            }
        }
    }
}

