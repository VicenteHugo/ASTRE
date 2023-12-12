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




-- Suppressions des tables
DROP TABLE IF EXISTS Affectation;
DROP TABLE IF EXISTS ModulesCatHeures;
DROP TABLE IF EXISTS Modules;
DROP TABLE IF EXISTS Intervenants;
DROP TABLE IF EXISTS Semestres;
DROP TABLE IF EXISTS CategorieHeures;
DROP TABLE IF EXISTS CategorieIntervenants;
DROP TABLE IF EXISTS Etat;


-- Création des tables ayant un niveau de liaison 0
CREATE TABLE Etat 
(
	libEtat  VARCHAR(25) PRIMARY KEY,
	dateCrea DATE DEFAULT CURRENT_DATE
);

-- Création des tables ayant un niveau de liaison 1
CREATE TABLE CategorieIntervenants
(
	libCatInt      VARCHAR(10), 
	coefCatInt     FLOAT DEFAULT 1 CHECK (coefCatInt > 0) ,
	heureMinCatInt INTEGER NOT NULL CHECK (heureMinCatInt > 0)               ,
	heureMaxCatInt INTEGER NOT NULL CHECK (heureMaxCatInt >= heureMinCatInt) ,
	etat           VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(libCatInt, etat)
);

CREATE TABLE CategorieHeures
(
	libCatHeur VARCHAR(10) PRIMARY KEY, 
	coefCatHeur FLOAT DEFAULT 1.0 CHECK (coefCatHeur > 0)
);

CREATE TABLE Semestres
(
	numSem    INTEGER PRIMARY KEY,
	nbGpTdSem INTEGER NOT NULL CHECK (nbGpTdSem > 0),
	nbGpTpSem INTEGER NOT NULL CHECK (nbGpTpSem > 0),
	nbEtdSem  INTEGER NOT NULL CHECK (nbEtdSem > 0) ,
	nbSemSem  INTEGER NOT NULL CHECK (nbSemSem > 0) 
);

-- Création des tables ayant un niveau de liaison 2
CREATE TABLE Modules
(
	codeMod     VARCHAR(10) PRIMARY KEY, 
	semMod      INTEGER NOT NULL REFERENCES Semestres(numSem),
	typeMod     VARCHAR(11) NOT NULL CHECK (typeMod IN ('Ressource', 'Sae', 'Stage', 'PPP')),
	libCourtMod VARCHAR(20) NOT NULL,
	libLongMod  VARCHAR(50) NOT NULL,
	validMod    BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE Intervenants
(
	nomInt      VARCHAR(20) NOT NULL,
	prenomInt   VARCHAR(20) NOT NULL,
	heureMinInt INTEGER NOT NULL CHECK (heureMinInt > 0) ,
	heureMaxInt INTEGER NOT NULL CHECK (heureMaxInt >= heureMinInt),
	categInt    VARCHAR(10) NOT NULL REFERENCES CategorieIntervenants(libCatInt),
	PRIMARY KEY(nomInt, prenomInt)
);

-- Création des tables ayant un niveau de liaison 3
CREATE TABLE ModulesCatHeures
(
	codeMod    VARCHAR(10) NOT NULL REFERENCES Modules(codeMod),
	libCatHeur VARCHAR(10) NOT NULL REFERENCES CategorieHeures(libCatHeur),
	nbHeurPonc INTEGER DEFAULT 0    NOT NULL,
	nbHeurePN  INTEGER NOT NULL CHECK (nbHeurePN > 0),
	nbHeureSem INTEGER NOT NULL CHECK (nbHeureSem > 0),
	nbSemaine  INTEGER NOT NULL CHECK (nbSemaine > 0),
	PRIMARY KEY(codeMod, libCatHeur)
);

CREATE TABLE Affectation
(
	intervenant VARCHAR(20) NOT NULL REFERENCES Intervenants(nomInt, prenomInt),
	codeMod     VARCHAR(10) NOT NULL REFERENCES Modules(codeMod),
	libCatHeur  VARCHAR(10) NOT NULL REFERENCES CategorieHeures(libCatHeur),
	nbHeureSem  INTEGER NOT NULL CHECK (nbHeureSem > 0),
	nbGroupe    INTEGER NOT NULL CHECK (nbGroupe > 0),
	PRIMARY KEY(intervenant, codeMod, libCatHeur)
);
