-- Suppression des données des tables
DELETE FROM Affectation;
DELETE FROM ModulesCatHeures;
DELETE FROM Modules;
DELETE FROM Intervenants;
DELETE FROM Semestres;
DELETE FROM CategorieHeures;
DELETE FROM CategorieIntervenants;
DELETE FROM Etat;




INSERT INTO Etat (libEtat) VALUES ('En cours');
INSERT INTO Etat (libEtat) VALUES ('Terminé');
-- Ajoute d'autres états si nécessaire


INSERT INTO CategorieIntervenants (libCatInt, coefCatInt, heureMinCatInt, heureMaxCatInt, etat)
VALUES ('PROF', 1.5, 10, 20, 'En cours');
-- Ajoute d'autres catégories d'intervenants si nécessaire


INSERT INTO CategorieHeures (libCatHeur, coefCatHeur) VALUES ('CM', 1.2);
INSERT INTO CategorieHeures (libCatHeur, coefCatHeur) VALUES ('TD', 1.0);
-- Ajoute d'autres catégories d'heures si nécessaire


INSERT INTO Semestres (numSem, nbGpTdSem, nbGpTpSem, nbEtdSem, nbSemSem)
VALUES (1, 5, 4, 100, 4);
-- Ajoute d'autres semestres si nécessaire


INSERT INTO Modules (codeMod, semMod, typeMod, libCourtMod, libLongMod, validMod)
VALUES ('R1.01', 1, 'Ressource', 'Ressource', 'Long1', true);
-- Ajoute d'autres modules si nécessaire


INSERT INTO Intervenants (nomInt, prenomInt, heureMinInt, heureMaxInt, categInt)
VALUES ('Nom1', 'Prenom1', 15, 30, 'PROF');
-- Ajoute d'autres intervenants si nécessaire


INSERT INTO ModulesCatHeures (codeMod, libCatHeur, nbHeurePN, nbHeureSem, nbSemaine)
VALUES ('R1.01', 'CM', 20, 10, 2);
-- Ajoute d'autres liaisons entre modules et catégories d'heures si nécessaire


INSERT INTO Affectation (intNom, intPrenom, codeMod, libCatHeur, nbSem, nbGroupe)
VALUES ('Nom1', 'Prenom1', 'R1.01', 'CM', 8, 2);
-- Ajoute d'autres affectations si nécessaire