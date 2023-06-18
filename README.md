# Challenge-ONE-Foro-Alura-API
> Proyecto realizado para el Challenge ONE Back Foro Alura, el cual consiste en desarrollar una API para el foro de Alura, donde los alumnos pueden consultar sus dudas mediante la creación de un Tópico y un mensaje y los demás usuarios pueden realizar comentarios sobre éste. La API sigue una arquitectura REST y utiliza diversas bibliotecas para mejorar su funcionalidad y seguridad.

## :pencil: Funcionalidades
- CRUD: La API tendrá endpoints para procesar request del tipo CRUD (Create, Read, Update, Delete) para las entidades Topico, Respuesta, Autor
- Autenticación y Registro: La API utiliza tokens JWT para autenticar a los usuarios y permite el registro de nuevos usuarios.
- Protección de API: Se ha implementado seguridad en la API utilizando Spring Security para proteger los endpoints y garantizar que solo los usuarios autenticados puedan acceder a ellos.
- Base de datos: La aplicación utiliza MySQL como base de datos para almacenar la información de las publicaciones, comentarios , autores y usuarios permitidos para la autenticación así como los roles de éstos.

## :hammer: Tecnologías utilizadas
Las principales tecnologías utilizadas en este proyecto son:

<table align="center">
<tr>
    <td align="center" width="100">
      <a href="#">
        <img src="https://www.vectorlogo.zone/logos/java/java-icon.svg" width="50" height="50" alt="JAVA"/>
      </a>
      <br>JAVA
    </td>
    <td align="center" width="100">
      <a href="#">
        <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" width="50" height="50" alt="Spring Boot"/>
      </a>
      <br>Spring Boot
    </td>
    <td align="center" width="100">
        <img src="https://maven.apache.org/favicon.ico" width="50" height="50" alt="Maven"/>
      <br>Maven
    </td>
  </tr>
  <tr>
     <td align="center" width="100">
       <a href="#">
        <img src="https://www.vectorlogo.zone/logos/mysql/mysql-icon.svg"  width="50" height="50" alt="MySQL" />
       </a>
       <br>MySQL
    </td>
    <td align="center" width="100">
       <a href="#">
        <img src="https://jwt.io/img/favicon/android-icon-192x192.png" width="50" height="50" alt="JWT" />
       </a>
       <br>JWT
    </td>
     <td align="center" width="100">
       <a href="#">
        <img src="https://www.jetbrains.com/favicon.ico" width="50" height="50" alt="Intellij" />
       </a>
       <br>Intellij
      </td>
  </tr>
  <tr>
     <td align="center" width="100">
       <a href="#">
        <img src="https://www.postman.com/_ar-assets/images/favicon-1-48.png" width="50" height="50" alt="Postman" />
       </a>
       <br>Postman
     </td>
     <td align="center" width="100">
       <a href="#">
        <img src="https://labs.mysql.com/common/themes/sakila/favicon.ico" width="50" height="50" alt="MySQLWorkbench" />
       </a>
       <br>MySQL Workbench
     </td>
     <td align="center" width="100">
       <a href="#">
        <img src="https://github.com/favicon.ico" width="50" height="50" alt="GitHub" />
       </a>
       <br>GitHub
     </td>
  </tr>
</table>

## :wrench: Librerías
- Spring Data JPA: Biblioteca de Spring para trabajar con bases de datos relacionales mediante el uso de objetos y consultas JPQL.
- Spring Security: Framework de seguridad de Spring que proporciona autenticación y autorización en la aplicación.
- MySQL: Sistema de gestión de bases de datos relacional utilizado para almacenar la información del foro.
- Model Mapper: Biblioteca utilizada para mapear objetos DTO (Data Transfer Objects) a entidades y viceversa.
- Spring Boot Starter Validation: Biblioteca de validación utilizada para realizar validaciones en los datos de entrada.
- Lombok: Biblioteca que agiliza el desarrollo eliminando la necesidad de escribir código repetitivo, como getters, setters y constructores.
- JWT (JSON Web Tokens): Estándar abierto basado en JSON utilizado para transmitir información segura entre partes en forma de tokens.

## :electric_plug: Configuración
Clona el repositorio del proyecto.
Importa el proyecto en tu IDE preferido.
Configura las credenciales de la base de datos en el archivo application.properties.
Ejecuta la aplicación y accede a la API a través de http://localhost:8080.
