@echo off
echo Setting up the College Events Database...
echo.
mysql -u root < database_setup.sql
if %ERRORLEVEL% EQU 0 (
    echo Database setup completed successfully!
) else (
    echo Failed to set up database. Make sure MySQL is running and the root password is correct.
)
pause