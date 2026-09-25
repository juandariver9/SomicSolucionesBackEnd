# KardexOne – API de facturación e inventario

API REST construida con **Spring Boot** para gestionar facturación con control de inventario (kardex) y cartera de clientes. Fue desarrollada como prueba técnica y tiene su interfaz web en [SomicSolucionesFrontEnd](https://github.com/juandariver9/SomicSolucionesFrontEnd).

## Funcionalidades

- **Clientes (NIT):** registro con documento, cupo de crédito y plazo de pago en días.
- **Artículos:** catálogo con código, laboratorio, costo, precio de venta y saldo en inventario.
- **Facturas:** al guardar una factura se asigna la fecha y se calcula el vencimiento según el plazo del cliente.
- **Cartera automática:** cada factura genera su registro de cartera con el valor pendiente.
- **Kardex:** cada línea de factura descuenta el saldo del artículo y valida que haya existencias suficientes.
- **Manejo global de errores:** respuestas JSON consistentes ante errores de negocio.

## Tecnologías

- Java 17
- Spring Boot 3.4 (Web, Data JPA)
- MySQL
- Maven

## Estructura

```
kardexone/src/main/java/com/pruebatecnica/kardexone/
├── Config/       # CORS
├── Controller/   # Endpoints REST
├── Exception/    # GlobalExceptionHandler
├── Model/        # Nit, Articulo, Factura, FacturaKardex, Cartera, TipoFactura
├── Repository/   # Repositorios JPA
└── Service/      # Reglas de negocio
```

## Endpoints

| Recurso | Ruta base | Operaciones |
|---|---|---|
| Clientes | `/api/nit` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |
| Artículos | `/api/articulo` | `GET`, `GET /{id}`, `GET /codigo/{codigo}`, `POST`, `DELETE /{id}` |
| Facturas | `/api/factura` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |
| Detalle / kardex | `/api/facturakardex` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |
| Cartera | `/api/cartera` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |

## Cómo ejecutarlo

1. Clona el repositorio y entra a la carpeta del proyecto:
   ```bash
   git clone https://github.com/juandariver9/SomicSolucionesBackEnd.git
   cd SomicSolucionesBackEnd/kardexone
   ```
2. Configura la conexión a tu base de datos MySQL en `src/main/resources/application.properties`.
3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
4. La API queda disponible en `http://localhost:8080/api`.

## Autor

**Juan David Rivero Romero** · [GitHub](https://github.com/juandariver9) · [LinkedIn](https://co.linkedin.com/in/juandariver9)
