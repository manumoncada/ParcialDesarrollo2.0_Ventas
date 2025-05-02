package com.example.parcialdesarrollo2


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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

    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    var showDeleteDialog by remember { mutableStateOf(false) }

    val idInt = id.toIntOrNull()
    val productoExistente = productos.find { it.id == idInt }

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
            Text("Registrar Producto", fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("URL de Imagen", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("Cancelar", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {
                    val idIntLocal = id.toIntOrNull()
                    val precioDouble = precio.toDoubleOrNull()

                    if (
                        idIntLocal == null ||
                        nombre.isBlank() ||
                        precioDouble == null ||
                        descripcion.isBlank() ||
                        imagen.isBlank()
                    ) {
                        dialogMessage = "Todos los campos deben estar completos y válidos."
                        showDialog = true
                        return@Button
                    }

                    if (productoExistente != null) {
                        dialogMessage = "El ID ya existe. Usa otro ID."
                        showDialog = true
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
                },
                colors = ButtonDefaults.buttonColors(containerColor = Verde)
            ) {
                Text("Guardar", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        if (productoExistente != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    productos.remove(productoExistente)
                    showDeleteDialog = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar producto existente", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Atención", fontWeight = FontWeight.Bold) },
                text = { Text(dialogMessage) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Producto eliminado", fontWeight = FontWeight.Bold) },
                text = { Text("El producto se eliminó correctamente.") },
                confirmButton = {
                    Button(onClick = {
                        showDeleteDialog = false
                        navController.popBackStack()
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}



