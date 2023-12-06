/*
* @title  : CreateTablesAstre
* @author : SHOAL™
* @date   : 06/12/2023
* @sub    : Création des tables 
*
*
* MLD : 
	* CategorieIntervenants(libCatInt, coefCatInt, heureMinCatInt, heureMaxCatInt)
	* CategorieHeures      (libCatHeur, coefCatHeur)
	* Semestres            (numSem, nbGpTdSem, nbGpTpSem, nbEtdSem, nbSemSem)

	* Intervenants         (nomInt, prenomInt, heureMinInt, heureMaxInt, #libCatInt)
	* Modules              (codeMod, typeMod, libCourtMod, libLongMod, validMod, #numSem)

	* ModulesCatHeures     (#codeMod, #libCatHeur, nbHeurePN, nbHeureSem, nbSemaine)
	* Affectation          (#(nomInt,prenomInt),#codeMod,#libCatHeur, nbHeureSem, nbGroupe, nbSemaine, commentaire)

*/




-- Suppresions des tables
DROP TABLE IF EXISTS Affectation;

DROP TABLE IF EXISTS ModulesCatHeures;
DROP TABLE IF EXISTS Modules;
DROP TABLE IF EXISTS Intervenants;

DROP TABLE IF EXISTS Semestres;
DROP TABLE IF EXISTS CategorieHeures;
DROP TABLE IF EXISTS CategorieIntervenants;




-- Creation des tables ayant un niveau de liaison 0

CREATE TABLE CategorieIntervenants
(
	libCatInt      VARCHAR(10) PRIMARY KEY, 
	coefCatInt     FLOAT       CHECK (coefCatInt > 0)                   DEFAULT 1.0,
	heureMinCatInt INTEGER     CHECK (heureMinCatInt > 0)               NOT NULL,
	heureMaxCatInt INTEGER     CHECK (heureMaxCatInt >= heureMinCatInt) NOT NULL
);

CREATE TABLE CategorieHeures
(
	libCatHeur  VARCHAR(10) PRIMARY KEY, 
	coefCatHeur FLOAT       CHECK (coefCatHeur > 0) DEFAULT 1.0
);

CREATE TABLE Semestres
(
	numSem    INTEGER PRIMARY KEY,
	nbGpTdSem INTEGER CHECK ( nbGpTdSem > 0) NOT NULL,
	nbGpTpSem INTEGER CHECK ( nbGpTpSem > 0) NOT NULL,
	nbEtdSem  INTEGER CHECK ( nbEtdSem  > 0) NOT NULL,
	nbSemSem  INTEGER CHECK ( nbSemSem  > 0) NOT NULL
);




-- Creation des tables ayant un niveau de liaison 1

CREATE TABLE Modules
(
	codeMod     VARCHAR(10) PRIMARY KEY, 
	semMod      INTEGER     NOT NULL REFERENCES Semestres(numSem),
	typeMod     VARCHAR(11) NOT NULL CHECK (typeMod IN ('Ressource','SAE','Stage/Suivi')),
	libCourtMod VARCHAR(20) NOT NULL,
	libLongMod  VARCHAR(50) NOT NULL,
	validMod    BOOLEAN     NOT NULL DEFAULT false
);

CREATE TABLE Intervenants
(
	nomInt      VARCHAR(20) NOT NULL,
	prenomInt   VARCHAR(20) NOT NULL,
	heureMinInt INTEGER     CHECK (heureMinInt > 0)            NOT NULL,
	heureMaxInt INTEGER     CHECK (heureMaxInt >= heureMinInt) NOT NULL,
	categInt    NOT NULL    REFERENCES CategorieIntervenants(libCatInt),
	PRIMARY KEY (nomInt, prenomInt)
);




-- Creation des tables ayant un niveau de liaison 1

CREATE TABLE ModulesCatHeures
(
	codeMod    VARCHAR(10) NOT NULL REFERENCES Modules(codeMod),
	libCatHeur VARCHAR(10) NOT NULL REFERENCES CategorieHeures(libCatHeur),
	nbHeurePN  INTEGER     NOT NULL CHECK      (nbHeurePN  > 0),
	nbHeureSem INTEGER     NOT NULL CHECK      (nbHeureSem > 0),
	nbSemaine  INTEGER     NOT NULL CHECK      (nbSemaine  > 0),

	PRIMARY KEY (codeMod, libCatHeur)
);

CREATE TABLE Affectation
(
	intervenant VARCHAR(20) NOT NULL REFERENCES Intervenants(nomInt, prenomInt),
	codeMod     VARCHAR(10) NOT NULL REFERENCES Modules(codeMod),
	libCatHeur  VARCHAR(10) NOT NULL REFERENCES CategorieHeures(libCatHeur),
	nbHeureSem  INTEGER     NOT NULL CHECK (nbHeureSem > 0),
	nbGroupe    INTEGER     NOT NULL CHECK (nbGroupe > 0),

	PRIMARY KEY (intervenant, codeMod, libCatHeur)
);

