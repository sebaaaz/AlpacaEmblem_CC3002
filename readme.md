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
Luego de que la unidad recibe el daño, existe la posibilidad de motivar un contraataque. El método *startCombat* de la unidad analiza si la unidad atacada posee un ítem nulo, si es el caso se motivará un contraataque con el método *motivateCounterAttack(IUnit unit)* del ítem de la unidad enemiga. Esta lanzará a su dueño (unidad) un mensaje para contraatacar definido por el método *counterAttack(IUnit unit)* aplicando un double dispatch dado que este método le dice a la unidad equipada que contraataque a la unidad que le paso. Este DD es necesario dado que armas curativas como el Staff no contraatacan nunca. (Esto será resuelto en futuras entregas con nuevas clases abstractas e interfaces que faciliten el código y no se necesite hacer *override* en clases particulares). Finalmente la unidad que atacó recibe daño según el contraataque del arma atacada y el método que le pase esta a la primera unidad atacante.

#### Trabajo futuro
Junto con lo mencionado anteriormente, se espera implementar adicionalmente una clase llamada NullItem que elimine muchas piezas de código que rondan por ahí y haga las implementaciones más fáciles. Para esto se espera hacer uso de *Null Patern* que será visto en clases más adelante.

Los libros mágicos son los únicos que implementan dos interfaces distintas y fueron las dos clases que motivaron hacer cast en uno de sus métodos para funcionar adecuadamente. Posteriormente se espera tener una interfaz parecida y más general para ternas de ítems que funcionan con debilidades y fortalezas entre sí.

#### Supuestos tomados
Un primer supuesto es que se asume que todos los ítems y unidades creados de la misma clase tendrían diferentes nombres, si bien esto no influye en esta entrega, probablemente lo haga en el futuro si se buscan crear nuevos comparadores de ítems.

#### Otros aspectos
Las soluciones implementadas no son mayormente las más óptimas para esta entrega, la estrategia adoptada para cada aspecto solicitado por la tarea es perfectible con el tiempo. Los tests sin embargo cubren más del 98% de lineas para cada uno de los tres paquetes y además al mirarlos se aprecia que buscan crear la mayor cantidad de escenarios posibles distintos (aún cuando ya se lograba el coverage pedido), esto porque siempre pueden ocurrir bugs que generen comportamientos indeseados, sobretodo en casos borde.