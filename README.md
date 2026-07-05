
Taller de POO 4, aplicando patrones de diseño y gui

Autora: Laura García
Rut: 26427429-k
Asignatura: Programación Orientada a Objetos   
Paralelo: C2  



El siguiente proyecto corresponde al Taller 4 de Programación Orientada a Objetos.  
Es un sistema permite administrar una colección de cartas inspirada en Pokémon TCG mediante una interfaz gráfica desarrollada en Java Swing.

Este programa permite cargar cartas desde un archivo de texto, agregar nuevas cartas, eliminar cartas, modificar atributos específicos según el tipo de carta, ordenar la colección y visualizar el detalle de cada carta junto con su imagen y su poder calculado.

Enunciado: 
Sutrostian y POOsandon comenzaron a coleccionar cartas Pokémon TCG, pero con el tiempo su colección se volvió difícil de organizar.  
Por este motivo, desarrollan un software con interfaz gráfica que permite administrar la colección, visualizar cartas y calcular su puntuación según su tipo.


Interfaz gráfica del sistema

El sistema cuenta con una interfaz gráfica desarrollada en Java Swing. La ventana principal se organiza mediante pestañas, permitiendo separar las funciones administrativas de la visualización de la colección.

Pestaña 1: Administración

La pestaña de Administración permite gestionar la colección de cartas mediante operaciones CRUD:

Agregar carta: permite ingresar una nueva carta indicando su nombre, rareza, tipo y atributos específicos según corresponda.
Eliminar carta: permite eliminar una carta seleccionada de la colección. La imagen no se elimina del proyecto, solo se elimina el objeto y sus datos del archivo.
Modificar carta: permite modificar únicamente los atributos adicionales de la carta, respetando el tipo de carta:
  - En una carta Pokémon se puede modificar el daño y la cantidad de energías.
  - En una carta Item se puede modificar la bonificación.
  - En una carta Supporter se puede modificar la cantidad de efectos por turno.
  - En una carta Energy se puede modificar el elemento.

Cada operación realizada se guarda en el archivo `sobres.txt`, permitiendo que los cambios persistan al cerrar y volver a abrir el programa.

Pestaña 2: Ver Colección

La pestaña Ver Colección permite visualizar todas las cartas cargadas desde el archivo `sobres.txt`.

Desde esta pestaña se pueden realizar las siguientes acciones:

- Ver la colección completa.
- Ordenar las cartas por nombre.
- Ordenar las cartas por rareza.
- Ordenar las cartas por poder calculado.
- Seleccionar una carta y abrir su vista ampliada.
- Hacer doble clic sobre una carta para visualizar su detalle.

La visualización ampliada muestra:

- Imagen de la carta.
- Nombre.
- Rareza.
- Tipo.
- Atributos específicos según el tipo de carta.
- Poder calculado.
- Ruta de imagen utilizada.

Si la carta no posee una imagen asociada dentro de la carpeta `imagenes`, el sistema muestra automáticamente una imagen por defecto llamada `default.png`.
Tipos de cartas

El sistema trabaja con cuatro tipos de cartas Pokémon:

Formato en el archivo:
NombreCarta;Rareza;Pokemon;Daño;CantEnergias

Implementación de patrones de diseño

En este proyecto se implementaron cuatro patrones de diseño solicitados en el enunciado: Singleton, Factory, Visitor y Strategy. Cada uno fue aplicado en una parte específica del sistema para mantener una arquitectura más ordenada, separar responsabilidades y facilitar el mantenimiento del código.

Patrón Singleton

El patrón Singleton fue implementado en la clase `SistemaImple`.

Esta clase representa el sistema principal del programa y es la encargada de administrar la colección de cartas mediante un `ArrayList<Carta>`. Se aplicó Singleton para asegurar que durante la ejecución del programa exista una sola instancia del sistema.

Esto es importante porque tanto la clase `Main` como la interfaz gráfica `Ventana` necesitan acceder a la misma colección de cartas. Si se crearan varias instancias de `SistemaImple`, podrían existir varias listas distintas y los cambios realizados desde la GUI no estarían centralizados.

Por esta razón, `SistemaImple` tiene un atributo estático llamado `instancia`, un constructor privado y un método `getInstancia()`, que permite obtener siempre el mismo objeto del sistema.

Gracias a este patrón, operaciones como cargar cartas, agregar, eliminar, modificar, ordenar y guardar cambios se realizan sobre una única colección compartida.

Clase donde se implementa:`SistemaImple`  
Clases que lo utilizan: `Main` y `Ventana`



Patrón Factory

El patrón Factory fue implementado en la clase `FactoryLectura`.

