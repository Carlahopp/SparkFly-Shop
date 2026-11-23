# SparkFly-Shop
Tienda en línea Full Stack desarrollada con Java, Spring Boot y frontend en HTML, CSS y JavaScript para gestión de catálogo de productos.
# SparkFlyShop 🎆

Aplicación web Full Stack desarrollada con **Spring Boot** (Java) y frontend en **HTML, CSS y JavaScript** para la gestión de catálogo de productos temáticos.

## Estructura del Proyecto
- `model/Product.java`: Modelo de datos del producto.
- `repository/ProductRepository.java`: Almacenamiento en memoria con productos precargados.
- `service/ProductService.java`: Lógica de negocio y validaciones con `StringUtils`.
- `controller/ProductController.java`: Controlador REST con endpoints `GET` y `POST`.
- `src/main/resources/static/`: Interfaz visual (`index.html`, `styles.css`, `app.js`).
- `requests.http`: Colección de peticiones HTTP para probar los endpoints.

## Endpoints de la API

### Consultar todos los productos
`GET http://localhost:8080/api/products`

### Consultar productos disponibles (stock > 0)
`GET http://localhost:8080/api/products/available`

### Consultar un producto por ID
`GET http://localhost:8080/api/products/{id}`

### Crear un nuevo producto
`POST http://localhost:8080/api/products`

**Ejemplo de cuerpo JSON:**
```json
{
  "id": 0,
  "name": "Speak Now Tour Hoodie",
  "category": "Apparel",
  "price": 750.00,
  "stock": 14
}
```
