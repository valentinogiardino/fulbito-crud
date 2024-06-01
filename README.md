
# Fulbito CRUD - Spring Boot

Este proyecto es un ejemplo de un CRUD con Spring Boot que utiliza Java 17, JWT y puede ser desplegada utilizando Docker. 

## Requisitos

- Java 17
- Spring Boot 3.3

## Instalación

### Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio
```

### Compilar y empaquetar

Usa Maven para compilar y empaquetar la aplicación:

```bash
mvn clean package
```

### Ejecutar localmente

Para ejecutar la aplicación localmente, usa el siguiente comando:

```bash
java -jar target/fulbito-application.jar
```

La aplicación estará disponible en `http://localhost:8080`.

## Docker

### Crear la imagen de Docker

Primero, asegúrate de tener Docker instalado y corriendo. Luego, construye la imagen de Docker con el siguiente comando:

```bash
docker build -t fulbito-application .
```

### Ejecutar la aplicación usando Docker

Una vez que la imagen está construida, puedes ejecutar un contenedor con la aplicación:

```bash
docker run -p 8080:8080 fulbito-application
```

La aplicación estará disponible en `http://localhost:8080`.

## Endpoints

- **Swagger** http://localhost:8080/swagger-ui/index.html#
- **Postman** https://www.postman.com/crimson-firefly-818805/workspace/dux/request/21195402-b0090524-e763-4211-bac9-436c78f8089c

## Credenciales de Usuario

La aplicación viene con un usuario pre-cargado para pruebas:

- **username:** test
- **password:** 12345


```
