package mx.edu.utng.oic.denunciaapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utng.oic.denunciaapp.R // Importar el recurso R
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen

data class NewsData(
    val title: String,
    val link: String,
    val imageResId: Int
)

val NewsList = listOf(
    NewsData(
        title = "Renuncia de Gertz Manero: crecen críticas que presionaron su salida de la FGR",
        link = "https://mvsnoticias.com/entrevistas/2025/11/28/seguridad-en-mexico-claudia-sheinbaum-impulsa-ernestina-godoy-como-fiscal-interina-de-la-fgr-721866.html",
        imageResId = R.drawable.notice1
    ),
    NewsData(
        title = "Plan Michoacán: siete detenidos y vehículos asegurados en tres municipios",
        link = "https://udgtv.com/noticias/fuerzas-seguridad-mexico-detienen-siete-en-michoacan/295393",
        imageResId = R.drawable.notice2
    ),
    NewsData(
        title = "Estos son los cambios que habrá en el Servicio Militar para el 2026",
        link = "https://www.informador.mx/mexico/estos-son-los-cambios-que-habra-en-el-servicio-militar-para-el-2026-20251128-0161.html",
        imageResId = R.drawable.notice3
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(
    onOpenDrawer: () -> Unit = {},
    onNavigateTo: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // --- Colores Dinámicos del Tema ---
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val backgroundColor = MaterialTheme.colorScheme.background
    val outlineColor = MaterialTheme.colorScheme.outline

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surfaceColor)
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
                        tint = onSurfaceColor
                    )
                }

                // 2. Logo de la App (Centro)
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(60.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = onSurfaceVariantColor)
                }
            }
        },

        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
                .background(backgroundColor)
        ) {
            Text(
                text = "Noticias relevantes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = onSurfaceColor,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // --- Fila Superior de Noticias (2 columnas) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val news1 = NewsList[0]
                val news2 = NewsList[1]

                NewsItem(
                    modifier = Modifier.weight(1f),
                    title = news1.title,
                    imageResId = news1.imageResId,
                    height = 150.dp,
                    onClick = { openUrl(news1.link) },
                    onSurfaceColor = onSurfaceColor,
                    outlineColor = outlineColor,
                    surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
                )

                NewsItem(
                    modifier = Modifier.weight(1f),
                    title = news2.title,
                    imageResId = news2.imageResId,
                    height = 150.dp,
                    onClick = { openUrl(news2.link) },
                    onSurfaceColor = onSurfaceColor,
                    outlineColor = outlineColor,
                    surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Noticia Inferior (Ancho completo) ---
            val news3 = NewsList[2]
            NewsItem(
                modifier = Modifier.fillMaxWidth(),
                title = news3.title,
                imageResId = news3.imageResId,
                height = 200.dp,
                onClick = { openUrl(news3.link) },
                onSurfaceColor = onSurfaceColor,
                outlineColor = outlineColor,
                surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- Componente de Noticia Individual (Corregido) ---
@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    title: String,
    imageResId: Int,
    height: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit,
    onSurfaceColor: Color,
    outlineColor: Color,
    surfaceVariantColor: Color
) {
    Column(
        modifier = modifier.clickable { onClick() }
    ) {
        // Imagen Real
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Label no editable (Estilo de recuadro de texto)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, outlineColor, RoundedCornerShape(4.dp))
                .background(surfaceVariantColor, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = onSurfaceColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePageScreenPreview() {
    MaterialTheme {
        HomePageScreen(
            onOpenDrawer = { },
            onNavigateTo = {}
        )
    }
}