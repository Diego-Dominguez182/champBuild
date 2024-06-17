**Este documento proporciona una descripción general de la API ChampBuild, una aplicación Spring Boot diseñada para administrar builds de campeones en un juego como League of Legends.**

**Características**
Operaciones CRUD: Crea, lee, actualiza y elimina builds de campeones.
Búsqueda por nombre: Encuentra builds para campeones específicos.
Validación: Garantiza la integridad de los datos con la validación a nivel de campo.
Manejo de excepciones: Proporciona mensajes de error informativos para diversos escenarios.
Persistencia de datos: Almacena builds en una base de datos MongoDB.
Paquetes

**El código base está organizado en los siguientes paquetes:**

controller: Maneja las solicitudes HTTP entrantes e interactúa con la capa de servicio.
dto: Define objetos de transferencia de datos (DTOs) para representar datos de build.
exception: Maneja excepciones y devuelve respuestas de error adecuadas.
mapper: Mapea entre objetos DTO y entidad utilizando MapStruct.
model: Representa entidades de build.
repository: Interactúa con la base de datos MongoDB para la persistencia de datos.
service: Lógica de negocio para gestionar las operaciones de build.
Puntos de Entrada de la API


**La API expone los siguientes puntos de entrada:**
GET /api/builds: Recupera todas las builds disponibles.

GET /api/builds/{name}: Recupera builds asociadas con el nombre del campeón especificado.

POST /api/builds: Crea una nueva build (requiere un objeto ChampBuildDTO válido en el cuerpo de la solicitud).

PUT /api/builds/{name}: Actualiza una build existente (requiere un objeto ChampBuildDTO válido en el cuerpo de la solicitud y el nombre de la build en la ruta).

DELETE /api/builds/{name}/{buildTitle}: Elimina una build específica identificada tanto por el nombre del campeón como por el título de la build dentro de la ruta.


**Modelo de Datos**
La clase ChampBuildDTO representa una build de campeón con las siguientes propiedades:

buildTitle: El título único de la build (obligatorio, no vacío).

champName: El nombre del campeón para el que es la build (obligatorio, no vacío).

items: Una lista de seis nombres de elementos (obligatorio, no vacío, el tamaño debe ser exactamente 6).

userName: El nombre de usuario del jugador que creó la build (obligatorio, no vacío).


**Uso**
Descargue y ejecute la aplicación main.

El programa debería funcionar sin necesidad de configuraciones de base de datos.

**Uso con Docker**

Para poder utilizar esta API sin necesidad de descargar directamente el código, utilizaremos docker.

1.- docker pull huntermick182/champ_build:lastest
    Este comando sirve para hacer una copia local a nuestro equipo del contenedor alojado en la cuenta
    "huntermick182" con el nombre de contenedor "champ_build" con la versión "lastest"



2.- docker run -p 8080:8080 huntermick182/champ_build:lastest
    Este comando sirve para correr el contenedor previamente descargado, con el -p indicamos el puerto
    que quiere ser usado para nuestro localhost, indicamos de que cuenta queremos correr el contenedo
    y el nombre del contenedor.

Con estos dos sencillos pasos, podremos consultar en nuestro navegador "localhost:8080/api/builds" y
obtendremos todas las builds cargadas en nuestra base de datos hasta el momento de la consulta.
Con postman podremos hacer las diferentes cargas, actualizaciones y eliminaciones de las builds.
