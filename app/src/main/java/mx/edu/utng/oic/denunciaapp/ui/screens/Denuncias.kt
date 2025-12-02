package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image // Nuevo import
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
import androidx.compose.ui.res.painterResource // Nuevo import
import mx.edu.utng.oic.denunciaapp.R // Nuevo import
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen
@Composable
fun DenunciasScreen(
    onNavigateToMisDenuncias: () -> Unit,
    onOpenMenu: () -> Unit,
    onNavigateToAgencias: () -> Unit,
    onNavigateToPosts: () -> Unit,
    onNavigateToDenunciaFotografica: () -> Unit,
    onNavigateToPersonaDesaparecida: () -> Unit,
    onNavigateToRoboVehiculo: () -> Unit,
    onNavigateToRoboCasa: () -> Unit,
    onNavigateToRoboObjeto: () -> Unit,
    onNavigateToExtorsion: () -> Unit,
    onNavigateToDenunciaViolencia: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant


    Scaffold(
        topBar = {
            // Barra superior personalizada con botón de menú y botón "Mis Denuncias"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surfaceColor)
                    .padding(16.dp)
            ) {
                // 1. Botón para navegar a MenuScreen
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Abrir menú", modifier = Modifier.size(32.dp), tint = onSurfaceColor)
                }

                // 2. Logo Central
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(50.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = onSurfaceVariantColor)
                }

                Button(
                    onClick = onNavigateToMisDenuncias,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .height(36.dp)
                ) {
                    Text("Mis Denuncias", fontSize = 12.sp, color = onPrimaryColor)
                }
            }
        },
        containerColor = backgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Text(
                text = "Denuncias",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = onSurfaceColor,
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
                    DenunciaItem(
                        title = item.title,
                        icon = item.icon,
                        onClick = item.onClick,
                        onSurfaceColor = onSurfaceColor,
                        surfaceColor = surfaceColor,
                        primaryColor = primaryColor // Usaremos primary para el icono
                    )
                }
            }
        }
    }
}

data class GridOption(val title: String, val icon: ImageVector, val onClick: () -> Unit)

@Composable
fun DenunciaItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    onSurfaceColor: Color,
    surfaceColor: Color,
    primaryColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(surfaceColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(50.dp),
                tint = primaryColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            color = onSurfaceColor
        )
    }
}

@Composable
fun MenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit = {},
    onSurfaceColor: Color,
    primaryColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(28.dp), tint = primaryColor)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, color = onSurfaceColor)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciasScreenPreview() {
    MaterialTheme {
        DenunciasScreen(
            onNavigateToMisDenuncias = {},
            onOpenMenu = {},
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
}