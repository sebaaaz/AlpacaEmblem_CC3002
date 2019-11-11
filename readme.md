## Detalles de implementación - Tarea 1

##### Sorcerer y Libros Mágicos
Se implementa primero la clase **Sorcerer** que pertenece a *AbstractUnit*, la cuál implementa métodos de la interfaz *IUnit*. **Sorcerer** puede solo equiparse libros mágicos. Para esta entrega se implementaron tres de estos libros *(DarkBook, LightBook, SoulBook)*, los cuales heredan de la clase *AbstractItem* e implementan las interfaces *IEquipableItem* y *IMagicBook*.

##### Intercambio
Para realizar el intercambio se crearon en una unidad abstracta los métodos *unequipItem*, *addItem* y *removeItem* que facilitan el manejo de inventario de cualquier unidad.

Con estos fue implementado también una manera de transferir objetos de una unidad a otra, gracias al método *giveItemTo(IEquipableItem item, IUnit unit)* que es usado por las unidades y recibe como parámetro un ítem y otra unidad, que verifica que el objeto esté en el inventario que dará una unidad, que ambas unidades se encuentren a distancia de 1 y que la unidad que recibe el objeto no tenga inventario lleno.

El ítem que una unidad entregará será primero desequipado si lo estaba, luego se remueve del inventario de la unidad, posteriormente a la otra unidad se le agrega el ítem para finalmente decirle a este último que el nuevo dueño es la unidad que recibió el ítem.

##### Combate

En primer lugar, se arreglaron problemas encontrados en el código respecto al uso de *instanceof()*, ninguna clase utiliza este método para comprobar qué tipo es un objeto, sino que se realiza la técnica de *Double Dispatch* para mantener un orden y motivar la correcta ejecución de los programas.

Para facilitar el uso del método **startCombat(IUnit unit)**, se crearon los métodos *useItemAgainst(IUnit unit)* y *counterAttack(IUnit unit)* dentro de la unidad abstracta. Explicando paso a paso el método para empezar el combate, primero se explicará el primer método de ayuda.

###### Ataque
Un combate da a lugar cuando a una unidad emplea el método **startCombat(IUnit unit)**

Se revisa que la unidad posea un ítem equipado, de lo contrario no puede atacar. Luego se revisa que la distancia se encuentre dentro del rango atacable del ítem, si se cumplen las condiciones, se envía el mensaje al ítem equipado para que sea usado contra la unidad, este método es *useAgainst(IUnit unit)* y es overrideado en cada ítem para que le mande un mensaje a la unidad enemiga haciéndole saber si debe recibir un ataque físico o mágico. La unidad enemiga como clase que hereda de AbstractUnit decide cómo actuar frente al ataque y recibirá ataques normales, fuertes o débiles según corresponda su ítem equipado y el ítem que lo ataca. Posteriormente perderá vida basado en el poder del ítem atacante y de si este resultó en un ataque fuerte, débil o normal.

De esta forma la unidad abstracta implementa todos los métodos para recibir ataques de las distintas armas implementadas en el juego.

En futuras entregas se corregirá el hecho de que es la unidad quien se encarga de controlar los ataques enviados y recibidos, para que el ítem se encargue, dado que es este quien es débil, fuerte o neutral ante otras armas.

###### Contraataque
Luego de que la unidad recibe el daño, existe la posibilidad de contraatacar. El método *startCombat* de la unidad llama al método counterAttack de la unidad enemiga. Este le pedirá a su ítem equipado que contraataque a la unidad que le paso. Este DD es necesario dado que armas curativas como el Staff no contraatacan nunca, de esta forma se volverá a hacer uso de *useItemAgainst* que verificará nuevamente el rango del arma y la distancia de la otra unidad que comenzó el combate.

#### Trabajo futuro
Junto con lo mencionado anteriormente, se espera implementar adicionalmente una clase llamada NullItem que elimine muchas piezas de código que rondan por ahí y haga las implementaciones más fáciles. Para esto se espera hacer uso de *Null Patern* que será visto en clases más adelante.

Los libros mágicos son los únicos que implementan dos interfaces distintas y fueron las dos clases que motivaron hacer cast en uno de sus métodos para funcionar adecuadamente. Posteriormente se espera tener una interfaz parecida y más general para ternas de ítems que funcionan con debilidades y fortalezas entre sí.

