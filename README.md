# Projekt Neve

dpddemo

## Rövid Leírás

Ez a projekt egy Spring Boot alapú Java alkalmazás, amely bemutat egy példaalkalmazást.

## Telepítés és Indítás

Az alkalmazás Docker konténerekben fut, így a futtatáshoz Docker telepítése szükséges.

### Docker Telepítése

Telepítsd a Docker-t a számítógépedre a [hivatalos Docker webhelyről](https://docs.docker.com/get-docker/).

### Projekt Letöltése

Klónozd le a projektet a GitHubról

### Projekt Indítása

1. A RunDemo.bat fájl futtatásával

VAGY

1. Lépj be a projekt gyökérkönyvtárába a parancssorban vagy terminálban.

2. Futtasd a következő parancsokat a Docker konténerek indításához:

```bash
docker-compose build   # Az alkalmazás képének létrehozása (opcionális, ha még nincs)
docker-compose up -d   # Alkalmazás és adatbázis konténer indítása
