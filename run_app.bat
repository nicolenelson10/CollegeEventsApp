@echo off
echo Starting College Events Application...
echo.
echo Make sure MySQL is running before starting the application!
echo.
pause
java -cp "bin;lib/mysql-connector-j-8.2.0.jar" com.collegeeventsapp.AppLauncher
pause