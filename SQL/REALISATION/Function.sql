-- Insérer des données dans la table Etat
INSERT INTO Etat (etat) VALUES ('En cours');
INSERT INTO Etat (etat, dateCrea) VALUES ('Finit', '2023-12-12');

-- Insérer des données dans la table CategorieIntervenants
INSERT INTO CategorieIntervenants (codeCatInt, libCatInt, coefCatInt, heureMinCatInt, heureMaxCatInt, etat)
VALUES ('C1', 'Catégorie 1', 1.5, 10, 20, 'En cours');

-- Insérer des données dans la table CategorieHeures
INSERT INTO CategorieHeures (libCatHeur, coefCatHeur, etat)
VALUES ('CM', 1.2, 'En cours');

-- Insérer des données dans la table Semestres
INSERT INTO Semestres (numSem, nbGpTdSem, nbGpTpSem, nbEtdSem, nbSemSem, etat)
VALUES (1, 2, 1, 50, 15, 'En cours');

-- Insérer des données dans la table Modules
INSERT INTO Modules (codeMod, semMod, typeMod, libCourtMod, libLongMod, validMod, nbHeurPonc, etat)
VALUES ('M1', 1, 'Ressource', 'Mod1', 'Module 1', true, 10, 'En cours');

-- Insérer des données dans la table Intervenants
INSERT INTO Intervenants (nomInt, prenomInt, heureMinInt, heureMaxInt, categInt, etat)
VALUES ('Doe', 'John', 15, 25, 'C1', 'En cours');

-- Insérer des données dans la table ModulesCatHeures
INSERT INTO ModulesCatHeures (codeMod, libCatHeur, nbHeurePN, nbHeureSem, nbSemaine, etat)
VALUES ('M1', 'CM', 5, 20, 10, 'En cours');

-- Insérer des données dans la table Affectation
INSERT INTO Affectation (intNom, intPrenom, codeMod, libCatHeur, nbSem, nbGroupe, commentaire, etat)
VALUES ('Doe', 'John', 'M1', 'CM', 1, 1, 'Affectation en cours', 'En cours');
