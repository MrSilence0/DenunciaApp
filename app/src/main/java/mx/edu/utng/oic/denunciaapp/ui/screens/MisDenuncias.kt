package mx.edu.utng.oic.denunciaapp.ui.screens

// ------------------------------------------------------------------
// IMPORTACIONES CLAVE (YA NO NECESITAMOS 'getValue' o 'setValue' con esta implementación)
// ------------------------------------------------------------------
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel

// IMPORTACIONES DE DOMINIO Y VIEWMODEL
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MisDenunciasViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MisDenunciasViewModelFactory
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia as DomainDenuncia
import mx.edu.utng.oic.denunciaapp.data.model.TipoIncidente
import java.text.SimpleDateFormat

// ------------------------------------------------------------------
// OTRAS IMPORTACIONES
// ------------------------------------------------------------------
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import kotlinx.coroutines.launch


// --- 2. MODELO DE DATOS PARA LA DENUNCIA (Modelo de la UI) ---
data class UIDenuncia(
    val id: String,
    val tipo: String,
    val estatus: String,
    val fecha: String,
    val hora: String,
    val detalle: String = "Detalle breve de la denuncia."
)


// --- 3. FUNCIÓN DE MAPEO DE DOMINIO A UI ---
fun DomainDenuncia.toUiDenuncia(): UIDenuncia {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val timeFormat = SimpleDateFormat("HH:mm")

    val tipoString = when(this.tipo) {
        TipoIncidente.DENUNCIA_FOTOGRAFICA -> "Denuncia Fotográfica"
        TipoIncidente.PERSONA_DESAPARECIDA -> "Persona Desaparecida"
        TipoIncidente.ROBO_VEHICULO -> "Robo de Vehículo"
        TipoIncidente.EXTORSION -> "Extorsión"
        TipoIncidente.ROBO_CASA -> "Robo a Casa"
        TipoIncidente.ROBO_OBJETO -> "Robo de Objeto"
        TipoIncidente.DENUNCIA_VIOLENCIA -> "Denuncia de Violencia"
    }

    return UIDenuncia(
        id = this.id,
        tipo = tipoString,
        estatus = "En Revisión",
        fecha = dateFormat.format(this.creationDate),
        hora = timeFormat.format(this.creationDate)
    )
}


// --- 4. COMPONENTE INDIVIDUAL DE ITEM DE DENUNCIA ---
@Composable
fun DenunciaItem(denuncia: UIDenuncia, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick(denuncia.id) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Fila principal: Tipo de Denuncia y Estatus
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tipo de Denuncia
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Tipo de Denuncia",
                        tint = PrimaryColor,
                        modifier = Modifier.size(24.dp).padding(end = 4.dp)
                    )
                    Text(
                        text = denuncia.tipo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Estatus (etiqueta con color)
                val statusColor = when (denuncia.estatus) {
                    "Resuelta" -> Color(0xFF4CAF50)
                    "En Revisión" -> Color(0xFFFF9800)
                    "Rechazada" -> Color(0xFFF44336)
                    else -> WireframeGray
                }
                Text(
                    text = denuncia.estatus,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = statusColor,
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.15f), shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = WireframeGray.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(8.dp))

            // Fila de detalles: ID, Fecha y Hora
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // ID de la Denuncia
                Column {
                    Text("ID:", fontSize = 10.sp, color = WireframeGray)
                    Text(denuncia.id, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }

                // Fecha
                Column(horizontalAlignment = Alignment.End) {
                    Text("Fecha:", fontSize = 10.sp, color = WireframeGray)
                    Text(denuncia.fecha, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }

                // Hora
                Column(horizontalAlignment = Alignment.End) {
                    Text("Hora:", fontSize = 10.sp, color = WireframeGray)
                    Text(denuncia.hora, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }
            }
        }
    }
}


// ------------------------------------------------------------------
// 5. PANTALLA COMPLETA MIS DENUNCIAS (ESTADO Y VIEWMODEL)
// ------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisDenunciasScreen(
    onNavigateBack: () -> Unit,
    onOpenDrawer: () -> Unit,
    onNavigateToDenunciaDetail: (String) -> Unit,
    viewModel: MisDenunciasViewModel = viewModel(factory = MisDenunciasViewModelFactory())
) {
    // 🔥 CORRECCIÓN: Acceso directo al valor del State
    val denuncias = viewModel.denuncias.value
    val isLoading = viewModel.isLoading.value
    val feedbackMessage = viewModel.feedbackMessage.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(feedbackMessage) {
        if (feedbackMessage != null) {
            scope.launch {
                snackbarHostState.showSnackbar(feedbackMessage)
                viewModel.clearFeedbackMessage()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Mis Denuncias", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar a Denuncias"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->

        // Indicador de carga
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        } else if (denuncias.isEmpty()) {
            // Mensaje de lista vacía
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Aún no has realizado ninguna denuncia.",
                    fontSize = 16.sp,
                    color = WireframeGray
                )
            }
        } else {
            // Lista de denuncias reales
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(denuncias) { denuncia: DomainDenuncia ->

                    val uiDenuncia = denuncia.toUiDenuncia()

                    DenunciaItem(
                        denuncia = uiDenuncia,
                        onClick = { denunciaId ->
                            onNavigateToDenunciaDetail(denunciaId)
                        }
                    )
                }
            }
        }
    }
}


// ------------------------------------------------------------------
// 6. PREVIEW CORREGIDO (ENVUELTO EN MATERIALTHEME)
// ------------------------------------------------------------------
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MisDenunciasScreenPreview() {
    MaterialTheme {
        MisDenunciasScreen(
            onNavigateBack = {},
            onOpenDrawer = {},
            onNavigateToDenunciaDetail = {}
        )
    }
}
