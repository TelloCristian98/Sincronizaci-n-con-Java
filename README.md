# Sincronizacion-con-Java

# Cliente para Cristian's Algorithm en Java con Spring Boot

Este cliente se conecta a un servidor que proporciona la hora exacta y ajusta el tiempo local considerando la latencia de red. Está diseñado para ejecutarse como una aplicación Spring Boot y utiliza un cliente HTTP para realizar las solicitudes.

---

## **Estructura del Cliente**

1. **`TimeSyncClient`:**
   - Clase principal que realiza la conexión al servidor.
   - Calcula la latencia y ajusta la hora recibida del servidor.
   - Utiliza `RestTemplate` para realizar la solicitud HTTP.

2. **`TimeSyncRunner`:**
   - Clase que ejecuta automáticamente la sincronización al iniciar la aplicación.

3. **`application.properties`:**
   - Archivo de configuración donde puedes definir la URL del servidor.

---

## **Cómo Ejecutarlo**

1. **Requisitos previos:**
   - Tener Java 17 o superior instalado.
   - Instalar Maven (opcional si usas el wrapper `mvnw` incluido en el proyecto).
   - El servidor debe estar en ejecución en el puerto y URL configurados (por defecto: `http://localhost:8080/time`).

2. **Pasos para ejecutar el cliente:**
   - Importa el proyecto en IntelliJ IDEA.
   - Asegúrate de que el servidor esté corriendo.
   - Configura la URL del servidor en `application.properties` si es diferente:
     ```properties
     server.url=http://localhost:8080/time
     ```
   - Ejecuta la clase principal `TimeClientApplication` haciendo clic derecho y seleccionando **Run 'TimeClientApplication'**.

3. **Resultados esperados:**
   - En la consola, verás información similar a la siguiente:
     ```
     Iniciando sincronización de tiempo...
     Hora del servidor: 2025-01-21T15:45:30.123Z
     Latencia estimada: 15 ms
     Hora ajustada: 2025-01-21T15:45:30.138Z
     ```

---

## **Personalización y Optimización**

- **Cambiar la URL del servidor:** Modifica la constante en `TimeSyncClient` o usa el archivo `application.properties`.
- **Manejo de errores:** Agrega un bloque `try-catch` en el método `synchronizeTime` para manejar excepciones en caso de fallos de conexión.
- **Pruebas con alta latencia:** Usa herramientas como `Postman` o introduce retrasos artificiales en el servidor para simular condiciones reales.

---

## **Notas Adicionales**

- Este cliente está diseñado para ser sencillo y modular. Puedes expandirlo agregando:
  - Logs detallados.
  - Métricas de rendimiento para analizar la precisión del ajuste.
  - Integración con un sistema de sincronización de tiempo más robusto.

¡Ahora estás listo para ejecutar y probar el cliente en tu entorno!
