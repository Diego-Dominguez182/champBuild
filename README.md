ste documento proporciona una descripción general de la API ChampBuild, una aplicación Spring Boot diseñada para administrar builds de campeones en un juego como League of Legends.

Características
Operaciones CRUD: Crea, lee, actualiza y elimina builds de campeones.
Búsqueda por nombre: Encuentra builds para campeones específicos.
Validación: Garantiza la integridad de los datos con la validación a nivel de campo.
Manejo de excepciones: Proporciona mensajes de error informativos para diversos escenarios.
Persistencia de datos: Almacena builds en una base de datos MongoDB.
Paquetes

El código base está organizado en los siguientes paquetes:

controller: Maneja las solicitudes HTTP entrantes e interactúa con la capa de servicio.
dto: Define objetos de transferencia de datos (DTOs) para representar datos de build.
exception: Maneja excepciones y devuelve respuestas de error adecuadas.
mapper: Mapea entre objetos DTO y entidad utilizando MapStruct.
model: Representa entidades de build.
repository: Interactúa con la base de datos MongoDB para la persistencia de datos.
service: Lógica de negocio para gestionar las operaciones de build.
Puntos de Entrada de la API
La API expone los siguientes puntos de entrada:

GET /api/builds: Recupera todas las builds disponibles.

GET /api/builds/{name}: Recupera builds asociadas con el nombre del campeón especificado.

POST /api/builds: Crea una nueva build (requiere un objeto ChampBuildDTO válido en el cuerpo de la solicitud).

PUT /api/builds/{name}: Actualiza una build existente (requiere un objeto ChampBuildDTO válido en el cuerpo de la solicitud y el nombre de la build en la ruta).

DELETE /api/builds/{name}/{buildTitle}: Elimina una build específica identificada tanto por el nombre del campeón como por el título de la build dentro de la ruta.


Modelo de Datos

La clase ChampBuildDTO representa una build de campeón con las siguientes propiedades:

buildTitle: El título único de la build (obligatorio, no vacío).

champName: El nombre del campeón para el que es la build (obligatorio, no vacío).

items: Una lista de seis nombres de elementos (obligatorio, no vacío, el tamaño debe ser exactamente 6).

userName: El nombre de usuario del jugador que creó la build (obligatorio, no vacío).


Uso

Instale y configure una base de datos MongoDB.

Compile la aplicación Spring Boot utilizando Maven o Gradle.

Ejecute la aplicación.

Utilice un cliente HTTP como Postman o curl para interactuar con los puntos de entrada de la API.
