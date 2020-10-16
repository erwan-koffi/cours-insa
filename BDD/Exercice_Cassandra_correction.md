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
    PRIMARY KEY(idClient));
```

```sql
CREATE TABLE produits(
    idProduit int,
    description varchar,
    PRIMARY KEY(idProduit));
```

```sql
CREATE TABLE tickets(
    idTicket int,
    clientId int,
    PRIMARY KEY(idTicket , clientId));
```

```sql
CREATE TABLE produitsTickets(
    idProduit int,
    ticketId int,
    quantite int,
    PRIMARY KEY(idProduit , ticketId));
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
  PRIMARY KEY (id_ticket, id_article)
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
INSERT INTO ticket JSON '{"id": "bc7263ec-f1ad-11e9-9ae6-9cb6d0917299", "id_ticket": 5, "id_article": 1, "quantity": 5}'
```

7) Mettre à jour une des lignes

```cassandraql
UPDATE ticket SET lastname = 'Matthieu' WHERE id_ticket = 1 AND  id_article = 2
```

8) Supprimer une colonne d'une ligne

```cassandraql
DELETE age FROM ticket WHERE id_ticket = 1 AND id_article = 2
```

9) Supprimer une table

```cassandraql
DROP TABLE ticket;
```

# Exercice 2
Nous allons travailler sur table d'UVs (Unité de valeur crédits ECTS). Les UVs seront caractérisées par un id, un code et un titre.

1) Créez une table contenant les 3 colonnes décrites ci-dessus.
```cassandraql
CREATE TABLE uv (
  id int primary key,
  code text,
  title text
);
```

2) Insérez des données dans votre table.
```cassandraql
INSERT INTO uv(id, code, title) VALUES (0, 'UV_0', 'Proto unité de valeur');
INSERT INTO uv(id, code, title) VALUES (1, 'UV_1', 'Première unité de valeur');
INSERT INTO uv(id, code, title) VALUES (2, 'UV_2', 'Seconde unité de valeur');
```

3) Récupérez les données de l'UV dont l'id vaut 1.
```cassandraql
SELECT * FROM uv WHERE id = 1;
```

4) Modifiez la table pour ajouter un attribut type indiquant si l'UV est une CS ou TM.
```cassandraql
ALTER TABLE uv ADD uv_type text;
```

5) Faire la modification en conséquence sur chaque ligne de la table.
```cassandraql
UPDATE uv SET uv_type = 'CS' WHERE id = 1;
UPDATE uv SET uv_type = 'TM' WHERE id = 2;
```

# Exercice 3
On garde la table UV mais on ajoute une relation 1..n entre une UV et des étudiants.

1) Comment va-t-on gérer dans Cassandra les étudiants de chaque UV ?
 
Est-ce qu'on crée une table d'étudiants ?

2) Modifiez la table pour ajouter une collection d'étudiants. Un étudiant sera caractérisé par un champ login de type text.
```cassandraql
ALTER TABLE uv ADD students set<text>;
```
3) Mettez à jour la collection d'étudiants en ajoutant des étudiants dans les UVs.
```cassandraql
UPDATE uv SET students=students+{'etudiant_1'} WHERE id = 0;
UPDATE uv SET students=students+{'etudiant_2'} WHERE id = 1;
UPDATE uv SET students=students+{'etudiant_3'} WHERE id > -1;
```
4) Cherchez les lignes dont la colonne 'etudiants' contient une des valeurs que vous avez renseigné.
```cassandraql
SELECT * FROM uv WHERE students CONTAINS 'etudiant_2' ALLOW FILTERING;
```

5) Enlevez l'un des étudiants d'une UV, puis enlevez les tous.
```cassandraql
UPDATE uv SET students=students-{'etudiant_2'} WHERE id = 1;
DELETE students FROM uv WHERE id IN (0, 1, 2);
```

6) Nous voulons maintenant ajouter des informations supplémentaires pour chaque UV : nous voulons connaître les personnes qui assurent les différents rôles de responsable de l'UV, enseignant et chargé de TD dans l'UV.
```cassandraql
ALTER TABLE uv ADD role map<text, text>;
```

7) Alimenter les responsables pour une des UVS.
```cassandraql
UPDATE uv SET role=role+{'responsable': 'Marky', 'enseignant': 'Mark'} WHERE id IN (1, 2);
UPDATE uv SET role=role+{'chargetd': 'Pierre'} WHERE id IN (0, 2);
```

8) Quelle est la quantité d'UVs de type 'CS' ?
```cassandraql
CREATE INDEX uv_type_index ON uv(uv_type);
SELECT COUNT(1) FROM uv WHERE uv_type='CS';
```

9) Quelle est la quantité d'UVs par type?
```cassandraql
Ce n'est pas possible de répondre à la question avec ce modèle.
```
