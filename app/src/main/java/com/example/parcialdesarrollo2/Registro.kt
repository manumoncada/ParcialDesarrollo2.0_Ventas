package com.example.parcialdesarrollo2

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Registro(
    navController: NavController,
    productos: MutableList<Producto>
) {
    val context = LocalContext.current

    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }

    val idInt = id.toIntOrNull()
    val productoExistente = productos.find { it.id == idInt }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Registrar Producto", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("URL de Imagen") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Cancelar")
            }

            Button(onClick = {
                val idIntLocal = id.toIntOrNull()
                val precioDouble = precio.toDoubleOrNull()

                if (
                    idIntLocal == null ||
                    nombre.isBlank() ||
                    precioDouble == null ||
                    descripcion.isBlank() ||
                    imagen.isBlank()
                ) {
                    Toast.makeText(context, "Todos los campos deben estar completos y válidos.", Toast.LENGTH_LONG).show()
                    return@Button
                }

                if (productoExistente != null) {
                    Toast.makeText(context, "El ID ya existe. Usa otro ID.", Toast.LENGTH_LONG).show()
                    return@Button
                }

                productos.add(
                    Producto(
                        id = idIntLocal,
                        nombre = nombre,
                        precio = precioDouble,
                        descripcion = descripcion,
                        imagen = imagen
                    )
                )
                navController.popBackStack()

            }) {
                Text("Guardar")
            }
        }

        if (productoExistente != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    productos.remove(productoExistente)
                    Toast.makeText(context, "Producto eliminado", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar producto existente")
            }
        }
    }
}
