package mx.edu.utng.oic.denunciaapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen
import mx.edu.utng.oic.denunciaapp.ui.components.ImagePlaceholder
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray

// Colores específicos para esta pantalla
val SOSBlue = Color(0xFF2962FF) // Azul botón SOS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(
    onOpenDrawer: () -> Unit = {},
    onNavigateTo: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Función auxiliar para abrir URLs
    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            // Barra superior personalizada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 1. Botón Menú Lateral (Izquierda)
                IconButton(
                    onClick = onOpenDrawer,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Abrir menú",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Black
                    )
                }

                // 2. Logo de la App (Centro)
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImagePlaceholder(
                        size = 60.dp,
                        color = Color.LightGray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    Text("Logo de la app", fontSize = 10.sp, color = Color.Gray)
                }
            }
        },

        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Noticias relevantes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // --- Fila Superior de Noticias (2 columnas) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Noticia 1 (Izquierda)
                NewsItem(
                    modifier = Modifier.weight(1f),
                    title = "Título Noticia 1",
                    imageUrl = "https://google.com",
                    height = 150.dp,
                    onClick = { openUrl("https://www.google.com") }
                )

                // Noticia 2 (Derecha)
                NewsItem(
                    modifier = Modifier.weight(1f),
                    title = "Título Noticia 2",
                    imageUrl = "https://google.com",
                    height = 150.dp,
                    onClick = { openUrl("https://www.google.com") }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Noticia Inferior (Ancho completo) ---
            NewsItem(
                modifier = Modifier.fillMaxWidth(),
                title = "Título Noticia Principal Relevante",
                imageUrl = "https://google.com",
                height = 200.dp,
                onClick = { openUrl("https://www.google.com") }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- Componente de Noticia Individual (Sin cambios) ---
@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    height: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick() }
    ) {
        // Placeholder de Imagen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(Color.White)
                .border(1.dp, WireframeGray), // Borde estilo wireframe
            contentAlignment = Alignment.Center
        ) {
            // Líneas cruzadas para simular el placeholder de imagen "X"
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = "Placeholder de imagen",
                tint = WireframeGray,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Label no editable
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 1
            )
        }
    }
}

// --- Componentes de la barra inferior ELIMINADOS ---
// Se recomienda mover HomeBottomBar y HomeBottomNavItem al archivo donde se
// define BottomNavigationBar (probablemente en AppEntryNavigation o un componente auxiliar),
// ya que NO deben estar en HomePageScreen.kt si no se usan aquí.
// Se dejan comentados para que puedas reubicarlos si los necesitas en otro lado.

/*
@Composable
fun HomeBottomBar(onNavigateTo: (String) -> Unit) { ... }

@Composable
fun HomeBottomNavItem(icon: ImageVector, label: String, isActive: Boolean, onClick: () -> Unit) { ... }
*/


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePageScreenPreview() {
    HomePageScreen(
        onOpenDrawer = { },
        onNavigateTo = {}
    )
}