# Tienda de Ropa con Spring Boot, Thymeleaf y PostgreSQL

## Descripción del Proyecto

## Estructura del Proyecto

- Modelo de Datos:
Creamos entidades como Producto, Usuario y Pedido.
Definimos relaciones entre ellas (por ejemplo, un usuario puede tener varios pedidos).

- Controladores:
Implementamos controladores para manejar las rutas y las acciones del usuario.
Por ejemplo, un controlador para mostrar la lista de productos y otro para procesar pedidos.

- Vistas Thymeleaf:
Creamos plantillas HTML utilizando Thymeleaf.
Usamos etiquetas Thymeleaf para mostrar datos dinámicos (por ejemplo, la lista de productos).

- Estilos y Scripts:
Creamos archivos CSS para dar estilo a nuestras páginas.
Utilizamos JavaScript con Scroll Reveal para animaciones al desplazarse por la página.
Configuración de la Base de Datos

- ### Conexión a PostgreSQL:
Configuramos la conexión a la base de datos en el archivo application.properties.
Definimos la URL, el nombre de usuario y la contraseña.

- Creación de Tablas:
Creamos tablas para almacenar productos, usuarios y pedidos.
Especificamos las relaciones entre las tablas (claves primarias y foráneas).
