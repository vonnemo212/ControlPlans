cd %~dp0\target
pause
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=127.0.0.1:8001 .\ControlPlans-1.0-SNAPSHOT.jar