d:
cd ./[path]/createDB
mvn clean compile assembly:single
cd ./target
java -jar createDB.jar "create" "127.0.0.1" "5432" "postgres" "12345678"

java -jar createDB.jar "clear" "127.0.0.1" "5432" "postgres" "12345678"