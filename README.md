# challence
Para ejecutar el servicio /mutant/ se hace mediante el siguiente curl

curl --location --request POST 'http://3.15.93.114:9081/mutants/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
    “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]

}'

Para ejecutar el servicio /stats/ se hace mediante el siguiente curl

curl --location --request GET 'http://3.15.93.114:9081/mutants/stats' \
--data-raw ''

Este devolvera el siguiente json:

{
    "count_mutant_dna": numero de humanos detectados,
    "count_human_dna": Numero de mutants detectados,
    "ratio": Promedio de mutantes por humano
}

Url de la api: http://3.15.93.114:9081/mutants
La api se encuentra hosteada en Aws con una ip estatica.

Tecnologias usadas.
El lenguaje de programacion utilizado fue spring boot(Contiene el algoritmo de deteccion de mutantes) y spring batch(Ejecuta el job para grabar la informacion en la bd y así ser mas eficiente) con java 8.
Base de datos MongoDb.
Infraestructura en Aws con docker para contenerizar la bd y la app.
