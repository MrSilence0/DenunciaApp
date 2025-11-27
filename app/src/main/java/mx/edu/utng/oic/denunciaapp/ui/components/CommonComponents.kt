package mx.edu.utng.oic.denunciaapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

// --- Colores y constantes reutilizables ---
val WireframeGray = Color(0xFF9E9E9E)

// ----------------------------------------------------
// 1. LabelText: Etiqueta de texto alineada a la izquierda
// ----------------------------------------------------
// --- CÓDIGO CORREGIDO ---
@Composable
fun LabelText(text: String) {
    Text(
        text = text,
        color = Color.DarkGray,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start // <--- CORRECTO: Usa TextAlign.Start
    )
}

// ----------------------------------------------------
// 2. SimpleOutlinedTextField: Campo de texto genérico (no dialog)
// ----------------------------------------------------
@Composable
fun SimpleOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = WireframeGray) },
        modifier = modifier,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = WireframeGray,
            unfocusedBorderColor = Color.LightGray
        ),
        interactionSource = interactionSource
    )
}

// ----------------------------------------------------
// 3. OutlinedTextFieldWithDialog: Campo que abre un Pop-up (ej. Fecha, Ubicación)
// ----------------------------------------------------
@Composable
fun OutlinedTextFieldWithDialog(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onClick: () -> Unit,
    onValueChange: (String) -> Unit = {} // Opcional, solo si el campo es editable
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = WireframeGray) },
        modifier = modifier,
        readOnly = true,
        singleLine = true,
        // Configura el interactionSource para detectar el clic y ejecutar onClick
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            onClick()
                        }
                    }
                }
            },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = WireframeGray,
            unfocusedBorderColor = Color.LightGray
        )
    )
}

// ----------------------------------------------------
// 4. GenderOption: Componente de RadioButton + Etiqueta
// ----------------------------------------------------
@Composable
fun GenderOption(label: String, selectedOption: String, onSelect: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (label == selectedOption),
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = WireframeGray)
        )
        Text(
            text = label,
            color = Color.DarkGray,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onSelect() }
        )
    }
}

// ----------------------------------------------------
// 5. ImagePlaceholder: Box genérico para Logos/Imágenes con corrección de Shape
// ----------------------------------------------------
@Composable
fun ImagePlaceholder(
    size: Dp,
    color: Color = Color.LightGray,
    shape: Shape = RectangleShape,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    content: @Composable BoxScope.() -> Unit = {
        Icon(Icons.Default.Image, contentDescription = "Placeholder", tint = Color.Gray)
    }
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color, shape)
            .border(borderWidth, borderColor, shape),
        contentAlignment = Alignment.Center,
        content = content
    )
}

// ----------------------------------------------------
// 6. GridMenuItem: Elemento del menú de denuncias
// ----------------------------------------------------
@Composable
fun GridMenuItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        // Placeholder para el icono grande
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(50.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}