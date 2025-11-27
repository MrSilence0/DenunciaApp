package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color azul similar al del wireframe (puedes moverlo a tu archivo de tema)
val WireframeBlue = Color(0xFF64B5F6)

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    // Estados para guardar lo que escribe el usuario
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Scaffold provee la estructura básica, aunque aquí usaremos una Columna simple con scroll
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // Fondo blanco como el dibujo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Permite scroll en pantallas pequeñas
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- Encabezado ---
            Text(
                text = "Iniciar sesión",
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(24.dp))

            // --- Formulario ---

            // Campo Correo
            LabelText("Correo electrónico:")
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Introduce tu correo") }, // Placeholder como en el dibujo
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Contraseña
            LabelText("Contraseña:")
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("********") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(), // Oculta el texto
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- Botón Ingresa ---
            Button(
                onClick = { onLoginClick(email, password) },
                colors = ButtonDefaults.buttonColors(containerColor = WireframeBlue),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.width(160.dp) // Ancho fijo aproximado al dibujo
            ) {
                Text(
                    text = "Ingresa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Olvidaste tu contraseña ---
            TextButton(onClick = onForgotPasswordClick) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color(0xFF7986CB) // Un tono azul/lila suave
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)

            Spacer(modifier = Modifier.height(24.dp))

            // --- Sección Registro ---
            Text(
                text = "¿No tienes cuenta?",
                color = Color.Gray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onRegisterClick,
                colors = ButtonDefaults.buttonColors(containerColor = WireframeBlue),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.width(160.dp)
            ) {
                Text(
                    text = "Registrate", // Tal cual aparece en el dibujo (sin tilde según imagen)
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Componente auxiliar para las etiquetas de texto alineadas a la izquierda
@Composable
fun LabelText(text: String) {
    Text(
        text = text,
        color = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start
    )
}

// --- Preview para Android Studio ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = { _, _ -> },
        onRegisterClick = {},
        onForgotPasswordClick = {}
    )
}