#### Supuestos tomados
Un primer supuesto es que se asume que todos los ítems y unidades creados de la misma clase tendrían diferentes nombres, si bien esto no influye en esta entrega, probablemente lo haga en el futuro si se buscan crear nuevos comparadores de ítems.

#### Otros aspectos
Las soluciones implementadas no son mayormente las más óptimas para esta entrega, la estrategia adoptada para cada aspecto solicitado por la tarea es perfectible con el tiempo. Los tests sin embargo cubren más del 98% de lineas para cada uno de los tres paquetes y además al mirarlos se aprecia que buscan crear la mayor cantidad de escenarios posibles distintos (aún cuando ya se lograba el coverage pedido), esto porque siempre pueden ocurrir bugs que generen comportamientos indeseados, sobretodo en casos borde.

## Detalles de implementación - Tarea 2

##### Factories
Para una creación más rápida de items y unidades, se crean dos interfaces de fábricas. Implementando así un *Factory Pattern* en la aplicación.

###### IEquipableItemFactory
Cada fábrica de items implementa esta interfaz, la cual obliga a tener tres métodos que devuelven *IEquipableItem*s. Uno que crea items default y comunes, otra que crea items con *power* y *name* customizados y un último método que obliga a crear todo el item desde cero con nuevos parámetros.
###### IUnitFactory
Similar a la fábrica de ítems, pero esta implementa solo dos métodos que retornan *IUnit*s. Uno que crea unidades default y comunes y otra donde se setean sus puntos de vida máximos y su movimiento.
###### Fábricas estáticas
Se crean además dos clases que poseen desde el principio todas las fábricas posibles instanciadas a disposición del controlador. Esto permite una fácil manipulación de estas y acelera la creación de unidades e ítems.

##### Tactician
Los **Tactician** representan a los jugadores. Estos poseen un nombre único, una lista de unidades y mantiene una referencia a una unidad seleccionada al igual que un item. También posee un evento que notifica a los listeners de este que su héroe fue derrotado, de esta forma se notifica en este caso al controlador suscrito, que actuará removiendo a este Tactician del juego.
Cada Tactician tiene total control sobre sus unidades y los items que poseen estas, pueden seleccionar solo sus unidades activas y pueden ejecutar diversas acciones como atacar, desplazarse, dar items, etc...

##### Game Controller
(Desde ahora GC) La clase controller maneja todos los eventos realizados por un jugador que está asociado a algún *Tactician*.
Posee diversas referencias que ayudan a la fluidez del juego, principalmente una referencia al mapa, a los jugadores (con una copia original), números de turno y ronda. Otra principal referencia es el turno actual del jugador, que va cambiando a medida que pasan los turnos.

El GC maneja los turnos de los jugadores, define el orden de estos al inicio y en cada ronda, es capaz de crear mapas, derrotar jugadores, mostrar los ganadores, etc... También es capaz de manejar distintos eventos según como se va desarrollando el juego, se encarga de derrotar a un *Tactician* si su héroe muere gracias a un listener (notando así la implementación de un **Observer Pattern**) que espera la activación de dicho evento, pues un *Tactician* no posee referencia al controlador.
Otra gran responsabilidad es la de crear y asignar las unidades e items para un *Tactician*, los métodos están implementados y se espera usarlos adecuadamente en la *Vista* al implementar la tercera entrega de la tarea.

#### Cambios con respecto a la primera entrega
##### Null Pattern
Para esta segunda entrega se implementaron dos clases sumamente importantes. Una **unidad nula** y un **ítem nulo** que se encargan de manejar todos los eventos relacionados a sus tipos. Esto permitió eliminar una cantidad considerable de evaluaciones **if** dado que ahora es posible llamar a los métodos de clases nulas.
Todas las unidades son creadas con un *NullItem* como ítem equipado, de esta forma si por ejemplo la unidad decide atacar, lo hará con un ítem nulo que no afectará de manera alguna a esta o a su objetivo.

Por otro lado, si decidimos atacar a una *NullUnit* tampoco habrá efecto. Solo la unidad nula es implementada de forma estática y así se puede usar siempre la misma para distintos fines.
Es importante notar que desde esta entrega jamás se hace referencia al objeto **null** de *Java* para comparar unidades e ítems, lo que facilita y agiliza la fluidez del juego. Además facilita la creación de tests con el método *isNull* que simula un AssertNull, usando AssertTrue.

