
COPY (
SELECT 
i.nomInt      AS "Nom",
i.prenomInt   AS "Prenom",
ci.libCatInt  AS "categorie",
ci.coefCatInt AS "coefficient",
i.heureMinInt AS "service dû",
i.heureMaxInt AS "heure maximum autorisés",
SUM(CASE WHEN s.numSem = 1 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S1 (théo)",
SUM(CASE WHEN s.numSem = 1 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S1 (réel)",
SUM(CASE WHEN s.numSem = 3 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S3 (théo)",
SUM(CASE WHEN s.numSem = 3 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S3 (réel)",
SUM(CASE WHEN s.numSem = 5 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S5 (théo)",
SUM(CASE WHEN s.numSem = 5 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S5 (réel)",
SUM(CASE WHEN s.numSem % 2 = 1 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S Impaires (théo)",
SUM(CASE WHEN s.numSem % 2 = 1 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S Impaires (réel)",
SUM(CASE WHEN s.numSem = 2 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S2 (théo)",
SUM(CASE WHEN s.numSem = 2 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S2 (réel)",
SUM(CASE WHEN s.numSem = 4 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S4 (théo)",
SUM(CASE WHEN s.numSem = 4 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S4 (réel)",
SUM(CASE WHEN s.numSem = 6 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS "S6 (théo)",
SUM(CASE WHEN s.numSem = 6 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                             AS "S6 (réel)",
SUM(CASE WHEN s.numSem % 2 = 0 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS "S Impaires (théo)",
SUM(CASE WHEN s.numSem % 2 = 0 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur END)                                                                                                            AS "S Impaires (réel)",
SUM(CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END) AS "Semestres (théo)",
SUM(mc.nbHeureSem * ci.coefCatInt * coefCatHeur)                                                                                                     AS "Semestres (réel)"

FROM IntervenantsETAT i JOIN CategorieIntervenantsETAT ci ON i.categInt = ci.codeCatInt
                         JOIN AffectationETAT a ON i.nomInt = a.nomInt AND i.prenomInt = a.prenomInt
                         JOIN ModulesETAT m ON a.codeMod = m.codeMod
                         JOIN ModulesCatHeuresETAT mc ON m.codeMod = mc.codeMod
                         JOIN SemestresETAT s ON m.semMod = s.numSem
                         JOIN CategorieHeuresETAT ch ON mc.libCatHeur = ch.libCatHeur
GROUP BY
    i.nomInt, i.prenomInt, ci.libCatInt, ci.coefCatInt, i.heureMinInt, i.heureMaxInt) TO './ETAT.csv' DELIMITER ',' CSV HEADER;

