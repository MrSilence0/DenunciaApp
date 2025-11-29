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

val SOSBlue = Color(0xFF2962FF)

// --- Modelo y Datos de Noticias ---
data class NewsData(
    val title: String,
    val link: String,
    val imageResId: Int // ID del recurso drawable
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

                // 2. Logo de la App (Centro) - CORREGIDO
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        // Mantenemos el tamaño solicitado de 60.dp
                        modifier = Modifier.size(60.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = Color.Gray)
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
                val news1 = NewsList[0]
                val news2 = NewsList[1]

                // Noticia 1 (Izquierda) - DATOS REALES
                NewsItem(
                    modifier = Modifier.weight(1f),
                    title = news1.title,
                    imageResId = news1.imageResId,
                    height = 150.dp,
                    onClick = { openUrl(news1.link) }
                )

                // Noticia 2 (Derecha) - DATOS REALES
                NewsItem(
                    modifier = Modifier.weight(1f),
                    title = news2.title,
                    imageResId = news2.imageResId,
                    height = 150.dp,
                    onClick = { openUrl(news2.link) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Noticia Inferior (Ancho completo) - DATOS REALES ---
            val news3 = NewsList[2]
            NewsItem(
                modifier = Modifier.fillMaxWidth(),
                title = news3.title,
                imageResId = news3.imageResId,
                height = 200.dp,
                onClick = { openUrl(news3.link) }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- Componente de Noticia Individual (CORREGIDO para usar Image) ---
@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    title: String,
    imageResId: Int, // ⬅️ Ahora espera el ID del recurso
    height: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick() }
    ) {
        // Imagen Real (Reemplaza el Placeholder)
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = title,
            contentScale = ContentScale.Crop, // Ajusta la imagen al contenedor
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(RoundedCornerShape(8.dp)) // Borde redondeado sutil
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Label no editable
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 2, // Aumentado a 2 líneas para noticias largas
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePageScreenPreview() {
    HomePageScreen(
        onOpenDrawer = { },
        onNavigateTo = {}
    )
}