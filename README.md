Desafío ForumHub
================

Características del proyecto:
-----------------------------

.

Para desarrollar este proyecto se usaron las siguientes tecnologías

*   Java como lenguaje de programación en su version 17
*   Lombok para evitar la escritura de código redundante
*   Spring boot
    *   Spring data JPA para las interacciones con la base de datos
    *   Spring web para crear esta aplicacioón web y manejar todas las solicitudes http de la misma
    *   Spring validation usado para validar las peticiones http que hace la apicación
    *   Spring security usado para poner la capa de seguridad que usa este proyecto validando la autenticidad de los token que se le asignan a los usuarios registrados de la misma
*   Java jwt de auth0 para la creación y validacioón de los token que se les a asigna a cada usuario para poder realizar cierta peticione en le sitio
*   flyway-core y flyway-mysql se utilizan para gestionar las migraciones de base de datos de manera automatizada, siendo flyway-mysql opcional y reemplazable por si se desea usar una base de datos distinta para correr el proyecto.

### Endpoints del proyecto:

Este proyecto simula ser una de especie de foro donde se pueden hacer publicaciones de diferentes tipos, mediante programas como postman o insomnia, siempre y cuando cumplan con las sintaxís requerida para poder hacer publicaciones, por ejemplo:

*   "/tópicos" solicitud tipo post: se debe usar la siguiente sintaxis para poder realizar esta publicación de manera correcta:
    *   {  
        "titulo": debe contener una cadena no mayor a los 255 caracteres,  
        "mensaje": debe contener una cadena no mayor a los 255 caracteres1,  
        "estado": una cadena que puede ser "ABIERTO", "CERRADO", en función del "estado" de la publicación, el cual se puede modificar con otro endpoint que se expondrá más adelante,  
        "autor": una cadena que no sea mayor a los 255 caracteres,  
        "curso": una cadena que no sea mayor a los 255 caracteres  
        }
    *   también se imprime una fecha de la publicación pero esa es automática y se guarda la fecha en la que se realizó la petición
*   "/tópicos" solicitud tipo get: enlista todos los registros (post) de la tabla que almacenas los tópicos
*   "/tópicos/id" se usa para visualizar los datos de un tópico más a detalle, reemplazando el "id" de la dirección url con el id que aparee al listar todos los tópicos
*   "/tópicos" solicitud tipo put: se usa para actualizar los datos de un tópico mediante la siguiente estructura:
    *   {  
        "id": se le pasa el id de la publicación que se desea modificar,  
        "titulo": una cadena que no sea mayor a los 255 caracteres que reemplazará a la anterior,  
        "mensaje": una cadena que no sea mayor a los 255 caracteres que reemplazará a la anterior,  
        "fecha": una fecha con el siguiente formato "2024/02/01 05:35:25", indicando tambien la hora, minutos y segundos, introducido para actualizar la fecha a la que se haya modificadoo la publicación o dejarla intacta,  
        "estado": una cadena que puede ser "ABIERTO", "CERRADO", en función del "estado" de la publicación,  
        "autor": una cadena que no sea mayor a los 255 caracteres que reemplazará a la anterior,  
        "curso": una cadena que no sea mayor a los 255 caracteres que reemplazará a la anterior,  
        }
*   "tópicos/ocultar-post/{id}" solicitud tipo delete: se usa para cambiar el estado de la publicación a "OCULTO" lo que hará que se oculte de la lista general de todas las publicaciones
*   "/tópicos/id" solicitud de tipo delete: se reempaza por la parte "id" de la url por la id que se lista al momento de taer todos las publicaciones, realizar esta solicitud, hara que la publicación se borre definitivamente de la tabla donde estaba guardado su registro

### Enpoints de la parte de los usuarios:

*   "/login/registrar" solicitud tipo post: se usa para registrar un nuevo usuario en la base de datos, con el cual después será posible hacer "login" al sitio, se debe enviar un json con el siguiente formato:
    *   {  
        "correo": una cadena con formato de email de máximo 255 caracteres,  
        "usuario": una cadena de 255 caráteres como máximo ,  
        "contrasena": una cadena de 255 caráteres como máximo  
        }
*   "/login" solicitud tipo post: usado para acceder al y obtener un token que autoriza el acceso a los enpoints relacionados a los tópicos, se debe enviar un json son el siguiente formato:
    *   {  
        "usuario": el nombre de usuario de alguien ya registrado,  
        "contrasena": la contrasena de ese usuario ya registrado  
        }
