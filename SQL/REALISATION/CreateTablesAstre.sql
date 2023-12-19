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
-- DROP TABLE IF EXISTS AffectationETAT;
-- DROP TABLE IF EXISTS ModulesCatHeuresETAT;
-- DROP TABLE IF EXISTS ModulesETAT;
-- DROP TABLE IF EXISTS IntervenantsETAT;
-- DROP TABLE IF EXISTS SemestresETAT;
-- DROP TABLE IF EXISTS CategorieHeuresETAT;
-- DROP TABLE IF EXISTS CategorieIntervenantsETAT;

-- Création des tables ayant un niveau de liaison 1
CREATE TABLE IF NOT EXISTS CategorieIntervenantsETAT
(
    codeCatInt     VARCHAR(255) PRIMARY KEY, 
    libCatInt      VARCHAR(255), 
    coefCatInt     FLOAT DEFAULT 1 CHECK (coefCatInt >= 0) ,
    heureMinCatInt INTEGER NOT NULL CHECK (heureMinCatInt >= 0),
    heureMaxCatInt INTEGER NOT NULL CHECK (heureMaxCatInt >= heureMinCatInt)
);

CREATE TABLE IF NOT EXISTS CategorieHeuresETAT
(
    libCatHeur  VARCHAR(255) PRIMARY KEY, 
    coefCatHeur FLOAT DEFAULT 1.0 CHECK (coefCatHeur >= 0)
);

-- Création de la table Semestres
CREATE TABLE IF NOT EXISTS SemestresETAT
(
    numSem    INTEGER NOT NULL PRIMARY KEY,
    nbGpTdSem INTEGER DEFAULT 0 CHECK (nbGpTdSem >= 0),
    nbGpTpSem INTEGER DEFAULT 0 CHECK (nbGpTpSem >= 0),
    nbEtdSem  INTEGER DEFAULT 0 CHECK (nbEtdSem >= 0),
    nbSemSem  INTEGER DEFAULT 0 CHECK (nbSemSem >= 0)
);

-- Création des tables ayant un niveau de liaison 2
CREATE TABLE IF NOT EXISTS ModulesETAT
(
    codeMod     VARCHAR(255) PRIMARY KEY, 
    semMod      INTEGER      REFERENCES SemestresETAT(numSem),
    typeMod     VARCHAR(255) NOT NULL CHECK (typeMod IN ('Ressource', 'Sae', 'Stage', 'PPP')),
    libCourtMod VARCHAR(255) NOT NULL,
    libLongMod  VARCHAR(255) NOT NULL,
    validMod    BOOLEAN NOT NULL DEFAULT false,
    nbHeurPonc  INTEGER DEFAULT 0 NOT NULL
);

CREATE TABLE IF NOT EXISTS IntervenantsETAT
(
    nomInt      VARCHAR(255) NOT NULL,
    prenomInt   VARCHAR(255) NOT NULL,
    heureMinInt INTEGER NOT NULL CHECK (heureMinInt >= 0),
    heureMaxInt INTEGER NOT NULL CHECK (heureMaxInt >= heureMinInt),
    coefInt     FLOAT DEFAULT 1 CHECK  (coefInt >= 0),
    categInt    VARCHAR(255) REFERENCES CategorieIntervenantsETAT(codeCatInt),
	PRIMARY KEY (nomInt, prenomInt)
);


-- Création des tables ayant un niveau de liaison 3
CREATE TABLE IF NOT EXISTS ModulesCatHeuresETAT
(
	codeMod    VARCHAR(255) NOT NULL REFERENCES ModulesETAT(codeMod),
	libCatHeur VARCHAR(255) NOT NULL REFERENCES CategorieHeuresETAT(libCatHeur),
	nbHeurePN  INTEGER NOT NULL CHECK (nbHeurePN >= 0),
	nbHeureSem INTEGER NOT NULL CHECK (nbHeureSem >= 0),
	nbSemaine  INTEGER NOT NULL CHECK (nbSemaine >= 0),
	PRIMARY KEY(codeMod, libCatHeur)
);

CREATE TABLE IF NOT EXISTS AffectationETAT
(
    cle         SERIAL       PRIMARY KEY,
	nomInt      VARCHAR(255) NOT NULL,
	prenomInt   VARCHAR(255) NOT NULL,
	codeMod     VARCHAR(255) NOT NULL REFERENCES ModulesETAT(codeMod),
	libCatHeur  VARCHAR(255) NOT NULL REFERENCES CategorieHeuresETAT(libCatHeur),
	nbSem       INTEGER NOT NULL CHECK (nbSem >= 0),
	nbGroupe    INTEGER NOT NULL CHECK (nbGroupe >= 0),
	commentaire VARCHAR(255),
	FOREIGN KEY(nomInt, prenomInt) REFERENCES IntervenantsETAT(nomInt, prenomInt)
);

--Générer 6 semestres si il y a rien
INSERT INTO SemestresETAT (numSem)
SELECT numSem FROM generate_series(1, 6) numSem
ON CONFLICT (numSem) DO NOTHING;


--Générer les catégories d'heures si il y a rien
INSERT INTO CategorieHeuresETAT (libCatHeur, coefCatHeur)
VALUES      ('CM'   , 1.5),
            ('TD'   , 1.0),
            ('TP'   , 1.0),
            ('Tut'  , 1.0),
            ('REH'  , 1.0),
            ('Sae'  , 1.0),
            ('HP'   , 1.0),
            ('Autre', 1.0)
ON CONFLICT (libCatHeur) DO NOTHING;