package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.RectangleShape
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen

// --- Colores del Wireframe ---
val GreenButtonColor = Color(0xFF00897B)
val BottomBarColor = Color(0xFFB3E5FC) // Azul claro
val DrawerHeaderColor = Color(0xFF455A64) // Azul grisáceo oscuro

@Composable
fun DenunciasScreen(
    onNavigateToMisDenuncias: () -> Unit,
    // Callback para abrir la nueva pantalla de menú
    onOpenMenu: () -> Unit,
    // Callbacks de navegación para Denuncias Hub
    onNavigateToAgencias: () -> Unit,
    onNavigateToPosts: () -> Unit,
    onNavigateToDenunciaFotografica: () -> Unit,
    onNavigateToPersonaDesaparecida: () -> Unit,
    onNavigateToRoboVehiculo: () -> Unit,
    onNavigateToRoboCasa: () -> Unit,
    onNavigateToRoboObjeto: () -> Unit,
    onNavigateToExtorsion: () -> Unit,
    onNavigateToDenunciaViolencia: () -> Unit
    // ELIMINADOS: onLogOut, onNavigateToProfile, onNavigateToEmergencyContacts, etc.
    // Toda la lógica del Drawer ahora está en MenuScreen.kt
) {
    // ELIMINADOS: drawerState y scope
    Scaffold(
        topBar = {
            // Barra superior personalizada con botón de menú y botón "Mis Denuncias"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 1. Botón para navegar a MenuScreen
                IconButton(
                    onClick = onOpenMenu, // Usa el nuevo callback
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Abrir menú", modifier = Modifier.size(32.dp))
                }

                // 2. Placeholder del Logo Central
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImagePlaceholder(size = 50.dp, shape = RoundedCornerShape(4.dp))
                    Text("Logo App", fontSize = 10.sp, color = Color.Gray)
                }

                // 3. Botón para ir a "Mis Denuncias"
                Button(
                    onClick = onNavigateToMisDenuncias,
                    colors = ButtonDefaults.buttonColors(containerColor = GreenButtonColor),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .height(36.dp)
                ) {
                    Text("Mis Denuncias", fontSize = 12.sp)
                }
            }
        },
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Denuncias",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Grid de opciones
            val gridItems = listOf(
                GridOption("Denuncia\nFotográfica", Icons.Default.CameraAlt) { onNavigateToDenunciaFotografica() },
                GridOption("Persona\nDesaparecida", Icons.Default.Person) { onNavigateToPersonaDesaparecida() },
                GridOption("Robo de\nVehículo", Icons.Default.DirectionsCar) { onNavigateToRoboVehiculo() },
                GridOption("Extorsión", Icons.Default.PhoneAndroid) { onNavigateToExtorsion() },
                GridOption("Robo a\nCasa Habitación", Icons.Default.Home) { onNavigateToRoboCasa() },
                GridOption("Robo de\nObjetos", Icons.Default.Visibility) { onNavigateToRoboObjeto() },
                GridOption("Violencia de\nGénero", Icons.Default.Female) { onNavigateToDenunciaViolencia() },
                GridOption("Agencias de\nDenuncia", Icons.Default.Place) { onNavigateToAgencias() },
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(gridItems) { item ->
                    DenunciaItem(item.title, item.icon, item.onClick)
                }
            }
        }
    }
}

// ELIMINADO: SideMenuContent ya que ahora es MenuScreen.kt

// --- Componentes Auxiliares (sin cambios) ---

data class GridOption(val title: String, val icon: ImageVector, val onClick: () -> Unit)

@Composable
fun DenunciaItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(50.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun ImagePlaceholder(
    size: androidx.compose.ui.unit.Dp,
    color: Color = Color.LightGray,
    shape: androidx.compose.ui.graphics.Shape = RectangleShape
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color, shape),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Image, contentDescription = "Placeholder", tint = Color.Gray)
    }
}

// --- Preview Corregido ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciasScreenPreview() {
    DenunciasScreen(
        onNavigateToMisDenuncias = {},
        onOpenMenu = {}, // Nuevo callback
        onNavigateToAgencias = {},
        onNavigateToPosts = {},
        onNavigateToDenunciaFotografica = {},
        onNavigateToPersonaDesaparecida = {},
        onNavigateToRoboVehiculo = {},
        onNavigateToRoboCasa = {},
        onNavigateToRoboObjeto = {},
        onNavigateToExtorsion = {},
        onNavigateToDenunciaViolencia = {}
    )
}