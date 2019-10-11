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

