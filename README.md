Trabajo Práctico 
Afianzar la práctica de Programación Orientada a Objetos. En particular, de los mecanismos de herencia, polimorfismo, clases abstractas e interfaces. Se espera un adecuado manejo de excepciones.
1. Pautas de Trabajo
Individual con defensa oral.
1.1. Cuándo entregar
Hasta 24 hs. antes de la fecha de final. 
1.2. Cómo entregar
Se deberá adjuntar un conjunto de pruebas para poder evaluar el funcionamiento. 

El TP se deberá entregar en formato digital (tanto el informe como el código).
Se solicita un archivo .zip con la carpeta del proyecto. El archivo deberá tener la forma “Apellido_Nombre.zip”.

1.3. Qué entregar
Informe en un archivo README.md ubicado en el directorio raíz, el cual incluirá:
Nombres de los integrantes del grupo
Decisiones de diseño tomadas
Descripción de cada archivo *.java comprendido en solución del problema
Conclusiones
Diagrama de clases
Casos de prueba
Código fuente
Archivos necesarios para importar el proyecto (ej.: archivos .project, .classpath, etc.)

Si bien la idea es minimizar comentarios redundantes dentro del código, recordar que esta es una instancia de evaluación: aclaren todo lo que consideren importante, aún lo que consideren trivial.
Parte del proceso de corrección se realiza en forma automática. Por lo tanto, es importante que verifiquen que su proyecto pasa exitosamente los tests relevantes.

2. Ejercicio: Simulador ATM
2.1. Planteo
Un banco local pretende instalar una nueva máquina de cajero automático (ATM), para permitir a los usuarios (es decir, los clientes del banco) realizar transacciones financieras básicas. Cada cliente puede tener cuentas de tipo: cuenta corriente (que permite retirar hasta el  saldo más un descubierto de hasta cierta cantidad de dinero extra. El monto “en descubierto” se establece al momento de la apertura de la cuenta), caja de ahorro en pesos y caja de ahorro en dólares. Cada cuenta tiene un alias asociado.

Los usuarios del ATM pueden realizar las siguientes transacciones:
retirar efectivo (de sus cuentas en pesos)
comprar dólares solo con pesos en alguna de sus cuentas (no olvidar el impuesto PAIS)
depositar fondos en (pesos y en dólares)
realizar transferencias entre sus cuentas en pesos. Las transferencias pueden hacerse usando el alias de la cuenta de destino. 

Las transferencias son las únicas operaciones que se denominan “reversibles”, por lo que puede generarse un movimiento opuesto que las revierta si se solicita inmediatamente después y durante la misma operatoria. Otras operaciones serán reversibles a futuro, y debemos dejar el sistema listo para ello.

La interfaz del cajero automático contiene los siguientes componentes:
un lector de tarjetas (simular el ingreso de una tarjeta de débito, donde ésta le pasa al sistema los datos de identificación del usuario). 
una pantalla que muestra mensajes al usuario (utilizar la consola). 
un teclado que recibe datos numéricos de entrada del usuario.
un dispensador de efectivo que dispensa efectivo (en pesos) al usuario. 
una ranura de depósito que recibe sobres para depósitos del usuario (pesos, dólares).

El dispensador de efectivo comienza cada día cargado con 500 billetes de $100, 500 billetes de $500 y 500 billetes de $1000. Cada vez que debe entregar efectivo, trata de entregar la menor cantidad de billetes, por ejemplo si debe entregar $ 1.800 pesos entrega 1 billete de $1.000, uno de $500 y 3 de $100. Si no le quedan billetes de $ 1.000 entregará 3 billetes de $500 y 3 billetes de $100.
Se podrán obtener tickets impresos de cada operación, informes de últimos movimientos  (10),  para cada cuenta por pantalla e impresos.
En los tickets se debe informar: fecha, hora, cuenta, tipo de transacción, importe involucrado en la transacción y el nuevo saldo de la cuenta. 
También los clientes podrán consultar el alias de cada cuenta y obtenerlo impreso. 
Todo lo que sea impreso debe salir a un archivo de texto.
2.2. Uso del Sistema
Cada cliente ingresa su tarjeta de débito mediante la cual puede autenticar su identidad en la terminal. (Simular esto) La tarjeta de débito tiene algún dato que es único para cada cliente (CUIT).   
Además de ingresar la tarjeta, el sistema pide el pin de 4 dígitos numéricos. 
El ATM debe tener la búsqueda del CUIT optimizada mediante un algoritmo de  búsqueda binaria. 
Una vez que se valida la identificación, se le ofrecen las opciones para operar. 

2.3. Archivos del Sistema
Archivo de cuentas
tipo,alias,saldo,[descubierto]

Formato:
Tipo: 2 dígitos. Valores posibles:
01 CA
02 CC
03 CAU$S
Alias: Alfanumérico de hasta 20 caracteres..
Saldo: Decimal con precisión de 2 dígitos (centavos).
Descubierto: Opcional, sólo para cuentas tipo 02. Precisión de dos dígitos.

Ejemplo:
01,isla.pez.arbol,12000.03
02,sol.monte.valle,-1021.90,25000.00
01,uva.sandalia.halcon,0.00
02,hormiga.lija.crema,-50.97,150.00
03,lobo.luna.pasto,200.00
Archivo de clientes
cuit,alias

Formato:
CUIT: 11 dígitos
Alias: Alfanumérico de hasta 20 caracteres.

Ejemplo:
27102551236,isla.pez.arbol
27102551236,sol.monte.valle
23044303094,uva.sandalia.halcon
20311573951,hormiga.lija.crema
20311573951,lobo.luna.pasto
Archivo de movimientos
fecha,tipo,concepto,alias.desde,importe,[alias.hacia]


Formato:
Fecha: ISO 8601, sólo fecha
Tipo: código de Transacción (a definir por el desarrollador - en principio un número de dos dígitos asociado al tipo de Transacción, similar al código del tipo de cuenta)
Concepto: hasta 30 caracteres
Alias.desde: Alfanumérico de hasta 20 caracteres
Importe: Decimal con precisión de 2 dígitos (centavos)
Alias.hacia: Opcional, sólo para Transferencias. Alfanumérico de hasta 20 caracteres

Ejemplo (notar la implementación de reversibilidad):
2020-05-01,TT,XXX,isla.pez.arbol,175.00,lobo.luna.pasto
2020-05-01,TT,XXX,lobo.luna.pasto,10.99
2020-05-02,TT,XXX,hormiga.lija.crema,120.00
2020-05-02,TT,XXX,isla.pez.arbol,250.00,solmontedelvalle
2020-05-02,TT,XXX,solmontedelvalle,250.00,isla.pez.arbol
2020-05-03,TT,XXX,lobo.luna.pasto,-135.00
2020-05-03,TT,XXX,solmontedelvalle,2000.00
Archivo de validación de tarjetas
numeroDeTarjeta,pin,cuit

Formato:
NumeroDeTarjeta: 8 dígitos sin repetirse
Pin: 4 dígitos
CUIT: 11 dígitos

Ejemplo:
12345678,1234,27102551236
23456789,2345,27102551236
34567890,3456,23044303094
45678901,4567,20311573951
56789012,5678,20311573951
