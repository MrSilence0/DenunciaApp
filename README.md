# DenunciaApp - MX.EDU.UTNG.OIC.DENUNCIAAPP
## Descripción del Proyecto

**DenunciaApp** es una aplicación móvil desarrollada para facilitar y agilizar el proceso de reporte de diversos tipos de incidentes y denuncias por parte de los ciudadanos a las autoridades competentes.

La aplicación utiliza la estructura de directorios y componentes de **Jetpack Compose** para ofrecer una interfaz de usuario moderna y una navegación fluida, permitiendo a los usuarios reportar desde extravíos y robos (RoboCasa, RoboVehiculo, etc.) hasta situaciones de violencia y extorsión.

#**Imagenes**
## Icono
![Icono principal de la app](app/src/main/res/drawable/denunciaappicon.png)

## Login
![Login](app/src/main/res/drawable/image1.jpg)

## Denuncias
![Denuncias](app/src/main/res/drawable/image2.jpg)

## Home Page
![Home page](app/src/main/res/drawable/image3.jpg)
## Foros 

![Foros page](app/src/main/res/drawable/image4.jpg)

## Chat
![Chat](app/src/main/res/drawable/image5.jpg)

## Creación de foros
![Create foro](app/src/main/res/drawable/image6.jpg)

## Agenda
![Agenda](app/src/main/res/drawable/image7.jpg)

## Perfil
![User profile](app/src/main/res/drawable/image8.jpg)

## Promo
![Promocional](app/src/main/res/drawable/promocional.png)

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

## 15. AndroidManifest.xml
```bash
    <?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- === PERMISOS REQUERIDOS PARA GOOGLE MAPS Y UBICACIÓN === -->

    <!-- Permisos para acceder a la ubicación del usuario (necesario para FusedLocationProvider) -->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <!-- Este elemento es obligatorio para Google Maps API v2 -->
    <uses-feature
        android:glEsVersion="0x00020000"
        android:required="true" />

    <!-- ========================================================= -->

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.DenunciaApp">
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="${MAPS_API_KEY}" />
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.DenunciaApp">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

## 16.CommonComponents.kt
```bash
    package mx.edu.utng.oic.denunciaapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
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

val WireframeGray = Color(0xFF9E9E9E)


@Composable
fun LabelText(text: String, color: Color) {
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
```

## 17. LocationAndMapUtils.kt
```bash
    package mx.edu.utng.oic.denunciaapp.ui.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import mx.edu.utng.oic.denunciaapp.ui.screens.DefaultLocation
import java.io.IOException
import java.util.Locale

val YellowButton = Color(0xFFFFC107)
val RedCancelButton = Color(0xFFD32F2F)

// --- Componente de Diálogo con el Mapa (Compartido) ---
@Composable
fun MapSelectionDialog(
    cameraPosition: CameraPosition,
    markerLocation: LatLng?,
    onDismiss: () -> Unit,
    onLocationConfirmed: (LatLng) -> Unit,
    onCameraMove: (CameraPosition) -> Unit
) {
    var confirmedLocation by remember { mutableStateOf(markerLocation ?: DefaultLocation) }

    // Controlador de la Cámara del Mapa
    val cameraPositionState = rememberCameraPositionState {
        position = cameraPosition
    }

    // Sincronizar el estado externo con el interno del mapa
    LaunchedEffect(cameraPosition) {
        if (cameraPositionState.position.target != cameraPosition.target ||
            cameraPositionState.position.zoom != cameraPosition.zoom) {
            cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    // Usar LaunchedEffect con snapshotFlow para detectar cuando el mapa deja de moverse
    LaunchedEffect(cameraPositionState) {
        snapshotFlow { cameraPositionState.isMoving }
            .collect { isMoving ->
                if (!isMoving) {
                    // Cuando el mapa está IDLE, actualizamos la ubicación confirmada
                    val newTarget = cameraPositionState.position.target
                    confirmedLocation = newTarget
                    onCameraMove(cameraPositionState.position)
                }
            }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Selecciona la Ubicación del Hecho") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        onMapClick = { latLng ->
                            confirmedLocation = latLng
                        },
                        onMapLoaded = { /* Mapa cargado */ }
                    ) {
                        if (markerLocation != null) {
                            Marker(
                                state = rememberMarkerState(position = confirmedLocation),
                                title = "Ubicación del Hecho",
                                snippet = "Arrastra el mapa para mover el pin"
                            )
                        }
                    }

                    // Pin central para indicar la ubicación actual al arrastrar el mapa
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Pin de ubicación central",
                        tint = RedCancelButton,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                            .offset(y = (-24).dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Lat: ${String.format("%.4f", confirmedLocation.latitude)}, Lon: ${String.format("%.4f", confirmedLocation.longitude)}",
                    fontWeight = FontWeight.Bold
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onLocationConfirmed(confirmedLocation) }) {
                Text("Confirmar", color = YellowButton)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = RedCancelButton)
            }
        }
    )
}


// --- Funciones Auxiliares de Ubicación (Compartidas) ---

// 1. Verificar Permisos
fun checkLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

// 2. Obtener la ubicación actual (última conocida o una nueva)
@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationResult: (LatLng) -> Unit
) {
    val locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        numUpdates = 1
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                onLocationResult(LatLng(location.latitude, location.longitude))
            } ?: run {
                onLocationResult(DefaultLocation)
            }
            fusedLocationClient.removeLocationUpdates(this)
        }
    }

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            onLocationResult(LatLng(location.latitude, location.longitude))
        } else {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }
}

// 3. Obtener dirección a partir de LatLng (Geocoding Inverso)
fun getAddressFromLatLng(context: Context, latLng: LatLng): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    return try {
        // Suprimir la advertencia de deprecación para compatibilidad
        @Suppress("DEPRECATION")
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            address.getAddressLine(0) ?: "Lat: ${latLng.latitude}, Lon: ${latLng.longitude}"
        } else {
            "Lat: ${latLng.latitude}, Lon: ${latLng.longitude}"
        }
    } catch (e: IOException) {
        e.printStackTrace()
        "Lat: ${latLng.latitude}, Lon: ${latLng.longitude} (Error al obtener dirección)"
    }
}
```
## 18.AppConfig.kt
```bash
   import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size

// --- COLORES GLOBALES ---
val WireframeGray = Color(0xFF9E9E9E)
val PrimaryColor = Color(0xFF0D47A1) // Azul principal

// --- MODELOS DE DATOS GLOBALES ---
data class Post(
    val id: Int,
    val username: String,
    val text: String,
    val rating: Int, // Valoración de 1 a 5
    val timeAgo: String
)

// --- COMPONENTES GLOBALES ---

// Mockup para el componente RatingStars (Necesario para Posts.kt y Messages.kt)
@Composable
fun RatingStars(rating: Int) {
    val StarColor = Color(0xFFFFCC00)
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = if (i <= rating) "Estrella rellena" else "Estrella vacía",
                tint = StarColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
```

#   19. Screens
```bash
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray

// Modelo de datos para las agencias
data class Agencia(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val horario: String,
    val latLng: LatLng
)

// Datos
private val agenciesData = listOf(
    Agencia(
        id = 1,
        nombre = "MP Dolores Hidalgo",
        direccion = "Av. De los Heroes 8, Viñedo, 37804 Dolores Hidalgo...",
        horario = "24 horas",
        latLng = LatLng(21.18597861019045, -100.91918814562699)
    ),
    Agencia(
        id = 2,
        nombre = "Fiscalía San Miguel de Allende",
        direccion = "Blvrd de la Conspiración, La Luz, 37746 San Miguel de Allende, Gto.",
        horario = "09:00 - 18:00",
        latLng = LatLng(20.9149, -100.7583)
    ),
    Agencia(
        id = 3,
        nombre = "Módulo de Atención León",
        direccion = "Blvd. Hermanos Aldama 4321, Ciudad Industrial, 37490 León...",
        horario = "08:00 - 20:00",
        latLng = LatLng(21.0991, -101.5997)
    ),
    Agencia(
        id = 4,
        nombre = "Agencia Silao",
        direccion = "C. 5 de Mayo 13-15, Centro, 36100 Silao de la Victoria, Gto.",
        horario = "24 horas",
        latLng = LatLng(20.9427, -101.4255)
    ),
    Agencia(
        id = 5,
        nombre = "Oficina San Felipe",
        direccion = "San Felipe - Ocampo Sn, 37600 San Felipe, Gto.",
        horario = "09:00 - 17:00",
        latLng = LatLng(21.4883, -101.2185)
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgenciasScreen(
    onNavigateBack: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline
    val surfaceVariantColor = colorScheme.surfaceVariant // Para fondo sutil
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant // Para texto sutil

    val defaultLocation = LatLng(21.0188, -101.2587)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 8f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Agencias cercanas", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor,
                    navigationIconContentColor = onSurfaceColor
                )
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(backgroundColor)
        ) {
            // --- Mapa Interactivo de Google Maps ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(surfaceVariantColor)
                    .border(1.dp, outlineColor, RoundedCornerShape(8.dp))
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = true,
                        mapToolbarEnabled = false
                    )
                ) {
                    agenciesData.forEach { agencia ->
                        Marker(
                            state = MarkerState(position = agencia.latLng),
                            title = agencia.nombre,
                            snippet = agencia.direccion,
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Lista de agencias",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                // CORRECCIÓN 8: Usar onSurfaceColor
                color = onSurfaceColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Lista de Agencias ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(agenciesData) { agencia ->
                    AgencyItem(
                        agencia = agencia,
                        onSurfaceColor = onSurfaceColor,
                        surfaceColor = surfaceColor,
                        outlineColor = outlineColor,
                        primaryColor = primaryColor, // Mapeo de AgencyIconBlue
                        onSurfaceVariantColor = onSurfaceVariantColor
                    )
                }
            }
        }
    }
}