Este patrón se utilizó para crear objetos de tipo `Carta` a partir de las líneas del archivo `sobres.txt`. Cada línea del archivo contiene los datos de una carta, pero dependiendo del tipo de carta, los atributos cambian.

Por ejemplo, una carta de tipo `Pokemon` tiene daño y cantidad de energías, mientras que una carta de tipo `Item` tiene bonificación, una carta `Supporter` tiene efectos por turno y una carta `Energy` tiene un elemento.

La clase `FactoryLectura` recibe una línea del archivo, la separa usando el carácter `;`, identifica el tipo de carta y crea el objeto correspondiente. De esta forma, si el tipo es `Pokemon`, se crea un objeto `Pokemon`; si el tipo es `Item`, se crea un objeto `Item`; si el tipo es `Supporter`, se crea un objeto `Supporter`; y si el tipo es `Energy`, se crea un objeto `Energy`.

Este patrón permite separar la lógica de creación de objetos de la lógica de lectura del archivo. Así, la clase `GestorArchivo` se encarga principalmente de leer el archivo, mientras que `FactoryLectura` decide qué tipo de carta debe construirse.

Clase donde se implementa: `FactoryLectura`  
Clase que lo utiliza: `GestorArchivo`  
Clases que crea: `Pokemon`, `Item`, `Supporter` y `Energy`



Patrón Visitor

El patrón Visitor fue implementado mediante la interfaz `Visitor` y la clase concreta `Visitor1`.

Este patrón se utilizó para calcular el poder de las cartas según su tipo. Cada tipo de carta posee una fórmula distinta de poder, por lo que se necesitaba una forma ordenada de aplicar distintos cálculos sin concentrar toda la lógica en la clase abstracta `Carta`.

La clase abstracta `Carta` declara el método `aceptarVisitor(Visitor visitor)`, y cada subclase lo implementa llamando al método correspondiente del visitor. Por ejemplo, la clase `Pokemon` llama a `visitarPokemon`, la clase `Item` llama a `visitarItem`, la clase `Supporter` llama a `visitarSupporter` y la clase `Energy` llama a `visitarEnergy`.

Este patrón se usa principalmente en dos partes del programa. Primero, en la vista de detalle de la GUI, para mostrar el poder calculado de una carta. Segundo, en la clase `StrategyPoder`, para poder ordenar las cartas según su poder.

De esta forma, el cálculo de poder queda separado de las clases de cartas y puede aplicarse de manera uniforme a todos los tipos.

Interfaz del patrón: `Visitor`  
Clase concreta: `Visitor1`  
Clases visitables: `Carta`, `Pokemon`, `Item`, `Supporter` y `Energy`  
Clases que lo utilizan: `Ventana` y `StrategyPoder`



Patrón Strategy

El patrón Strategy fue implementado mediante la interfaz `Strategy` y las clases `StrategyNombre`, `StrategyRareza` y `StrategyPoder`.

Este patrón se utilizó para manejar las distintas formas de ordenar la colección de cartas. El enunciado solicitaba tres tipos de ordenamiento: por nombre, por rareza y por poder. Como cada criterio tiene una lógica diferente, se creó una estrategia independiente para cada uno.

La interfaz `Strategy` define el método `ordenarCarta(ArrayList<Carta> listaCarta)`, que debe ser implementado por cada clase concreta.

La clase `StrategyNombre` ordena las cartas alfabéticamente según el nombre.  
La clase `StrategyRareza` ordena las cartas por rareza de mayor a menor, ya que el enunciado indica que una carta con mayor rareza es mejor.  
La clase `StrategyPoder` ordena las cartas según su poder calculado de mayor a menor. Para lograr esto, utiliza también el patrón Visitor, ya que el poder depende del tipo de carta.

Además, se implementó la clase `ContextoStrategy`, que recibe una estrategia de ordenamiento y la aplica sobre la lista de cartas. Luego, desde `SistemaImple`, se llama al contexto para ordenar la colección según la estrategia seleccionada.

Desde la interfaz gráfica `Ventana`, cada botón de ordenamiento utiliza una estrategia distinta. Por ejemplo, el botón “Ordenar por Nombre” utiliza `StrategyNombre`, el botón “Ordenar por Rareza” utiliza `StrategyRareza` y el botón “Ordenar por Poder” utiliza `StrategyPoder`.

Gracias a este patrón, el sistema puede cambiar el criterio de ordenamiento sin modificar la lógica principal de la GUI ni de la clase que administra la colección.

Interfaz del patrón: `Strategy`  
Clases concretas: `StrategyNombre`, `StrategyRareza` y `StrategyPoder`  
Clase contexto: `ContextoStrategy`  
Clases que lo utilizan: `SistemaImple` y `Ventana`

