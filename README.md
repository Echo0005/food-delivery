# Food Delivery App

## ¿Qué es?

Una App web con Spring Boot.


## ¿Qué hace?

Podemos realizar un CRUD (crear, leer, actualizar, borrar) con Productos y crear una Orden donde se incluyen productos. Además poder crear y autenticar Usuarios.


## ¿Cómo hago para iniciar este proyecto?

### Java
Necesitamos un entorno Java configurado. Este proyecto fue desarrollado con Java 17, por ende sería lo mínimo a tener instalado en nuestro ordenador.

### Base de datos
Necesitamos tener MySQL.

#### User y Password de MySQL
En el archivo `application.properties` debemos asignar el nombre de usuario y contraseña para ingresar a MySQL:

`spring.datasource.username=root`\
`spring.datasource.password=root`

#### Nombre de la base de datos
También debemos crear una database en MySQL llamada "fooddelivery" o si prefieren un nombre propio, pueden crear una database con el nombre que quieran pero deberán cambiar el nombre en el application.properties:

`spring.datasource.url=jdbc:mysql://localhost:3306/nombre-de-su-db`

### Iniciar Aplicación

#### Windows

Es más sencillo abrir un IDE (VS Code, Eclipse, IntelliJ) y darle a "run". ¿Por qué es más sencillo? ¿qué alternativa "abría"?, por Terminal, pero por alguna razón puede dar problemas de inicio, mejor dejarlo en manos de un IDE.

#### Linux
Instalar Maven.

En una terminal ejecutamos `mvn spring-boot:run`.


## Detallando funcionamiento

Tenemos 2 tipos de usuario: Admin y User.

La App gira en torno a Productos y Ordenes.

El Admin sería el encargado de manejar la App en el Food Delivery. Este administra los Productos (crearlos, editarlos y eliminaros) y de las Órdenes tan solo puede actualizar los estados "tomado" (para indicar que el pedido ha sido tomado) y "enviado" (para indicar que el pedido está en viaje).

Mientras los User son los clientes. Pueden ver la lista de producto, crear Órdenes y pueden editar sus órdenes siempre y cuando los pedidos estén sin figurar como "tomados" por la administración.

## Especificación de herramientas
- Java 17

- MySQL

- Spring Boot

    - JPA
    - JWT
    - Spring Security

- Lombok

## Detallado técnico

### Mapa relacional

**Usuarios**: Clase User, relacionada a clase Role y a Order (uno [User] a muchos [Role's] y uno [User] a muchos [Order's]).

**Product**: Clase Product relacionada a clase Order (muchos a muchos).

**Order**: Clase Order relacionada a Product y a User (muchos [Order's] a muchos [Product's] y muchos [Order's] a uno [User]).

### Acceso a los Endpoints

**Crear nuevo usuario y Login**: libres.

**Todos los demas**: Con "token en mano" solo se pasa. O sea, usuario logeado.

### Restricciones por Rol

**User**: Acceso a los endpoints de Order. De Product solo puede acceder a una lista completa de productos. Acceso a una lista con todas las órdenes propietarias (un usuario puede ver todas las órdenes que pidio).

**Admin**: Acceso a los endpoints de Products. De Order solo accede a actualizar estados booleanos y a obtener una lista de todas las órdenes.


### Ciclo

**Usuarios se logean**: reciben su Token.

**Admin**: Crea los Products.

**User**: Obtiene una lista de todos los Products. Crea una orden con los productos buscados.

**Admin**: Con un Get All a Order ve todos los pedidos. Toma una orden (dando "actualizar" a dicha orden a la API).

**User**: Ve que su pedido pasa a "tomado".

**Admin**: A las órdenes tomadas, le dara "enviado".

**User**: Ve que su pedido ha sido "enviado".

### Estructura de Product y Order

**Productos**: Objeto comun.

- **Order**:

    - Cuando creamos (POST): Contiene el Id y Username del Cliente creador, además de un array con los Id's de los Productos.
    - Cuando queremos ver (GET): Misma estructura que en el POST solo que llevara una List con objetos Product en sí.

### Recursos

**Postman:** en la carpeta `src\test\postman` se encuentra un Json con todas las request utilizadas para testear este proyecto.

Nota: Las request con "(ADMIN)" y/o "(USER)" indican el rol que debe tener el usuario propietario del Token a utilizar. O sea, si dice "(ADMIN)" al final, requerirá el Token de un admin y así para user.