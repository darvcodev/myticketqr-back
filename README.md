# MyTicketQR - Kotlin con Spring Boot y MongoDB

Este proyecto de Kotlin con Spring Boot y MongoDB, llamado "MyTicketQR", es una aplicación que te permite gestionar la creación de tickets, usuarios y realizar autenticación. La aplicación se centra en la creación de tickets y permite a los usuarios autenticados administrar sus boletos de eventos.

## Características

- Creación y gestión de tickets.
- Registro de usuarios y autenticación.
- Administración de tickets por usuarios autenticados.
- Almacenamiento de datos en una base de datos MongoDB.

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes componentes:

- [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/javase-downloads.html) 11 o superior
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [Kotlin](https://kotlinlang.org/docs/getting-started.html)
- [Postman](https://www.postman.com/downloads/) (opcional, para probar las API)

## Configuración

1. Clona el repositorio de GitHub:

```
git clone https://github.com/darvcodev/MyTicketQR.git
```

2. Configura la base de datos MongoDB editando el archivo `application.properties` en la carpeta `src/main/resources`. Asegúrate de proporcionar la URL de conexión de tu base de datos MongoDB.

3. Abre el proyecto en tu entorno de desarrollo (por ejemplo, [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) o [Eclipse](https://www.eclipse.org/downloads/)).

4. Ejecuta la aplicación Spring Boot.

## Uso

La aplicación ofrece una serie de puntos finales REST que puedes utilizar para interactuar con ella. Puedes probar estos puntos finales utilizando Postman u otra herramienta similar.

- Crear un nuevo ticket: `POST /tickets`
- Obtener todos los tickets: `GET /tickets`
- Registrar un nuevo usuario: `POST /signup`
- Iniciar sesión como usuario: `POST /login`
- Obtener información del usuario autenticado: `GET /user`

## Contribución

¡Te invitamos a contribuir a este proyecto! Si deseas agregar nuevas características, solucionar problemas o realizar mejoras, no dudes en enviar un pull request.

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto con nosotros en [rojasvidaldaniel@gmail.com](mailto:rojasvidaldaniel@gmail.com).

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más información.
