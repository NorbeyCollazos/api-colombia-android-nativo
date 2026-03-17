# Info Colombia App
Info Colombia es una aplicación Android nativa diseñada para explorar la riqueza informativa de Colombia. Utiliza la API abierta de [api-colombia.com](https://api-colombia.com/) para visualizar datos detallados sobre departamentos, regiones, presidentes y sitios de interés turístico, integrando las herramientas más modernas del ecosistema Android.

<img width="210" height="530" alt="image" src="https://github.com/user-attachments/assets/6676c091-ad39-4c85-862e-2b36af03ee22" />

## 🚀 Características actuales
**Información General:** Datos clave sobre el país.
**Departamentos y Regiones:** Listado y detalle de la división política colombiana.
**Presidentes:** Línea histórica de los mandatarios de la nación.
**Turismo:** Guía de sitios turísticos con información relevante.
**Búsqueda y Filtrado:** Navegación optimizada entre las diferentes categorías.

## 🛠️ Stack Tecnológico
**Lenguaje:** Kotlin (Corrutinas y Flow para asincronía).
**Arquitectura:** * MVVM (Model-View-ViewModel) para la capa de presentación.
Clean Architecture para una separación de responsabilidades clara (Data, Domain, UI).
**Networking:** Retrofit + OkHttp para el consumo de la API REST.
**Inyección de Dependencias:** Dagger Hilt para la gestión de dependencias y modularización.
**UI:** Material Design 3 con componentes modernos de Android y Jetpack Compose.
**Testing:**  JUnit para pruebas unitarias.
MockK para el mockeo de dependencias en la capa de datos y dominio.

## 🏗️ Arquitectura y Patrones
La aplicación se divide en tres capas principales siguiendo los principios de Clean Architecture:

**Capa de Datos (Data):** Implementación de los servicios de Retrofit, DTOs y la implementación de los repositorios.

**Capa de Dominio (Domain):** Contiene las entidades puras de Kotlin y los Casos de Uso (Use Cases) que encapsulan la lógica de negocio.

**Capa de Presentación (UI):** ViewModels que gestionan el estado de la pantalla y se comunican con los componentes de la interfaz.

## 🧪 Pruebas Unitarias
El proyecto mantiene un enfoque en la calidad del código:

Pruebas de los Use Cases para asegurar la correcta ejecución de la lógica de negocio.

## 🗺️ Hoja de Ruta (Roadmap)
El proyecto está en constante evolución. Próximamente se integrarán las siguientes características:

 - Offline Mode: Implementación de Room Persistence Library para
   consultar datos sin conexión.
   
   Preferencias de Usuario: Uso de  DataStore para guardar
   configuraciones y filtros.
   
   Notificaciones y Auth: Integración de Firebase (Cloud Messaging y
   Authentication).
   
   Mapas: Visualización de sitios turísticos en un mapa interactivo.

## ⚙️ Configuración del Proyecto
Clona este repositorio.

Abre el proyecto en Android Studio (Ladybug o superior).

Sincroniza el proyecto con Gradle.

La API no requiere API Key (Pública), por lo que puedes ejecutar directamente en tu emulador o dispositivo físico.

### 📄 Licencia
Este proyecto es de código abierto y está disponible bajo la licencia MIT.

