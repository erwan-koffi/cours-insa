# Exemple

Se rendre sur http://trivisa.fr:7474

1. Créer un étudiant ayant comme propriétés un `nom`, un `prénom` ainsi qu'un `age`

i.e. `CREATE (jd:Person {name: "John", lastname: "Doe", age: 23})`

2. Créer plusieurs (min. 3) étudiants en une seule fois.

i.e. `CREATE (jd:Person {name: "Jane", lastname: "Doe", age: 24}), (pm:Person {name: "Pierre", lastname: "Martin", age: 24})`

3. Créer une classe avec un `nom` et une `année`.

4. Afficher tous les noeuds précédement créés à l'aide de la commande suivante: `MATCH (ee) RETURN ee;`

5. N'afficher que la classe.

i.e. `MATCH (ee:Person) RETURN ee`

6. N'afficher que les étudiant ayant un nom en particulier.

i.e. `MATCH (ee:Person) WHERE ee.lastname = "Doe" RETURN ee`
i.e. `MATCH (ee:Person {lastname = "Doe"}) RETURN ee`

7. Relier certains étudiants précédemment créés à la classe créée précedemment.

i.e.
```
MATCH (ee:Person), (ac:Activity) WHERE ee.name = "John" AND activity.name = "music"
CREATE (ee)-[:PLAYS {since: 2005}]->(aa)
```

8. N'afficher que les étudiants appartenant à la classe.

i.e. `MATCH(ee:Person)-[:PLAYS]-(activity) RETURN ee, activity`

9. N'afficher que les étudiants ayant un nom en particulier appartenant à la classe.

10. Créer une deuxième classe et lui assigner certains étudiants n'ayant pas été précédemment assignés ansi que certains ayant déjà été assignés.

11. Créer une nouvelle relation entre certains étudiant représentant une amitié.

12. Ajouter une propriété à la relation.

i.e.
```
MATCH (a {name:"John"})-[r]-(b {name:"Doe"})
SET r.age = 36
```

13. Retrouver tous les étudiants qui se connaissent par le biais d'une classe.

`MATCH (:Person)-[:PLAYS]-()-[:LISTENS]-(person) RETURN person;`

14. Supprimer un étudiant sans classe ni relation d'amitié.

i.e.
```
MATCH (n:Person { name: 'UNKNOWN' })
DELETE n
```

15. Supprimer une relation d'amitié entre étudiants.

i.e.
```
MATCH (n { name: 'Andy' })-[r:LISTENS]->()
DELETE r
```

16. Supprimer un étudiant ayant une classe et/ou une relation d'amitié.

i.e.
```
MATCH (s {nom: "Marky"})
DETACH DELETE s
```
