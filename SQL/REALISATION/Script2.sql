/*
* @title  : CreateTablesAstre
* @author : SHOAL™
* @date   : 10/01/2024
* @sub    : Insertion de table données
*/
DROP TABLE IF EXISTS AffectationEtat2;
DROP TABLE IF EXISTS ModulesCatHeuresEtat2;
DROP TABLE IF EXISTS ModulesEtat2;
DROP TABLE IF EXISTS IntervenantsEtat2;
DROP TABLE IF EXISTS SemestresEtat2;
DROP TABLE IF EXISTS CategorieHeuresEtat2;
DROP TABLE IF EXISTS CategorieIntervenantsEtat2;

-- Création des tables ayant un niveau de liaison 1
CREATE TABLE IF NOT EXISTS CategorieIntervenantsEtat2
(
    codeCatInt     VARCHAR(255) PRIMARY KEY , 
    libCatInt      VARCHAR(255), 
    coefCatInt     FLOAT DEFAULT 1 CHECK (coefCatInt >= 0) ,
    heureMinCatInt INTEGER NOT NULL CHECK (heureMinCatInt >= 0),
    heureMaxCatInt INTEGER NOT NULL CHECK (heureMaxCatInt >= heureMinCatInt)
);

CREATE TABLE IF NOT EXISTS CategorieHeuresEtat2
(
    libCatHeur  VARCHAR(255) PRIMARY KEY UNIQUE, 
    coefCatHeur FLOAT DEFAULT 1.0 CHECK (coefCatHeur >= 0)
);

-- Création de la table Semestres
CREATE TABLE IF NOT EXISTS SemestresEtat2
(
    numSem    INTEGER NOT NULL PRIMARY KEY,
    nbGpTdSem INTEGER DEFAULT 0 CHECK (nbGpTdSem >= 0),
    nbGpTpSem INTEGER DEFAULT 0 CHECK (nbGpTpSem >= 0),
    nbEtdSem  INTEGER DEFAULT 0 CHECK (nbEtdSem >= 0),
    nbSemSem  INTEGER DEFAULT 0 CHECK (nbSemSem >= 0)
);

-- Création des tables ayant un niveau de liaison 2
CREATE TABLE IF NOT EXISTS ModulesEtat2
(
    codeMod     VARCHAR(255) PRIMARY KEY, 
    semMod      INTEGER      REFERENCES SemestresEtat2(numSem),
    typeMod     VARCHAR(255) NOT NULL CHECK (typeMod IN ('Ressource', 'Sae', 'Stage', 'PPP')),
    libCourtMod VARCHAR(255) NOT NULL,
    libLongMod  VARCHAR(255) NOT NULL,
    validMod    BOOLEAN NOT NULL DEFAULT false,
    nbHeurPonc  INTEGER DEFAULT 0 NOT NULL
);

CREATE TABLE IF NOT EXISTS IntervenantsEtat2
(
    nomInt      VARCHAR(255) NOT NULL,
    prenomInt   VARCHAR(255) NOT NULL,
    heureMinInt INTEGER NOT NULL CHECK (heureMinInt >= 0),
    heureMaxInt INTEGER NOT NULL CHECK (heureMaxInt >= heureMinInt),
    coefInt     FLOAT DEFAULT 1 CHECK  (coefInt >= 0),
    categInt    VARCHAR(255) REFERENCES CategorieIntervenantsEtat2(codeCatInt),
	PRIMARY KEY (nomInt, prenomInt)
);


-- Création des tables ayant un niveau de liaison 3
CREATE TABLE IF NOT EXISTS ModulesCatHeuresEtat2
(
	codeMod    VARCHAR(255) NOT NULL REFERENCES ModulesEtat2(codeMod) ON DELETE CASCADE,
	libCatHeur VARCHAR(255) NOT NULL REFERENCES CategorieHeuresEtat2(libCatHeur),
	nbHeurePN  INTEGER NOT NULL CHECK (nbHeurePN >= 0),
	nbHeureSem INTEGER NOT NULL CHECK (nbHeureSem >= 0),
	nbSemaine  INTEGER NOT NULL CHECK (nbSemaine >= 0),
	PRIMARY KEY(codeMod, libCatHeur),
    UNIQUE(codeMod, libCatHeur)
);

