# DenunciaApp - MX.EDU.UTNG.OIC.DENUNCIAAPP
## Descripción del Proyecto

**DenunciaApp** es una aplicación móvil desarrollada para facilitar y agilizar el proceso de reporte de diversos tipos de incidentes y denuncias por parte de los ciudadanos a las autoridades competentes.

La aplicación utiliza la estructura de directorios y componentes de **Jetpack Compose** para ofrecer una interfaz de usuario moderna y una navegación fluida, permitiendo a los usuarios reportar desde extravíos y robos (RoboCasa, RoboVehiculo, etc.) hasta situaciones de violencia y extorsión.

## Tecnologías Utilizadas

* **Lenguaje:** Kotlin
* **Framework UI:** Jetpack Compose
* **Arquitectura:** Sigue principios de Clean Architecture y utiliza el patrón MVVM (Model-View-ViewModel).
* **Dependencias Comunes:** AndroidX, Jetpack Navigation.

## Estructura del Proyecto

El código está organizado siguiendo las mejores prácticas de desarrollo Android moderno: MVVM.

## ¿Qué es MVVM (Model-View-ViewModel)?
Es un patrón de arquitectura. Imagina una cocina de restaurante:

Model (Modelo): Los ingredientes y recetas (los datos)
View (Vista): El plato servido al cliente (lo que ve el usuario)
ViewModel: El chef que coordina todo (la lógica)
Ventaja: Si cambias el chef (ViewModel), los ingredientes (Model) y el plato (View) siguen funcionando. Todo está separado y organizado.

##  Instalación y Configuración

Para obtener una copia local de este proyecto y ponerlo en marcha, sigue estos sencillos pasos.

### Requisitos Previos

* Android Studio (Versión recomendada: Hedgehog o posterior)
* SDK de Android 34+
* Java/JDK 17+
* Conexión a internet para la descarga de dependencias de Gradle.

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/MrSilence0/DenunciaApp.git](https://github.com/MrSilence0/DenunciaApp.git)
    cd DenunciaApp
    ```
2.  **Abrir en Android Studio:**
    Abre el proyecto clonado en Android Studio.
3.  **Configurar Variables de Entorno (Opcional):**
    Si la aplicación requiere claves API o URLs de Backend, edita el archivo `local.properties` o `AppConfig.kt` según sea necesario.
4.  **Sincronizar Gradle:**
    Espera a que Gradle sincronice las dependencias del proyecto.
5.  **Ejecutar:**
    Selecciona un emulador o dispositivo físico y presiona el botón **Run** (Ejecutar).

## Contribución

Las contribuciones son bienvenidas. Si deseas mejorar la aplicación, reportar un *bug* o sugerir una nueva característica, por favor:

1.  Haz un `Fork` del proyecto.
2.  Crea una nueva rama (`git checkout -b feature/AmazingFeature`).
3.  Comité tus cambios (`git commit -m 'Add some AmazingFeature'`).
4.  Sube la rama (`git push origin feature/AmazingFeature`).
5.  Abre un `Pull Request`.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## Contacto

* **Universidad Tecnologíca del Norte de Guanajuato** - 1224100506@alumnos.utng.edu.mx
* **Enlace de GitHub del Proyecto:** [https://github.com/MrSilence0/DenunciaApp](https://github.com/MrSilence0/DenunciaApp)

## Arquitectura del proyecto

<code>.
└── app
    ├── manifests
    │   └── AndroidManifest.xml
    ├── kotlin+java
    │   └── mx.edu.utng.oic.denunciaapp
    │       ├── data
    │       ├── navigation
    │       └── ui
    │           ├── components
    │           └── screens
    │               ├── Agencias.kt
    │               ├── BottomNavigationBar.kt
    │               ├── CreateForumScreen.kt
    │               ├── DenunciaFotografica.kt
    │               ├── Denuncias.kt
    │               ├── DenunciaViolencia.kt
    │               ├── Extorsion.kt
    │               ├── ForgotPasswordScreen.kt
    │               ├── ForosPage.kt
    │               ├── HomePage.kt
    │               ├── Login.kt
    │               ├── Menu.kt
    │               ├── Messages.kt
    │               ├── MessagesPage.kt
    │               ├── MisDenuncias.kt
    │               ├── PersonaDesaparecida.kt
    │               ├── Posts.kt
    │               ├── Register.kt
    │               └── RoboCasa.kt
    ├── RoboCasa.kt
    ├── RoboObjeto.kt
    ├── RoboVehiculo.kt
    ├── SosScreen.kt
    ├── UserProfile.kt
    ├── theme
    ├── utils
    ├── viewmodel
    │   └── NuevaDenunciaViewModel.kt
    ├── AppConfig.kt
    └── MainActivity.kt
    ├── mx.edu.utng.oic.denunciaapp (androidTest)
    ├── mx.edu.utng.oic.denunciaapp (test)
    └── res
        ├── drawable
        │   ├── denunciaappicon.png
        │   ├── ic_launcher_background.xml
        │   ├── ic_launcher_foreground.xml
        │   ├── notice1.png
        │   ├── notice2.png
        │   └── notice3.png
        └── java (generated)</code>

## Configuración inicial
### 1. Archivo libs.version.toml
 ```bash
[versions]
agp = "8.13.1"
firebaseBom = "34.6.0"
kotlin = "2.0.21"
coreKtx = "1.17.0"
junit = "4.13.2"
junitVersion = "1.3.0"
espressoCore = "3.7.0"
lifecycleRuntimeKtx = "2.10.0"
activityCompose = "1.12.0"
composeBom = "2024.09.00"
mapsCompose = "6.12.2"
materialIconsExtended = "1.7.8"
playServicesLocation = "21.3.0"
playServicesMaps = "19.2.0"
uiText = "1.9.5"
foundation = "1.9.5"
uiGraphics = "1.9.5"
navigationRuntimeKtx = "2.9.6"
navigationCompose = "2.9.6"
firebaseFirestoreKtxVersion = "25.1.4"
firebaseAuthKtx = "24.0.1"
firebaseCrashlyticsBuildtools = "3.0.6"
roomKtx = "2.8.4" # Versión de Firestore antes de la unificación total

[libraries]
androidx-compose-material-icons-extended = { module = "androidx.compose.material:material-icons-extended", version.ref = "materialIconsExtended" }
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }

--- Firebase Dependencies ---
firebase-bom = { module = "com.google.firebase:firebase-bom", version.ref = "firebaseBom" }

firebase-firestore-ktx-v2514 = { module = "com.google.firebase:firebase-firestore-ktx", version.ref = "firebaseFirestoreKtxVersion" }

--- Testing ---
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-compose-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-compose-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-compose-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-compose-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-compose-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-compose-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-compose-ui-text = { group = "androidx.compose.ui", name = "ui-text", version.ref = "uiText" }
androidx-compose-foundation = { group = "androidx.compose.foundation", name = "foundation", version.ref = "foundation" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics", version.ref = "uiGraphics" }
androidx-navigation-runtime-ktx = { group = "androidx.navigation", name = "navigation-runtime-ktx", version.ref = "navigationRuntimeKtx" }
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }

