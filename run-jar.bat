cd C:\Users\Administrator\IdeaProjects\ControlPlanes\target
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=127.0.0.1:8000 .\ControlPlanes-1.0-SNAPSHOT.jar