CREATE TABLE IF NOT EXISTS AffectationEtat2
(
    cle         SERIAL       PRIMARY KEY,
	nomInt      VARCHAR(255) NOT NULL,
	prenomInt   VARCHAR(255) NOT NULL,
	codeMod     VARCHAR(255) NOT NULL REFERENCES ModulesEtat2(codeMod),
	libCatHeur  VARCHAR(255) NOT NULL REFERENCES CategorieHeuresEtat2(libCatHeur),
	nbSem       INTEGER NOT NULL CHECK (nbSem >= 0),
	nbGroupe    INTEGER NOT NULL CHECK (nbGroupe >= 0),
	commentaire VARCHAR(255),
	FOREIGN KEY(nomInt, prenomInt) REFERENCES IntervenantsEtat2(nomInt, prenomInt)
);


/* Données dans la table CatégoriesIntervenant */
INSERT INTO CategorieIntervenantsEtat2 VALUES('prof'  ,'Professeurs'             ,2,600,1024);
INSERT INTO CategorieIntervenantsEtat2 VALUES('cher'  ,'Professeurs-Chercheur'   ,4,300,600 );
INSERT INTO CategorieIntervenantsEtat2 VALUES('vac'   ,'Vacataire'               ,4,50 ,100 );
INSERT INTO CategorieIntervenantsEtat2 VALUES('cont'  ,'Contractuel'             ,4,30 ,80  );
INSERT INTO CategorieIntervenantsEtat2 VALUES('a'     ,'Autre'                   ,4,1  ,20  );



/* Données dans la table CatégoriesHeures */
INSERT INTO CategorieHeuresEtat2 VALUES('TP' ,1.0);
INSERT INTO CategorieHeuresEtat2 VALUES('TD' ,1.0);
INSERT INTO CategorieHeuresEtat2 VALUES('CM' ,1.5);
INSERT INTO CategorieHeuresEtat2 VALUES('TUT',1.5);
INSERT INTO CategorieHeuresEtat2 VALUES('REH',1.5);
INSERT INTO CategorieHeuresEtat2 VALUES('SAE',1.0);
INSERT INTO CategorieHeuresEtat2 VALUES('HP', 1.5);



/* Données dans la table Semestre */
INSERT INTO SemestresEtat2 VALUES(1,4,9,131,16);
INSERT INTO SemestresEtat2 VALUES(2,3,6,85 ,9 );
INSERT INTO SemestresEtat2 VALUES(3,4,8,120,15);
INSERT INTO SemestresEtat2 VALUES(4,2,5,82 ,10);
INSERT INTO SemestresEtat2 VALUES(5,3,7,104,13);
INSERT INTO SemestresEtat2 VALUES(6,2,4,62 ,12);



/* Données dans la table Module */
/* S1 */
INSERT INTO ModulesEtat2 VALUES('R1.01',1,'Ressource','Init-Dev'   ,'Initiation au développement'                                ,false,10);
INSERT INTO ModulesEtat2 VALUES('R1.02',1,'Ressource','Dev-Web'    ,'Développement interfaces Web'                               ,false,5 );
INSERT INTO ModulesEtat2 VALUES('R1.03',1,'Ressource','Intro-Archi','Introduction Architecture'                                  ,false,5 );


/* S2 */
INSERT INTO ModulesEtat2 VALUES('R2.01',2,'Ressource','Dev-Objet'  ,'Développement orienté objets'                               ,false,10);


/* S3 */
INSERT INTO ModulesEtat2 VALUES('R3.01',3,'Ressource','Dev-Web'  ,'Développement WEB'                                            ,false,10);

/* S4 */
INSERT INTO ModulesEtat2 VALUES('R4.01',4,'Ressource','Archi-log' ,'Architecture logicielle'                                     ,false,10);