--- Maps Dependencies ---
maps-compose = { module = "com.google.maps.android:maps-compose", version.ref = "mapsCompose" }
play-services-location = { module = "com.google.android.gms:play-services-location", version.ref = "playServicesLocation" }
play-services-maps = { module = "com.google.android.gms:play-services-maps", version.ref = "playServicesMaps" }
firebase-auth-ktx = { group = "com.google.firebase", name = "firebase-auth-ktx", version.ref = "firebaseAuthKtx" }
firebase-crashlytics-buildtools = { group = "com.google.firebase", name = "firebase-crashlytics-buildtools", version.ref = "firebaseCrashlyticsBuildtools" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomKtx" }


[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```

2. Archivo build.gradle.kts (Module: app)
    ```bash
    plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
    }

    android {
    namespace = "mx.edu.utng.oic.denunciaapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "mx.edu.utng.oic.denunciaapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["MAPS_API_KEY"] = "AIzaSyCDDrnsnyBme0UcW8Z7G9VKPPdIA9tAcJ4"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    }

    dependencies {
      implementation("com.google.code.gson:gson:2.10.1")


    // === DEPENDENCIAS DE CORE/COMPOSE ===
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx.v2514)
    implementation("com.google.firebase:firebase-auth")

    implementation("com.google.maps.android:maps-compose:3.1.0")
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    }
    ```

## Capa de datos
### Denuncia.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

const val DENUNCIA_TYPE_FIELD = "denunciaClassType"

enum class TipoIncidente(val id: Int) {
    DENUNCIA_FOTOGRAFICA(1),
    PERSONA_DESAPARECIDA(2),
    ROBO_VEHICULO(3),
    EXTORSION(4),
    ROBO_CASA(5),
    ROBO_OBJETO(6),
    DENUNCIA_VIOLENCIA(7)
}


sealed class Denuncia {
    abstract val id: String
    abstract val tipo: TipoIncidente
    abstract val idUser: String
    abstract val creationDate: Date
    abstract val denunciaClassType: String
}


/**
 * 1. Denuncia Fotográfica
 */
data class DenunciaFotografica(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val descripcion: String = "",
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.DENUNCIA_FOTOGRAFICA,
    override val denunciaClassType: String = "DenunciaFotografica"
) : Denuncia()


/**
 * 2. Persona Desaparecida
 */
data class PersonaDesaparecida(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val nombreDesaparecido: String = "",
    val sexo: String = "",
    val descripcionFisica: String = "",
    val vestimenta: String = "",
    val edad: Int = 0,
    override val tipo: TipoIncidente = TipoIncidente.PERSONA_DESAPARECIDA,
    override val denunciaClassType: String = "PersonaDesaparecida"
) : Denuncia()


/**
 * 3. Robo de Vehículo
 */
data class RoboVehiculo(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val placas: String = "",
    val numeroSerie: String = "",
    val marca: String = "",
    val color: String = "",
    val anio: Int = 0,
    val nombreReportante: String = "",
    override val tipo: TipoIncidente = TipoIncidente.ROBO_VEHICULO,
    override val denunciaClassType: String = "RoboVehiculo"
) : Denuncia()


/**
 * 4. Extorsión
 */
data class Extorsion(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val numeroTelefonico: String = "",
    val descripcion: String = "",
    override val tipo: TipoIncidente = TipoIncidente.EXTORSION,
    override val denunciaClassType: String = "Extorsion"
) : Denuncia()


/**
 * 5. Robo a Casa
 */
data class RoboCasa(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val descripcion: String = "",
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    val telefonoContacto: String = "",
    override val tipo: TipoIncidente = TipoIncidente.ROBO_CASA,
    override val denunciaClassType: String = "RoboCasa"
) : Denuncia()


/**
 * 6. Robo de Objeto
 */
data class RoboObjeto(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val tipoObjeto: String = "",
    val marca: String = "",
    val estado: String = "",
    val color: String = "",
    val anio: Int? = null,
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.ROBO_OBJETO,
    override val denunciaClassType: String = "RoboObjeto"
) : Denuncia()


/**
 * 7. Denuncia de Violencia (Descripción, Ubicación, Tipo de Conducta y Teléfono)
 */
data class DenunciaViolencia(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val descripcion: String = "",
    val tipoConducta: String = "",
    val telefonoContacto: String = "",
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.DENUNCIA_VIOLENCIA,
    override val denunciaClassType: String = "DenunciaViolencia"
) : Denuncia()</code>


## Repositorio
### DenunciaRepository.kt

<code>package mx.edu.utng.oic.denunciaapp.data.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia

class DenunciaRepository(
    private val firestore: FirebaseFirestore
) {

    private val COLLECTION_NAME = "denuncias"

    fun getDenunciasCollection(): CollectionReference =
        firestore.collection(COLLECTION_NAME)

    /**
     * Guarda SIEMPRE usando el ID del objeto Denuncia.
     */
    suspend fun addDenuncia(denuncia: Denuncia): Boolean {
        return try {
            firestore.collection(COLLECTION_NAME)
                .document(denuncia.id)
                .set(denuncia)
                .await()

            Log.i("DenunciaRepository", "Denuncia guardada con ID correcto: ${denuncia.id}")

            true
        } catch (e: Exception) {
            Log.e("DenunciaRepository", "ERROR al guardar denuncia: ${e.message}", e)
            false
        }
    }

}
```

## Arquitectura y Gestión de Datos

El proyecto sigue el patrón de arquitectura **MVVM (Model-View-ViewModel)**. Esta estructura desacopla la lógica de negocio de la interfaz de usuario, haciendo el código más mantenible y fácil de testear.

### 1. El Rol del Paquete `data.model`

El paquete `data.model` contiene todas las estructuras de datos esenciales para la aplicación. Su rol dentro del modelo MVVM es crucial:

* **Representación:** Define cómo se ven los objetos de la aplicación, como `Denuncia`, `Usuario`, o `Agencia`.
* **Transferencia:** Actúa como el formato estándar para mover datos entre la capa de **Model** (API o Base de Datos) y la capa de **ViewModel**.
* **Persistencia:** Facilita la serialización y deserialización de datos (ej. al guardar en la base de datos o al comunicarse con una API REST).

### 2. Clases de Kotlin: `class` vs. `sealed class`

El proyecto utiliza diferentes tipos de clases para propósitos específicos de la arquitectura:

#### **Clase de Datos (`data class`)**

| Concepto | Uso en el Proyecto |
| :--- | :--- |
| Una clase diseñada **únicamente para contener datos**. | **Modelos de datos** en `data.model`, como `data class Denuncia(...)` o `data class Post(...)`. |
| Kotlin genera automáticamente métodos clave como `equals()`, `hashCode()`, `toString()`, y `copy()`. | Simplifica la manipulación y comparación de objetos de datos. |

#### **Clase Sellada (`sealed class`)**

| Concepto | Uso en el Proyecto |
| :--- | :--- |
| Restringe la jerarquía de herencia: solo las clases declaradas en el mismo archivo (o módulo) pueden ser sus subclases. | **Representar estados finitos de la UI** en `viewmodel` o `ui`, como el estado de una carga de datos. |
| Es ideal para trabajar con expresiones `when`, ya que el compilador te **obliga a manejar todos los posibles estados** definidos. | Previene errores en la interfaz de usuario asegurando que se gestionen los estados `Loading`, `Success`, y `Error`. |

**Ejemplo Conceptual de Uso de Clase Sellada (Sealed Class):**

