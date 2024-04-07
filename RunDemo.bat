@echo off

rem Ellenőrizzük, hogy a Docker fut-e
docker --version >nul 2>&1
if %errorlevel% neq 0 (
  echo Docker nincs telepítve vagy nem fut. Kérlek telepítsd és indítsd el a Docker-t.
  exit /b 1
)

rem Ellenőrizzük, hogy a Docker Compose fut-e
docker-compose --version >nul 2>&1
if %errorlevel% neq 0 (
  echo Docker Compose nincs telepítve. Kérlek telepítsd.
  exit /b 1
)

rem Docker konténerek indítása
echo Docker konténerek indítása...
docker-compose up -d

echo Docker konténerek sikeresen elindítva.
