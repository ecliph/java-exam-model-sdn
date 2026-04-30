# Modèle Examen Java - Prépa SDN

Ce projet est un support pédagogique complet pour préparer les examens de Structures de Données et Programmation Orientée Objet en Java.

## Thème
Gestion d'une flotte de drones (Transport et Médicaux).

## Notions couvertes
- **POO de base** : Classes, attributs privés, getters/setters, constructeurs.
- **Interfaces** : Méthodes abstraites, `default`, `static`.
- **Héritage** : Classes abstraites, `extends`, `super()`, polymorphisme.
- **Collections** : `ArrayList`, `HashMap`, `TreeMap`, `TreeSet`.
- **Généricité** : Classes génériques `<T>`, Wildcards (`? extends`, `? super`).
- **Streams** : Filtrage, calcul de moyenne, sommes, collecte de données.
- **Exceptions** : Hiérarchie d'exceptions, `try/catch` multiple.
- **Algorithmes classiques** : Recherche par clé vs boucle, tri via `Comparable`.

## Fichiers principaux
- `src/Flotte.java` : Contient la logique de gestion et les comparaisons Streams vs Boucles.
- `src/Main.java` : Test d'intégration complet.
- `SUPPORT_EXAMEN.html` : Version imprimable du code avec coloration syntaxique.

## Usage
1. Compiler : `javac src/*.java`
2. Lancer : `java -cp src Main`
