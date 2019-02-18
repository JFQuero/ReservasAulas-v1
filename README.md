# Tarea Reservas de Aulas
## Profesor: JosÃ© RamÃ³n JimÃ©nez Reyes
## Alumno: Juan Fernandez Quero

Desde el IES Al-Ã�ndalus nos acaban de comentar que por favor eliminemos la restricciÃ³n de tamaÃ±o en los datos de la aplicaciÃ³n. Por lo que decidimos utilizar estructuras dinÃ¡micas de datos, en concreto Listas. Para las diferentes clases del modelo que contienen las colecciones de objetos del dominio (las que estÃ¡n incluidas en el paquete **dao**) deberemos sustituir los **Array** por **ArrayList** y, cÃ³mo no, ajustar los diferentes mÃ©todos para que sigan haciendo lo mismo que antes, pero utilizando las nuevas estructuras de datos. Como observarÃ¡s, los mÃ©todos privados que tenÃ­amos antes desaparecen ya que ahora no serÃ¡n necesarios.

El diagrama de clases queda como te muestro a continuaciÃ³n y poco a poco te irÃ© explicando los diferentes pasos a realizar:

![Diagrama de clases para reservasaulas](src/main/resources/reservasAulas.png)

He subido a GitHub un esqueleto de proyecto gradle que ya lleva incluidos todos los test necesarios que el programa debe pasar. Dichos test estÃ¡n todos comentados y deberÃ¡s ir descomentÃ¡ndolos conforme vayas avanzando con la tarea. La URL del repositorio es en la que te encuentras.

Por tanto, tu tarea va a consistir en completar los siguientes apartados:

1. Lo primero que debes hacer es realizar un fork del repositorio donde he colocado el proyecto gradle con la estructura del proyecto y todos los test necesarios.
2. Clona tu repositorio remoto reciÃ©n copiado en github a un repositorio local que serÃ¡ donde irÃ¡s realizando lo que a continuaciÃ³n se te pide. AÃ±ade tu nombre al fichero `README.md` en el apartado "Alumno". Haz tu primer commit.
3. Modifica la clase `Aulas` para que utilice un `ArrayList` en vez de un `Array`. Modifica tambiÃ©n los mÃ©todos, eliminando los necesarios, para que sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el mÃ©todo `representar` ahora tambiÃ©n devuelve una lista. Haz un commit.
4. Modifica la clase `Profesores` para que utilice un `ArrayList` en vez de un `Array`. Modifica tambiÃ©n los mÃ©todos, eliminando los necesarios, para que sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el mÃ©todo `representar` ahora tambiÃ©n devuelve una lista. Haz un commit.
5. Modifica la clase `Reservas` para que utilice un `ArrayList` en vez de un `Array`. Modifica tambiÃ©n los mÃ©todos, eliminando los necesarios, para que sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el mÃ©todo `representar` ahora tambiÃ©n devuelve una lista. Haz un commit.
6. Haz los ajustes necesarios en la clase `IUTextual` para que todo siga funcionando igual. Haz un commit.



###### Se valorarÃ¡:
- La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
- La indentaciÃ³n debe ser correcta en cada uno de los apartados.
- El nombre de las variables debe ser adecuado.
- Se debe utilizar la clase `Entrada` para realizar la entrada por teclado.
- El proyecto debe pasar todas las pruebas que van en el esqueleto del mismo y toda entrada del programa serÃ¡ validada para evitar que el programa termine abruptamente debido a una excepciÃ³n.
- Se deben utilizar los comentarios adecuados.
- Se valorarÃ¡ la correcciÃ³n ortogrÃ¡fica tanto en los comentarios como en los mensajes que se muestren al usuario.

