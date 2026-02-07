$BASE_URL = "http://localhost:8080"
$USERNAME = "testuser_$(Get-Random)"
$PASSWORD = "Test@12345"

Write-Host "=== PRUEBAS DE API BANKING ===" -ForegroundColor Cyan

Write-Host "`n1. REGISTRANDO USUARIO: $USERNAME" -ForegroundColor Yellow
$registerRes = curl.exe -s -X POST "$BASE_URL/api/auth/register" `
  -H "Content-Type: application/json" `
  -d "{`"username`":`"$USERNAME`",`"password`":`"$PASSWORD`"}"

Write-Host "Respuesta Registro:" -ForegroundColor Green
Write-Host $registerRes

$jwtToken = ($registerRes | ConvertFrom-Json).jwtToken
if (-not $jwtToken) {
    Write-Host "ERROR: No se obtuvo JWT en registro" -ForegroundColor Red
    exit
}

Write-Host "`nJWT obtenido: $($jwtToken.Substring(0, 50))..." -ForegroundColor Green

Write-Host "`n2. CREANDO CUENTA BANCARIA" -ForegroundColor Yellow
$cuentaRes = curl.exe -s -X POST "$BASE_URL/api/cuentas" `
  -H "Authorization: Bearer $jwtToken" `
  -H "Content-Type: application/json" `
  -d "{`"propietario`":`"$USERNAME`",`"saldo`":5000.00}"

Write-Host "Respuesta Crear Cuenta:" -ForegroundColor Green
Write-Host $cuentaRes

Write-Host "`n3. LISTANDO CUENTAS" -ForegroundColor Yellow
$cuentasRes = curl.exe -s -X GET "$BASE_URL/api/cuentas" `
  -H "Authorization: Bearer $jwtToken"

Write-Host "Respuesta Cuentas:" -ForegroundColor Green
Write-Host $cuentasRes

Write-Host "`n4. HEALTH CHECK (sin JWT)" -ForegroundColor Yellow
$healthRes = curl.exe -s -X GET "$BASE_URL/actuator/health"
Write-Host "Health: $healthRes" -ForegroundColor Green

Write-Host "`n5. METRICAS PROMETHEUS" -ForegroundColor Yellow
Write-Host "URL: $BASE_URL/actuator/prometheus" -ForegroundColor Cyan

Write-Host "`n=== PRUEBAS COMPLETADAS ===" -ForegroundColor Cyan
