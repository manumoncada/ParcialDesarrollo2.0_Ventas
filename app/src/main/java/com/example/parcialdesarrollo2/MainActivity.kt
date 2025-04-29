package com.example.parcialdesarrollo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val productos = remember { mutableStateListOf<Producto>() }
                val carrito = remember { mutableStateListOf<Producto>() }

                NavHost(navController, startDestination = "catalogo") {
                    composable("catalogo") {
                        Catalogo(navController, productos, carrito)
                    }
                    composable("registro") {
                        Registro(navController, productos)
                    }
                    composable("detalle/{productoId}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
                        Detalle(navController, id, productos, carrito)
                    }
                    composable("carrito") {
                        Carrito(navController, carrito)
                    }
                }
            }
        }
    }
}
