<p align="center">
  <!-- Backend -->
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="50" height="50"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring Boot" width="50" height="50"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" alt="PostgreSQL" width="50" height="50"/>


  <!-- Queues -->
  <img src="https://raw.githubusercontent.com/simple-icons/simple-icons/develop/icons/rabbitmq.svg" alt="RabbitMQ" width="50" height="50"/>

  <!-- Deployment -->
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-plain-wordmark.svg" alt="Docker Compose" width="50" height="50"/>

  <!-- Monitoring -->
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/prometheus/prometheus-original.svg" alt="Prometheus" width="50" height="50"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/grafana/grafana-original.svg" alt="Grafana" width="50" height="50"/>

  <!-- Testing -->
  <img src="https://raw.githubusercontent.com/simple-icons/simple-icons/develop/icons/postman.svg" alt="Postman" width="50" height="50"/>

  <!-- Others -->
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" alt="Maven" width="50" height="50"/>
</p>

## Sistema de Procesamiento de Transacciones Bancarias

El **Sistema de Procesamiento de Transacciones Bancarias** es un backend moderno desarrollado con **Spring Boot** que simula el núcleo de un sistema bancario. Permite gestionar cuentas bancarias y procesar transacciones (depósitos, retiros y transferencias) con validaciones en tiempo real, como verificación de saldos, límites diarios y detección básica de fraudes. Las transacciones se procesan de forma asíncrona usando **RabbitMQ**, garantizando escalabilidad y robustez. El sistema incluye autenticación con **JWT**, auditoría de operaciones y monitoreo con **Prometheus** y **Grafana**. Todo está implementado con herramientas **open source** para mantener los costos en cero, y la API se prueba con **Postman**.


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


## Arquitectura General

![upscalemedia-transformed](https://github.com/user-attachments/assets/1669f713-87eb-4f4f-a2c5-529dbdd31ba8)

## This is what a production incident looks like in Grafana:
<img width="1600" height="758" alt="image" src="https://github.com/user-attachments/assets/68d61829-6727-495b-92f1-6ddaa69aa42e" />

Captured during testing - shows JVM garbage collection and resource correlation.

**Key observations:**
- G1 Eden Space: Major GC triggered (drop from 70MB to ~0MB)
- Disk usage: Sudden increase coinciding with GC event
- DB connections: Spike to 1 active connection during the event