@Composable
fun AgencyItem(
    agencia: Agencia,
    onSurfaceColor: Color,
    surfaceColor: Color,
    outlineColor: Color,
    primaryColor: Color,
    onSurfaceVariantColor: Color
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, outlineColor),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo (Icono)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(primaryColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Datos de la agencia
            Column {
                Text(
                    text = agencia.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = onSurfaceColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                val shortAddress = agencia.direccion.split(",").take(2).joinToString(", ")
                Text(
                    text = shortAddress,
                    fontSize = 14.sp,
                    color = onSurfaceVariantColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Horario: ${agencia.horario}",
                    fontSize = 12.sp,
                    color = onSurfaceVariantColor
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgenciasScreenPreview() {
    MaterialTheme {
        AgenciasScreen(onNavigateBack = {})
    }
}
 ```


```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateForumScreen(
    onNavigateBack: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline // Mapeo de Color.LightGray
    val secondaryContainerColor = colorScheme.secondaryContainer // Mapeo de Color(0xFFE3F2FD)
    val onSecondaryContainerColor = colorScheme.onSecondaryContainer
    val errorColor = colorScheme.error


    val foroService = remember { ForoService() }
    val userService = remember { UserService() }
    val factory = remember { ForoViewModelFactory(foroService, userService) }
    val viewModel: ForoViewModel = viewModel(factory = factory)

    val isProcessing by viewModel.isProcessing.collectAsState()
    val operationError by viewModel.operationError.collectAsState()
    val operationSuccess by viewModel.operationSuccess.collectAsState()

    var tema by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(operationError) {
        operationError?.let { errorMsg ->
            scope.launch {
                snackbarHostState.showSnackbar(errorMsg, duration = SnackbarDuration.Short)
            }
        }
    }

    LaunchedEffect(operationSuccess) {
        if (operationSuccess) {
            scope.launch {
                snackbarHostState.showSnackbar("Tema creado con éxito!", duration = SnackbarDuration.Short)
                onNavigateBack()
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text("Nuevo Tema de Foro", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver a Foros", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor,
                    navigationIconContentColor = onSurfaceColor
                )
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Tema del Foro",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = onSurfaceColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = tema,
                onValueChange = { tema = it },
                label = {
                    Text("Escribe aquí el título de tu tema...")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 200.dp),
                singleLine = false,
                maxLines = 5,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor,
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = onSurfaceColor.copy(alpha = 0.6f)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(secondaryContainerColor, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                InfoRow("Nota:", "El creador y fecha se registrarán automáticamente al guardar.",
                    onSecondaryContainerColor = onSecondaryContainerColor,
                    onSurfaceColor = onSurfaceColor
                )
                InfoRow("Fecha de Guardado:", SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()),
                    onSecondaryContainerColor = onSecondaryContainerColor,
                    onSurfaceColor = onSurfaceColor
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    viewModel.saveForo(
                        foroId = null,
                        tema = tema
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isProcessing) colorScheme.surfaceVariant else primaryColor,
                    contentColor = if (isProcessing) onSurfaceColor else onPrimaryColor
                ),
                enabled = tema.isNotBlank() && !isProcessing
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(color = if (isProcessing) onSurfaceColor else onPrimaryColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Creando...", color = if (isProcessing) onSurfaceColor else onPrimaryColor)
                } else {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(20.dp), tint = onPrimaryColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Crear Foro", fontSize = 16.sp, color = onPrimaryColor)
                }
            }
        }
    }
}
@Composable
fun InfoRow(
    label: String,
    value: String,
    onSecondaryContainerColor: Color,
    onSurfaceColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = onSecondaryContainerColor.copy(alpha = 0.9f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            color = onSecondaryContainerColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateForumScreenPreview() {
    MaterialTheme {
        CreateForumScreen(
            onNavigateBack = {}
        )
    }
}
 ```


```bash
   package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText // Se añade LabelText que se usa
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaFotograficaViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory

val DefaultLocation = LatLng(19.4326, -99.1332)
const val DefaultZoom = 15f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenunciaFotograficaScreen(
    onCancel: () -> Unit,
    onSuccess: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val tertiaryColor = colorScheme.tertiary
    val onTertiaryColor = colorScheme.onTertiary
    val errorColor = colorScheme.error
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline
    val surfaceVariantColor = colorScheme.surfaceVariant
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val snackbarHostState = remember { SnackbarHostState() }

    val viewModel: DenunciaFotograficaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createDenunciaFotograficaViewModelFactory()
    )

    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    var showMapDialog by remember { mutableStateOf(false) }
    var currentCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, DefaultZoom)) }
    var selectedMarkerLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoadingLocation by remember { mutableStateOf(false) }

    var hasLocationPermission by remember {
        mutableStateOf(checkLocationPermission(context))
    }

    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val isSaving by viewModel.isSaving.collectAsState()

    // Efecto para manejar el éxito y el error
    LaunchedEffect(saveSuccess, saveError) {
        if (saveSuccess) {
            onSuccess()
            viewModel.resetStates()
        }

        saveError?.let { errorMsg ->
            snackbarHostState.showSnackbar(
                message = errorMsg,
                actionLabel = "OK"
            )
            viewModel.resetStates()
        }
    }


    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (hasLocationPermission) {
                isLoadingLocation = true
                getCurrentLocation(context, fusedLocationClient) { latLng ->
                    currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                    selectedMarkerLocation = latLng
                    location = getAddressFromLatLng(context, latLng)
                    isLoadingLocation = false
                }
            } else {
                location = "Permiso de ubicación denegado."
                isLoadingLocation = false
            }
        }
    )

    // Función para solicitar permisos e intentar obtener ubicación
    fun requestLocationAndUpdate() {
        if (hasLocationPermission) {
            isLoadingLocation = true
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                selectedMarkerLocation = latLng
                location = getAddressFromLatLng(context, latLng)
                isLoadingLocation = false
            }
        } else {
            locationPermissionLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Denuncia Fotográfica", fontWeight = FontWeight.Bold, color = onSurfaceColor)
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
                            // CORRECCIÓN 8: Usar onSurfaceVariant para iconos sutiles
                            tint = onSurfaceVariantColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // CORRECCIÓN 9: Usar onSurfaceVariant para texto sutil
                        Text("Imagen a subir", color = onSurfaceVariantColor)
                    }
                } else {
                    // CORRECCIÓN 10: Usar onSurfaceColor para texto principal
                    Text("Imagen seleccionada", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedImageUri = "simulated_camera_uri_${System.currentTimeMillis()}" },
                    // CORRECCIÓN 11: Usar surfaceVariant para botones de media/secundarios
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { selectedImageUri = "simulated_gallery_uri_${System.currentTimeMillis()}" },
                    // CORRECCIÓN 12: Usar surfaceVariant para botones de media/secundarios
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

            LabelText("Describe el hecho", color = onSurfaceColor)
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Escribe aquí los detalles del hecho", color = onSurfaceVariantColor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            LabelText("Ubicación del hecho", color = onSurfaceColor)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    placeholder = { Text("Ubicación actual o seleccionada", color = onSurfaceVariantColor) },
                    modifier = Modifier
                        .weight(1f),
                    readOnly = false,
                    trailingIcon = {
                        IconButton(onClick = {
                            requestLocationAndUpdate()
                        }, enabled = !isLoadingLocation) {
                            if (isLoadingLocation) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp,
                                    color = primaryColor
                                )
                            } else {
                                Icon(Icons.Default.LocationOn, contentDescription = "Obtener GPS", tint = primaryColor)
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

                Spacer(modifier = Modifier.width(8.dp))

                // Botón para abrir el Mapa en un diálogo
                Button(
                    onClick = {
                        showMapDialog = true
                        if (selectedMarkerLocation == null) {
                            requestLocationAndUpdate()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Mapa", tint = onSurfaceColor)
                }
            }

            if (!hasLocationPermission && location.contains("denegado")) {
                Text(
                    "Pulsa el botón de GPS para solicitar permisos de ubicación.",
                    color = errorColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de la ubicación de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        description = description,
                        locationAddress = location,
                        latitud = selectedMarkerLocation?.latitude,
                        longitud = selectedMarkerLocation?.longitude,
                        imageUri = selectedImageUri
                    )
                },
                enabled = !isSaving && description.isNotBlank() && location.isNotBlank(),
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
                        color = onTertiaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo para mostrar el Mapa interactivo ---
    if (showMapDialog) {
        MapSelectionDialog(
            cameraPosition = currentCameraPosition,
            markerLocation = selectedMarkerLocation,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = { latLng ->
                selectedMarkerLocation = latLng
                location = getAddressFromLatLng(context, latLng)
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                showMapDialog = false
            },
            onCameraMove = { cameraPos ->
                currentCameraPosition = cameraPos
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciaFotograficaPreview() {
    MaterialTheme {
        DenunciaFotograficaScreen(
            onCancel = {},
            onSuccess = {}
        )
    }
}
 ```


```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image // Nuevo import
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource // Nuevo import
import mx.edu.utng.oic.denunciaapp.R // Nuevo import
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen
@Composable
fun DenunciasScreen(
    onNavigateToMisDenuncias: () -> Unit,
    onOpenMenu: () -> Unit,
    onNavigateToAgencias: () -> Unit,
    onNavigateToPosts: () -> Unit,
    onNavigateToDenunciaFotografica: () -> Unit,
    onNavigateToPersonaDesaparecida: () -> Unit,
    onNavigateToRoboVehiculo: () -> Unit,
    onNavigateToRoboCasa: () -> Unit,
    onNavigateToRoboObjeto: () -> Unit,
    onNavigateToExtorsion: () -> Unit,
    onNavigateToDenunciaViolencia: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant


    Scaffold(
        topBar = {
            // Barra superior personalizada con botón de menú y botón "Mis Denuncias"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surfaceColor)
                    .padding(16.dp)
            ) {
                // 1. Botón para navegar a MenuScreen
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Abrir menú", modifier = Modifier.size(32.dp), tint = onSurfaceColor)
                }

                // 2. Logo Central
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(50.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = onSurfaceVariantColor)
                }

                Button(
                    onClick = onNavigateToMisDenuncias,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .height(36.dp)
                ) {
                    Text("Mis Denuncias", fontSize = 12.sp, color = onPrimaryColor)
                }
            }
        },
        containerColor = backgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Text(
                text = "Denuncias",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = onSurfaceColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Grid de opciones
            val gridItems = listOf(
                GridOption("Denuncia\nFotográfica", Icons.Default.CameraAlt) { onNavigateToDenunciaFotografica() },
                GridOption("Persona\nDesaparecida", Icons.Default.Person) { onNavigateToPersonaDesaparecida() },
                GridOption("Robo de\nVehículo", Icons.Default.DirectionsCar) { onNavigateToRoboVehiculo() },
                GridOption("Extorsión", Icons.Default.PhoneAndroid) { onNavigateToExtorsion() },
                GridOption("Robo a\nCasa Habitación", Icons.Default.Home) { onNavigateToRoboCasa() },
                GridOption("Robo de\nObjetos", Icons.Default.Visibility) { onNavigateToRoboObjeto() },
                GridOption("Violencia de\nGénero", Icons.Default.Female) { onNavigateToDenunciaViolencia() },
                GridOption("Agencias de\nDenuncia", Icons.Default.Place) { onNavigateToAgencias() },
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(gridItems) { item ->
                    DenunciaItem(
                        title = item.title,
                        icon = item.icon,
                        onClick = item.onClick,
                        onSurfaceColor = onSurfaceColor,
                        surfaceColor = surfaceColor,
                        primaryColor = primaryColor // Usaremos primary para el icono
                    )
                }
            }
        }
    }
}

data class GridOption(val title: String, val icon: ImageVector, val onClick: () -> Unit)

@Composable
fun DenunciaItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    onSurfaceColor: Color,
    surfaceColor: Color,
    primaryColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(surfaceColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(50.dp),
                tint = primaryColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            color = onSurfaceColor
        )
    }
}

@Composable
fun MenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit = {},
    onSurfaceColor: Color,
    primaryColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(28.dp), tint = primaryColor)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, color = onSurfaceColor)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciasScreenPreview() {
    MaterialTheme {
        DenunciasScreen(
            onNavigateToMisDenuncias = {},
            onOpenMenu = {},
            onNavigateToAgencias = {},
            onNavigateToPosts = {},
            onNavigateToDenunciaFotografica = {},
            onNavigateToPersonaDesaparecida = {},
            onNavigateToRoboVehiculo = {},
            onNavigateToRoboCasa = {},
            onNavigateToRoboObjeto = {},
            onNavigateToExtorsion = {},
            onNavigateToDenunciaViolencia = {}
        )
    }
}
 ```


```bash
   package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ExtorsionViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory

