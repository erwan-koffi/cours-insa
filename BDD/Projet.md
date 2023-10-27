# Projet

## Choisir la base NoSQL adequate pour traiter les données suivantes:

Importer le fichier `relation_dechets_exutoire.csv` dans Cassandra et construire une ou des tables qui sont en mesure de répondre aux questions suivantes:

1. Quel est le nombre d'exutoires pour les déchets `Dématérialisation` ?
2. Quel est le type d'exutoire pour les déchets de `Peinture à l'eau` ?
3. Quels sont les déchets associés à l'exutoire id `3` ?
4. Quels sont les déchets qui ont comme famille `Verre` ?
5. Quelle est la description de l'exutoire `6` ?
6. Combien de type de déchets sont concernés par les exutoires de type `Conseil` ?
7. Quel est le type d'exutoire qui contient le plus de déchets différents ?
8. Quel est le type d'exutoire le plus courant ?
9. Quel est le type d'exutoire le plus courant par famille de déchet ?
10. Quels sont les labels d'exutoires associés à chaque famille de déchets ?
11. Indiquer une clé qui permettrait de s'assurer que les données soient réparties de manière uniforme sur un cluster distribué.
12. Indiquer une clé qui permettrait de ne traiter les requêtes ne concernant que les déchets sur un seul noeud.

## Choisir la base de données adequate pour traiter les données suivantes:

Importer le fichier `atmo-indice.geojson` dans Mongo et répondre aux questions suivantes:

1. Quels sont les communes qui ont eu un indice de qualité de l'air `Moyen` ?
2. Quelle est le code de qualité de l'air maximal sur la commune de `Saint-Genès-Champanelle` ?
3. Quelles sont les différentes communes observées ?
4. Quelle est le code qualité moyen par commune ?
5. Quelle est la qualité de l'air la plus récente dans un rayon de 1 km de la position GPS `45.77441539864761, 3.0890499134717686` ?
6. Combien y-a t'il de relevé d'indice différent pour les communes dont la lettre commence par `C` ?
7. Pour chaque libellé de qualité de l'air, quelles sont les communes qui lui sont associé ?
8. Quelle est la concentration moyenne de `PM10` dans la région `Auvergne-Rhône-Alpes` le `22 octobre 2023` ?
9. Afficher l'évolution de la concentration de `NO2` dans la commune de `Clermont-Ferrand` à travers le temps.
10. Quelle est la commune qui a l'indice de concentration d'ozone le plus faible sur la période observée ?
11. Quelle serait la clé de sharding à utiliser pour pouvoir s'assurer que tous les enregistrements d'une même commune soient traités par le même noeud ?

### A rendre avant le 20/11/2023 00:00:00
à l'adresse mail: `erwan` `[at]` `gmail.com`