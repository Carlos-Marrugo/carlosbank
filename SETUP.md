# Guía de Setup - Banco API

Este proyecto requiere PostgreSQL, RabbitMQ, Prometheus y Grafana para ejecutarse localmente.

## Opción 1: Setup Local con Docker (RECOMENDADO)

### Prerrequisitos
- Docker Desktop instalado y corriendo
- Docker Compose incluido en Docker Desktop

### Pasos

#### 1. Levantar servicios de infraestructura
```bash
docker-compose up -d
```

Esto levantará:
- **PostgreSQL** en `localhost:5432` (usuario: postgres, password: postgres)
- **RabbitMQ** Management en `localhost:15672` (usuario: guest, password: guest)
- **Prometheus** en `localhost:9090`
- **Grafana** en `localhost:3000`

#### 2. Verificar que los contenedores están corriendo
```bash
docker-compose ps
```

Deberías ver 4 contenedores activos.

#### 3. Ejecutar la aplicación
Abre VS Code y en la paleta de comandos (`Ctrl+Shift+D`):
- Selecciona `Launch BankApplication (Local Dev)`
- Presiona F5 o haz clic en "Start Debugging"

La app estará disponible en `http://localhost:8080`

### Endpoints para probar

```bash
# Health Check
curl http://localhost:8080/actuator/health

# Crear usuario
curl -X POST http://localhost:8080/api/auth/registro \
  -H "Content-Type: application/json" \
  -d '{"username": "test", "password": "test123"}'

# Autenticarse
curl -X POST http://localhost:8080/api/auth/autenticar \
  -H "Content-Type: application/json" \
  -d '{"username": "test", "password": "test123"}'
```

---

## Opción 2: Usar Base de Datos en la Nube (Neon)

Si prefieres usar Neon en lugar de Docker local:

### Pasos

1. **Crear cuenta en Neon** (https://neon.tech)
2. **Copiar credenciales de tu proyecto Neon**
3. **Actualizar `.env`** con credenciales de Neon:
```env
DB_HOST=your-project.neon.tech
DB_PORT=5432
DB_NAME=neondb
DB_USER=neondb_owner
DB_PASS=your-secure-password
```

4. **Levantar solo RabbitMQ local**:
```bash
docker-compose up -d rabbitmq prometheus grafana
```

5. **Ejecutar la app** seleccionando `Launch BankApplication (Neon Cloud)` en VS Code

---

## Verificar Servicios

### PostgreSQL (Local o Neon)
```bash
# Conectar localmente
psql -h localhost -U postgres -d bank

# Query de prueba
SELECT * FROM usuarios;
```

### RabbitMQ Management
Abre en tu navegador: `http://localhost:15672`  
**Usuario:** guest  
**Contraseña:** guest

### Prometheus
`http://localhost:9090`

### Grafana
`http://localhost:3000`  
**Usuario:** admin  
**Contraseña:** admin (cambiar en primer login)

---

## Troubleshooting

### Error: "connection refused" en PostgreSQL
- Verifica que Docker está corriendo: `docker ps`
- Verifica que el contenedor de postgres existe: `docker-compose ps postgres`
- Si no existe, ejecuta: `docker-compose up -d postgres`

### Error: "authentication failed for user postgres"
- Asegúrate de que `.env` tiene las credenciales correctas
- Las credenciales por defecto en Docker son: usuario `postgres`, contraseña `postgres`

### Error de RabbitMQ
- Revisar que el puerto 5672 no esté en uso: `lsof -i :5672` (en macOS/Linux)
- En Windows: `netstat -ano | findstr :5672`

### Limpiar todo (borrar volúmenes y contenedores)
```bash
docker-compose down -v
```

---

## Compilar y Testear

```bash
# Limpiar y compilar
mvn clean package

# Ejecutar pruebas
mvn test

# Ejecutar con Maven (sin IDE)
mvn spring-boot:run
```

---

## Variables de Entorno

Ver `.env.example` para todas las variables disponibles y sus valores por defecto.

**Importante:** Nunca commitees `.env` con credenciales reales. Usa `.env.example` como plantilla.

