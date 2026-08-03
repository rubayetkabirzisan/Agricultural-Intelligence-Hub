@echo off
echo =========================================
echo  Building Agricultural Intelligence Hub
echo =========================================
echo.
echo [1/2] Compiling and building custom Java Runtime (jlink)...
call mvnw.cmd clean compile javafx:jlink

echo.
echo [2/2] Generating Windows .exe Installer (jpackage)...
jpackage --type exe ^
  --dest target/installer ^
  --name "AgriHub" ^
  --module com.rubaet.agrihub/com.rubaet.agrihub.Start ^
  --runtime-image target/app ^
  --win-shortcut ^
  --win-menu ^
  --app-version "2.0.0"

echo.
echo =========================================
echo  Done! Installer located at:
echo  target/installer/AgriHub-2.0.0.exe
echo =========================================
pause
