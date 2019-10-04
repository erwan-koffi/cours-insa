# Installer CQLSH

`pip install cqlsh`

`cqlsh --cqlversion=3.4.4 asi-tdm.insa-rouen.fr`

# Exercice 1

On cherche à modéliser les ventes de produits à des utilisateurs.

Les utilisateurs sont représentés par leurs noms, prénoms et age. Les articles ont un numéro et une description. 

Pour chaque vente, un ticket de caisse est créé sur lequel est indiqué le prix de l'article ainsi que la quantité acheté pour chaque article.

Nous souhaitons effectuer des recherches seulement par rapport aux tickets et aux produits.

1) Proposer une modélisation normalisée.
```sql
CREATE TABLE clients(
    idClient int,
    nom varchar,
    prenom varchar,
    age int,
    PRIMARY KEY(idClient ));
```

```sql
CREATE TABLE produits(
    idProduit int,
    description varchar,
    PRIMARY KEY(idProduit ));
```

```sql
CREATE TABLE tickets(
    idTicket int,
    clientId int,
    produitId int,
    quantite int,
    PRIMARY KEY(idTicket , clientId, produitId));
```

```sql
CREATE TABLE produitsTickets(
    idProduit int,
    ticketId int,
    quantite int,
    PRIMARY KEY(idProduit , ticketId  ));
```

2) Proposer maintenant la modélisation pour Cassandra.
```sql
CREATE TABLE ticket (
  id uuid,
  id_ticket int,
  firstname text static,
  lastname text static,
  age int static,
  id_article int,
  description text,
  price DOUBLE,
  quantity int,
  PRIMARY KEY (id, id_ticket, id_article)
);
```

3) Créer un keyspace <nom>.
```sql
CREATE KEYSPACE <nom> WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };
```

4) Créer les tables nécessaires.


5) Insérer des données dans les tables via des inserts.

```sql
INSERT INTO ticket(id, id_ticket, id_article, age, description, firstname, lastname, price, quantity) VALUES (uuid(), 1, 2, 25, 'coca', 'Marc', 'Mathieu', 2, 2);

```

6) Insérer des données via du JSON.

```sql
INSERT INTO produitsTickets JSON '{"idProduit": 1, "ticketId": 1, "quantite": 5}'
```

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

7) Alimenter le rôle pour une des UVS.

8) Quelle est la quantité d'UVs de type 'CS' ?

9) Quelle est la quantité d'UVs par type?
