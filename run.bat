@echo off
chcp 65001 > nul
set SRC_DIR=src
set BIN_DIR=bin

echo ==================================================
echo  Proyecto Integrador I - Estructuras de Datos UNJu
echo ==================================================

echo [1/3] Limpiando binarios anteriores...
if exist %BIN_DIR% rd /s /q %BIN_DIR%
mkdir %BIN_DIR%

echo [2/3] Compilando codigo fuente...
dir /s /b %SRC_DIR%\*.java > sources.txt
javac -d %BIN_DIR% @sources.txt
del sources.txt

if %ERRORLEVEL% EQU 0 (
    echo [3/3] Compilacion exitosa. Ejecutando programa...
    echo --------------------------------------------------
    java -cp %BIN_DIR% main.Principal
) else (
    echo ERROR: Fallo la compilacion.
    pause
    exit /b 1
)

pause