##### Units
Se crean referencias a su dueño *Tactician* y a un nombre genérico, este último solo sirve para imprimir (de momento y como método comentado) datos en pantalla por parte del jugador, se espera que con la *Vista* pueda ser de útilidad. Cambios relevantes son la implementación de la muerte de la unidad, lo que permite que esta salga del tablero colocándose en una *InvalidLocation*. Esto permite que no pueda ser contraatacada, pues esta posición no es vecina de ninguna celda. Otro gran cambio es la imposibilidad de moverse si ya lo hizo una vez. Para esto se crean métodos *denyMovement* y *allowMovement* que cambian el atributo *movement* a 0 o a su valor original según corresponde. Estos son llamados por su *Tactician* y por el *GC*.

##### Items
Fueron reestructurados completamente, pensando a futuro en como la implementación de nuevos ítems puede cambiar las cosas. Desde ahora las interfaces que extiendan la interfaz *IEquipableItem* deben implementar dos métodos fundamentales, **sendItemTypeAttack** y **sendSpecificEffect** además de otros dos métodos igual de importantes como son **initUseOn** y **beAttacked**.
En orden, los primeros dos piden el tipo de ataque y el efecto específico que causan. Los otros dos, en orden, definen el comportamiento de un ítem al atacar primero y al ser atacado. A continuación se explica generalmente cada nueva interfaz y la única clase abstracta que implementa cada una de estas.

###### IWeaponItem extends IEquipableItem
Son las armas del juego, las que pueden atacar y contraatacar (como los pide sus métodos). Estos métodos simulan un leve **Adapter Pattern** donde *attackTo* en realidad es *sendItemTypeAttack* y *counterAttackTo* es *beAttacked*. Esta arma obliga a contraatacar si es atacada, o recibir un contraataque de otra *IWeaponItem*. Esta interfaz pide el método *sendEffectAttackTo* en las clases que lo implementen, y se encuentran en las interfaces: **IMagicWeapon** y **IPhysicWeapon**, que lo implementan para así manejar más llamadas permitiendo multi-dispatch hasta llegar (como nivel más profundo) al efecto específico que solicita la interfaz de ítem equipable.

###### INonWeapon extends IEquipableItem
Es la otra interfaz restante y contraparte de IWeapon, actualmente solo el *Staff* la implementa directamente al extender la clase abstracta. Como tal no posee métodos en su contrato. No contraataca o recibe contraataques y se espera manipular en esta clase o interfaz todos aquellos ítems que no califiquen como un arma.

#### Funcionamiento esperado
Al instanciar un GC, se preparan los listeners (se cuenta con uno pero está toda la implementación en caso de necesitar crear otros). Se generan y almacenan las semillas del GC y del mapa generado. Se crean (por el momento) los jugadores con nombres genéricos, finalmente se almacena una copia de estos y se define el primer turno.
El controlador posee métodos para generar y seleccionar unidades, así como generar ítems para las unidades seleccionadas según el Tactician que está jugando y así tener los preparativos antes de iniciar una partida, la cantidad de jugadores es arbitraria.

Posteriormente y con el mapa creado y las unidades asignadas se puede iniciar una partida infinita (solo un ganador) o con un máximo de rondas. Los jugadores van combatiendo y pasando el turno, pueden desplazar a sus unidades solo una vez por cada ronda y solo sus propias unidades (y así controlar solo sus ítems) y también ver sus stats.

#### Notas adicionales
Todos los problemas de la primera entrega fueron corregidos y esto permitió aprender de mejor manera como funcionan las interfaces y clases abstractas en Java.

Cuando un *Tactician* selecciona una de las unidades del juego, se llama al método de la unidad *beSelectedBy* que modifica la unidad seleccionada de su tactician (que puede o no ser el jugador actual). Esto evita el uso de if para comparar si el dueño es quien seleccionó la unidad (a excepción de evaluar que el *Tactician* no sea nulo) pero puede cambiar la referencia de unidad seleccionada de otro jugador, pero al no ser su turno, esto no influye.

La clase *Hero* sobreescribe el método *toBeDefeated* para funcionar de igual manera pero además le pide a su *Tactician* no nulo que notifique que el héroe fue derrotado para que este controle el evento como desee.
Si se ponen complicadas las cosas se espera tener en el futuro un *NullTactician* para evitar más aún el uso de if en el código, sin embargo este servirá solo para tests, pues no debiesen existir en un juego real unidades sin *Tactician*.

