package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.service.UserService // Importar el servicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit, // Callback para navegación exitosa
    onNavigateBack: () -> Unit // Callback para volver a la pantalla anterior
) {
    // Inicializar el servicio
    val userService = remember { UserService() }

    // --- Estados del Formulario ---
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Hombre") } // Default
    var specifyOther by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }

    // --- Estados para Lógica de UI ---
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope() // Para lanzar el Snackbar
    var showDatePicker by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) } // Estado de carga

    // Configuración del DatePicker
    val datePickerState = rememberDatePickerState()

    // --- Lógica de Registro ---
    fun validateAndRegister() {
        if (isLoading) return // Evitar doble clic

        // 1. Validaciones básicas
        if (name.isBlank() || phone.isBlank() || birthDate.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            scope.launch { snackbarHostState.showSnackbar("Todos los campos son obligatorios.") }
            return
        }
        if (password != confirmPassword) {
            scope.launch { snackbarHostState.showSnackbar("Las contraseñas no coinciden.") }
            return
        }
        if (password.length < 6) {
            scope.launch { snackbarHostState.showSnackbar("La contraseña debe tener al menos 6 caracteres.") }
            return
        }
        if (!termsAccepted) {
            scope.launch { snackbarHostState.showSnackbar("Debe aceptar los Términos y Condiciones.") }
            return
        }
        if (selectedGender == "Otro" && specifyOther.isBlank()) {
            scope.launch { snackbarHostState.showSnackbar("Debe especificar el género.") }
            return
        }

        // 2. Crear objeto User para el registro
        val userToRegister = User(
            nombre = name,
            sexo = selectedGender,
            telefono = phone,
            fechaNacimiento = birthDate,
            correoElectronico = email,
            // La descripción se toma de 'Especifique' si es 'Otro', sino está vacía.
            descripcion = if (selectedGender == "Otro") specifyOther else "",
            contrasenia = password
            // id, rol, respectPoints, isAnonymus usan los valores por defecto
        )

        // 3. Ejecutar el registro en un Coroutine
        scope.launch {
            isLoading = true
            try {
                userService.registerUser(userToRegister)
                // Éxito: Navegar
                onRegisterSuccess()
            } catch (e: Exception) {
                // Manejo de errores de Firebase (ej: correo ya en uso, formato inválido)
                val errorMessage = when (e.message) {
                    "The email address is already in use by another account." -> "El correo electrónico ya está registrado."
                    "The email address is badly formatted." -> "Formato de correo inválido."
                    else -> "Error en el registro: ${e.message}"
                }
                snackbarHostState.showSnackbar(errorMessage)
            } finally {
                isLoading = false
            }
        }
    }

    // --- Lógica de Registro Anónimo ---
    fun registerAnonymously() {
        scope.launch {
            isLoading = true
            try {
                userService.signInAnonymously()
                // Mostrar Snackbar
                val result = snackbarHostState.showSnackbar(
                    message = "Esto ocultará algunos datos a los demás",
                    actionLabel = "Aceptar",
                    duration = SnackbarDuration.Short
                )
                // Si el usuario acepta o el Snackbar termina, se navega hacia atrás
                onNavigateBack()

            } catch (e: Exception) {
                snackbarHostState.showSnackbar("Error al iniciar sesión anónima.")
            } finally {
                isLoading = false
            }
        }
    }

    // Scaffold es necesario para mostrar el Snackbar correctamente encima del contenido
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                // Personalizamos el Snackbar para que se vea oscuro
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(0xFF546E7A), // Gris azulado oscuro
                    contentColor = Color.White,
                    actionColor = Color(0xFF448AFF) // Azul para "Aceptar"
                )
            }
        },
        containerColor = Color.White
    ) { paddingValues ->

        // --- Contenido Principal ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Scroll vertical
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Campo Nombre
            SimpleOutlinedTextField(value = name, onValueChange = { name = it }, placeholder = "Nombre")

            Spacer(modifier = Modifier.height(16.dp))

            // Radio Buttons: Género
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GenderOption("Hombre", selectedGender) { selectedGender = "Hombre" }
                Spacer(modifier = Modifier.width(8.dp))
                GenderOption("Mujer", selectedGender) { selectedGender = "Mujer" }
                Spacer(modifier = Modifier.width(8.dp))
                GenderOption("Otro", selectedGender) { selectedGender = "Otro" }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo Especifique (Habilitado visualmente si es 'Otro' o siempre, según el diseño)
            if (selectedGender == "Otro") {
                SimpleOutlinedTextField(
                    value = specifyOther,
                    onValueChange = { specifyOther = it },
                    placeholder = "Especifique"
                )
            } else {
                // Usamos un Spacer para mantener la consistencia vertical cuando no está visible
                Spacer(modifier = Modifier.height(56.dp))
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Teléfono
            SimpleOutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                placeholder = "Teléfono de contacto",
                keyboardType = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- FECHA DE NACIMIENTO (Con DatePicker) ---
            OutlinedTextField(
                value = birthDate,
                onValueChange = { },
                placeholder = { Text("Fecha de nacimiento", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true, // No se puede escribir, solo seleccionar
                trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray) },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    showDatePicker = true
                                }
                            }
                        }
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Correo
            SimpleOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Correo electrónico personal",
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contraseñas
            SimpleOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña (mínimo 6 caracteres)",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimpleOutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirmar contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Términos y Condiciones
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Gray)
                )

                // Texto con negrita en "Términos y Condiciones"
                val termsText = buildAnnotatedString {
                    append("Acepto ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Términos y Condiciones")
                    }
                }
                Text(text = termsText, fontSize = 14.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Registrarme (Gris oscuro) -> Éxito de Registro
            Button(
                onClick = ::validateAndRegister, // Llamar a la función de registro
                enabled = !isLoading, // Deshabilitar mientras carga
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF616161))
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Registrarme", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- BOTÓN ANÓNIMO ---
            OutlinedButton(
                onClick = ::registerAnonymously,
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.DarkGray)
            ) {
                Text("Registrarme como anonimo", fontSize = 16.sp)
            }
        }
    }

    // --- Lógica del Pop-up de Calendario ---
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        // Formatear fecha
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        birthDate = formatter.format(Date(millis))
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

// --- Componentes Auxiliares para reducir código repetitivo (se mantienen) ---

@Composable
fun SimpleOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray
        )
    )
}

@Composable
fun GenderOption(label: String, selectedOption: String, onSelect: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = Color.Gray)
        )
        Text(text = label, color = Color.Gray, modifier = Modifier.clickable { onSelect() })
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterSuccess = {},
        onNavigateBack = {}
    )
}