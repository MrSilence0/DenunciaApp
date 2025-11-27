package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray

val ReplyButtonColor = Color(0xFF43A047)

// Modelo de datos para simular un mensaje de chat (respuesta)
data class Message(
    val id: Int,
    val username: String,
    val text: String,
    val isCurrentUser: Boolean,
    val time: String
)

// Nota: El modelo Post debe estar definido en otro archivo accesible (ej: Posts.kt)
// Si Post no está definido, causará un error de tipo 'Unresolved reference: Post'.
// Asumimos que Post y RatingStars ya son accesibles desde este paquete.

// Datos del Post Original (Simulación)
val originalPost = Post(
    id = 1,
    username = "Ana_Segura",
    text = "Excelente respuesta del 911 en mi caso de robo a vehículo. La policía llegó en menos de 10 minutos. Muy agradecida por la eficiencia.",
    rating = 5,
    timeAgo = "15/11/2025 10:30 AM" // Usamos formato completo aquí para el header
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    onBack: () -> Unit,
    onOpenDrawer: () -> Unit, // Añadido para el botón de menú
    forumName: String = "Soporte legal para violencia doméstica" // Nombre del foro de donde viene el post
) {
    var messageText by remember { mutableStateOf("") }

    // Lista de mensajes (Simulación de respuestas al post)
    val chatMessages = remember {
        mutableStateListOf(
            Message(1, "Ana_Segura", originalPost.text, false, "10:30 AM"), // El post original
            Message(2, "ModeradorApp", "¿Podrías especificar qué delegación atendió tu reporte? Gracias.", false, "10:45 AM"),
            Message(3, "Usuario Actual", "¡Qué bien! Me alegra que tu experiencia haya sido positiva. Le da confianza al sistema.", true, "11:00 AM")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Responder Post", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                // Añadimos el botón de menú lateral aquí
                actions = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Abrir menú lateral")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            MessageInput(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSend = {
                    if (messageText.isNotBlank()) {
                        chatMessages.add(
                            Message(
                                id = chatMessages.size + 1,
                                username = "Usuario Actual",
                                text = messageText,
                                isCurrentUser = true,
                                time = "Justo ahora"
                            )
                        )
                        messageText = "" // Limpiar campo
                    }
                }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 1. Cabecera con datos del Post Original (Nombre del Foro y Fecha/Hora)
            PostHeader(forumName = forumName, dateTime = originalPost.timeAgo)

            Divider(color = DividerColor, thickness = 1.dp)

            // 2. Columna para el Post Original y las Respuestas
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                reverseLayout = true // Hace que los últimos mensajes estén abajo
            ) {
                // Muestra los mensajes de chat/respuestas (en orden inverso)
                items(chatMessages.reversed()) { message ->
                    if (message.id == 1) {
                        // El primer mensaje es el post original, lo mostramos con el componente PostItem
                        Box(modifier = Modifier.padding(bottom = 16.dp)) {
                            PostItemImmutable(post = originalPost)
                        }
                    } else {
                        // Mostrar las respuestas
                        ChatMessageItem(message = message)
                    }
                }
            }
        }
    }
}

// --- Componente del Header de la Conversación ---
@Composable
fun PostHeader(forumName: String, dateTime: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE3F2FD)) // Fondo azul claro para resaltar
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = "Foro: $forumName",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Post original: $dateTime",
            fontSize = 12.sp,
            color = Color.DarkGray
        )
    }
}

// --- Componente de Item de Mensaje (Respuesta) ---
@Composable
fun ChatMessageItem(message: Message) {
    val bubbleColor = if (message.isCurrentUser) Color(0xFFDCF8C6) else Color(0xFFF0F0F0) // Verde claro para propio, gris para otros
    val alignment = if (message.isCurrentUser) Alignment.End else Alignment.Start
    val paddingStart = if (message.isCurrentUser) 60.dp else 0.dp
    val paddingEnd = if (message.isCurrentUser) 0.dp else 60.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 4.dp),
        horizontalAlignment = alignment
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            modifier = Modifier
                .padding(start = paddingStart, end = paddingEnd)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                // Nombre de usuario (solo si no es el usuario actual)
                if (!message.isCurrentUser) {
                    Text(
                        text = message.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                Text(text = message.text, fontSize = 14.sp)
            }
        }

        // Hora/Fecha bajo el mensaje (alineado)
        Text(
            text = message.time,
            fontSize = 10.sp,
            color = WireframeGray,
            modifier = Modifier.padding(top = 4.dp, end = 8.dp, start = 8.dp)
        )
    }
}


// --- Componente de Entrada de Mensaje (BottomBar) ---
@Composable
fun MessageInput(messageText: String, onMessageChange: (String) -> Unit, onSend: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, DividerColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = messageText,
            onValueChange = onMessageChange,
            placeholder = { Text("Escribe tu respuesta...", color = WireframeGray) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = DividerColor,
                focusedContainerColor = Color(0xFFF7F7F7),
                unfocusedContainerColor = Color(0xFFF7F7F7)
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        FloatingActionButton(
            onClick = onSend,
            containerColor = ReplyButtonColor,
            shape = CircleShape,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(Icons.Default.Send, contentDescription = "Enviar", tint = Color.White)
        }
    }
}

// --- Componente del Post Original (Versión Inmutable de Posts.kt) ---
// Se crea una versión simplificada, sin botones de acción.
@Composable
fun PostItemImmutable(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8EAF6), RoundedCornerShape(8.dp)) // Fondo ligeramente diferente para destacar
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto de Perfil (Placeholder)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = post.username.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Nombre de Usuario
            Text(
                text = post.username,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Estrellas de Valoración
            RatingStars(rating = post.rating)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Texto del Post
        Text(
            text = post.text,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ELIMINADA LA FUNCIÓN RatingStars(rating: Int) DUPLICADA AQUÍ

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MessagesScreenPreview() {
    MessagesScreen(onBack = {}, onOpenDrawer = {})
}
