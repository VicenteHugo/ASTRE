-- \copy (SELECT nom_c, COUNT(*) FROM Enfant e JOIN Centre c ON e.num_c= c.num_c GROUP BY num_c, nom_c) TO './sae2_04bis.csv' DELIMITER ',' CSV HEADER

-- Le tableau affichera pour chaque intervenant :
-- ▪ sa catégorie
-- ▪ son nom
-- ▪ son prénom
-- ▪ service dû
-- ▪ maximum d’heures autorisé
-- ▪ coefficient TP
-- ▪ total d’heures du prévisionnel par semestres impairs et total des heures du prévisionnel des semestres
-- impairs
-- ▪ total d’heures du prévisionnel par semestres pairs et total des heures du prévisionnel des semestres
-- pairs
-- ▪ total de toutes les heures du prévisionnel


SELECT i.nomInt,i.prenomInt,ci.libCatInt AS categorie,ci.coefCatInt AS coefficient,i.heureMinInt AS service_du,i.heureMaxInt AS maximum_heures_autorise,
    SUM(CASE WHEN s.numSem % 2 = 1 THEN mc.nbHeureSem ELSE 0 END) AS total_heures_impaires,
    SUM(CASE WHEN s.numSem % 2 = 0 THEN mc.nbHeureSem ELSE 0 END) AS total_heures_paires,
    SUM(mc.nbHeureSem) AS total_heures_previsionnel
FROM
    IntervenantsEtat1 i
JOIN CategorieIntervenantsEtat1 ci ON i.categInt = ci.codeCatInt
JOIN AffectationEtat1 a ON i.nomInt = a.nomInt AND i.prenomInt = a.prenomInt
JOIN ModulesEtat1 m ON a.codeMod = m.codeMod
JOIN ModulesCatHeuresEtat1 mc ON m.codeMod = mc.codeMod
JOIN SemestresEtat1 s ON m.semMod = s.numSem
GROUP BY
    i.nomInt,i.prenomInt,ci.libCatInt,ci.coefCatInt,i.heureMinInt,i.heureMaxInt;
