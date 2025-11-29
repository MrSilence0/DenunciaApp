package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utng.oic.denunciaapp.data.model.ChatThread
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen
import java.text.SimpleDateFormat
import java.util.*

/**
 * Pantalla principal de Mensajes/Inbox que lista todos los hilos de chat activos.
 * @param onNavigateToChatDetail Callback para navegar a la pantalla de chat detallado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesMainPageScreen(
    onNavigateToChatDetail: (String) -> Unit
) {
    // Simulación de hilos de chat activos
    val threads = getMockChatThreads()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bandeja de Mensajes", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(threads, key = { it.forumId }) { thread ->
                ChatThreadItem(thread = thread) { forumId ->
                    // Navega a la ruta del chat detallado, pasando el forumId
                    //onNavigateToChatDetail(AppScreen.MessagesPage.route.replace("{forumId}", forumId))
                }
                Divider(color = Color.LightGray, thickness = 0.5.dp)
            }
        }

        if (threads.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes mensajes activos.", color = Color.Gray)
            }
        }
    }
}

/**
 * Componente para un elemento en la lista de hilos de chat.
 */
@Composable
fun ChatThreadItem(thread: ChatThread, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(thread.forumId) }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono de Chat/Avatar
        Icon(
            imageVector = Icons.Filled.Chat,
            contentDescription = "Chat icon",
            tint = PrimaryBlue,
            modifier = Modifier.size(36.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Contenido del Chat
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = thread.forumTitle,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = thread.lastMessageContent,
                fontSize = 13.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Metadatos (Hora y Contador)
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(thread.lastMessageTime),
                fontSize = 11.sp,
                color = Color.Gray
            )
            if (thread.unreadCount > 0) {
                Spacer(modifier = Modifier.height(4.dp))
                Badge(
                    containerColor = PrimaryBlue
                ) {
                    Text(text = thread.unreadCount.toString(), color = Color.White, fontSize = 10.sp)
                }
            }
        }
    }
}

// --- Datos Simulados para la Bandeja de Entrada ---
fun getMockChatThreads(): List<ChatThread> {
    return listOf(
        ChatThread(
            forumId = "f001",
            forumTitle = "Soporte legal para violencia doméstica",
            lastMessageContent = "¡Gracias por la información! Es muy útil.",
            lastMessageTime = Calendar.getInstance().apply { add(Calendar.MINUTE, -5) }.time,
            unreadCount = 2
        ),
        ChatThread(
            forumId = "f002",
            forumTitle = "Dudas sobre el proceso de denuncia anónima",
            lastMessageContent = "El formulario se encuentra en la sección de 'Denuncias'.",
            lastMessageTime = Calendar.getInstance().apply { add(Calendar.HOUR, -1) }.time,
            unreadCount = 0
        ),
        ChatThread(
            forumId = "f003",
            forumTitle = "Solicitud de ayuda psicológica urgente",
            lastMessageContent = "Por favor, llame al 911 de inmediato. Los psicólogos están en línea.",
            lastMessageTime = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }.time,
            unreadCount = 5
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MessagesMainPagePreview() {
    MessagesMainPageScreen(onNavigateToChatDetail = {})
}

