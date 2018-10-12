# Exercice 1

**Collection**: restaurants

1. Retourner un restaurant qui a un grade avec un score supérieur à 7.
    ```javascript
    db.restaurants.findOne({"grades.score":{$gt: 7}});
    ```
2. Retourner un restaurant qui a un grade (champ grade) à A avec un score supérieur à 10.
    ```javascript
    db.restaurants.find({$and : [ 
        { "grades": { $elemMatch: {"grade" : "A", "score": {$gt: 10} } }}
    ]});
    db.restaurants.find(
        { "grades": { $elemMatch: {"grade" : "A", "score": {$gt: 10} } }}
    );
    ```
3. Retourner un restaurant qui a un grade avec une date égale à `2012-03-27T00:00:00Z`, un grade (champ grade) A, un score supérieur ou égal à 10 et dont le tableau grade à une taille de 5.
    ```javascript
    db.restaurants.findOne({$and : [ 
        { "grades": { $elemMatch: 
            { "date": ISODate("2012-03-27T00:00:00Z"), "grade" : "A",
            "score": {$gte: 10} } }
        },
        { "grades": {$size : 5 }} 
    ]});
    ```
4. La même chose de précédemment (3.) en projetant le premier élément du tableau qui matche la condition.
    ```javascript
    db.restaurants.findOne({$and : [ 
         { "grades": 
           { $elemMatch: 
             { 
               "date": ISODate("2012-03-27T00:00:00Z"),
               "grade" : "A",
               "score": {$gte: 10} 
             }
           }
         },
         { "grades": {$size : 5 }}   
       ]},
       { "grades.$": 1}
    );
    ```
5. Rechercher un restaurant avec un grade à A et un grade à Z.
    ```javascript
    db.restaurants.findOne({"grades.grade":{$all:["A","Z"]}});
    db.restaurants.findOne({"grades.grade":"A","grades.grade":"Z"});
    ```

6. Executer les requêtes suivantes et analyser:
    ```javascript
    db.restaurants.find({"grades.grade":{$all:["A","Z"]}}).toArray().length;
    db.restaurants.find({$and:[{"grades.grade":"A"},{"grades.grade":"Z"}]}).toArray().length; 
    db.restaurants.find({"grades.grade":"A","grades.grade":"Z"}).toArray().length;
    db.restaurants.find({"grades.grade": {$eq : "A","$eq":"Z"}}).toArray().length;
    db.restaurants.find({ "grades": { $elemMatch: { grade: "A", grade: "Z" } } }).toArray().length;
    ```

---

# Exercice 2

**Copier la collection**: restaurants `robo3t -> clic droit sur la collection test.restaurants -> dupliquer la collection`

1. Mettre à jour le champ "cuisine" d'un document en faisant une recherche sur l'ObjectId de votre souhait.
    ```javascript
    db.restaurants.update({_id: ObjectId("57bd62688acc811fe3b3826c")}, { $set: {"cuisine": "test"} })
    ```

2. Mettre à jour tous les documents ayant comme valeur pour le champ cuisine: `CafÃ©/Coffee/Tea` par `Café/Coffee/Tea`.
    ```javascript
    db.restaurants.update( {"cuisine": "CafÃ©/Coffee/Tea"}, { $set : {"cuisine": "Café/Coffee/Tea"}}, {multi: true})
    ```

3. Ajouter un élément au tableau grades à un restaurant.
    ```javascript
    db.restaurants.update({_id: ObjectId("59d76ea4ce2dc4fbe7e91d59")}, { $push: { "grades": { "date" : ISODate("2011-10-19T00:00:00Z"), "grade" : "A", "score" : 13 }  } } )
    ```

4. Ajouter un nouvel élément au tableau grades de ce restaurant en ne gardant que les 4 meilleurs scores.
    ```javascript
    db.restaurants.update({_id: ObjectId("57bd62688acc811fe3b3826c")},{$push:{"grades": {$each : [ {"date" : ISODate("2011-10-19T00:00:00Z"), "grade" : "A", "score" : 14 }] , $sort: {"score": -1}, $slice : 3 } }} )
    ```

---

# Exercice 3

**Collection**: zips

1. Calculer la population par état et garder que ceux dont la population est supérieur à 10 millions.

2. Pour chaque état, sous la forme `{"biggestCity" : { "name" : "WORCESTER", "pop" : 169856 },"smallestCity" : { "name" : "BUCKLAND", "pop" : 16 },"state" : "MA"}` la ville avec: 
 * la plus grosse population ainsi que son nom
 * la plus petite population ainsi que son nom 

---

# Exercice 4

**Copier la collection**: grades `robo3t -> clic droit sur la collection test.grades -> dupliquer la collection`

1) Calculer la moyenne de chaque étudiant

2) Calculer la moyenne de chaque classe en ayant préalablement calculé la moyenne de chaque étudiant

3) La même chose que précédemment (2.) en effectuant les modifications suivantes: 
    * Supprimer la plus mauvaise note de chaque étudiant.
    * Faire une jointure avec la collection classe pour récupérer son nom.
    * Enregistrer le résultat dans une autre collection sous la forme: `{ "avgClass" : 55.970072353204436, "className" : "classe 5" }`

---

# Exercice 5

**Copier la collection**: grades `robo3t -> clic droit sur la collection test.grades -> dupliquer la collection`

1. Mettre à jour la collection grades en supprimant la plus mauvaise note de type homework de chaque étudiant.
