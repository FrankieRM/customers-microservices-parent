# Customer Microservice

### El microservicio de clientes provee una api rest
Las operaciones del API son los siguientes:

* /creacliente
    * Método HTTP: POST
    * Objeto de entrada: Customer
        * Nombre
        * Apellido
        * Edad
        * Fecha de nacimiento
        
* /kpideclientes
    * Método HTTP: GET
    * Objeto de salida: KPICustomers
        * Promedio de edad entre todos los clientes
        * Desviación estándar entre las edades de todos los clientes
        
* /listclientes
    * Método HTTP: GET
    * Objeto de salida: List<Customer>
        * Listado:
            * Nombre
            * Apellido
            * Edad
            * Fecha de nacimiento
            * Fecha probable de muerte