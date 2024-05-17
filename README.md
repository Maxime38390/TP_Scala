# Projet d'Analyse de BookCorpus

## Aperçu du Projet

Ce projet est une analyse du jeu de données BookCorpus. L'objectif est de prétraiter les données textuelles, de réaliser diverses analyses statistiques et de visualiser les résultats. Ce README vous guidera à travers les étapes du projet, du prétraitement des données à la visualisation.

## Objectifs et Choix Méthodologiques

### Pourquoi analyser ces données ?

Le jeu de données BookCorpus est une collection riche de textes de livres, ce qui en fait une excellente ressource pour des analyses linguistiques et textuelles. En étudiant ces données, nous pouvons obtenir des insights sur les structures linguistiques, les styles d'écriture et les caractéristiques lexicales des livres. De telles analyses peuvent être utiles pour des applications variées telles que :

- La création de modèles de langage.
- La recherche en linguistique computationnelle.
- Le développement d'outils d'analyse textuelle et de résumé automatique.

### Pourquoi limiter à 3 000 000 de lignes ?

Le jeu de données BookCorpus est volumineux, avec des dizaines de millions de lignes. Traiter l'intégralité de ce jeu de données en une seule fois pourrait entraîner des problèmes de mémoire et de performance. Pour des raisons de praticité et de démonstration, nous avons choisi de limiter l'analyse aux 3 000 000 premières lignes de chaque fichier. Cela permet de :

- Réduire la charge mémoire et les temps de traitement.
- Illustrer les techniques d'analyse et de visualisation de manière efficace sans nécessiter des ressources computationnelles excessives.
- Maintenir la faisabilité de l'analyse sur des machines locales ou des environnements de développement limités.

## Étapes Impliquées

### 1. Prétraitement

#### Chargement des Données

Nous commençons par charger les données textuelles à partir de deux fichiers texte volumineux. Chaque fichier contient des lignes de texte provenant de plusieurs livres. Pour éviter les problèmes de mémoire, nous limitons le nombre de lignes chargées de chaque fichier aux 3 premiers millions de lignes.

#### Nettoyage des Données

Les données brutes sont ensuite nettoyées pour supprimer tout caractère indésirable et les espaces blancs, ne laissant que des caractères alphanumériques et des espaces. Ce nettoyage est crucial pour garantir que les analyses suivantes soient précises et pertinentes.

#### Division des Données en Livres

Les données nettoyées sont divisées en livres individuels. Nous utilisons une heuristique basée sur la présence de numéros ISBN ou du mot "Chapter" pour identifier le début d'un nouveau livre. Chaque ligne se voit attribuer un `book_id`. Cela nous permet de regrouper les lignes par livre et de mener des analyses spécifiques à chaque livre.

### 2. Analyse

#### Comptage des Livres

Nous comptons le nombre de livres distincts dans le jeu de données pour avoir une idée de la taille et de la diversité du corpus analysé.

#### Affichage des Statistiques des Livres

Nous calculons et affichons des statistiques de base pour chaque livre, y compris le nombre total de lignes, le nombre total de mots, la moyenne de mots par ligne, le nombre total de mots uniques, la moyenne de mots uniques par ligne et le nombre total de caractères. Ces statistiques nous donnent un aperçu général des caractéristiques des livres.

#### Statistiques Descriptives

Nous calculons des statistiques descriptives, telles que la moyenne de mots par ligne et la moyenne de mots uniques par ligne pour chaque livre. Nous comptons également le nombre de phrases (lignes) dans chaque livre. Ces informations sont essentielles pour comprendre la structure textuelle des livres.

#### Analyse Textuelle Avancée

Une analyse plus approfondie est réalisée pour calculer des statistiques détaillées, telles que la moyenne de mots par ligne, la moyenne de mots uniques par ligne et le nombre de phrases par livre. Ces analyses nous permettent d'extraire des insights plus fins sur les styles d'écriture et les structures linguistiques des livres.

### 3. Visualisation

#### Visualisation des Données

Nous utilisons la bibliothèque Breeze pour créer des visualisations des données analysées. Plus précisément, nous créons des graphiques pour :

- La moyenne de mots par ligne par identifiant de livre
- La moyenne de mots uniques par ligne par identifiant de livre
- Le nombre de phrases par identifiant de livre

Ces visualisations nous aident à comprendre la distribution et les caractéristiques des données textuelles dans le jeu de données BookCorpus. Les graphiques fournissent une représentation visuelle des statistiques calculées, facilitant ainsi l'interprétation des résultats.

## Conclusion

Ce projet démontre comment prétraiter, analyser et visualiser les données textuelles du jeu de données BookCorpus. Les étapes de prétraitement nettoient et organisent les données, l'analyse calcule diverses statistiques, et les visualisations aident à interpréter les résultats. Ce flux de travail peut être appliqué à d'autres jeux de données textuels pour des analyses similaires.
