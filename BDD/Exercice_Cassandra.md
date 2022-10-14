# Installer CQLSH

`pip install cqlsh`

`cqlsh --cqlversion=3.4.5 iti-tdm-22.insa-rouen.fr`

# Exercice 1

On cherche à modéliser les ventes de produits à des utilisateurs.

Les utilisateurs sont représentés par leurs noms, prénoms et age. Les articles ont un numéro et une description. 

Pour chaque vente, un ticket de caisse est créé sur lequel est indiqué le prix de l'article ainsi que la quantité acheté pour chaque article.

Nous souhaitons effectuer des recherches seulement par rapport aux tickets et aux produits.

1) Proposer une modélisation normalisée.

2) Proposer maintenant la modélisation pour Cassandra.

3) Créer un keyspace <nom>.

4) Créer les tables nécessaires.

5) Insérer des données dans les tables via des inserts.

6) Insérer des données via du JSON.

7) Mettre à jour une des lignes

8) Supprimer une colonne d'une ligne

9) Supprimer une table

# Exercice 2
Nous allons travailler sur table d'UVs (Unité de valeur crédits ECTS). Les UVs seront caractérisées par un id, un code et un titre.

1) Créez une table contenant les 3 colonnes décrites ci-dessus.

2) Insérez des données dans votre table.

3) Récupérez les données de l'UV dont l'id vaut 1.

4) Modifiez la table pour ajouter un attribut type indiquant si l'UV est une CS, TM etc.

5) Faire la modification en conséquence sur chaque ligne de la table.

# Exercice 3
On garde la table UV mais on ajoute une relation 1..n entre une UV et des étudiants.

1) Comment va-t-on gérer dans Cassandra les étudiants de chaque UV ?

Est-ce qu'on crée une table d'étudiants ?

2) Modifiez la table pour ajouter une collection d'étudiants. Un étudiant sera caractérisé par un champ login de type text.

3) Mettez à jour la collection d'étudiants en ajoutant des étudiants dans les UVs.

4) Cherchez les lignes dont la colonne 'etudiants' contient une des valeurs que vous avez renseigné.

5) Enlevez l'un des étudiants d'une UV, puis enlevez les tous.

6) Nous voulons maintenant ajouter des informations supplémentaires pour chaque UV : nous voulons connaître les personnes qui assurent les différents rôles de responsable de l'UV, enseignant et chargé de TD dans l'UV.

7) Alimenter les responsables pour une des UVS.

8) Quelle est la quantité d'UVs de type 'CS' ?

9) Quelle est la quantité d'UVs par type?
