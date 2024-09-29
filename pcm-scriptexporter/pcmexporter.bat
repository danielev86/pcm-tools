@echo off
set typeCommand=%1
set pcmUser=%2
if %typeCommand%==import goto importData
if %typeCommand%==export goto exportData

echo Not found.
goto commonexit

:importData
set pathData=Pro Cycling Manager 2024\Cloud\%pcmUser%\%3
echo %pathData%
SQLiteExporter.exe -import "%pathData%"
goto commonexit

:exportData
set pathData=Pro Cycling Manager 2024\Cloud\%pcmUser%\%3.cdb
echo %pathData%
SQLiteExporter.exe -export "%pathData%"
goto commonexit

:commonexit
pause
