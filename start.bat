@echo off
title Destino da Coroa

echo ==============================
echo   DESTINO DA COROA
echo ==============================
echo.

rem --- Tenta encontrar javac (JDK) ---

if not "%JAVA_HOME%"=="" (
    if exist "%JAVA_HOME%\bin\javac.exe" (
        set JAVAC="%JAVA_HOME%\bin\javac"
        set JAVA_RUN="%JAVA_HOME%\bin\java"
        goto compilar
    )
)

where javac > nul 2>&1
if %ERRORLEVEL% equ 0 (
    set JAVAC=javac
    set JAVA_RUN=java
    goto compilar
)

for /d %%i in ("C:\Program Files\Java\jdk*") do (
    if exist "%%i\bin\javac.exe" (
        set JAVAC="%%i\bin\javac"
        set JAVA_RUN="%%i\bin\java"
        goto compilar
    )
)
for /d %%i in ("C:\Program Files (x86)\Java\jdk*") do (
    if exist "%%i\bin\javac.exe" (
        set JAVAC="%%i\bin\javac"
        set JAVA_RUN="%%i\bin\java"
        goto compilar
    )
)

rem --- Tenta modo source-file (Java 11+) ---

echo Tentando modo source-file...
cd src
java --source 11 Main.java 2> nul
if %ERRORLEVEL% equ 0 (
    cd ..
    echo.
    pause
    exit /b
)
cd ..

rem --- Nada funcionou ---

echo.
echo Erro: javac nao encontrado e modo source-file falhou.
echo.
echo Voce tem instalado:
java -version 2>&1
echo.
echo Para compilar, instale o JDK em:
echo https://www.oracle.com/java/technologies/downloads/
echo.
echo Ou configure a variavel JAVA_HOME para a pasta do JDK.
echo.
pause
exit /b

:compilar
echo Compilando...
if not exist bin mkdir bin
dir /s /B src\*.java > sources.txt
%JAVAC% -d bin -encoding UTF-8 @sources.txt
if %ERRORLEVEL% neq 0 (
    echo Erro na compilacao.
    del sources.txt > nul 2>&1
    pause
    exit /b
)
del sources.txt

echo.
echo Iniciando o jogo...
echo.
%JAVA_RUN% -cp bin Main

echo.
pause