```kotlin
sealed class DenunciaUiState {
    object Cargando : DenunciaUiState()
    data class Exito(val denuncias: List<Denuncia>) : DenunciaUiState()
    data class Error(val mensaje: String) : DenunciaUiState()
}


## Capa de Servicio
### DenunciaService.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.data.service

import android.util.Log
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.*
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository

class DenunciaService(
    private val denunciaRepository: DenunciaRepository
) {

    suspend fun saveDenuncia(denuncia: Denuncia): Boolean {
        return try {
            denunciaRepository.addDenuncia(denuncia)
        } catch (e: Exception) {
            Log.e("DenunciaService", "Error guardando denuncia: ${e.localizedMessage}", e)
            false
        }
    }

    suspend fun getDenunciasByUserId(idUser: String): List<Denuncia> {
        val denuncias = mutableListOf<Denuncia>()
        try {
            val snapshot = denunciaRepository
                .getDenunciasCollection()
                .whereEqualTo("idUser", idUser)
                .get()
                .await()

            for (doc in snapshot.documents) {
                // Obtiene el campo discriminador (denunciaClassType)
                val type = doc.getString(DENUNCIA_TYPE_FIELD)

                val denuncia = when (type) {
                    "DenunciaFotografica" -> doc.toObject(DenunciaFotografica::class.java)
                    "PersonaDesaparecida" -> doc.toObject(PersonaDesaparecida::class.java)
                    "RoboVehiculo" -> doc.toObject(RoboVehiculo::class.java)
                    "Extorsion" -> doc.toObject(Extorsion::class.java)
                    "RoboCasa" -> doc.toObject(RoboCasa::class.java)
                    "RoboObjeto" -> doc.toObject(RoboObjeto::class.java)
                    "DenunciaViolencia" -> doc.toObject(DenunciaViolencia::class.java)
                    else -> null
                }

                if (denuncia != null) denuncias.add(denuncia)
            }

        } catch (e: Exception) {
            Log.e("DenunciaService", "Error al obtener denuncias para $idUser", e)
        }

        return denuncias
    }

}
```
### La Capa de Servicio (Service Layer)

La capa de Servicio, encapsulada en archivos como `DenunciaService.kt`, es la responsable de **orquestar las operaciones de negocio** complejas.

| Rol en MVVM | Descripción |
| :--- | :--- |
| **Coordinación** | Actúa como un intermediario que recibe solicitudes del **ViewModel** y las traduce en acciones concretas de datos. |
| **Aislamiento** | **Aísla** al ViewModel de los detalles de bajo nivel, como la gestión de errores de red o la lógica de mapeo de datos. |
| **Manejo de Errores** | Gestiona los bloques `try-catch` y los mensajes de registro (`Log.e`), retornando un resultado simple (ej. `Boolean` o una lista) al ViewModel. |

#### **Funcionamiento con un Ejemplo (`DenunciaService.kt`)**

La implementación de `DenunciaService.kt` muestra cómo la capa de Servicio maneja la complejidad:

1.  **Inyección de Dependencia:** Recibe el `DenunciaRepository` como dependencia, delegando a este la interacción directa con la fuente de datos (ej. Firebase).
2.  **Lógica de Mapeo y Filtrado:** En la función `getDenunciasByUserId`, el Servicio no solo obtiene los documentos del repositorio, sino que también contiene la **lógica de discriminación** (`when (type)`). Esto es crucial para convertir el documento genérico de la base de datos en el tipo de objeto específico de Kotlin que corresponde (`DenunciaFotografica`, `RoboVehiculo`, etc.), antes de enviarlo al ViewModel.
3.  **Encapsulamiento Asíncrono:** Utiliza las funciones `suspend` y `await` para realizar operaciones de red o base de datos de manera asíncrona, simplificando el código para el ViewModel.

**Principio fundamental:** El **ViewModel** pregunta **QUÉ** se necesita (ej. "dame las denuncias del usuario"), y el **Service** se encarga del **CÓMO** (ej. "voy a buscar en la base de datos, mapear los tipos y manejar los errores").

## 4. Capa de Presentación
### DenunciaViolenciaViewModel.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.DenunciaViolencia
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

/**
 * ViewModel específico para la creación y gestión del Reporte de Denuncia de Violencia.
 */
class DenunciaViolenciaViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    // --- ESTADOS OBSERVABLES ---
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    /**
     * Crea y guarda un reporte de Denuncia de Violencia.
     */
    fun submitDenuncia(
        descripcionHecho: String,
        ubicacionText: String?, // Dirección legible
        descripcionConducta: String,
        telefono: String,
        confirmarTelefono: String,
        // Los datos de ubicación (lat/lng) se deben obtener del estado del mapa,
        // pero por simplicidad en esta función, se asume que la pantalla los provee
        // o que ya están disponibles a través de otros estados.
        latitud: Double?,
        longitud: Double?,
        imageUri: String? // Campo no usado en el modelo de datos DenunciaViolencia, pero útil para la UI
    ) {
        if (_isSaving.value) return

        _isSaving.value = true
        _saveError.value = null
        _saveSuccess.value = false

        viewModelScope.launch {
            try {
                // --- 1. Autenticación de Usuario (Asíncrona y robusta) ---
                val userId = withContext(Dispatchers.IO) {
                    userService.getOrCreateUserId()
                } ?: run {
                    _saveError.value = "Fallo al obtener el ID de usuario. Por favor, intente de nuevo."
                    _isSaving.value = false
                    Log.e("DenunciaViolenciaVM", "Fallo al obtener o crear ID de usuario.")
                    return@launch
                }

                // --- 2. Validación de datos ---
                if (descripcionHecho.isBlank() || descripcionConducta.isBlank() || telefono.isBlank()) {
                    _saveError.value = "Los campos de descripción, conducta y teléfono son obligatorios."
                    _isSaving.value = false
                    return@launch
                }

                if (telefono != confirmarTelefono) {
                    _saveError.value = "El teléfono de contacto y su confirmación no coinciden."
                    _isSaving.value = false
                    return@launch
                }

                if (ubicacionText.isNullOrBlank() && (latitud == null || longitud == null)) {
                    _saveError.value = "La ubicación es obligatoria. Por favor, seleccione un punto en el mapa."
                    _isSaving.value = false
                    return@launch
                }

                // --- 3. Construir el objeto DenunciaViolencia ---
                val newDenuncia = DenunciaViolencia(
                    id = UUID.randomUUID().toString(), // Generación de ID
                    idUser = userId,
                    creationDate = Date(),
                    descripcion = descripcionHecho,
                    tipoConducta = descripcionConducta, // Mapeado a tipoConducta
                    telefonoContacto = telefono,
                    locationAddress = ubicacionText,
                    latitud = latitud,
                    longitud = longitud
                )

                // --- 4. Guardar la denuncia ---
                val success = withContext(Dispatchers.IO) {
                    denunciaService.saveDenuncia(newDenuncia)
                }

                if (success) {
                    _saveSuccess.value = true
                } else {
                    _saveError.value = "Error al guardar la denuncia."
                }
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: "Error desconocido al enviar el reporte."
                _saveError.value = errorMessage
                Log.e("DenunciaViolenciaVM", "Error al enviar el reporte de violencia", e)
            } finally {
                _isSaving.value = false
            }
        }
    }

    /**
     * Limpia los estados de error y éxito.
     */
    fun resetStates() {
        _saveError.value = null
        _saveSuccess.value = false
    }
}

/**
 * Factoría para crear instancias de DenunciaViolenciaViewModel.
 */
class DenunciaViolenciaViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DenunciaViolenciaViewModel::class.java)) {
            return DenunciaViolenciaViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```
### El ViewModel: La Lógica de la Presentación

El **ViewModel** (como `DenunciaViolenciaViewModel.kt`) es el componente fundamental de la capa de Presentación en la arquitectura MVVM. Está diseñado para ser **consciente del ciclo de vida** del componente de la UI (la Pantalla Compose) y gestionar su estado de manera segura.

#### **Rol Crucial en MVVM**

| Rol | Función |
| :--- | :--- |
| **Gestor de Estado** | **Almacena y mantiene los datos** de la UI, sobreviviendo a rotaciones de pantalla o cambios de configuración. |
| **Mediador** | Actúa como un *puente* entre la **View** (las pantallas de Compose) y las **Capas de Servicio/Datos**. Nunca accede directamente a una base de datos o API. |
| **Encapsulador de Lógica** | Contiene toda la lógica de validación, formateo de datos y orquestación de operaciones asíncronas (`Coroutines`). |



#### **Funcionamiento Detallado del ViewModel**

El ejemplo `DenunciaViolenciaViewModel.kt` ilustra cómo el ViewModel maneja el flujo de datos y el estado:

1.  **Exposición de Estado (Observable States):**
    * Utiliza **`StateFlow`** (`_isSaving`, `_saveError`, `_saveSuccess`) para exponer el estado actual de la operación a la View.
    * La View (pantalla) se **suscribe** a estos flujos y se redibuja automáticamente solo cuando el estado cambia (ej. el botón de guardar se deshabilita si `isSaving` es `true`).
2.  **Manejo de Eventos (`submitDenuncia`):**
    * La función `submitDenuncia` es el punto de entrada para los eventos de la UI.
    * Inicia una `coroutine` (`viewModelScope.launch`) para ejecutar operaciones asíncronas sin bloquear la interfaz.
3.  **Orquestación de la Lógica:**
    * **Validación de Datos:** Verifica que los campos obligatorios (teléfono, descripción) existan y que la lógica de negocio se cumpla (ej. `telefono != confirmarTelefono`). Si falla, actualiza `_saveError`.
    * **Delegación al Servicio:** Si la validación es exitosa, construye el objeto `DenunciaViolencia` y delega la tarea de guardar al Service: `denunciaService.saveDenuncia(newDenuncia)`.
    * **Gestión del Resultado:** Captura el resultado del Service (`success`) y actualiza el estado de la UI (`_saveSuccess.value = true` o `_saveError.value = "Error..."`).

**En resumen:** El ViewModel se asegura de que la UI sea tonta (solo sepa mostrar el estado) y que toda la inteligencia sobre qué hacer y cuándo hacerlo resida en él, con la ayuda de la capa de Servicio.

## ViewModel Factory
### UserProfileViewModelFactory.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.oic.denunciaapp.data.service.UserService

class UserProfileViewModelFactory(
    private val userService: UserService // Dependencia: Ya se inicializa solo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserProfileViewModel(
                userService = userService
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```
### ViewModel Factory: Inyección de Dependencias

La **ViewModel Factory** (ej. `UserProfileViewModelFactory.kt`) es un patrón de diseño crucial en Android para mantener la arquitectura limpia y permitir la **Inyección de Dependencias (DI)** en los ViewModels.

#### **Rol y Propósito**

| Rol | Descripción |
| :--- | :--- |
| **Inyección de Dependencias** | Su función principal es **inyectar los servicios** (como `UserService` o `DenunciaService`) en el constructor del ViewModel. |
| **Desacoplamiento** | Permite que el ViewModel sea una clase regular con dependencias en el constructor, en lugar de depender del framework de Android para su inicialización sin argumentos. |
| **Testing** | Facilita las pruebas unitarias, ya que se pueden pasar "mocks" o versiones de prueba de los servicios a la Factory. |



#### **Funcionamiento (`UserProfileViewModelFactory.kt`)**

La implementación de la Factory es sencilla y sigue los siguientes pasos:

1.  **Recepción de Dependencias:** La Factory recibe los objetos de servicio necesarios (`userService: UserService`) en su propio constructor, que generalmente son proporcionados por un contenedor de DI (como Koin o Hilt) o manualmente en la aplicación.
2.  **El Método `create()`:** Este método es llamado por el sistema Android cuando necesita una nueva instancia del ViewModel.
3.  **Instanciación Segura:**
    * Verifica que la `modelClass` solicitada sea la correcta (`modelClass.isAssignableFrom(UserProfileViewModel::class.java)`).
    * Si coincide, crea la instancia de `UserProfileViewModel`, pasando el objeto `userService` que había recibido previamente.

Esto garantiza que cada ViewModel tenga acceso a sus dependencias de servicio sin que la View (o la Activity) tenga que saber cómo inicializarlas.

## UI
### DenunciaViolencia.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.*
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaViolenciaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenunciaViolenciaScreen(
    onCancel: () -> Unit,
    onReportSaved: () -> Unit
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor = MaterialTheme.colorScheme.onTertiary
    val errorColor = MaterialTheme.colorScheme.error
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val backgroundColor = MaterialTheme.colorScheme.background
    val outlineColor = MaterialTheme.colorScheme.outline
    val surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant

    // --- ViewModel y Observación de Estados (Se mantiene) ---
    val viewModel: DenunciaViolenciaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createDenunciaViolenciaViewModelFactory()
    )
    val isSaving by viewModel.isSaving.collectAsStateWithLifecycle()
    val saveError by viewModel.saveError.collectAsStateWithLifecycle()
    val saveSuccess by viewModel.saveSuccess.collectAsStateWithLifecycle()


    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // --- Estados del formulario ---
    var descripcionHecho by remember { mutableStateOf("") }
    var ubicacionText by remember { mutableStateOf("") }
    var descripcionConducta by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var confirmarTelefono by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del Mapa/Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf(DefaultLocation) }
    var mapCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, 14f)) }
    var locationPermissionGranted by remember { mutableStateOf(checkLocationPermission(context)) }


    // --- Manejo de Permisos de Ubicación---
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            getCurrentLocation(context, fusedLocationClient) { newLatLng ->
                selectedLatLng = newLatLng
                mapCameraPosition = CameraPosition.fromLatLngZoom(newLatLng, 15f)
                ubicacionText = getAddressFromLatLng(context, newLatLng)
                showMapDialog = true
            }
        } else {
            showMapDialog = true
        }
    }

    // --- Efecto para obtener la ubicación inicial---
    LaunchedEffect(Unit) {
        if (locationPermissionGranted) {
            getCurrentLocation(context, fusedLocationClient) { newLatLng ->
                selectedLatLng = newLatLng
                mapCameraPosition = CameraPosition.fromLatLngZoom(newLatLng, 15f)
                ubicacionText = getAddressFromLatLng(context, newLatLng)
            }
        }
    }

    // --- Lógica para abrir el mapa/pedir permisos ---
    val openMapAction: () -> Unit = {
        if (checkLocationPermission(context)) {
            showMapDialog = true
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // --- Manejo de Resultado de Guardado ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onReportSaved()
            viewModel.resetStates()
        }
    }

    // --- Snackbar Host State  ---
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(saveError) {
        saveError?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Aceptar",
                duration = SnackbarDuration.Short
            )
            viewModel.resetStates()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Violencia de Género", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(onClick = onCancel) {
                        Text("Cancelar", color = errorColor, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                // CORRECCIÓN 5: Asegurar el fondo dinámico
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    // CORRECCIÓN 6: Usar surfaceVariant para el fondo de placeholder
                    .background(surfaceVariantColor, RoundedCornerShape(8.dp))
                    // CORRECCIÓN 7: Usar outline color para el borde
                    .border(1.dp, outlineColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Placeholder de imagen",
                            modifier = Modifier.size(60.dp),
                            tint = onSurfaceVariantColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Evidencia fotográfica (opcional)", color = onSurfaceVariantColor)
                    }
                } else {
                    Text("Imagen seleccionada", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería", color = onSurfaceColor)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Campos de texto ---

            // Descripción del hecho
            LabelText("Describe el hecho", color = onSurfaceColor)
            OutlinedTextField(
                value = descripcionHecho,
                onValueChange = { descripcionHecho = it },
                placeholder = { Text("¿Qué sucedió?", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Ubicación
            LabelText("Ubicación", color = onSurfaceColor)
            OutlinedTextField(
                value = ubicacionText,
                onValueChange = { /* No permitir edición manual */ },
                placeholder = { Text("Toque para seleccionar en el mapa", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = openMapAction) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Abrir mapa", tint = primaryColor)
                    }
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    openMapAction()
                                }
                            }
                        }
                    },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Descripción de la conducta
            LabelText("Describe la conducta", color = onSurfaceColor)
            OutlinedTextField(
                value = descripcionConducta,
                onValueChange = { descripcionConducta = it },
                placeholder = { Text("Detalle la conducta del agresor", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Teléfono de contacto
            LabelText("Teléfono de contacto", color = onSurfaceColor)
            OutlinedTextField(
                value = telefono,
                onValueChange = { if (it.length <= 10) telefono = it },
                placeholder = { Text("Número a 10 dígitos", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Confirmar teléfono
            LabelText("Confirmar teléfono", color = onSurfaceColor)
            OutlinedTextField(
                value = confirmarTelefono,
                onValueChange = { if (it.length <= 10) confirmarTelefono = it },
                placeholder = { Text("Repita el número", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Su información es confidencial y solo será utilizada para los fines de la denuncia. Asegúrese de que todos los datos sean correctos antes de guardar.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        descripcionHecho = descripcionHecho,
                        ubicacionText = ubicacionText.ifBlank { null },
                        descripcionConducta = descripcionConducta,
                        telefono = telefono,
                        confirmarTelefono = confirmarTelefono,
                        latitud = selectedLatLng.latitude,
                        longitud = selectedLatLng.longitude,
                        imageUri = selectedImageUri
                    )
                },
                enabled = !isSaving,
                colors = ButtonDefaults.buttonColors(containerColor = tertiaryColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(color = onTertiaryColor, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        "Guardar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        // CORRECCIÓN 19: Usar onTertiary (asegura contraste)
                        color = onTertiaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo REAL con el Mapa de Google  ---
    if (showMapDialog) {
        MapSelectionDialog(
            cameraPosition = mapCameraPosition,
            markerLocation = selectedLatLng,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = { newLatLng ->
                selectedLatLng = newLatLng
                ubicacionText = getAddressFromLatLng(context, newLatLng)
                showMapDialog = false
            },
            onCameraMove = { newCameraPosition ->
                mapCameraPosition = newCameraPosition
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciaViolenciaPreview() {
    MaterialTheme {
        DenunciaViolenciaScreen(
            onCancel = {},
            onReportSaved = {}
        )
    }
}
```
### La Capa de Presentación (View) con Jetpack Compose

El paquete `ui.screens` contiene las **Vistas**, que en este proyecto son funciones `@Composable` de **Jetpack Compose**. Su único rol es **observar el estado** proporcionado por el **ViewModel** y **traducir ese estado en píxeles** en la pantalla.

#### **Rol en MVVM (`DenunciaViolenciaScreen.kt`)**

La pantalla de `DenunciaViolenciaScreen` ejemplifica la **View** "tonta" (Dumb View) del patrón:

1.  **Observación de Estado:** La pantalla utiliza `viewModel.isSaving.collectAsStateWithLifecycle()` para leer los estados que expone el ViewModel. Esto le permite reaccionar automáticamente: si `isSaving` es `true`, el botón "Guardar" muestra un `CircularProgressIndicator` y se deshabilita.
2.  **Manejo de Eventos:** Los clics y la entrada de texto del usuario (eventos) se traducen en llamadas al ViewModel (ej. `viewModel.submitDenuncia(...)`). La View **nunca** contiene lógica de validación ni accede a servicios.
3.  **Gestión de la UI:** Contiene los elementos visuales (Botones, `OutlinedTextField`, `Scaffold`) y la lógica mínima requerida para la UI, como el manejo de permisos o la apertura de diálogos.

#### **Interacción con Servicios y Usuario**

Esta pantalla demuestra una interacción compleja con el usuario y las APIs externas, mediada por el ViewModel:

| Interacción | Componentes en Juego | Funcionamiento |
| :--- | :--- | :--- |
| **Recolección de Datos** | **Compose State** (`remember { mutableStateOf("") }`) | Los campos de texto (ej. `descripcionHecho`, `telefono`) guardan temporalmente la entrada del usuario en el estado local de Compose. |
| **Ubicación (Maps API)** | **Location Services** y **Permissions Launcher** | La pantalla gestiona los permisos de ubicación (`locationPermissionLauncher`). Si se concede, obtiene las coordenadas (`latitud`/`longitud`) y la dirección legible (`ubicacionText`) a través de la API de Google Maps. |
| **Guardado (Firebase API)** | **ViewModel** y **LaunchedEffect** | Al presionar "Guardar", la pantalla llama a `viewModel.submitDenuncia()`. Un `LaunchedEffect` observa el `saveSuccess` del ViewModel; si es `true`, navega a otra pantalla (`onReportSaved()`). |
| **Notificación al Usuario** | **SnackbarHostState** | La pantalla observa el `saveError` del ViewModel. Si recibe un error, muestra una notificación (`Snackbar`) al usuario, informándole del problema sin que la pantalla haya manejado el error de red o base de datos. |

El modelo **MVVM** garantiza que esta pantalla permanezca enfocada en la presentación, delegando la complejidad del usuario, la ubicación y el *backend* a sus respectivas capas.

## Navegación 
### BottomNavigationBar.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen
/**
 * Componente principal de la barra de navegación inferior, utilizado por el AppEntryNavigation.
 *
 * @param onNavigateTo Callback que se llama cuando se selecciona un ítem de navegación.
 * Recibe el string de la ruta a la que debe navegar.
 */
@Composable
fun BottomNavigationBar(onNavigateTo: (String) -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    val surfaceColor = colorScheme.surface
    val errorColor = colorScheme.error
    val onErrorColor = colorScheme.onError
    val primaryColor = colorScheme.primary
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant
    val onSurfaceColor = colorScheme.onSurface


    val navItems = listOf(
        BottomNavItemData(Icons.Default.Home, "Inicio", AppScreen.HomePage.route),
        BottomNavItemData(Icons.Default.Warning, "Denuncias", AppScreen.Denuncias.route),
        BottomNavItemData(Icons.Default.Groups, "Foros", AppScreen.ForosPage.route),
        BottomNavItemData(Icons.Default.Mail, "Mensajes", AppScreen.Messages.route),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        navItems.forEach { item ->
            BottomNavItem(
                icon = item.icon,
                label = item.label,
                // isActive: Debería calcularse usando el currentRoute
                isActive = false,
                onClick = { onNavigateTo(item.route) },
                primaryColor = primaryColor,
                onSurfaceVariantColor = onSurfaceVariantColor
            )
        }

        // Botón SOS destacado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onNavigateTo(AppScreen.Sos.route) }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(errorColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Botón SOS",
                    tint = onErrorColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                "SOS",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = errorColor
            )
        }
    }
}

data class BottomNavItemData(
    val icon: ImageVector,
    val label: String,
    val route: String
)

/**
 * Componente individual para los ítems de navegación.
 */
@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    primaryColor: Color,
    onSurfaceVariantColor: Color
) {
    val iconColor = if (isActive) primaryColor else onSurfaceVariantColor
    val textColor = if (isActive) primaryColor else onSurfaceVariantColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = iconColor
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = textColor,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    MaterialTheme {
        BottomNavigationBar(onNavigateTo = {})
    }
}
```

## Navegación del Proyecto

El proyecto utiliza **Jetpack Compose Navigation** para gestionar la transición entre pantallas, asegurando una experiencia de usuario fluida y manejando correctamente la pila de retroceso (back stack).

### Barra de Navegación Inferior (`BottomNavigationBar.kt`)

El componente `BottomNavigationBar.kt` es el elemento principal para la navegación de nivel superior en la aplicación.

#### **Construcción y Uso**

| Aspecto | Implementación en `BottomNavigationBar.kt` |
| :--- | :--- |
| **Definición de Rutas** | La navegación está basada en un mapa de rutas predefinidas (ej. `AppScreen.HomePage.route`) que se almacenan en el `data class BottomNavItemData`. |
| **Componentes de Compose** | Utiliza componentes `Row` y `Column` (flexbox) para alinear los ítems de navegación horizontalmente, aprovechando la capacidad de Compose para construir interfaces rápidamente. |
| **Manejo de Clicks** | Cada ítem (y el botón `SOS` destacado) utiliza un *callback* `onClick` que dispara la función `onNavigateTo(route: String)`. Esta función es consumida por el `NavController` principal en la estructura de navegación de la aplicación (`AppEntryNavigation`), la cual realiza la transición a la pantalla deseada. |
| **Botón SOS** | Se ha integrado un botón `SOS` destacado con un color de error (`errorColor`) para proporcionar un acceso rápido a la función de emergencia, separándolo visualmente del resto de los elementos de navegación. |

El diseño modular de Compose permite que este componente sea independiente y solo se preocupe de notificar a la capa superior qué ruta debe cargarse.

### AppNavHost.kt
 ```bash
package mx.edu.utng.oic.denunciaapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mx.edu.utng.oic.denunciaapp.ui.screens.LoginScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RegisterScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.UserProfileScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.HomePageScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.DenunciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.AgenciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.CreateForumScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.DenunciaFotograficaScreen
//import mx.edu.utng.oic.denunciaapp.ui.screens.MisDenunciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.PersonaDesaparecidaScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RoboVehiculoScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RoboCasaScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RoboObjetoScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.ExtorsionScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.DenunciaViolenciaScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.ForosPageScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.MessagesScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.PostsScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.ForgotPasswordScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.MenuScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.MisDenunciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.SosScreen


/**
 * Define el NavHost que gestiona las transiciones entre las diferentes pantallas
 * de la aplicación, utilizando las rutas definidas en AppScreen.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppScreen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // --- 1. Rutas de Autenticación/Perfil ---

        composable(AppScreen.Login.route) {
            LoginScreen(
                onLoginSuccess = { user -> // Acepta el objeto User
                    // Aquí se maneja la navegación después de que el ViewModel confirma el éxito
                    navController.navigate(AppScreen.Denuncias.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(AppScreen.Register.route) },
                onForgotPasswordClick = { navController.navigate(AppScreen.ForgotPassword.route) }
            )
        }
        composable(AppScreen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // Navega a Denuncias y limpia la pila
                    navController.navigate(AppScreen.Denuncias.route) {
                        popUpTo(AppScreen.Register.route) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.ForgotPassword.route) {
            ForgotPasswordScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(AppScreen.UserProfile.route) {
            UserProfileScreen(onNavigateBack = { navController.popBackStack() })
        }

        // --- 2. Rutas de Nivel Superior (Bottom Bar / Menú Principal) ---

        composable(AppScreen.HomePage.route) {
            HomePageScreen(
                onOpenDrawer = { navController.navigate(AppScreen.Menu.route) },
                onNavigateTo = { route -> navController.navigate(route) }
            )
        }

        composable(AppScreen.Denuncias.route) {
            DenunciasScreen(
                // Navegación desde el TopBar/Íconos
                onNavigateToMisDenuncias = { navController.navigate(AppScreen.MisDenuncias.route) },
                // >>> NUEVO CALLBACK para abrir el Menú <<<
                onOpenMenu = { navController.navigate(AppScreen.Menu.route) },

                // Callbacks que se mantienen
                onNavigateToAgencias = { navController.navigate(AppScreen.Agencias.route) },
                onNavigateToPosts = { navController.navigate(AppScreen.Posts.route) },

                // Navegación a Formularios específicos
                onNavigateToDenunciaFotografica = { navController.navigate(AppScreen.DenunciaFotografica.route) },
                onNavigateToPersonaDesaparecida = { navController.navigate(AppScreen.PersonaDesaparecida.route) },
                onNavigateToRoboVehiculo = { navController.navigate(AppScreen.RoboVehiculo.route) },
                onNavigateToRoboCasa = { navController.navigate(AppScreen.RoboCasa.route) },
                onNavigateToRoboObjeto = { navController.navigate(AppScreen.RoboObjeto.route) },
                onNavigateToExtorsion = { navController.navigate(AppScreen.Extorsion.route) },
                onNavigateToDenunciaViolencia = { navController.navigate(AppScreen.DenunciaViolencia.route) },
            )
        }

        composable(AppScreen.ForosPage.route) {
            ForosPageScreen(
                onNavigateToCreateForum = { navController.navigate(AppScreen.CreateForum.route) },
                onNavigateTo = { route -> navController.navigate(route) },
                onOpenMenu = { navController.navigate(AppScreen.Menu.route) }
            )
        }

        composable(AppScreen.CreateForum.route) {
            CreateForumScreen(
                onNavigateBack = { navController.popBackStack() }

            )
        }

        composable(
            route = AppScreen.Messages.route,
            arguments = listOf(navArgument("forumId") { type = NavType.StringType })
        ) { backStackEntry ->
            val forumId = backStackEntry.arguments?.getString("forumId")
            if (forumId != null) {
                MessagesScreen(
                    onBack = { navController.popBackStack() },
                    onOpenDrawer = {
                        navController.navigate(AppScreen.Menu.route)
                    },
                    forumId = forumId
                )
            } else {
                Text("Error: ID del Foro no encontrado.", color = Color.Red)
            }
        }

        composable(AppScreen.Menu.route) {
            MenuScreen(
                onNavigateToProfile = { navController.navigate(AppScreen.UserProfile.route) }, // <<-- ¡El navController SÍ está disponible aquí!
                onNavigateToEmergency = { navController.navigate(AppScreen.EmergencyContacts.route) },
                onNavigateToTerms = { navController.navigate(AppScreen.TermsAndConditions.route) },
                onLogOut = { navController.navigate(AppScreen.Login.route) { popUpTo(0) } },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.Posts.route) {
            PostsScreen(onBack = { navController.popBackStack() },
                onNavigateToChat = { postId -> navController.navigate("messages_page_screen/$postId") },
                onNavigateToMenu = { navController.navigate(AppScreen.Menu.route) }
            )
        }


        // --- 3. Sub-rutas de Detalle y Formularios ---

        composable(AppScreen.MisDenuncias.route) {
            MisDenunciasScreen(
                // Callback para regresar a la pantalla anterior
                onNavigateBack = { navController.popBackStack() },
                onOpenDrawer = { /* Si no se usa el Drawer, se puede dejar vacío o llamar a una función */ },
                onNavigateToDenunciaDetail = { denunciaId -> navController.navigate("${AppScreen.DenunciaDetail.route}/$denunciaId") } // Navega a la vista de detalle
            )
        }

        composable(AppScreen.Agencias.route) {
            AgenciasScreen(onNavigateBack = { navController.popBackStack() })
        }

// FORMULARIOS DE DENUNCIA

        composable(AppScreen.DenunciaFotografica.route) {
            // 1. Uso de onSuccess en lugar de onSave.
            // 2. onCancel regresa a la pila anterior.
            // 3. onSuccess también regresa a la pila anterior después de un guardado exitoso
            //    (la lógica de guardado está en el ViewModel).
            DenunciaFotograficaScreen(
                onCancel = { navController.popBackStack() },
                onSuccess = { navController.popBackStack() }
            )
        }

        composable(AppScreen.PersonaDesaparecida.route) {
            PersonaDesaparecidaScreen(
                onNavigateBack = { navController.popBackStack() } // Implementa el botón "Volver/Cancelar"
                // NOTA: Se eliminó el parámetro 'onSave' ya que la lógica de guardado
                // se maneja internamente dentro de PersonaDesaparecidaScreen a través del ViewModel.
            )
        }

        composable(AppScreen.RoboVehiculo.route) {
            RoboVehiculoScreen(
                // Al ViewModel le pasamos la acción de Volver/Cancelar
                onNavigateBack = { navController.popBackStack() }
                // Se elimina onSave = { ... } porque el ViewModel maneja la lógica de guardado
                // y llama a onNavigateBack cuando la operación tiene éxito.
            )
        }

        composable(AppScreen.RoboCasa.route) {
            RoboCasaScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(AppScreen.RoboObjeto.route) {
            RoboObjetoScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.Extorsion.route) {
            ExtorsionScreen(
                onNavigateBack = { navController.popBackStack() }

            )
        }

        composable(AppScreen.DenunciaViolencia.route) {
            DenunciaViolenciaScreen(
                onCancel = { navController.popBackStack() },

                onReportSaved = {

                    navController.popBackStack()
                }
            )
        }

        composable(AppScreen.Sos.route) {
            SosScreen(
                onOpenMenu = { navController.navigate(AppScreen.Menu.route) }
            )
        }
    }
}
```

## Declaración de Rutas
### AppScreen
 ```bash
package mx.edu.utng.oic.denunciaapp.navigation

/**
 * Define las rutas de navegación de la aplicación como objetos.
 * La 'route' se utiliza para navegar entre Composable.
 */
sealed class AppScreen(val route: String) {
    data object Login : AppScreen("login")
    data object Register : AppScreen("register")
    data object UserProfile : AppScreen("user_profile")

    data object HomePage : AppScreen("home")
    data object Denuncias : AppScreen("denuncias_hub")

    // ...
    data object ForosPage : AppScreen("foros_page_screen")
    data object Messages : AppScreen("messages_page_screen/{forumId}")

    data object Agencias : AppScreen("agencias_screen")
    data object DenunciaFotografica : AppScreen("denuncia_fotografica_screen")
    data object MisDenuncias : AppScreen("mis_denuncias")

    data object PersonaDesaparecida : AppScreen("persona_desaparecida_screen")
    data object RoboVehiculo : AppScreen("robo_vehiculo_screen")
    data object RoboCasa : AppScreen("robo_casa_screen")
    data object RoboObjeto : AppScreen("robo_objeto_screen")
    data object Extorsion : AppScreen("extorsion_screen")
    data object DenunciaViolencia : AppScreen("denuncia_violencia_screen")

    data object Menu : AppScreen("Menu_screen")

    data object Posts : AppScreen("posts_screen")
    data object ForgotPassword : AppScreen("forgot_password_screen")

    data object EmergencyContacts : AppScreen("emergency_contacts_screen")
    data object TermsAndConditions : AppScreen("terms_and_conditions_screen")
    data object DenunciaDetail : AppScreen("denuncia_detail_screen/{denunciaId}")

    data object CreateForum : AppScreen("create_forum")
    data object Sos : AppScreen("sos_screen")
}
```
### Navigation
 ```bash
package mx.edu.utng.oic.denunciaapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.oic.denunciaapp.ui.screens.BottomNavigationBar

@Composable
fun AppEntryNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        AppScreen.HomePage.route,
        AppScreen.Denuncias.route,
        AppScreen.ForosPage.route,
        AppScreen.Agencias.route,
        AppScreen.Messages.route,
        AppScreen.Sos.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    onNavigateTo = { route ->
                        if (route == AppScreen.Sos.route) {
                            navController.navigate(route)
                        } else {
                            navController.navigate(route) {
                                // Configuración para evitar duplicar pantallas en la pila
                                popUpTo(AppScreen.Denuncias.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = AppScreen.Login.route
        )
    }
}
```
## Estructura de Navegación (Jetpack Compose Navigation)

La navegación del proyecto está construida usando **Jetpack Compose Navigation**, proporcionando un manejo declarativo y seguro de las transiciones entre pantallas.

### Flujo de Navegación Principal

El flujo de control de la aplicación se establece en tres capas principales:

| Archivo | Rol | Descripción |
| :--- | :--- | :--- |
| **`MainActivity.kt`** | **Punto de Entrada** | Inicializa la aplicación dentro del `DenunciaAppTheme` y simplemente llama al `AppEntryNavigation`, delegando toda la gestión de la UI y la navegación. |
| **`AppEntryNavigation.kt`** | **Contenedor Raíz (Scaffold)** | Crea el `NavController` principal (`rememberNavController`). Contiene el `Scaffold`, que define la estructura base de la UI (incluyendo la `BottomNavigationBar`). Utiliza la ruta actual (`currentRoute`) para decidir cuándo mostrar la barra inferior. |
| **`AppNavHost.kt`** | **Mapeo de Rutas** | Es el corazón de la navegación. Recibe el `NavController` y mapea cada **ruta única** definida en `AppScreen` a su correspondiente función `@Composable` (pantalla). |

#### **Manejo de la Pila de Navegación**

En `AppEntryNavigation.kt`, la navegación de la barra inferior está configurada para preservar el estado de las pantallas y evitar duplicación mediante las siguientes directivas:

```kotlin
// Evita que se creen múltiples instancias de la misma pantalla
popUpTo(AppScreen.Denuncias.route) { 
    saveState = true // Guarda el estado de la pantalla al salir
} 
launchSingleTop = true // Evita duplicar la pantalla en la pila
restoreState = true // Restaura el estado guardado al volver a la pantalla

###  Declaración de Rutas (`AppScreen.kt`)

El objeto `sealed class AppScreen` centraliza todas las rutas posibles en la aplicación.

* **Propósito:** Garantiza que las rutas sean **fuertemente tipadas** y fáciles de referenciar. Al ser `sealed class`, se conoce el conjunto exacto de destinos, mejorando la seguridad del código.
* **Rutas con Argumentos:** Algunas rutas requieren pasar datos. Por ejemplo, para un foro o un detalle de denuncia, la ruta se define con un *placeholder*:

    ```kotlin
    data object Messages : AppScreen("messages_page_screen/{forumId}")
    ```

    `AppNavHost.kt` es el encargado de extraer este argumento (`forumId`) usando `navArgument` y pasarlo a la pantalla `MessagesScreen`.

---

### Conexión de Pantallas (`AppNavHost.kt`)

`AppNavHost.kt` es donde se define la lógica de transición. Cada función `composable()`:

1.  **Declara una Ruta:** `composable(AppScreen.Login.route) { ... }`
2.  **Define la Pantalla:** Llama a la función `@Composable` de la pantalla (ej. `LoginScreen`).
3.  **Gestiona Callbacks:** La conexión entre la **View** y la navegación se realiza a través de *callbacks*. Cuando un evento ocurre en una pantalla (ej. `onLoginSuccess`), la pantalla llama al *callback* que, a su vez, utiliza el `navController` para cambiar de destino (`navController.navigate(...)`).

#### **Ejemplo de Transición y Limpieza de Pila**

Después de un inicio de sesión exitoso, la pila de navegación se limpia para que el usuario no pueda presionar "Atrás" y volver a la pantalla de Login. Esta lógica se implementa dentro del `navController.navigate`:

```kotlin
navController.navigate(AppScreen.Denuncias.route) {
    // Elimina todas las rutas hasta el Login (incluido) de la pila
    popUpTo(AppScreen.Login.route) { inclusive = true } 
}
```

## MainActivity.kt
 ```bash
package mx.edu.utng.oic.denunciaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import mx.edu.utng.oic.denunciaapp.navigation.AppEntryNavigation
import mx.edu.utng.oic.denunciaapp.ui.theme.DenunciaAppTheme // Se asume un tema de aplicación

/**
 * Actividad principal de la aplicación.
 * Utiliza setContent para definir la UI de la aplicación con Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Envuelve toda la aplicación con el tema definido (DenunciaAppTheme)
            DenunciaAppTheme {
                // Una superficie contenedora que utiliza el color de fondo del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // El punto de entrada principal para el flujo de navegación.
                    // Aquí se inicializa NavController, BottomBar y AppNavHost.
                    AppEntryNavigation()
                }
            }
        }
    }
}
```
### Punto de Entrada: `MainActivity.kt`

`MainActivity.kt` es la única **Activity** de la aplicación y sirve como el **punto de partida** para la interfaz de usuario construida con Jetpack Compose. Su función es mínima, ya que delega toda la responsabilidad de la UI y la navegación a los componentes Composable.

#### **Funcionamiento**

1.  **Herencia:** Extiende `ComponentActivity`, la clase base ligera necesaria para utilizar Compose.
2.  **`onCreate` y `setContent`:** Dentro del método `onCreate`, se utiliza la función `setContent` de Compose. Esto le indica a Android que en lugar de cargar un *layout* XML tradicional, debe usar la jerarquía de funciones Composable definidas a continuación.
3.  **Aplicación del Tema:** La función `DenunciaAppTheme` envuelve todo el contenido. Esto asegura que los colores, la tipografía y las formas definidas en el tema de Material Design se apliquen consistentemente a toda la aplicación.
4.  **Contenedor Principal (`Surface`):** El `Surface` es un contenedor que utiliza el color de fondo del tema (`MaterialTheme.colorScheme.background`) y se expande para llenar toda la pantalla (`Modifier.fillMaxSize()`).
5.  **Delegación a la Navegación:** El paso final y más importante es la llamada a `AppEntryNavigation()`.
    * `MainActivity` **no sabe** qué pantallas existen ni cómo navegar.
    * Simplemente entrega el control al componente `AppEntryNavigation`, que es donde se inicializan el `NavController`, la `BottomNavigationBar` y el `AppNavHost` (el sistema completo de navegación).



De esta forma, la **Activity** actúa como un *host* simple, mientras que la complejidad del estado y la UI se gestiona enteramente dentro de la arquitectura de Jetpack Compose y MVVM.

## Integración de Servicios Externos

La aplicación `DenunciaApp` depende de servicios externos para la autenticación, el almacenamiento de datos y la funcionalidad de geolocalización.

### 1Gestión de Credenciales y API Keys

Para asegurar el acceso a servicios de pago o confidenciales, las credenciales no deben estar en archivos de código fuente.

| Servicio | Credencial | Implementación |
| :--- | :--- | :--- |
| **Firebase** | `google-services.json` | Este archivo, ubicado en el directorio raíz del módulo `app`, contiene todas las configuraciones necesarias para conectar la aplicación con el proyecto de Firebase (IDs de cliente, claves, etc.). Es gestionado por el **plugin de Google Services** de Gradle. |
| **Google Maps** | **API Key** | La clave específica para los servicios de Maps debe declararse dentro del `AndroidManifest.xml` de la aplicación, referenciando la clave generada en Google Cloud Console. |

#### **Implementación de la API Key en `AndroidManifest.xml`**

La clave de la API de Google Maps se incrusta en el archivo del manifiesto de la aplicación bajo la etiqueta `<application>`:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="AQUI_VA_TU_API_KEY_DE_MAPS" />
```

### 13.Conexión con Firebase

Firebase es el *backend* sin servidor (BaaS) utilizado para gestionar la mayoría de las operaciones de datos:

* **Autenticación:** Se utiliza el servicio **Firebase Authentication** para manejar el registro, inicio de sesión y gestión de usuarios (ej. `LoginScreen.kt` y `RegisterScreen.kt`).
* **Base de Datos:** Se utiliza **Cloud Firestore** para el almacenamiento de datos no relacionales, como las denuncias, los perfiles de usuario y los mensajes de los foros.
* **Almacenamiento de Archivos:** Se utiliza **Firebase Storage** para guardar archivos binarios grandes, como las **imágenes fotográficas** adjuntas a las denuncias.
* **Implementación:** La capa de **Service** (ej. `UserService`, `DenunciaService`) es la única parte de la arquitectura que interactúa directamente con los SDK de Firebase, manteniendo la integridad del patrón MVVM.

---

### 14. Geolocalización con Google Maps API

La integración de mapas y ubicación se divide en dos áreas clave:

1.  **Obtención de Ubicación:** Se utiliza la API de **Google Location Services (FusedLocationProviderClient)**, la cual se gestiona dentro de la capa de la **View** (`DenunciaViolencia.kt`) para solicitar los permisos necesarios y obtener la ubicación actual del usuario (latitud y longitud).
2.  **Visualización y Selección del Mapa:**
    * Se utiliza la librería **Maps Compose** (parte del SDK de Google Maps para Android).
    * El componente `MapSelectionDialog` utiliza las coordenadas obtenidas para mostrar un mapa centrado en el lugar del incidente y permitir al usuario refinar la ubicación precisa.

Esto asegura que la información geográfica esencial para las denuncias sea precisa y fácil de introducir por el usuario.
