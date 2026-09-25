# KardexOne – API de facturación e inventario

API REST construida con **Spring Boot** para gestionar facturación con control de inventario (kardex) y cartera de clientes. Fue desarrollada como prueba técnica y tiene su interfaz web en [SomicSolucionesFrontEnd](https://github.com/juandariver9/SomicSolucionesFrontEnd).

## Funcionalidades

- **Clientes (NIT):** registro con documento, cupo de crédito y plazo de pago en días.
- **Artículos:** catálogo con código, laboratorio, costo, precio de venta y saldo en inventario.
- **Facturas:** al guardar una factura se asigna la fecha y se calcula el vencimiento según el plazo del cliente.
- **Control de cupo:** una venta no puede superar el cupo disponible del cliente (cupo menos cartera pendiente). Un cupo de 0 significa sin límite.
- **Cartera automática:** cada factura genera su registro de cartera con el valor pendiente, en la misma transacción.
- **Kardex:** las ventas descuentan inventario y las compras lo aumentan. Se valida que haya saldo suficiente y que no se venda por debajo del costo. El tipo de movimiento se toma de la factura guardada, no del cliente.
- **Validación y errores:** los datos de entrada se validan con Bean Validation y los errores se devuelven en JSON con el código HTTP adecuado.

## Tecnologías

- Java 17
- Spring Boot 3.4 (Web, Data JPA, Validation)
- MySQL (H2 en memoria para las pruebas)
- JUnit 5 y Mockito
- Maven

## Estructura

```
kardexone/src/main/java/com/pruebatecnica/kardexone/
├── Config/       # CORS
├── Controller/   # Endpoints REST
├── Exception/    # Excepciones de negocio y manejador global
├── Model/        # Nit, Articulo, Factura, FacturaKardex, Cartera, TipoFactura
├── Repository/   # Repositorios JPA
└── Service/      # Reglas de negocio
```

## Endpoints

| Recurso | Ruta base | Operaciones |
|---|---|---|
| Clientes | `/api/nit` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| Artículos | `/api/articulo` | `GET`, `GET /{id}`, `GET /codigo/{codigo}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| Facturas | `/api/factura` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |
| Detalle / kardex | `/api/facturakardex` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |
| Cartera | `/api/cartera` | `GET`, `GET /{id}`, `POST`, `DELETE /{id}` |

### Códigos de respuesta

| Código | Cuándo |
|---|---|
| `200` / `201` / `204` | Consulta, creación o eliminación exitosa |
| `400` | Datos inválidos o regla de negocio incumplida (saldo, cupo, precio) |
| `404` | El recurso no existe |
| `409` | Registro duplicado o con registros relacionados |

## Configuración

La conexión se configura con variables de entorno:

| Variable | Descripción | Valor por defecto |
|---|---|---|
| `DB_URL` | URL JDBC de MySQL | `jdbc:mysql://localhost:3306/kardexone?createDatabaseIfNotExist=true` |
| `DB_USERNAME` | Usuario de la base de datos | `root` |
| `DB_PASSWORD` | Contraseña de la base de datos | vacía |
| `CORS_ALLOWED_ORIGINS` | Orígenes permitidos del frontend, separados por coma | `http://127.0.0.1:5500,http://localhost:5500` |
| `JPA_DDL_AUTO` | Estrategia de esquema de Hibernate | `update` |
| `JPA_SHOW_SQL` | Mostrar el SQL en consola | `false` |

## Cómo ejecutarlo

1. Clona el repositorio y entra a la carpeta del proyecto:
   ```bash
   git clone https://github.com/juandariver9/SomicSolucionesBackEnd.git
   cd SomicSolucionesBackEnd/kardexone
   ```
2. Define las variables de entorno de la base de datos (o usa los valores por defecto con un MySQL local):
   ```bash
   export DB_URL="jdbc:mysql://localhost:3306/kardexone?createDatabaseIfNotExist=true"
   export DB_USERNAME="root"
   export DB_PASSWORD="tu_contraseña"
   ```
3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
4. La API queda disponible en `http://localhost:8080/api`.

## Pruebas

```bash
./mvnw test
```

Incluye pruebas unitarias de las reglas de facturación y kardex (saldo, cupo, precio mínimo, vencimiento) y una prueba de arranque con H2 en memoria.

## Autor

**Juan David Rivero Romero** · [GitHub](https://github.com/juandariver9) · [LinkedIn](https://co.linkedin.com/in/juandariver9)