import mx.edu.utng.oic.denunciaapp.ui.components.LabelText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtorsionScreen(
    onNavigateBack: () -> Unit,
    viewModel: ExtorsionViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createExtorsionViewModelFactory()
    )
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val errorColor = MaterialTheme.colorScheme.error
    val outlineColor = MaterialTheme.colorScheme.outline
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val backgroundColor = MaterialTheme.colorScheme.background
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor = MaterialTheme.colorScheme.onTertiary

    // --- Observación de Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()

    // --- Estados del formulario local ---
    var numeroTelefonico by remember { mutableStateOf("") }
    var descripcionHechos by remember { mutableStateOf("") }
    var audioAdjuntoUri by remember { mutableStateOf<String?>(null) }
    var isPlaying by remember { mutableStateOf(false) }

    // --- Efecto para Navegación en Éxito ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onNavigateBack()
            viewModel.resetStates()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Reporte de Extorsión", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isSaving
                    ) {
                        Text("Cancelar", color = errorColor, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
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
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Número telefónico" ---
            LabelText("Número telefónico", color = onSurfaceColor) // Usar color de texto dinámico
            OutlinedTextField(
                value = numeroTelefonico,
                onValueChange = { numeroTelefonico = it },
                placeholder = { Text("Número que realizó la llamada", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    disabledBorderColor = outlineColor.copy(alpha = 0.5f),
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Describe los hechos" ---
            LabelText("Describa los hechos", color = onSurfaceColor) // Usar color de texto dinámico
            OutlinedTextField(
                value = descripcionHechos,
                onValueChange = { descripcionHechos = it },
                placeholder = { Text("Relate lo sucedido, qué pedían y cómo lo pidieron", color = onSurfaceVariantColor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    disabledBorderColor = outlineColor.copy(alpha = 0.5f),
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Botón "Adjuntar llamada" (Simulación) ---
            Button(
                onClick = { audioAdjuntoUri = "file://path/to/recording.mp3" },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(8.dp),
                enabled = !isSaving,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    if (audioAdjuntoUri == null) "Adjuntar llamada" else "Audio Adjunto (Cambiar)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = onPrimaryColor
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Controles de reproducción (Simulación) ---
            if (audioAdjuntoUri != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Llamada extorsión.mp3", fontSize = 14.sp, color = onSurfaceVariantColor)
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Iconos pequeños
                    IconButton(onClick = { /* Anterior */ }, enabled = !isSaving) {
                        Icon(Icons.Default.SkipPrevious, contentDescription = "Pista anterior", tint = onSurfaceVariantColor)
                    }
                    IconButton(onClick = { /* Rewind */ }, enabled = !isSaving) {
                        Icon(Icons.Default.FastRewind, contentDescription = "Rebobinar", tint = onSurfaceVariantColor)
                    }

                    // Botón Play/Pause central
                    Button(
                        onClick = { isPlaying = !isPlaying },
                        modifier = Modifier.size(56.dp),
                        enabled = !isSaving,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                            tint = onPrimaryColor,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    IconButton(onClick = { /* Fast Forward */ }, enabled = !isSaving) {
                        // CORRECCIÓN 15: Usar color de texto sutil
                        Icon(Icons.Default.FastForward, contentDescription = "Adelantar", tint = onSurfaceVariantColor)
                    }
                    IconButton(onClick = { /* Siguiente */ }, enabled = !isSaving) {
                        // CORRECCIÓN 16: Usar color de texto sutil
                        Icon(Icons.Default.SkipNext, contentDescription = "Pista siguiente", tint = onSurfaceVariantColor)
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(50.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de la llamada de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = { viewModel.submitDenuncia(numeroTelefonico, descripcionHechos) },
                enabled = !isSaving && numeroTelefonico.isNotBlank() && descripcionHechos.isNotBlank(),
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
                        color = onTertiaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo de Error ---
    saveError?.let { message ->
        AlertDialog(
            onDismissRequest = { viewModel.resetStates() },
            title = { Text("Error al Enviar Reporte", color = onSurfaceColor) },
            text = { Text(message, color = onSurfaceColor) },
            confirmButton = {
                Button(onClick = { viewModel.resetStates() }) {
                    Text("Aceptar")
                }
            },
            containerColor = surfaceColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExtorsionPreview() {
    MaterialTheme {
        ExtorsionScreen(onNavigateBack = {})
    }
}
 ```


```bash
   package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant

    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Recuperar Contraseña", color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor,
                    navigationIconContentColor = onSurfaceColor
                )
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Ingresa tu correo electrónico para enviarte un enlace de recuperación.",
                color = onSurfaceVariantColor,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Campo Correo
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                placeholder = { Text("ejemplo@correo.com") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = onSurfaceVariantColor.copy(alpha = 0.5f),
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = onSurfaceColor.copy(alpha = 0.6f),
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botón Enviar
            Button(
                onClick = {
                    // Lógica de envío de correo (placeholder)
                    onNavigateBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
            ) {
                Text("Enviar Enlace", fontWeight = FontWeight.Bold, color = onPrimaryColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    MaterialTheme {
        ForgotPasswordScreen(onNavigateBack = {})
    }
}
 ```


```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.R
import mx.edu.utng.oic.denunciaapp.data.model.Foro as ForoModel
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray // Mantener si WireframeGray es necesario para placeholders
import mx.edu.utng.oic.denunciaapp.ui.theme.White
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForosPageScreen(
    onNavigateToCreateForum: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onOpenMenu: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor = MaterialTheme.colorScheme.onTertiary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val surfaceColor = MaterialTheme.colorScheme.surface
    val backgroundColor = MaterialTheme.colorScheme.background

    val foroService = remember { ForoService() }
    val userService = remember { UserService() }
    val factory = remember { ForoViewModelFactory(foroService, userService) }
    val viewModel: ForoViewModel = viewModel(factory = factory)

    val forosList by viewModel.foros.collectAsState()
    val currentUserId by viewModel.currentUserId.collectAsState()
    val isLoading by viewModel.isLoadingForos.collectAsState()
    val operationError by viewModel.operationError.collectAsState()
    val isDeleting by viewModel.isProcessing.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // 2. Manejar mensajes de error
    LaunchedEffect(operationError) {
        operationError?.let { errorMsg ->
            scope.launch {
                snackbarHostState.showSnackbar(errorMsg, duration = SnackbarDuration.Long)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadForos()
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surfaceColor)
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Abrir menú",
                        modifier = Modifier.size(32.dp),
                        tint = onSurfaceColor
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(50.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = White, fontWeight = FontWeight.Bold)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateForum,
                containerColor = tertiaryColor,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nuevo tema",
                    tint = onTertiaryColor
                )
            }
        },
        containerColor = backgroundColor
    ) { paddingValues ->

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = primaryColor)
            }
            return@Scaffold
        }

        if (forosList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No hay foros disponibles. ¡Sé el primero en crear uno!", color = onSurfaceColor)
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(forosList, key = { it.id }) { foro ->
                ForumItem(
                    foro = foro,
                    currentUserId = currentUserId,
                    onDelete = {
                        if (!isDeleting) {
                            viewModel.deleteForo(foro.id)
                        }
                    },
                    onClick = {
                        onNavigateTo("messages_page_screen/${foro.id}")
                    },
                    primaryColor = primaryColor,
                    onSurfaceColor = onSurfaceColor,
                    onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant,
                    outlineColor = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun ForumItem(
    foro: ForoModel,
    currentUserId: String?,
    onDelete: (String) -> Unit,
    onClick: () -> Unit,
    primaryColor: Color,
    onSurfaceColor: Color,
    onSurfaceVariantColor: Color,
    surfaceVariantColor: Color,
    outlineColor: Color
) {
    val isOwner = foro.idUser == currentUserId

    val deleteIconColor = Color(0xFFD32F2F)
    val deleteContainerColor = Color(0xFFF44336).copy(alpha = 0.1f)

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceVariantColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(primaryColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Forum,
                        contentDescription = "Icono de foro",
                        tint = primaryColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Título y Creador
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = foro.tema,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = onSurfaceColor,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // Creador
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Autor",
                            tint = onSurfaceVariantColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Autor: ${foro.username}",
                            fontSize = 12.sp,
                            color = onSurfaceVariantColor
                        )
                    }
                }

                // Fecha de creación
                val dateFormatter = remember { SimpleDateFormat("dd/MM/yy", Locale.getDefault()) }
                Text(
                    text = dateFormatter.format(foro.creationDate),
                    fontSize = 12.sp,
                    color = onSurfaceVariantColor,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = outlineColor)
            Spacer(modifier = Modifier.height(12.dp))

            // --- 2. Pie de la tarjeta: Respuestas y Botón Responder/Borrar ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Contador de Respuestas
                Text(
                    text = "${foro.responseCount} respuestas",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = primaryColor
                )

                // Botones de Acción
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // BOTÓN DE BORRADO CONDICIONAL
                    if (isOwner) {
                        IconButton(
                            onClick = { onDelete(foro.id) },
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                // Se mantienen los colores fijos para el botón de ELIMINAR (rojo)
                                .background(deleteContainerColor)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar Foro",
                                tint = deleteIconColor
                            )
                        }
                    }

                    // Botón Responder (Navega al detalle)
                    OutlinedButton(
                        onClick = onClick,
                        shape = RoundedCornerShape(8.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(primaryColor)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Responder",
                            modifier = Modifier.size(18.dp).padding(end = 4.dp),
                            tint = primaryColor
                        )
                        Text(
                            "Responder",
                            color = primaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForosPageScreenPreview() {
    MaterialTheme {
        ForosPageScreen(
            onNavigateToCreateForum = {},
            onNavigateTo = {},
            onOpenMenu = {}
        )
    }
}
 ```


```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.oic.denunciaapp.R
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.LoginViewModel
val WireframeBlue = Color(0xFF64B5F6)

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: (User) -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val authenticatedUser = viewModel.authenticatedUser

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(authenticatedUser) {
        authenticatedUser?.let { user ->
            onLoginSuccess(user)
        }
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Cerrar",
                duration = SnackbarDuration.Short
            )
            viewModel.clearError()
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // --- Logo y Nombre de la App ---
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 32.dp, top = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.denunciaappicon),
                            contentDescription = "DenunciaApp Icon",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "DenunciaApp",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // --- Encabezado ---
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textAlign = TextAlign.Start
                    )

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Formulario ---

                    // Campo Correo
                    LabelText("Correo electrónico:")
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                "Introduce tu correo",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        enabled = !isLoading
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo Contraseña
                    LabelText("Contraseña:")
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                "********",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        enabled = !isLoading
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // --- Botón Ingresa ---
                    Button(
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                viewModel.errorMessage = "El correo electrónico y la contraseña son obligatorios."
                            } else {
                                viewModel.login(email, password)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = WireframeBlue),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.width(160.dp),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                        } else {
                            Text(
                                text = "Ingresa",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // --- Olvidaste tu contraseña ---
                    TextButton(onClick = onForgotPasswordClick, enabled = !isLoading) {
                        Text(
                            text = "¿Olvidaste tu contraseña?",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Mantenemos el divisor dinámico
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Sección Registro ---
                    Text(
                        text = "¿No tienes cuenta?",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onRegisterClick,
                        colors = ButtonDefaults.buttonColors(containerColor = WireframeBlue),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.width(160.dp),
                        enabled = !isLoading
                    ) {
                        Text(
                            text = "Registrate",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    )
}

// Componente auxiliar para las etiquetas de texto alineadas a la izquierda
@Composable
fun LabelText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginSuccess = { _ -> },
        onRegisterClick = {},
        onForgotPasswordClick = {}
    )
}
 ```


```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource // Importar painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.oic.denunciaapp.R // Importar la clase R para recursos
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MenuViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MenuViewModelFactory
private const val ROUTE_PROFILE = "profile_screen"
private const val ROUTE_EMERGENCY = "emergency_contact_screen"
private const val ROUTE_TERMS = "terms_and_conditions_screen"
private const val ROUTE_LOGOUT = "logout_action"


// Modelo de datos auxiliar
data class DrawerItem(val icon: ImageVector, val label: String, val route: String)

/**
 * COMPONENTE PRINCIPAL DE PANTALLA: MenuScreen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToEmergency: () -> Unit,
    onNavigateToTerms: () -> Unit,
    onLogOut: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val userService = remember { UserService() }
    val factory = remember { MenuViewModelFactory(userService) }
    val viewModel: MenuViewModel = viewModel(factory = factory)

    val userName by viewModel.userName.collectAsState()

    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Menú Principal", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor,
                    navigationIconContentColor = onSurfaceColor
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        MenuLateral(
            modifier = Modifier.padding(paddingValues),
            userName = userName,
            currentRoute = null,
            onNavigate = { route ->
                when (route) {
                    ROUTE_PROFILE -> onNavigateToProfile()
                    ROUTE_EMERGENCY -> onNavigateToEmergency()
                    ROUTE_TERMS -> onNavigateToTerms()
                    ROUTE_LOGOUT -> onLogOut()
                }
            }
        )
    }
}

// --- COMPONENTE DEL MENÚ LATERAL (Corregido) ---
@Composable
fun MenuLateral(
    modifier: Modifier = Modifier,
    userName: String,
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val background = MaterialTheme.colorScheme.background
    val dividerColor = MaterialTheme.colorScheme.outline
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant


    // Definición de las opciones de navegación
    val items = listOf(
        DrawerItem(Icons.Default.Person, "Mi Perfil", ROUTE_PROFILE),
        DrawerItem(Icons.Default.Group, "Contactos de emergencia", ROUTE_EMERGENCY),
        DrawerItem(Icons.Default.Description, "Términos y Condiciones", ROUTE_TERMS),
        DrawerItem(Icons.Default.ExitToApp, "Cerrar Sesión", ROUTE_LOGOUT)
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Icono de la App
            Image(
                painter = painterResource(id = R.drawable.denunciaappicon),
                contentDescription = "Logo DenunciaApp",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre de la App
            Text(
                text = "DenunciaApp",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del Usuario
            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = onSurfaceColor
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Mensaje de Bienvenida
            Text(
                text = "¡Bienvenido/a!",
                fontSize = 14.sp,
                color = onSurfaceVariantColor
            )
        }

        Divider(color = dividerColor.copy(alpha = 0.5f), thickness = 1.dp)
        Spacer(Modifier.height(8.dp))

        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationDrawerItem(
                label = { Text(item.label, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        tint = if (isSelected) primaryColor else onSurfaceColor
                    )
                },
                selected = isSelected,
                onClick = {
                    onNavigate(item.route)
                },
                modifier = Modifier.padding(vertical = 4.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = primaryColor.copy(alpha = 0.1f),
                    selectedIconColor = primaryColor,
                    selectedTextColor = primaryColor,
                    unselectedTextColor = onSurfaceColor // Texto de ítem no seleccionado
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    MaterialTheme {
        MenuLateral(
            userName = "Carlos Hernández",
            currentRoute = ROUTE_PROFILE,
            onNavigate = {}
        )
    }
}
 ```


```bash
   package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MisDenunciasViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MisDenunciasViewModelFactory
import mx.edu.utng.oic.denunciaapp.data.model.*
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
import androidx.compose.ui.text.style.TextOverflow

data class UIDenuncia(
    val id: String,
    val tipo: String,
    val estatus: String,
    val fecha: String,
    val hora: String,
    val detallesAdicionales: List<Pair<String, String>> // Lista de pares (Etiqueta, Valor)
)

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
        estatus = "En Revisión",
        fecha = dateFormat.format(this.creationDate),
        hora = timeFormat.format(this.creationDate),
        detallesAdicionales = detalles
    )
}
@Composable
fun DenunciaItem(denuncia: UIDenuncia) {
    // --- Colores Dinámicos del Tema ---
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant

    // Colores fijos para estados específicos
    val resolvedColor = Color(0xFF4CAF50)
    val reviewingColor = Color(0xFFFF9800)
    val rejectedColor = Color(0xFFF44336)
    val iconColor = Color(0xFF0086FF)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
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
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(6.dp))

                    Text(
                        text = denuncia.tipo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = onSurfaceColor
                    )
                }

                val statusColor = when (denuncia.estatus) {
                    "Resuelta" -> resolvedColor
                    "En Revisión" -> reviewingColor
                    "Rechazada" -> rejectedColor
                    else -> onSurfaceVariantColor
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
            Divider(color = MaterialTheme.colorScheme.outline)
            Spacer(Modifier.height(10.dp))

            // DETALLES ADICIONALES
            Text(
                "Detalles:",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = onSurfaceColor
            )

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
                        color = onSurfaceVariantColor,
                        modifier = Modifier.weight(0.4f)
                    )
                    Text(
                        text = value,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = onSurfaceColor,
                        modifier = Modifier.weight(0.6f)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Divider(color = MaterialTheme.colorScheme.outline)
            Spacer(Modifier.height(10.dp))


            // FECHA + HORA + ID

            // ID
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("ID:", fontSize = 10.sp, color = onSurfaceVariantColor)
                Text(
                    text = denuncia.id,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = onSurfaceColor
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
                    Text("Fecha:", fontSize = 10.sp, color = onSurfaceVariantColor)
                    Text(denuncia.fecha, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = onSurfaceColor)
                }

                // Columna para la Hora
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text("Hora:", fontSize = 10.sp, color = onSurfaceVariantColor)
                    Text(denuncia.hora, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = onSurfaceColor)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisDenunciasScreen(
    onNavigateBack: () -> Unit,
    onOpenDrawer: () -> Unit,
    onNavigateToDenunciaDetail: (String) -> Unit,
) {
    val firestore = remember { FirebaseFirestore.getInstance() }

    val factory = remember { MisDenunciasViewModelFactory(firestore = firestore) }

    val viewModel: MisDenunciasViewModel = viewModel(factory = factory)

    val denuncias = viewModel.denuncias.value
    val isLoading = viewModel.isLoading.value
    val feedbackMessage = viewModel.feedbackMessage.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface


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
                title = {
                    Text("Mis Denuncias", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                )
            )
        },

        containerColor = backgroundColor

    ) { paddingValues ->

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues).background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = primaryColor)
            }
            return@Scaffold
        }

        if (denuncias.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues).background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                val displayMessage = feedbackMessage ?: "Aún no has realizado ninguna denuncia."
                Text(displayMessage, color = onSurfaceVariantColor)
            }
            return@Scaffold
        }

        // LISTA DE DENUNCIAS
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {

            items(denuncias) { domainDenuncia ->

                val uiDenuncia = domainDenuncia.toUiDenuncia()

                // Llamamos a DenunciaItem
                DenunciaItem(denuncia = uiDenuncia)
            }
        }
    }
}

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
 ```


```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
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
import androidx.lifecycle.viewmodel.compose.viewModel
import android.widget.Toast
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.PersonaDesaparecidaViewModel
enum class Sexo { MUJER, HOMBRE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaDesaparecidaScreen(
    onNavigateBack: () -> Unit,
    viewModel: PersonaDesaparecidaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createPersonaDesaparecidaViewModelFactory()
    )
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val outlineColor = MaterialTheme.colorScheme.outline

    // --- Estados del formulario ---
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var descripcionFisica by remember { mutableStateOf("") }
    var vestimenta by remember { mutableStateOf("") }
    var detallesHecho by remember { mutableStateOf("") }
    var selectedSexo by remember { mutableStateOf(Sexo.MUJER) }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val context = LocalContext.current

    // --- Efecto Secundario para manejar el éxito y error (Sin cambios relevantes) ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            Toast.makeText(context, "✅ Reporte enviado con éxito.", Toast.LENGTH_LONG).show()
            viewModel.resetStates()
            onNavigateBack()
        }
    }
    LaunchedEffect(saveError) {
        if (saveError != null) {
            Toast.makeText(context, "❌ Error al enviar: $saveError", Toast.LENGTH_LONG).show()
            viewModel.resetStates()
        }
    }

    // --- Colores para OutlinedTextFields ---
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = primaryColor,
        unfocusedBorderColor = outlineColor,
        focusedTextColor = onSurfaceColor,
        unfocusedTextColor = onSurfaceColor,
        focusedPlaceholderColor = onSurfaceVariantColor,
        unfocusedPlaceholderColor = onSurfaceVariantColor
    )
    val placeholder: @Composable (String) -> @Composable (() -> Unit) = { text -> { Text(text, color = onSurfaceVariantColor) } }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Reporte de Persona Desaparecida", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isSaving
                    ) {
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(surfaceColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
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
                        Text(
                            "Foto de la persona",
                            color = onSurfaceVariantColor
                        )
                    }
                } else {
                    Text("Imagen de la persona seleccionada", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Colores para botones secundarios (Cámara/Galería)
                val secondaryButtonColors = ButtonDefaults.buttonColors(
                    containerColor = onSurfaceVariantColor.copy(alpha = 0.5f),
                    contentColor = onSurfaceColor
                )

                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = secondaryButtonColors,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                    enabled = !isSaving
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
                    colors = secondaryButtonColors,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                    enabled = !isSaving
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Nombre" ---
            LabelText("Nombre completo",)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = placeholder("Nombre de la persona desaparecida"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Radio Buttons de Sexo ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Sexo:", fontWeight = FontWeight.Medium, color = onSurfaceColor)
                Spacer(modifier = Modifier.width(16.dp))

                // Colores dinámicos para RadioButton
                val radioColors = RadioButtonDefaults.colors(
                    selectedColor = primaryColor,
                    unselectedColor = outlineColor,
                    disabledSelectedColor = outlineColor,
                    disabledUnselectedColor = outlineColor.copy(alpha = 0.5f)
                )

                // Opción Mujer
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSexo == Sexo.MUJER,
                        onClick = { selectedSexo = Sexo.MUJER },
                        enabled = !isSaving,
                        colors = radioColors
                    )
                    Text("Mujer", modifier = Modifier.padding(end = 16.dp), color = onSurfaceColor) // CORRECCIÓN 12
                }
                // Opción Hombre
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSexo == Sexo.HOMBRE,
                        onClick = { selectedSexo = Sexo.HOMBRE },
                        enabled = !isSaving,
                        colors = radioColors
                    )
                    Text("Hombre", color = onSurfaceColor) // CORRECCIÓN 12
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Edad" ---
            LabelText("Edad (aproximada)",)
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = if (it.length <= 3) it else it.substring(0, 3) },
                placeholder = placeholder("Ej: 35"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Descripción física" ---
            LabelText("Descripción física",)
            OutlinedTextField(
                value = descripcionFisica,
                onValueChange = { descripcionFisica = it },
                placeholder = placeholder("Altura, color de ojos/cabello, señas particulares"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Vestimenta" ---
            LabelText("Vestimenta (última vez vista)",)
            OutlinedTextField(
                value = vestimenta,
                onValueChange = { vestimenta = it },
                placeholder = placeholder("Color y tipo de ropa, calzado"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Detalles del hecho" ---
            LabelText("Describe el lugar, la hora y cómo sucedió",)
            OutlinedTextField(
                value = detallesHecho,
                onValueChange = { detallesHecho = it },
                placeholder = placeholder("Proporciona detalles cruciales de la desaparición"),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de los la persona desaparecida de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        nombre = nombre, sexo = selectedSexo, edad = edad, descripcionFisica = descripcionFisica,
                        vestimenta = vestimenta, detallesHecho = detallesHecho
                    )
                },
                enabled = !isSaving && nombre.isNotBlank() && edad.isNotBlank() && detallesHecho.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        color = Color.DarkGray,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonaDesaparecidaPreview() {
    PersonaDesaparecidaScreen(
        onNavigateBack = {},
        viewModel = viewModel(
            factory = DenunciaAppViewModelFactory.createPersonaDesaparecidaViewModelFactory()
        )
    )
}
 ```


```bash
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
 ```


```bash
   package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory // Importar la factoría central
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.RoboCasaViewModel // Importar el ViewModel
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton

// Valores constantes
private val DefaultLocation2 = LatLng(20.940, -100.900)
private const val DefaultZoom2 = 15f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoboCasaScreen(
    onNavigateBack: () -> Unit,
    viewModel: RoboCasaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createRoboCasaViewModelFactory()
    )
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val surfaceColor = MaterialTheme.colorScheme.surface

    // --- Observación de Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()

    // --- Estados del formulario local ---
    var descripcion by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var confirmarTelefono by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estado del Mapa y Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var currentCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, DefaultZoom)) }
    var selectedMarkerLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoadingLocation by remember { mutableStateOf(false) }

    // --- Estados de Permisos ---
    var hasLocationPermission by remember {
        mutableStateOf(checkLocationPermission(context))
    }

    // --- Efecto para Navegación en Éxito y Feedback (Sin cambios relevantes) ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onNavigateBack()
            viewModel.resetStates()
        }
    }

    // Launcher y función de permisos (Sin cambios relevantes)
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            if (hasLocationPermission) {
                isLoadingLocation = true
                getCurrentLocation(context, fusedLocationClient) { latLng ->
                    currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                    selectedMarkerLocation = latLng
                    ubicacion = getAddressFromLatLng(context, latLng)
                    isLoadingLocation = false
                }
            } else {
                ubicacion = "Permiso de ubicación denegado."
                isLoadingLocation = false
            }
        }
    )

    fun requestLocationAndUpdate() {
        if (hasLocationPermission) {
            isLoadingLocation = true
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                selectedMarkerLocation = latLng
                ubicacion = getAddressFromLatLng(context, latLng)
                isLoadingLocation = false
            }
        } else {
            locationPermissionLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }

    // --- TopBar y Fondo ---
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Robo a Casa Habitación", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isSaving
                    ) {
                        // Se mantiene RedCancelButton (color fijo de utilidad/peligro)
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(surfaceColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp)),
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
                        Text(
                            "Foto del lugar (opcional)",
                            color = onSurfaceVariantColor
                        )
                    }
                } else {
                    // CORRECCIÓN 7: Usar color de texto principal dinámico
                    Text("Imagen seleccionada", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen (Simulación) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Colores para botones secundarios (Cámara/Galería)
                val secondaryButtonColors = ButtonDefaults.buttonColors(
                    containerColor = onSurfaceVariantColor.copy(alpha = 0.5f),
                    contentColor = onSurfaceColor
                )

                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = secondaryButtonColors, // CORRECCIÓN 8
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSaving,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { selectedImageUri = "file://path/to/image.jpg" },
                    colors = secondaryButtonColors, // CORRECCIÓN 8
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSaving,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería", color = onSurfaceColor)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Colores para OutlinedTextFields ---
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedTextColor = onSurfaceColor,
                unfocusedTextColor = onSurfaceColor,
                focusedPlaceholderColor = onSurfaceVariantColor,
                unfocusedPlaceholderColor = onSurfaceVariantColor
            )

            // --- Campo "Describe el hecho" ---
            LabelText("Describe el hecho",)
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                placeholder = { Text("Detalle lo sucedido...") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Ubicación" (Con GPS y Mapa) ---
            LabelText("Ubicación del Domicilio Robado",)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = ubicacion,
                    onValueChange = { ubicacion = it },
                    placeholder = { Text("Ubicación actual o seleccionada") },
                    modifier = Modifier.weight(1f),
                    readOnly = false,
                    enabled = !isSaving,
                    trailingIcon = {
                        // Botón para obtener la ubicación actual (GPS)
                        IconButton(onClick = {
                            requestLocationAndUpdate()
                        }, enabled = !isLoadingLocation && !isSaving) {
                            if (isLoadingLocation) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp,
                                    color = primaryColor // CORRECCIÓN 10: Color dinámico
                                )
                            } else {
                                Icon(Icons.Default.LocationOn, contentDescription = "Obtener GPS", tint = primaryColor)
                            }
                        }
                    },
                    colors = textFieldColors // CORRECCIÓN 9
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Botón para abrir el Mapa en un diálogo
                Button(
                    onClick = {
                        showMapDialog = true
                        if (selectedMarkerLocation == null) {
                            requestLocationAndUpdate()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor), // CORRECCIÓN 12: Usar color primario
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSaving,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Mapa", tint = MaterialTheme.colorScheme.onPrimary)
                }
            }

            if (!hasLocationPermission && ubicacion.contains("denegado")) {
                Text(
                    "Pulsa el botón de GPS para solicitar permisos de ubicación.",
                    color = RedCancelButton, // Se mantiene el color de alerta fijo
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Teléfono de contacto" ---
            LabelText("Teléfono de contacto",)
            OutlinedTextField(
                value = telefono,
                onValueChange = {
                    if (it.length <= 10) { telefono = it.filter { char -> char.isDigit() } }
                },
                placeholder = { Text("Número a 10 dígitos") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Confirmar teléfono" ---
            LabelText("Confirmar teléfono",)
            OutlinedTextField(
                value = confirmarTelefono,
                onValueChange = {
                    if (it.length <= 10) { confirmarTelefono = it.filter { char -> char.isDigit() } }
                },
                placeholder = { Text("Repita el número") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "La ubicación es crucial para la investigación. Puede usar el GPS para su ubicación actual o el mapa para señalar la dirección exacta del domicilio robado.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor, // CORRECCIÓN 14: Usar color secundario/sutil
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        descripcion = descripcion, locationAddress = ubicacion, latitud = selectedMarkerLocation?.latitude,
                        longitud = selectedMarkerLocation?.longitude, telefono = telefono, confirmarTelefono = confirmarTelefono
                    )
                },
                enabled = !isSaving && descripcion.isNotBlank() && ubicacion.isNotBlank() && telefono.isNotBlank() && confirmarTelefono.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    // El color del indicador debe contrastar con YellowButton (usar un color oscuro fijo es aceptable)
                    CircularProgressIndicator(color = Color.DarkGray, modifier = Modifier.size(24.dp))
                } else {
                    // El color del texto debe contrastar con YellowButton
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo para mostrar el Mapa interactivo ---
    if (showMapDialog) {
        MapSelectionDialog(
            cameraPosition = currentCameraPosition,
            markerLocation = selectedMarkerLocation,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = { latLng ->
                selectedMarkerLocation = latLng
                ubicacion = getAddressFromLatLng(context, latLng)
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                showMapDialog = false
            },
            onCameraMove = { cameraPos ->
                currentCameraPosition = cameraPos
            }
        )
    }

    // --- Diálogo de Error (Asegurar que use colores del tema) ---
    saveError?.let { message ->
        AlertDialog(
            onDismissRequest = { viewModel.resetStates() },
            title = { Text("Error al Enviar Reporte", color = onSurfaceColor) },
            text = { Text(message, color = onSurfaceVariantColor) },
            confirmButton = {
                Button(onClick = { viewModel.resetStates() }) {
                    Text("Aceptar")
                }
            },
            containerColor = surfaceColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoboCasaPreview() {
    RoboCasaScreen(onNavigateBack = {})
}
 ```



```bash
    package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.UserProfileViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.UserProfileViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

// ⚠️ IMPORTAR LAS FUNCIONES COMUNES DE TU PAQUETE:
import mx.edu.utng.oic.denunciaapp.ui.components.GenderOption
import mx.edu.utng.oic.denunciaapp.ui.components.ImagePlaceholder
import mx.edu.utng.oic.denunciaapp.ui.components.OutlinedTextFieldWithDialog
import mx.edu.utng.oic.denunciaapp.ui.components.SimpleOutlinedTextField

val HeaderBlue = Color(0xFF42A5F5)
val HeaderDarkBlue = Color(0xFF1976D2)
val StarYellow = Color(0xFFFFCC00)
val StarGray = Color(0xFFE0E0E0)
val SuccessGreen = Color(0xFF4CAF50)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    onNavigateBack: () -> Unit
) {
    val userService = remember { UserService() }
    val factory = remember { UserProfileViewModelFactory(userService) }
    val viewModel: UserProfileViewModel = viewModel(factory = factory)

    val user: User = viewModel.userState
    val isLoading = viewModel.isLoading
    val isSaving = viewModel.isSaving
    val feedbackMessage = viewModel.feedbackMessage

    var showPhotoDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // --- 4. Manejo de Mensajes (Snackbar) ---
    LaunchedEffect(feedbackMessage) {
        if (feedbackMessage != null) {
            scope.launch {
                snackbarHostState.showSnackbar(feedbackMessage)
            }
            viewModel.clearFeedbackMessage()
        }
    }


    // --- Contenido Principal ---
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            // Encabezado con Logout/Guardar y la Imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HeaderBlue)
                    .height(200.dp)
            ) {
                // Botones de ACCIÓN
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // 1. Botón de REGRESAR
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(HeaderDarkBlue, CircleShape)
                            .clickable { onNavigateBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar a la pantalla anterior",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    // 2. Botón de GUARDAR
                    if (!user.isAnonymus && !isLoading) {
                        Button(
                            onClick = viewModel::saveUserProfile,
                            enabled = !isSaving,
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = if (isSaving) "Guardando" else "Guardar",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                // Placeholder de la Imagen de Perfil
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (50).dp)
                        .clickable { showPhotoDialog = true }
                ) {
                    ImagePlaceholder(
                        size = 100.dp,
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape,
                        borderColor = HeaderBlue,
                        borderWidth = 3.dp
                    )
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        },
    ) { paddingValues ->

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues).background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Column(horizontalAlignment = Alignment.Start) {
                // Nombre
                LabelText("Nombre",)

                SimpleOutlinedTextField(
                    value = user.nombre,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(nombre = it)) },
                    placeholder = "User name"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GenderOption("Hombre", user.sexo) { viewModel.onUserFieldChange(user.copy(sexo = "Hombre")) }
                    Spacer(modifier = Modifier.width(16.dp))
                    GenderOption("Mujer", user.sexo) { viewModel.onUserFieldChange(user.copy(sexo = "Mujer")) }
                    Spacer(modifier = Modifier.width(16.dp))
                    GenderOption("Otro", user.sexo) { viewModel.onUserFieldChange(user.copy(sexo = "Otro")) }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Teléfono
                LabelText("Teléfono",)
                SimpleOutlinedTextField(
                    value = user.telefono,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(telefono = it)) },
                    placeholder = "",
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(16.dp))

                LabelText("Fecha de nacimiento",)
                OutlinedTextFieldWithDialog(
                    value = user.fechaNacimiento,
                    placeholder = "",
                    onClick = { showDatePicker = true }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Correo electrónico
                LabelText("Correo electrónico",)
                SimpleOutlinedTextField(
                    value = user.correoElectronico,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(correoElectronico = it)) },
                    placeholder = "",
                    keyboardType = KeyboardType.Email,
                    enabled = user.isAnonymus
                )

                Spacer(modifier = Modifier.height(16.dp))

            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(horizontalAlignment = Alignment.Start) {
                // Descripción/Bio
                LabelText("Descripción",)
                OutlinedTextField(
                    value = user.descripcion,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(descripcion = it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    placeholder = {
                        Text(
                            "Inserte una descripción",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    maxLines = 5,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                LabelText("Puntos de respeto",)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    repeat(5) { index ->
                        val isFilled = index < user.respectPoints
                        Icon(
                            imageVector = if (isFilled) Icons.Default.Star else Icons.Outlined.StarBorder,
                            contentDescription = "Estrella de respeto",
                            tint = if (isFilled) StarYellow else StarGray, // Colores fijos para estrellas
                            modifier = Modifier
                                .size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }

    // --- Diálogos y Pop-ups ---

    if (showPhotoDialog) {
        AlertDialog(
            onDismissRequest = { showPhotoDialog = false },
            title = { Text(user.nombre, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface) },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Abrir Cámara */ showPhotoDialog = false }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Abrir camara", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Menu, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Divider(color = MaterialTheme.colorScheme.outlineVariant) // Usar un color de tema para la división
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Abrir Galería */ showPhotoDialog = false }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Abrir Galeria", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Menu, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            },
            confirmButton = {}
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val newDate = formatter.format(Date(millis))
                        viewModel.onUserFieldChange(user.copy(fechaNacimiento = newDate))
                    }
                    showDatePicker = false
                }) {
                    Text("OK", color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.primary)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
```

## ForoModel.kt

```bash
    package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

/**
 * Modelo de datos para un Foro dentro de la aplicación.
 *
 * NOTA DE CORRECCIÓN: Se añaden valores por defecto a TODOS los campos
 * para asegurar la correcta deserialización desde Firestore, incluso
 * si algún campo falta o es nulo.
 *
 * @property id Identificador único del foro.
 * @property tema Título o tema principal del foro (establecido por el usuario).
 * @property username Nombre del usuario creador del foro.
 * @property creationDate Fecha y hora de creación del foro.
 * @property responseCount Número actual de respuestas (mensajes) que tiene el foro.
 * @property idUser Identificador del usuario creador (para verificación de propiedad).
 */
data class Foro(
    val id: String = "",
    val tema: String = "",
    val username: String = "",
    val creationDate: Date = Date(),
    val responseCount: Int = 0,
    val idUser: String = ""
)
 ```

## Message.kt
```bash
   package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

/**
 * Clase corregida. Se añadieron valores por defecto a todos los parámetros
 * del constructor principal. Esto es esencial para que Firebase Firestore
 * pueda deserializar los documentos (convertir datos de la DB a objetos Message)
 * al leer los mensajes.
 */
data class Message(
    val id: String = "",
    val forumId: String = "",
    val userId: String = "",
    val contenido: String = "",
    val dateTime: Date = Date()
)

/**
 * Clase ChatThread (no requiere corrección, ya que si sus campos
 * tienen valores por defecto, cumple con el requisito, aunque se recomienda
 * revisar si Date es serializable/deserializable por Firebase correctamente
 * en todos los casos.
 */
data class ChatThread(
    val forumId: String = "",
    val forumTitle: String = "",
    val lastMessageContent: String = "",
    val lastMessageTime: Date = Date(),
    val unreadCount: Int = 0
)
 ```

## User.kt
```bash
    package mx.edu.utng.oic.denunciaapp.data.model

import java.util.UUID

enum class UserRole {
    USUARIO,
    USUARIO_ANONIMO
}

data class User(
    val idUser: String = UUID.randomUUID().toString(),

    val nombre: String = "",
    val correoElectronico: String = "",
    val contrasenia: String = "",
    val sexo: String = "",
    val telefono: String = "",
    val fechaNacimiento: String = "",
    val descripcion: String = "",
    val rol: UserRole = UserRole.USUARIO,
    val respectPoints: Int = 0,
    val isAnonymus: Boolean = false
)
```

## Denuncia.kt
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

## ForoService
```bash
    package mx.edu.utng.oic.denunciaapp.data.service

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Foro
import android.util.Log
import com.google.firebase.firestore.Query

class ForoService {

    private val db = FirebaseFirestore.getInstance()
    private val forumsCollection = db.collection("forums")

    /**
     * Obtiene la lista completa de foros, ordenados por fecha de creación descendente.
     * Incluye logging para detectar fallos en el mapeo (toObject).
     */
    suspend fun getAllForos(): List<Foro> {
        return try {
            val snapshot = forumsCollection
                .orderBy("creationDate", Query.Direction.DESCENDING)
                .get()
                .await()

            Log.d("ForoService", "Documentos obtenidos de Firestore: ${snapshot.documents.size}")

            snapshot.documents.mapNotNull { document ->
                val foro = try {
                    document.toObject(Foro::class.java)?.copy(id = document.id)
                } catch (e: Exception) {
                    // Si el mapeo falla (por campos faltantes, tipos incorrectos, etc.)
                    Log.e("ForoService", "FALLO CRÍTICO DE MAPEO en documento ID: ${document.id}", e)
                    null
                }

                if (foro == null) {
                    Log.w("ForoService", "Documento ID: ${document.id} NO PUDO SER MAPPEADO a Foro. Verifique el modelo de datos (data class Foro).")
                }
                foro
            }
        } catch (e: Exception) {
            // Error en la conexión, permisos o consulta
            Log.e("ForoService", "Error al ejecutar la consulta de foros", e)
            emptyList()
        }
    }

    /**
     * Guarda o actualiza un foro en Firestore.
     */
    suspend fun saveForo(foro: Foro) {
        try {
            forumsCollection.document(foro.id)
                .set(foro)
                .await()
        } catch (e: Exception) {
            Log.e("ForoService", "Error al guardar el foro con ID: ${foro.id}", e)
            throw e
        }
    }

    /**
     * Elimina un foro de Firestore por su ID.
     */
    suspend fun deleteForo(foroId: String) {
        try {
            forumsCollection.document(foroId)
                .delete()
                .await()
        } catch (e: Exception) {
            Log.e("ForoService", "Error al eliminar el foro con ID: $foroId", e)
            throw e
        }
    }

    /**
     * Obtiene un foro específico por su ID.
     */
    suspend fun getForoById(foroId: String): Foro? {
        return try {
            val snapshot = forumsCollection.document(foroId).get().await()
            snapshot.toObject(Foro::class.java)?.copy(id = snapshot.id)
        } catch (e: Exception) {
            Log.e("ForoService", "Error al obtener foro con ID: $foroId", e)
            null
        }
    }
}
 ```

## MessageService
```bash
    package mx.edu.utng.oic.denunciaapp.data.service

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Message
import java.util.Date

class MessageService {

    private val db = FirebaseFirestore.getInstance()
    private val messagesCollection = db.collection("messages")
    private val forumsCollection = db.collection("forums")

    /**
     * Guarda un nuevo mensaje (respuesta) en Firestore y actualiza el contador de respuestas
     * en el documento del Foro padre.
     */
    suspend fun sendMessage(message: Message) {
        try {
            // 1. Guardar el mensaje usando su ID como ID del documento
            messagesCollection.document(message.id)
                .set(message)
                .await()

            // 2. Actualizar el contador de respuestas (responseCount) en el Foro
            // Usamos una transacción para asegurar la consistencia.
            db.runTransaction { transaction ->
                val forumRef = forumsCollection.document(message.forumId)
                val forumSnapshot = transaction.get(forumRef)

                val currentCount = forumSnapshot.getLong("responseCount")?.toInt() ?: 0
                val newCount = currentCount + 1

                transaction.update(forumRef, "responseCount", newCount)
            }.await()

        } catch (e: Exception) {
            Log.e("MessageService", "Error al enviar mensaje o actualizar contador del foro ${message.forumId}", e)
            throw e
        }
    }

    /**
     * Obtiene los mensajes de un foro específico en tiempo real (Flow).
     * @param forumId El ID del foro.
     */
    fun getMessagesForForum(forumId: String): Flow<List<Message>> = callbackFlow {
        val subscription = messagesCollection
            .whereEqualTo("forumId", forumId) // Filtrar por el ID del foro
            .orderBy("dateTime", Query.Direction.ASCENDING) // Ordenar por fecha de envío
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("MessageService", "Error al escuchar mensajes", e)
                    // Si hay un error, cerrar el flow con la excepción
                    close(e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val messages = snapshot.documents.mapNotNull { document ->
                        document.toObject(Message::class.java)?.copy(id = document.id)
                    }
                    trySend(messages) // Enviar la nueva lista de mensajes al Flow
                }
            }

        awaitClose {
            subscription.remove()
        }
    }
}
 ```

## UserService
```bash
    package mx.edu.utng.oic.denunciaapp.data.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.User

class UserService {

    private val auth = FirebaseAuth.getInstance()
    private val usersCollection = FirebaseFirestore.getInstance().collection("users")

    /**
     * Devuelve el ID (UID) del usuario actualmente logueado.
     * @throws IllegalStateException si no hay ningún usuario autenticado.
     */
    fun getLoggedUserId(): String {
        return auth.currentUser?.uid ?: throw IllegalStateException("User not authenticated.")
    }

    suspend fun getOrCreateUserId(): String {
        val current = auth.currentUser
        if (current != null) return current.uid

        val result = auth.signInAnonymously().await()
        return result.user?.uid ?: throw Exception("Error creando usuario anónimo")
    }

    suspend fun getUserByEmail(email: String): User? {
        val query = usersCollection.whereEqualTo("correoElectronico", email)
            .limit(1)
            .get()
            .await()

        if (query.isEmpty) return null

        val user = query.documents.first().toObject(User::class.java)
        val uid = query.documents.first().id

        return user?.copy(idUser = uid)
    }

    suspend fun registerUser(user: User) {
        // 1) Crear usuario en FirebaseAuth
        val authResult = auth.createUserWithEmailAndPassword(
            user.correoElectronico,
            user.contrasenia
        ).await()

        val uid = authResult.user?.uid ?: throw Exception("No se obtuvo UID al registrar")

        // 2) Guardar en Firestore con el UID real
        val data = user.copy(idUser = uid)

        usersCollection.document(uid)
            .set(data)
            .await()
    }

    suspend fun loginUser(email: String, password: String): User? {

        val result = auth.signInWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: return null

        val snapshot = usersCollection.document(uid).get().await()

        val user = snapshot.toObject(User::class.java)
        return user?.copy(idUser = uid)
    }

    fun logout() {
        auth.signOut()
    }

    suspend fun signInAnonymously(): String {
        val result = auth.signInAnonymously().await()
        return result.user?.uid ?: throw Exception("No UID returned")
    }

    suspend fun updateUser(user: User) {
        // Solo actualiza los datos del usuario en Firestore
        usersCollection.document(user.idUser)
            .set(user)
            .await()
    }

    suspend fun getUserById(uid: String): User? {
        val snapshot = usersCollection.document(uid).get().await()
        return snapshot.toObject(User::class.java)?.copy(idUser = uid)
    }

}
 ```

## Theme.kt
```bash
   package mx.edu.utng.oic.denunciaapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // ¡Importar Color!
import androidx.compose.ui.platform.LocalContext


val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val DarkGrey = Color(0xFF1C1B1F)
val LightGrey = Color(0xFFFFFBFE)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,

    background = DarkGrey,
    surface = DarkGrey,
    onBackground = White,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun DenunciaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
 ```
