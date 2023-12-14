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
    codeCatInt     VARCHAR(10) PRIMARY KEY, 
    libCatInt      VARCHAR(10), 
    coefCatInt     FLOAT DEFAULT 1 CHECK (coefCatInt > 0) ,
    heureMinCatInt INTEGER NOT NULL CHECK (heureMinCatInt > 0),
    heureMaxCatInt INTEGER NOT NULL CHECK (heureMaxCatInt >= heureMinCatInt)
);

CREATE TABLE IF NOT EXISTS CategorieHeuresETAT
(
    libCatHeur  VARCHAR(10) PRIMARY KEY, 
    coefCatHeur FLOAT DEFAULT 1.0 CHECK (coefCatHeur > 0)
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
    codeMod     VARCHAR(10) PRIMARY KEY, 
    semMod      INTEGER,
    typeMod     VARCHAR(11) NOT NULL CHECK (typeMod IN ('Ressource', 'Sae', 'Stage', 'PPP')),
    libCourtMod VARCHAR(20) NOT NULL,
    libLongMod  VARCHAR(50) NOT NULL,
    validMod    BOOLEAN NOT NULL DEFAULT false,
    nbHeurPonc  INTEGER DEFAULT 0 NOT NULL
);

CREATE TABLE IF NOT EXISTS IntervenantsETAT
(
    nomInt      VARCHAR(20) NOT NULL,
    prenomInt   VARCHAR(20) NOT NULL,
    heureMinInt INTEGER NOT NULL CHECK (heureMinInt > 0),
    heureMaxInt INTEGER NOT NULL CHECK (heureMaxInt >= heureMinInt),
    categInt    VARCHAR(10) REFERENCES CategorieIntervenantsETAT(codeCatInt),
	PRIMARY KEY (nomInt, prenomInt)
);


-- Création des tables ayant un niveau de liaison 3
CREATE TABLE IF NOT EXISTS ModulesCatHeuresETAT
(
	codeMod    VARCHAR(10) NOT NULL REFERENCES ModulesETAT(codeMod),
	libCatHeur VARCHAR(10) NOT NULL REFERENCES CategorieHeuresETAT(libCatHeur),
	nbHeurePN  INTEGER NOT NULL CHECK (nbHeurePN > 0),
	nbHeureSem INTEGER NOT NULL CHECK (nbHeureSem > 0),
	nbSemaine  INTEGER NOT NULL CHECK (nbSemaine > 0),
	PRIMARY KEY(codeMod, libCatHeur)
);

CREATE TABLE IF NOT EXISTS AffectationETAT
(
	nomInt      VARCHAR(20) NOT NULL,
	prenomInt   VARCHAR(20) NOT NULL,
	codeMod     VARCHAR(10) NOT NULL REFERENCES ModulesETAT(codeMod),
	libCatHeur  VARCHAR(10) NOT NULL REFERENCES CategorieHeuresETAT(libCatHeur),
	nbSem       INTEGER NOT NULL CHECK (nbSem > 0),
	nbGroupe    INTEGER NOT NULL CHECK (nbGroupe > 0),
	commentaire VARCHAR(255),
	PRIMARY KEY(nomInt, prenomInt, codeMod, libCatHeur),
	FOREIGN KEY(nomInt, prenomInt) REFERENCES IntervenantsETAT(nomInt, prenomInt)
);





-- Création de la fonctions pour les semestres

-- CREATE TRIGGER InsertSixSemestres
-- AFTER INSERT ON Etat
-- FOR EACH ROW
-- BEGIN
--     DECLARE i INT DEFAULT 1;

--     WHILE i <= 6 DO
--         INSERT INTO Semestres (numSem, etat) VALUES (i, NEW.etat);
--         SET i = i + 1;
--     END WHILE;
-- END ;


