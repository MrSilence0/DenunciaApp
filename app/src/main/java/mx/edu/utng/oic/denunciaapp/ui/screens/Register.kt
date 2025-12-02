package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
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
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import mx.edu.utng.oic.denunciaapp.R
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.RegisterViewModel
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    // --- Estados del Formulario (Locales a la pantalla) ---
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Hombre") } // Default
    var specifyOther by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }

    // --- Estados del ViewModel ---
    val isLoading = registerViewModel.isLoading || loginViewModel.isLoading
    val message = registerViewModel.message
    val isRegistrationSuccessful = registerViewModel.isRegistrationSuccessful

    // --- Estados para Lógica de UI ---
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showDatePicker by remember { mutableStateOf(false) }

    // Configuración del DatePicker
    val datePickerState = rememberDatePickerState()

    // --- Efectos del ViewModel ---

    // 1. Manejar el mensaje (error o éxito)
    LaunchedEffect(message) {
        message?.let { msg ->
            snackbarHostState.showSnackbar(
                message = msg,
                actionLabel = "Cerrar",
                duration = SnackbarDuration.Short
            )
            if (!isRegistrationSuccessful) {
                registerViewModel.clearMessage()
            }
        }
    }

    // 2. Manejar el éxito del registro
    LaunchedEffect(isRegistrationSuccessful) {
        if (isRegistrationSuccessful) {
            // La navegación se realiza después de que el ViewModel confirma el éxito
            onRegisterSuccess()
        }
    }


    // --- Lógica de Registro (Delegada al ViewModel) ---
    fun handleRegistration() {
        // Validaciones que son específicas de la UI o antes de enviar al VM
        if (!termsAccepted) {
            scope.launch { snackbarHostState.showSnackbar("Debe aceptar los Términos y Condiciones.") }
            return
        }
        if (selectedGender == "Otro" && specifyOther.isBlank()) {
            scope.launch { snackbarHostState.showSnackbar("Debe especificar el género.") }
            return
        }

        // Llamada al ViewModel con todos los datos del formulario
        registerViewModel.register(
            nombre = name,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            sexo = selectedGender,
            telefono = phone,
            fechaNacimiento = birthDate,
            descripcion = if (selectedGender == "Otro") specifyOther else ""
        )
    }

    fun LoginViewModel.signInAnonymously(): Boolean {
        return false
    }

    // --- Lógica de Registro Anónimo (Delegada al LoginViewModel o RegisterViewModel) ---
    // Usaremos el LoginViewModel ya que la lógica de iniciar sesión anónimamente
    // es conceptualmente más cercana a un "login" que a un "registro" completo.
    fun registerAnonymously() {
        scope.launch {
            if (loginViewModel.signInAnonymously()) {
                // Si el inicio de sesión anónimo es exitoso:
                snackbarHostState.showSnackbar(
                    message = "Iniciado sesión como anónimo. Esto ocultará algunos datos a los demás.",
                    actionLabel = "Aceptar",
                    duration = SnackbarDuration.Short
                )
                // Usamos onNavigateBack para volver a la pantalla de Denuncias
                onNavigateBack()

            } else {
                // El error se maneja y se muestra vía el LoginViewModel.errorMessage, pero forzamos un mensaje aquí también.
                snackbarHostState.showSnackbar("Error al iniciar sesión anónima.")
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                // Personalizamos el Snackbar para que se vea oscuro
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(0xFF546E7A),
                    contentColor = Color.White,
                    actionColor = Color(0xFF448AFF)
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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- NUEVO: Encabezado de la App (Icono y Nombre) ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Icono de la App
                Image(
                    painter = painterResource(id = R.drawable.denunciaappicon),
                    contentDescription = "Logo DenunciaApp",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                // Nombre de la App
                Text(
                    text = "DenunciaApp",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D47A1)
                )
            }

            // Campo Nombre
            SimpleOutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Nombre",
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Radio Buttons: Género
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GenderOption("Hombre", selectedGender, !isLoading) { selectedGender = "Hombre" }
                Spacer(modifier = Modifier.width(8.dp))
                GenderOption("Mujer", selectedGender, !isLoading) { selectedGender = "Mujer" }
                Spacer(modifier = Modifier.width(8.dp))
                GenderOption("Otro", selectedGender, !isLoading) { selectedGender = "Otro" }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo Especifique
            if (selectedGender == "Otro") {
                SimpleOutlinedTextField(
                    value = specifyOther,
                    onValueChange = { specifyOther = it },
                    placeholder = "Especifique",
                    enabled = !isLoading
                )
            } else {
                Spacer(modifier = Modifier.height(56.dp))
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Teléfono
            SimpleOutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                placeholder = "Teléfono de contacto",
                keyboardType = KeyboardType.Phone,
                enabled = !isLoading
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
                enabled = !isLoading,
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release && !isLoading) {
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
                keyboardType = KeyboardType.Email,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contraseñas
            SimpleOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña (mínimo 6 caracteres)",
                isPassword = true,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimpleOutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirmar contraseña",
                isPassword = true,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Términos y Condiciones
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = {
                        if (!isLoading) termsAccepted = it
                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Gray),
                    enabled = !isLoading
                )

                // Texto con negrita en "Términos y Condiciones"
                val termsText = buildAnnotatedString {
                    append("Acepto ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Términos y Condiciones")
                    }
                }
                Text(
                    text = termsText,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.clickable(enabled = !isLoading) { termsAccepted = !termsAccepted }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Registrarme (Gris oscuro) -> Éxito de Registro
            Button(
                onClick = ::handleRegistration, // Llamar a la función que usa el ViewModel
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
            Spacer(modifier = Modifier.height(24.dp)) // Espacio al final del scroll
        }
    }

    // --- Lógica del Pop-up de Calendario ---
    if (showDatePicker && !isLoading) {
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
    isPassword: Boolean = false,
    enabled: Boolean = true // Añadir parámetro enabled
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
        ),
        enabled = enabled // Aplicar el estado de enabled
    )
}

@Composable
fun GenderOption(label: String, selectedOption: String, enabled: Boolean, onSelect: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = { if (enabled) onSelect() }, // Solo permitir click si está habilitado
            colors = RadioButtonDefaults.colors(selectedColor = Color.Gray),
            enabled = enabled // Aplicar el estado de enabled
        )
        Text(
            text = label,
            color = if (enabled) Color.Gray else Color.LightGray,
            modifier = Modifier.clickable(enabled = enabled) { onSelect() }
        )
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
