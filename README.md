# Sistema de Alquiler de Bicicletas

Este proyecto es un sistema de alquiler de bicicletas que calcula tarifas según el día, la hora de alquiler y la distancia recorrida. Además, implementa autenticación y autorización a través de Keycloak, con roles de cliente y administrador.

## Requisitos del sistema
- Java JDK 8 o superior
- Maven
- Keycloak instalado y configurado

## Instrucciones de instalación
1. Clona este repositorio.
2. Configura la conexión con la base de datos(Se encuentra conectado ya porque la bd esta en la raiz del proyecto.
3. Importa las dependencias Maven.


## Ejecución de los proyectos
Sigue estos pasos para ejecutar los proyectos:

### Ejecución
1. Ejecuta el APIgateway
2. Ejecuta el proyecto de Alquileres
3. Ejecuta el proyecto de Estaciones


## Roles de Usuario
El sistema tiene dos roles de usuario:
- **Cliente:** Puede realizar alquileres y ver historial.
- **Administrador:** Tiene acceso a funciones administrativas.

## Carpeta de Postman
En la carpeta `postman` encontrarás colecciones de Postman para realizar pruebas en el sistema. Asegúrate de tener el entorno configurado correctamente.
Por otro lado, deben pedir primeoro el token correspondiente en el endpoint de cada rol y luego agregarlo como bearer token en la petición que deseen.


# Bike Rental System

This project is a bike rental system that calculates fares based on the day, rental time, and distance traveled. It also implements authentication and authorization through Keycloak, with client and administrator roles.

## System Requirements
- Java JDK 8 or higher
- Maven
- Installed and configured Keycloak

## Installation Instructions
1. Clone this repository.
2. Configure the database connection (It is already connected because the database is in the project's root).
3. Import Maven dependencies.

## Project Execution
Follow these steps to run the projects:

### Execution
1. Run the API Gateway.
2. Run the Rental project.
3. Run the Stations project.

## User Roles
The system has two user roles:
- **Client:** Can rent bikes and view rental history.
- **Administrator:** Has access to administrative functions.

## Postman Collection
In the `postman` folder, you will find Postman collections for testing the system. Make sure to have the environment configured correctly.
Additionally, you need to request the corresponding token at the endpoint for each role and then add it as a Bearer token in the desired request.
