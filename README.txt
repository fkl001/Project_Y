Hvordan kj�re programmet:

1. �pne Javaprosjeketet Gjesdalkommune i Eclipse
2. Kj�r javaprogrammet Model.java fra Gjesdalkommune/src/prosjekt/Model.java
	- For � kj�re Java-programmet trenger man Jena p� datamaskinen
	- Denne genererer en ttl-fil kalt 'Gjesdal.ttl' og legger den i Gjesdalkommune-mappen
3. Start Fuseki server lokalt p� maskinen og �pne nettsiden localhost:3030
4. Legg til et nytt datasett i Fuseki, kall det 'Gjesdal' og last opp ttl-filen fra Gjesdalkommune/Gjesdal.ttl
5. �pne HTML-siden index.html fra Nettside/index.html i Firefox eller Chrome