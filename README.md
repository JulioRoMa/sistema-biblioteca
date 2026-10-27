# Sistema de Biblioteca - Práctica JavaFX + XAMPP

## Requisitos previos
1. Inicia **XAMPP** y levanta el servicio **MySQL/MariaDB** (puerto por defecto `3306`).
2. Abre `http://localhost/phpmyadmin`.

## Crear base de datos y tabla
1. En phpMyAdmin, abre la pestaña **SQL**.
2. Ejecuta el script:
   - `/home/runner/work/sistema-biblioteca/sistema-biblioteca/sql/biblioteca_db.sql`

## Credenciales JDBC
La conexión está centralizada en:
- `/home/runner/work/sistema-biblioteca/sistema-biblioteca/src/main/java/com/biblioteca/DatabaseConnection.java`

Valores por defecto:
- Host: `localhost`
- Puerto: `3306`
- Base de datos: `biblioteca_db`
- Usuario: `root`
- Contraseña: vacía

Si tu instalación usa otras credenciales, edita esa clase.

## Ejecutar con Maven o NetBeans
- Compilar:
  ```bash
  mvn -q -DskipTests compile
  ```
- Ejecutar JavaFX:
  ```bash
  mvn javafx:run
  ```
También puedes abrir el proyecto Maven en NetBeans y ejecutar la clase principal `com.biblioteca.App`.
