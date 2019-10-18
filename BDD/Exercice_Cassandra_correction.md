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
