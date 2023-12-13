/*
* @title  : CreateTablesAstre
* @author : SHOAL™
* @date   : 06/12/2023
* @sub    : Création des tables 
*
*
* MLD : 
	* Etat
	* CategorieIntervenants(libCatInt, coefCatInt, heureMinCatInt, heureMaxCatInt)
	* CategorieHeures      (libCatHeur, coefCatHeur)
	* Semestres            (numSem, nbGpTdSem, nbGpTpSem, nbEtdSem, nbSemSem)

	* Intervenants         (nomInt, prenomInt, heureMinInt, heureMaxInt, #codeCatInt)
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
	etat  VARCHAR(25) PRIMARY KEY,
	dateCrea DATE DEFAULT CURRENT_DATE
);

-- Création des tables ayant un niveau de liaison 1
CREATE TABLE CategorieIntervenants
(
	codeCatInt     VARCHAR(10), 
	libCatInt      VARCHAR(10), 
	coefCatInt     FLOAT DEFAULT 1 CHECK (coefCatInt > 0) ,
	heureMinCatInt INTEGER NOT NULL CHECK (heureMinCatInt > 0)               ,
	heureMaxCatInt INTEGER NOT NULL CHECK (heureMaxCatInt >= heureMinCatInt) ,
	etat           VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(codeCatInt, etat)
);

CREATE TABLE CategorieHeures
(
	libCatHeur  VARCHAR(10), 
	coefCatHeur FLOAT DEFAULT 1.0 CHECK (coefCatHeur > 0),
	etat        VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(libCatHeur, etat)
);

CREATE TABLE Semestres
(
	numSem    INTEGER NOT NULL,
	nbGpTdSem INTEGER NOT NULL CHECK (nbGpTdSem > 0),
	nbGpTpSem INTEGER NOT NULL CHECK (nbGpTpSem > 0),
	nbEtdSem  INTEGER NOT NULL CHECK (nbEtdSem > 0) ,
	nbSemSem  INTEGER NOT NULL CHECK (nbSemSem > 0) ,
	etat      VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(numSem, etat)
);

-- Création des tables ayant un niveau de liaison 2
CREATE TABLE Modules
(
	codeMod     VARCHAR(10) , 
	semMod      INTEGER NOT NULL REFERENCES Semestres(numSem),
	typeMod     VARCHAR(11) NOT NULL CHECK (typeMod IN ('Ressource', 'Sae', 'Stage', 'PPP')),
	libCourtMod VARCHAR(20) NOT NULL,
	libLongMod  VARCHAR(50) NOT NULL,
	validMod    BOOLEAN NOT NULL DEFAULT false,
	nbHeurPonc  INTEGER DEFAULT 0    NOT NULL,
	etat        VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(codeMod, etat)
);

CREATE TABLE Intervenants
(
	nomInt      VARCHAR(20) NOT NULL,
	prenomInt   VARCHAR(20) NOT NULL,
	heureMinInt INTEGER NOT NULL CHECK (heureMinInt > 0) ,
	heureMaxInt INTEGER NOT NULL CHECK (heureMaxInt >= heureMinInt),
	categInt    VARCHAR(10) NOT NULL REFERENCES CategorieIntervenants(codeCatInt),
	etat        VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(nomInt, prenomInt, etat)
);

-- Création des tables ayant un niveau de liaison 3
CREATE TABLE ModulesCatHeures
(
	codeMod    VARCHAR(10) NOT NULL REFERENCES Modules(codeMod),
	libCatHeur VARCHAR(10) NOT NULL REFERENCES CategorieHeures(libCatHeur),
	nbHeurePN  INTEGER NOT NULL CHECK (nbHeurePN > 0),
	nbHeureSem INTEGER NOT NULL CHECK (nbHeureSem > 0),
	nbSemaine  INTEGER NOT NULL CHECK (nbSemaine > 0),
	etat       VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(codeMod, libCatHeur, etat)
);

CREATE TABLE Affectation
(
	intNom      VARCHAR(20) NOT NULL REFERENCES Intervenants(nomInt),
	intPrenom   VARCHAR(20) NOT NULL REFERENCES Intervenants(prenomInt),
	codeMod     VARCHAR(10) NOT NULL REFERENCES Modules(codeMod),
	libCatHeur  VARCHAR(10) NOT NULL REFERENCES CategorieHeures(libCatHeur),
	nbSem       INTEGER NOT NULL CHECK (nbSem > 0),
	nbGroupe    INTEGER NOT NULL CHECK (nbGroupe > 0),
	commentaire VARCHAR(255),
	etat        VARCHAR(25) REFERENCES Etat(libEtat),
	PRIMARY KEY(intNom, intPreNom, codeMod, libCatHeur, etat)
);
