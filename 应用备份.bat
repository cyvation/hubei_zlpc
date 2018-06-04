@echo off
rem Get today's year, month, day
for /f "tokens=1-3 delims=/ " %%a in ('date /t') do (set year=%%a& set month=%%b& set day=%%c)

rem Modify the following line for your environment
set TARGET=D:\tomcat\apache-tomcat-9.0.0.M3\webapps\cqe\WEB-INF
set TARGETNAME=classes
set BACKUPDIR=E:\backup
if not exist %BACKUPDIR% mkdir %BACKUPDIR%

echo Y | xcopy %TARGET%\%TARGETNAME%\com\*.* %BACKUPDIR%\%TARGETNAME%_%year%%month%%day%\com\ /S /I /Y
echo Y | xcopy %TARGET%\%TARGETNAME%\mapper\*.* %BACKUPDIR%\%TARGETNAME%_%year%%month%%day%\mapper\ /S /I /Y
echo Y | xcopy %TARGET%\%TARGETNAME%\STATIC %BACKUPDIR%\%TARGETNAME%_%year%%month%%day%\static\ /s /e /y /Exclude:list_of_exclusions.txt
REM net start mysql4
REM echo start mysql version 4 >> %LOGFILE%

rem Compress the target directory
rem zip a %BACKUPFILE% %BACKUPSOURCEFILE%



REM delete file
rd %BACKUPDIR%\%TARGETNAME% /S /q

set TARGET=
set TARGETNAME=
set BACKUPDIR=
rem set BACKUPFILE=
set LOGFILE=

:end
