# spring-jpa-mysql-example



```
CREATE DATABASE garage CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'sa'@'localhost' IDENTIFIED BY 'sa';

GRANT ALL PRIVILEGES ON garage.* TO 'sa'@'localhost';

FLUSH PRIVILEGES;

SET GLOBAL time_zone = '+2:00';
```