# Sistema de Procesamiento de Transacciones Bancarias

![Java](https://img.shields.io/badge/Java-21+-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.9-yellow)
![Docker](https://img.shields.io/badge/Docker-20.10-blue)
![License](https://img.shields.io/badge/License-MIT-blue)

## Descripción

El **Sistema de Procesamiento de Transacciones Bancarias** es un backend moderno desarrollado con **Spring Boot** que simula el núcleo de un sistema bancario. Permite gestionar cuentas bancarias y procesar transacciones (depósitos, retiros y transferencias) con validaciones en tiempo real, como verificación de saldos, límites diarios y detección básica de fraudes. Las transacciones se procesan de forma asíncrona usando **RabbitMQ**, garantizando escalabilidad y robustez. El sistema incluye autenticación con **JWT**, auditoría de operaciones y monitoreo con **Prometheus** y **Grafana**. Todo está implementado con herramientas **open source** para mantener los costos en cero, y la API se prueba con **Postman**.

Este proyecto es ideal para demostrar habilidades avanzadas en desarrollo backend, diseño de sistemas financieros, arquitectura de microservicios y procesamiento asíncrono. Está diseñado con una **arquitectura hexagonal** para ser modular, testable y escalable.

## Características Principales

- **Gestión de cuentas**: Crear y consultar cuentas bancarias (titular, saldo, número de cuenta).
- **Transacciones**: Procesar depósitos, retiros y transferencias con validaciones:
  - Verificación de saldo suficiente.
  - Límites diarios (máximo $10,000 por día).
  - Detección básica de fraudes (por ejemplo, rechazar transacciones repetitivas en menos de 1 minuto).
- **Procesamiento asíncrono**: Usa RabbitMQ para encolar y procesar transacciones en segundo plano.
- **Seguridad**: Autenticación con JWT para proteger endpoints sensibles.
- **Auditoría**: Registro de todas las operaciones en una tabla de auditoría.
- **Monitoreo**: Métricas expuestas con Spring Actuator y visualizadas en Grafana.
- **Pruebas**: Pruebas unitarias (JUnit), de integración (Testcontainers) y manuales con Postman.
- **Documentación**: API documentada con Springdoc OpenAPI (Swagger UI).

## Tecnologías Utilizadas

- **Backend**:
  - **Spring Boot 3.x**: Framework para la API RESTful y lógica de negocio.
  - **Spring Web**: Endpoints REST.
  - **Spring Data JPA**: Interacción con PostgreSQL.
  - **Spring Security**: Autenticación con JWT.
  - **Spring AMQP**: Procesamiento asíncrono con RabbitMQ.
  - **Spring Boot Actuator**: Métricas de salud y rendimiento.
- **Base de datos**:
  - **PostgreSQL**: Almacenamiento de cuentas, transacciones y logs de auditoría.
  - **H2 Database**: Base de datos embebida para pruebas.
- **Colas**:
  - **RabbitMQ**: Procesamiento asíncrono de transacciones.
- **Despliegue local**:
  - **Docker**: Contenedorización de servicios.
  - **Docker Compose**: Orquestación de PostgreSQL, RabbitMQ, Prometheus y Grafana.
- **Monitoreo**:
  - **Prometheus**: Recolección de métricas.
  - **Grafana**: Visualización de métricas.
- **Pruebas**:
  - **JUnit 5**: Pruebas unitarias.
  - **MockMvc**: Pruebas de endpoints REST.
  - **Testcontainers**: Pruebas de integración con contenedores.
  - **Postman**: Pruebas manuales de la API.
- **Documentación**:
  - **Springdoc OpenAPI**: Documentación automática de la API.
- **Otras herramientas**:
  - **Java 17**: Lenguaje de programación.
  - **Maven**: Gestión de dependencias.
  - **Mockaroo**: Generación de datos de prueba.
  - **Draw.io**: Diagramas de arquitectura.

## Arquitectura

El sistema utiliza una **arquitectura hexagonal** para separar la lógica de negocio de la infraestructura, garantizando modularidad y testabilidad. Los componentes principales son:

- **Controladores REST**: Exponen endpoints como `/api/accounts` y `/api/transactions`.
- **Servicios**: Contienen la lógica de negocio (validaciones, procesamiento).
- **Repositorios**: Gestionan el acceso a PostgreSQL.
- **Adaptadores**: Conectan con RabbitMQ y PostgreSQL.
- **Colas**: Procesan transacciones asíncronamente.
- **Seguridad**: JWT para autenticación.
- **Auditoría**: Registro de operaciones.
- **Monitoreo**: Métricas con Spring Actuator, Prometheus y Grafana.

### Flujo de una Transacción
1. El cliente envía una solicitud a la API (por ejemplo, `POST /api/transactions`) con un token JWT.
2. El controlador valida el token y pasa la solicitud al servicio.
3. El servicio aplica validaciones (saldo, límites, fraudes) y encola la transacción en RabbitMQ.
4. Un consumidor procesa la transacción, actualiza saldos en PostgreSQL y registra un log de auditoría.
5. El cliente recibe un ID de transacción para seguimiento.


## Requisitos

- **Java 17+**
- **Maven**
- **Docker** y **Docker Compose**
- **Postman** (plan gratuito)

## Configuración del Proyecto

1. **Clona el repositorio**:
   ```bash
   git clone <url-repositorio>
   cd bank

## Arquitectura General

![upscalemedia-transformed](https://github.com/user-attachments/assets/1669f713-87eb-4f4f-a2c5-529dbdd31ba8)
