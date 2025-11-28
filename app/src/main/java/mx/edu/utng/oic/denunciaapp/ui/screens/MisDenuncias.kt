package mx.edu.utng.oic.denunciaapp.ui.screens

// ------------------------------------------------------------------
// IMPORTS
// ------------------------------------------------------------------
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MisDenunciasViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MisDenunciasViewModelFactory
import mx.edu.utng.oic.denunciaapp.data.model.* // Importamos todo el modelo para Denuncia
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia as DomainDenuncia
import java.text.SimpleDateFormat

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
import androidx.compose.ui.text.style.TextOverflow // Importación necesaria


// ==================================================================
// 1. DATA CLASS PARA UI (ACTUALIZADA)
// ==================================================================
data class UIDenuncia(
    val id: String,
    val tipo: String,
    val estatus: String,
    val fecha: String,
    val hora: String,
    val detallesAdicionales: List<Pair<String, String>> // Lista de pares (Etiqueta, Valor)
)


// ==================================================================
// 2. MAPEO Domain → UI (ACTUALIZADO)
// ==================================================================
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

    // Mapeo de detalles específicos según el tipo de Denuncia
    val detalles = when (this) {
        is DenunciaFotografica -> listOf(
            "Descripción" to this.descripcion,
            "Dirección" to (this.locationAddress ?: "N/A")
        )
        is PersonaDesaparecida -> listOf(
            "Nombre" to this.nombreDesaparecido,
            "Sexo" to this.sexo,
            "Edad" to this.edad.toString(),
            "Descripción Física" to this.descripcionFisica
        )
        is RoboVehiculo -> listOf(
            "Placas" to this.placas,
            "Marca/Color" to "${this.marca}/${this.color}",
            "Año" to this.anio.toString(),
            "Reportante" to this.nombreReportante
        )
        is Extorsion -> listOf(
            "Teléfono" to this.numeroTelefonico,
            "Descripción" to this.descripcion
        )
        is RoboCasa -> listOf(
            "Descripción" to this.descripcion,
            "Dirección" to (this.locationAddress ?: "N/A"),
            "Contacto" to this.telefonoContacto
        )
        is RoboObjeto -> listOf(
            "Objeto" to this.tipoObjeto,
            "Marca/Color" to "${this.marca}/${this.color}",
            "Estado" to this.estado,
            "Dirección" to (this.locationAddress ?: "N/A")
        )
        is DenunciaViolencia -> listOf(
            "Conducta" to this.tipoConducta,
            "Descripción" to this.descripcion,
            "Contacto" to this.telefonoContacto,
            "Dirección" to (this.locationAddress ?: "N/A")
        )
        else -> emptyList()
    }


    return UIDenuncia(
        id = this.id,
        tipo = tipoString,
        estatus = "En Revisión", // Asumiendo estatus fijo por ahora
        fecha = dateFormat.format(this.creationDate),
        hora = timeFormat.format(this.creationDate),
        detallesAdicionales = detalles // Añadimos los detalles
    )
}


// ==================================================================
// 3. ITEM INDIVIDUAL (ACTUALIZADO PARA MOSTRAR TODOS LOS DETALLES)
// ==================================================================
@Composable
fun DenunciaItem(denuncia: UIDenuncia) { // Ya no necesitamos onClick
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // TIPO + ESTATUS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Tipo",
                        tint = Color(0xFF0086FF),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(6.dp))

                    Text(
                        text = denuncia.tipo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                val statusColor = when (denuncia.estatus) {
                    "Resuelta" -> Color(0xFF4CAF50)
                    "En Revisión" -> Color(0xFFFF9800)
                    "Rechazada" -> Color(0xFFF44336)
                    else -> Color.Gray
                }

                Text(
                    text = denuncia.estatus,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = statusColor,
                    modifier = Modifier
                        .background(statusColor.copy(0.15f), MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }


            Spacer(Modifier.height(10.dp))
            Divider()
            Spacer(Modifier.height(10.dp))

            // DETALLES ADICIONALES (NUEVA SECCIÓN)
            Text("Detalles:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)

            denuncia.detallesAdicionales.forEach { (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$label:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier.weight(0.4f)
                    )
                    Text(
                        text = value,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(0.6f)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Divider()
            Spacer(Modifier.height(10.dp))


            // FECHA + HORA + ID (Estructura de la corrección anterior)

            // ID
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("ID:", fontSize = 10.sp, color = Color.Gray)
                Text(
                    text = denuncia.id,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.height(10.dp))

            // Fecha y Hora
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Columna para la Fecha
                Column(modifier = Modifier.weight(1f)) {
                    Text("Fecha:", fontSize = 10.sp, color = Color.Gray)
                    Text(denuncia.fecha, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                // Columna para la Hora
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text("Hora:", fontSize = 10.sp, color = Color.Gray)
                    Text(denuncia.hora, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}


// ==================================================================
// 4. PANTALLA COMPLETA - CORREGIDA
// ==================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisDenunciasScreen(
    onNavigateBack: () -> Unit,
    onOpenDrawer: () -> Unit,
    onNavigateToDenunciaDetail: (String) -> Unit, // Ya no se usa, pero se mantiene el parámetro
) {
    // 1. Obtener la instancia de FirebaseFirestore (estable)
    val firestore = remember { FirebaseFirestore.getInstance() }

    // 2. Crear la factoría con la dependencia de Firestore (estable)
    val factory = remember { MisDenunciasViewModelFactory(firestore = firestore) }

    // 3. Obtener el ViewModel usando la factoría
    val viewModel: MisDenunciasViewModel = viewModel(factory = factory)

    // Obtener los estados del ViewModel
    val denuncias = viewModel.denuncias.value
    val isLoading = viewModel.isLoading.value
    val feedbackMessage = viewModel.feedbackMessage.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Mostrar mensajes
    LaunchedEffect(feedbackMessage) {
        if (feedbackMessage != null) {
            scope.launch {
                snackbarHostState.showSnackbar(feedbackMessage)
            }
            viewModel.clearFeedbackMessage()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

        topBar = {
            TopAppBar(
                title = { Text("Mis Denuncias", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },

        containerColor = Color(0xFFF5F5F5)

    ) { paddingValues ->

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
            return@Scaffold
        }

        if (denuncias.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                val displayMessage = feedbackMessage ?: "Aún no has realizado ninguna denuncia."
                Text(displayMessage, color = Color.Gray)
            }
            return@Scaffold
        }

        // LISTA DE DENUNCIAS
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            items(denuncias) { domainDenuncia ->

                val uiDenuncia = domainDenuncia.toUiDenuncia()

                // Llamamos a DenunciaItem sin la función onClick
                DenunciaItem(denuncia = uiDenuncia)
            }
        }
    }
}


// ==================================================================
// 5. PREVIEW (Sin cambios)
// ==================================================================
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