/* S5 */
INSERT INTO ModulesEtat2 VALUES('R5.01',5,'Ressource','Init-Manag','Initiation au management d’une équipe de projet informatique',false,10);


/* S6 */
INSERT INTO ModulesEtat2 VALUES('R6.01',6,'Ressource','Init-Entr','Initiation à l’entrepreneuriat'                               ,false,10);




/* Données dans la table Intervenant */
INSERT INTO IntervenantsEtat2 VALUES('Cocagne' , 'Oscar'  , 20, 25, 1, 'prof');
INSERT INTO IntervenantsEtat2 VALUES('Hautot'  , 'Sarah'    , 14 , 50 , 2, 'vac' );
INSERT INTO IntervenantsEtat2 VALUES('Chalansonnet'  , 'Adrian'  , 60 , 70 , 5, 'cher' );
INSERT INTO IntervenantsEtat2 VALUES('Marouard' , 'Louis'  , 100, 125, 1, 'prof');
INSERT INTO IntervenantsEtat2 VALUES('Vicente'  , 'Hugo'    , 160 , 270 , 2, 'vac' );
INSERT INTO IntervenantsEtat2 VALUES('Gaulos' , 'thiérie'  , 200, 205, 5, 'prof');
INSERT INTO IntervenantsEtat2 VALUES('Ochon'  , 'Paul'    , 12 , 35 , 5, 'vac' );
INSERT INTO IntervenantsEtat2 VALUES('Ptipeu'  , 'Justin'  , 10 , 15 , 1, 'cher' );
INSERT INTO IntervenantsEtat2 VALUES('Bonboeur' , 'Jean'  , 10, 190, 10, 'prof');
INSERT INTO IntervenantsEtat2 VALUES('Mentation'  , 'Ali'    , 190 , 191 , 2, 'vac' );




/* Données dans la table ModulesCatHeuresETAT */

/* S1 */
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.01', 'TP' , 10, 2, 14);

INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.02', 'CM' , 10 , 5, 10 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.02', 'TD' , 2 , 3, 20);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.02', 'TP' , 1 , 3, 14);

INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.03', 'TD' , 8 , 2, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.03', 'TP' , 8 , 2, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R1.03', 'CM' , 8 , 2, 14);

/* S2 */
INSERT INTO ModulesCatHeuresEtat2 VALUES('R2.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R2.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R2.01', 'TP' , 10, 2, 14);

/* S3*/
INSERT INTO ModulesCatHeuresEtat2 VALUES('R3.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R3.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R3.01', 'TP' , 10, 2, 14);

/* S4 */
INSERT INTO ModulesCatHeuresEtat2 VALUES('R4.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R4.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R4.01', 'TP' , 10, 2, 14);

/* S5 */
INSERT INTO ModulesCatHeuresEtat2 VALUES('R5.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R5.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R5.01', 'TP' , 10, 2, 14);

/* S6 */
INSERT INTO ModulesCatHeuresEtat2 VALUES('R6.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat2 VALUES('R6.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat2 VALUES('R6.01', 'TP' , 10, 2, 14);




/* Données dans la table AffectationETAT */
INSERT INTO AffectationEtat2(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe,commentaire) VALUES('Cocagne', 'Oscar', 'R1.01', 'CM', 1 , 3, 'Réunion début année');
INSERT INTO AffectationEtat2(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe) VALUES('Hautot', 'Sarah', 'R1.01', 'TD', 14, 2);

INSERT INTO AffectationEtat2(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Chalansonnet', 'Adrian', 'R1.01', 'TP', 13 , 2);
INSERT INTO AffectationEtat2(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Chalansonnet', 'Adrian', 'R2.01', 'TP', 12 , 2);

INSERT INTO AffectationEtat2(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe,commentaire)  VALUES('Bonboeur', 'Jean', 'R1.01', 'TP', 13 , 2,'C est le meilleur sandwich !');
INSERT INTO AffectationEtat2(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Vicente', 'Hugo', 'R2.01', 'TP', 12 , 2);