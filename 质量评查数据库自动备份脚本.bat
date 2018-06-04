@echo off
echo =====================================================
echo  Windows环境下Oracle数据库的自动备份脚本 配合系统计划任务
echo  1. 使用当前日期命名备份文件。
echo  2. 自动删除10天前的备份。
echo =====================================================
::以“YYYYMMDD”格式取出当前时间。
set BACKUPDATE=%date:~0,4%%date:~5,2%%date:~8,2%
set CURTIME=%time:~0,2%
REM 小时数如果小于10，则在前面补0
if "%CURTIME%"==" 0" set CURTIME=00
if "%CURTIME%"==" 1" set CURTIME=01
if "%CURTIME%"==" 2" set CURTIME=02
if "%CURTIME%"==" 3" set CURTIME=03
if "%CURTIME%"==" 4" set CURTIME=04
if "%CURTIME%"==" 5" set CURTIME=05
if "%CURTIME%"==" 6" set CURTIME=06
if "%CURTIME%"==" 7" set CURTIME=07
if "%CURTIME%"==" 8" set CURTIME=08
if "%CURTIME%"==" 9" set CURTIME=09
set CURTIME=%CURTIME%%time:~3,2%%time:~6,2%

::设置用户名、密码和要备份的数据库。
set USER=ZLPC
set PASSWORD=zlpc
set DATABASE=orcl

::创建备份目录。
set BACKUP_DIR=C:\datapump
if not exist "%BACKUP_DIR%\data\%BACKUPDATE%"     mkdir %BACKUP_DIR%\data\%BACKUPDATE%
rem if not exist "%BACKUP_DIR%\log\%BACKUPDATE%"      mkdir %BACKUP_DIR%\log\%BACKUPDATE%
set DATADIR=%BACKUP_DIR%\data\%BACKUPDATE%
rem set LOGDIR=%BACKUP_DIR%\log\%BACKUPDATE%

expdp %USER%/%PASSWORD% schemas=%USER% directory=dmpdir DUMPFILE=%USER%_%BACKUPDATE%%CURTIME%.dmp logfile=%USER%_%BACKUPDATE%%CURTIME%.log  compression=all parallel=4 CLUSTER=N

if exist "%BACKUP_DIR%\%USER%_%BACKUPDATE%%CURTIME%.dmp" move %BACKUP_DIR%\%USER%_%BACKUPDATE%%CURTIME%.dmp %DATADIR%\%USER%_%BACKUPDATE%%CURTIME%.dmp

if exist "%BACKUP_DIR%\%USER%_%BACKUPDATE%%CURTIME%.log" move %BACKUP_DIR%\%USER%_%BACKUPDATE%%CURTIME%.log %DATADIR%\%USER%_%BACKUPDATE%%CURTIME%.log


::删除30天前的备份。
forfiles /p "%DATADIR%" /s /m *.* /d -10 /c "cmd /c del @path"
rem forfiles /p "%LOGDIR%" /s /m *.* /d -10 /c "cmd /c del @path"
pause
exit