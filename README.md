# inventory-manager-services

## Docker

####  Generar imagen
    docker build -t product-services:latest .
    docker build -t inventory-services:latest .

####  Generar los container:

    docker run -t -d -p 8080:8080 product-services:latest
    docker run -t -d -p 8081:8081 inventory-services:latest

##  Docker compose

####  Generar imagenes

    docker compose build

####  Generar los containers:

    docker compose up

## Documentación

#### Microsercicio de productos

    http://localhost:8080/product/swagger-ui/index.html#/

#### Microsercicio de inventario

    http://localhost:8081/inventory/swagger-ui/index.html#/

### Generar token de autenticación

* Llevandolo a un caso real este servicio iria en otro microservicio
* Implemente JWT porque permite solicitudes entre servicios de forma segura cumpliendo con el objetivo de autenticación entre microservicios

    ```java
    curl --location 'localhost:8080/product/login?username=admin' \
        --header 'Content-Type: application/json' \
        --data '{
        "username": "admim",
        "password": "1020"
        }'

### Curl

#### product-services

* Crear un nuevo producto.

    ```java
    curl --location 'localhost:8080/product/products' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbSIsImlhdCI6MTc2MDk1MzQ3MSwiZXhwIjoxNzYwOTU3MDcxfQ.go8a8wpmkYUYJ8CW-5iVw1KgzVe43kI0Hyj-fHgeGP0' \
    --header 'Content-Type: application/json' \
    --data '{
    "name": "Pañito humedos",
    "price": 10000
    }'


* Obtener un producto específico por ID.

    ```java
    curl --location 'localhost:8080/product/products/prod_65cf8dd9-5c2f-465e-b6be-f90becc27950' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbSIsImlhdCI6MTc2MDk0NDU1OSwiZXhwIjoxNzYwOTQ4MTU5fQ.ksVCJlO5vcFZmNd_zn7BtIgi-JaElihw7rVrIwQqkYs'

* Eliminar un producto por ID.

    ```java
    curl --location --request DELETE 'localhost:8080/product/products/prod_3061bba4-396d-491c-940d-12ae715a8a05' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbSIsImlhdCI6MTc2MDkxMTgxMSwiZXhwIjoxNzYwOTE1NDExfQ.vP9BcnQQ0bATemb7jq9znP72Dztg1yYt_wxVN6YoCc0'


* Actualizar un producto por ID.

    ```java
     curl --location --request PUT 'localhost:8080/product/products/prod_3061bba4-396d-491c-940d-12ae715a8a05' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbSIsImlhdCI6MTc2MDkxMTgxMSwiZXhwIjoxNzYwOTE1NDExfQ.vP9BcnQQ0bATemb7jq9znP72Dztg1yYt_wxVN6YoCc0' \
    --header 'Content-Type: application/json' \
    --data '{
    "name": "pañitos humedos aloe",
    "price": 12000
    }'


* Listar todos los productos con paginación simple.

    ```java
    curl --location 'localhost:8080/product/products?page=0&pageSize=2&=null' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbSIsImlhdCI6MTc2MDkxMTgxMSwiZXhwIjoxNzYwOTE1NDExfQ.vP9BcnQQ0bATemb7jq9znP72Dztg1yYt_wxVN6YoCc0'
        
#### inventory-services

* Consultar la cantidad disponible de un producto específico por ID

    ```java
    curl --location 'localhost:8081/inventory/stock/prod_63d2100e-7b8e-4d85-8e7d-9ddc11d05064' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbSIsImlhdCI6MTc2MDk1MzQ3MSwiZXhwIjoxNzYwOTU3MDcxfQ.go8a8wpmkYUYJ8CW-5iVw1KgzVe43kI0Hyj-fHgeGP0'

#### Product-services#### Product-services


## Base de datos

Seleccione una base de datos en memoria debido a su rapidez, facil de usar y perfecta para simular operaciones de almacenamiento temporal,
permitiendome mayor enfoque en la lógica de negocio y pruebas.

### inventory

    url: jdbc:h2:mem:inventory
    username: inventory

    http://localhost:8081/inventory/h2-console/

Ejemplo para insertar en la tabla inventory

    INSERT INTO INVENTORY (INVENTORY_ID, PRODUCT_ID, QUANTITY)
    VALUES ('inv_ee893dc4-625d-42f6-82b7-965efac71a36', 'prod_ee893dc4-625d-42f6-82b7-965efac71a36', 20);


### Product

    url: jdbc:h2:mem:product
    username: product

    http://localhost:8080/product/h2-console/l

## Diagramas

### Diagrama Arquitectura hexagonal

La Arquitectura hexagonal permite la separacion de responsabilidades, aislando la logica de negocio, facilitando la pruebas unitaria y logrando una arquitectura limpia
permitiendo flexibilidad y escalabilidad

                    +------------------------+
                    |      Cliente / UI      |
                    +------------------------+
                              |
                              v
                   +------------------------+
                   |      Aplicación        |  <-- Controllers / API
                   | (REST Controllers)    |
                   +------------------------+
                              |
                              v
                   +------------------------+
                   |        Dominio         |  <-- Lógica de negocio
                   | (Servicios)            |
                   +------------------------+
                              |
            ------------------------------------------
            |                                        |
            v                                        v
    +----------------------+                +-----------------------+
    |    Infraestructura   |                |    Infraestructura    |
    | (Repositorio Product)|                | (Repositorio Inventory|
    | - Acceso a BD        |                | - Acceso a BD         |
    |                      |                | - Integración externa |
    +----------------------+                +-----------------------+

### Diagrama de interacción entre servicios


    Cliente       Inventory Service       Product Service       Repositorios
    |                  |                     |                  |
    |----GET /inventory/stock               |                  |
    |                  |---GET /products/{id}|                  |
    |                  |                     |---Consulta DB----|
    |                  |                     |<-----------------|
    |                  |<---Producto existe--|                  |
    |                  |---Consulta inventario en memoria------>|
    |                  |<--------------------|                  |
    |<---Inventario----|                     |                  |


## Coverage Test unitarios

    product Services
    Total	203 of 2.487	91 %
    
    inventory Services
    Total	284 of 1.412	79 %


