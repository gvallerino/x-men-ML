# x-men-ML

#Hipótesis

#  0- Siguiendo lo que indica el enunciado, una matriz debe ser obligatoriamente cuadrada para 
# 		verificar si el humano es mutante o no.

#  0- Si la matriz tuviese información incompleta, el servicio va a devolver que el humano no es mutante, 
#		debido a que la firma del método isMutant del enunciado no permite
#		devolver una excepción.

#  1- Si existe un código de 5 letras iguales correlativas se tiene en cuenta como una sola secuencia 
#		de cuatro letras, y no dos secuencias.

#	2- Si se da el caso de 8 letras iguales correlativas, se toma como criterio la existencia de dos 
#		secuencias, debido a que son dos grupos de 4 letras iguales.

#	0-	El servicio tiene en cuenta que en el código de ADN tenga información incorrecta (es decir, alguna 
#		letra que no sea A, T, C o G). En dicho caso, el servicio retornará que el humano no es mutante.

#  0- Si la cantidad de humanos llegase a ser 0, por lo que no podria ejecutarse la division, o existe algun
#     error en la ejecucion del servicio stats, el resultado del mismo será 
#      "{"count_mutant_dna":0,"count_human_dna":0,"ratio":-1}

Si el algoritmo detecta primero 2 matchs antes que una codigo de adn incorrecto (es decir que no sea A, T, C o G), se tomara como mutante valido