# x-men-ML

## Objetivo

Crear un programa con un método o función con la siguiente firma "boolean isMutant(String[] dna);" en donde se recibirá como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN.
Un humano es mutante si se encuentra más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.


## Hipótesis y supuestos

1- Siguiendo lo que indica el enunciado, una matriz debe ser obligatoriamente cuadrada para verificar si el humano es mutante o no.

2- Cualquier request incorrecto que se envíe a la API, en el servicio mutant, la misma devolverá 403-Forbidden.
	Posibles casos de requests incorrectos:
		- Código de ADN que tenga información incorrecta (es decir, alguna letra que no sea A, T, C o G).
		- Matriz no cuadrada.
	
3- Se tomó como criterio que si un código de ADN tiene una letra incorrecta, pero primero se encuentran dos matcheos de mutantes,
la API devolverá que el humano es mutante, ignorando el codigo ADN incorrecto.

4- En el enunciado menciona que una posible dirección de ADN para ser mutante es la oblicua. Se toma como decisión que existen dos tipos de direcciones oblicuas: Oblicua Izquierda y oblicua derecha.


5- Si existe un código de 5 letras iguales correlativas se tiene en cuenta como una sola secuencia de cuatro letras, y no dos secuencias.

6- Si se da el caso de 8 letras iguales correlativas, se toma como criterio la existencia de dos secuencias, debido a que son dos grupos de 4 letras iguales.

7- En el servicio stats, si la cantidad de humanos llegase a ser 0 o existe algún error en la ejecucion del mismo, el resultado  será: {"count_mutant_dna":0.0,"count_human_dna":0.0,"ratio":"NaN"}

8- Si en la API se ingresa un request incorrecto (ver punto 2) o se produce algún error de aplicación, dicho ADN no se tendrá en cuenta para la estadística.
