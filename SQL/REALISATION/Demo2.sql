/*
* @title  : CreateTablesAstre
* @author : SHOAL™
* @date   : 10/01/2024
* @sub    : Insertion de table données
*/



/* Données dans la table CatégoriesIntervenant */
INSERT INTO CategorieIntervenantsETAT2 VALUES('prof'  ,'Professeurs'             ,2,600,1024);
INSERT INTO CategorieIntervenantsETAT2VALUES('cher'  ,'Professeurs-Chercheur'   ,4,300,600 );
INSERT INTO CategorieIntervenantsETAT2VALUES('vac'   ,'Vacataire'               ,4,50 ,100 );
INSERT INTO CategorieIntervenantsETAT2VALUES('cont'  ,'Contractuel'             ,4,30 ,80  );
INSERT INTO CategorieIntervenantsETAT2VALUES('a'     ,'Autre'                   ,4,1  ,20  );



/* Données dans la table CatégoriesHeures */
INSERT INTO CategorieHeuresETAT2  VALUES('CM'   ,1.5);
INSERT INTO CategorieHeuresETAT2  VALUES('TP'   ,1.5);
INSERT INTO CategorieHeuresETAT2  VALUES('TD'   ,1.5);
INSERT INTO CategorieHeuresETAT2  VALUES('Tut'  ,1  );
INSERT INTO CategorieHeuresETAT2  VALUES('REH'  ,1  );
INSERT INTO CategorieHeuresETAT2  VALUES('Autre',1  );



/* Données dans la table Semestre */
INSERT INTO SemestresETAT2 VALUES(1,4,9,131,16);
INSERT INTO SemestresETAT2 VALUES(2,3,6,85 ,9 );
INSERT INTO SemestresETAT2 VALUES(3,4,8,120,15);
INSERT INTO SemestresETAT2 VALUES(4,2,5,82 ,10);
INSERT INTO SemestresETAT2 VALUES(5,3,7,104,13);
INSERT INTO SemestresETAT2 VALUES(6,2,4,62 ,12);



/* Données dans la table Module */
/* S1 */
INSERT INTO ModulesETAT2 VALUES('R1.01',1,'Ressource','Init-Dev'   ,'Initiation au développement'                                ,false,10);
INSERT INTO ModulesETAT2 VALUES('R1.02',1,'Ressource','Dev-Web'    ,'Développement interfaces Web'                               ,false,5 );
INSERT INTO ModulesETAT2 VALUES('R1.03',1,'Ressource','Intro-Archi','Introduction Architecture'                                  ,false,5 );


/* S2 */
INSERT INTO ModulesETAT2 VALUES('R2.01',2,'Ressource','Dev-Objet'  ,'Développement orienté objets'                               ,false,10);


/* S3 */
INSERT INTO ModulesETAT2 VALUES('R3.01',3,'Ressource','Dev-Web'  ,'Développement WEB'                                            ,false,10);

/* S4 */
INSERT INTO ModulesETAT2 VALUES('R4.01',4,'Ressource','Archi-log' ,'Architecture logicielle'                                     ,false,10);


/* S5 */
INSERT INTO ModulesETAT2 VALUES('R5.01',5,'Ressource','Init-Manag','Initiation au management d’une équipe de projet informatique',false,10);


/* S6 */
INSERT INTO ModulesETAT2 VALUES('R6.01',6,'Ressource','Init-Entr','Initiation à l’entrepreneuriat'                               ,false,10);




/* Données dans la table Intervenant */
INSERT INTO IntervenantsETAT VALUES('Cocagne' , 'Oscar'  , 20, 25, 1, 'prof');
INSERT INTO IntervenantsETAT VALUES('Hautot'  , 'Sarah'    , 14 , 50 , 2, 'vac' );
INSERT INTO IntervenantsETAT VALUES('Chalansonnet'  , 'Adrian'  , 60 , 70 , 5, 'cher' );
INSERT INTO IntervenantsETAT VALUES('Marouard' , 'Louis'  , 100, 125, 1, 'prof');
INSERT INTO IntervenantsETAT VALUES('Vicente'  , 'Hugo'    , 160 , 270 , 2, 'vac' );
INSERT INTO IntervenantsETAT VALUES('Gaulos' , 'thiérie'  , 200, 205, 5, 'prof');
INSERT INTO IntervenantsETAT VALUES('Ochon'  , 'Paul'    , 12 , 35 , 5, 'vac' );
INSERT INTO IntervenantsETAT VALUES('Ptipeu'  , 'Justin'  , 10 , 15 , 1, 'cher' );
INSERT INTO IntervenantsETAT VALUES('Bonboeur' , 'Louis'  , 10, 190, 10, 'prof');
INSERT INTO IntervenantsETAT VALUES('Mentation'  , 'Ali'    , 190 , 191 , 2, 'vac' );




/* Données dans la table ModulesCatHeuresETAT */
INSERT INTO ModulesCatHeuresETAT VALUES('R1.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresETAT VALUES('R1.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R1.01', 'TP' , 10, 2, 14);

INSERT INTO ModulesCatHeuresETAT VALUES('R1.02', 'CM' , 10 , 5, 10 );
INSERT INTO ModulesCatHeuresETAT VALUES('R1.02', 'TD' , 2 , 3, 20);
INSERT INTO ModulesCatHeuresETAT VALUES('R1.02', 'TP' , 1 , 3, 14);

INSERT INTO ModulesCatHeuresETAT VALUES('R1.03', 'TD' , 8 , 2, 14);


INSERT INTO ModulesCatHeuresETAT VALUES('R2.01', 'TP'   , 10, 4 , 13);
INSERT INTO ModulesCatHeuresETAT VALUES('R2.01', 'TD'   , 5 , 2 , 13);
INSERT INTO ModulesCatHeuresETAT VALUES('R2.05', 'CM'   , 10, 1 , 7 );
INSERT INTO ModulesCatHeuresETAT VALUES('R2.05', 'TD'   , 8 , 2 , 7 );
INSERT INTO ModulesCatHeuresETAT VALUES('R2.07', 'TD'   , 10, 2 , 13);
INSERT INTO ModulesCatHeuresETAT VALUES('S2.01', 'Autre', 2 , 10, 2 );
INSERT INTO ModulesCatHeuresETAT VALUES('S2.01', 'CM'   , 2 , 1 , 1 );
INSERT INTO ModulesCatHeuresETAT VALUES('S2.02', 'Autre', 2 , 9 , 2 );

INSERT INTO ModulesCatHeuresETAT VALUES('R3.01', 'TD' , 10, 2, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R3.01', 'TP' , 2 , 4, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R3.03', 'TD' , 5 , 2, 10);
INSERT INTO ModulesCatHeuresETAT VALUES('P3.01', 'REH', 5 , 2, 2);

INSERT INTO ModulesCatHeuresETAT VALUES('R4.01', 'TD' , 10, 2, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R4.01', 'TP' , 2 , 4, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R4.02', 'TD' , 5 , 2, 10);
INSERT INTO ModulesCatHeuresETAT VALUES('S4.ST', 'REH', 5 , 2, 2);

INSERT INTO ModulesCatHeuresETAT VALUES('R5.01', 'TD'   , 10, 2 , 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R5.01', 'TP'   , 2 , 4 , 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R5.02', 'TD'   , 5 , 2 , 10);
INSERT INTO ModulesCatHeuresETAT VALUES('R5.14', 'TD'   , 8 , 2 , 14);
INSERT INTO ModulesCatHeuresETAT VALUES('S5.01', 'Autre', 5 , 10, 2);

INSERT INTO ModulesCatHeuresETAT VALUES('R6.01', 'TD' , 10, 2, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R6.01', 'TP' , 2 , 4, 14);
INSERT INTO ModulesCatHeuresETAT VALUES('R6.02', 'TD' , 5 , 2, 10);
INSERT INTO ModulesCatHeuresETAT VALUES('S6.ST', 'REH', 5 , 2, 2 );



/* Données dans la table AffectationETAT */
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe,commentaire) VALUES('Le Pivert', 'Philippe', 'R1.01', 'CM', 1 , 3, 'Réunion début année');
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe) VALUES('Le Pivert', 'Philippe', 'R1.01', 'TD', 14, 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R1.01', 'TP', 14, 4);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R2.01', 'TD', 14, 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R2.02', 'TD', 12, 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R3.02', 'TD', 11, 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R3.03', 'TD', 10, 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R5.06', 'TD', 8 , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colignon', 'Thomas', 'R1.01', 'TP', 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colignon', 'Thomas', 'R2.01', 'TP', 12 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colignon', 'Thomas', 'R2.03', 'TP', 10 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Dubocage', 'Tiphaine', 'R1.01', 'TP', 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Dubocage', 'Tiphaine', 'R2.02', 'TP', 12 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R1.01', 'TD', 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R2.01', 'TP', 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R2.03', 'TP', 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R2.07', 'TD', 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R3.02', 'TP', 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R4.08', 'TD', 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R5.06', 'TD', 13 , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R1.05', 'TD' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R1.12', 'TD' , 3  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R3.03', 'TD' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'S3.01', 'REH', 2  , 4);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R5.05', 'TP' , 2  , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Guinand', 'Frederic', 'R1.02', 'TP', 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Guinand', 'Frederic', 'R5.05', 'TP', 14  , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Pytel', 'Steve', 'R1.02', 'TP', 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Pytel', 'Steve', 'R2.04', 'TD', 10 , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R1.03', 'TP' , 8  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R1.04', 'TP' , 6  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R3.05', 'TP' , 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R4.04', 'TP' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'S2.03', 'REH', 2  , 3);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R1.03', 'TP', 8 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R1.04', 'TP', 6 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R2.04', 'TP', 10, 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R2.05', 'TP', 8 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R3.05', 'TP', 8 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R1.05', 'TP' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R1.05', 'TD' , 12 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R2.06', 'TP' , 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'S2.04', 'REH', 2  , 3);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R4.03', 'TP' , 12 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R1.05', 'TP' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R2.06', 'TP' , 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R3.07', 'TP' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R4.03', 'TP' , 12 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R1.06', 'TD' , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R1.07', 'TD' , 12 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R2.07', 'TD' , 13 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R3.08', 'TD' , 14 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Pascal', 'Rembert', 'R1.06', 'TD' , 14 , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Griette', 'Quentin', 'R1.07', 'TD'  , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Griette', 'Quentin', 'R3.08', 'TD'  , 14 , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Griette', 'Quentin', 'S1.02', 'REH' , 1  , 3);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R1.08', 'TD' , 13  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R1.09', 'TD' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'S1.05', 'REH', 1   , 3);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R3.11', 'TD' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R5.01', 'TD' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R6.02', 'TD' , 14  , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Foubert', 'Jean', 'R1.10', 'TD' , 14  , 3);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Foubert', 'Jean', 'S1.06', 'REH', 1   , 3);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Foubert', 'Jean', 'R5.14', 'TD' , 14  , 2);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Bertin', 'Sebastien', 'R1.11', 'TD' , 14  , 1);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Bertin', 'Sebastien', 'R2.13', 'TD' , 14  , 1);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Bertin', 'Sebastien', 'R5.03', 'TD' , 14  , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Delarue', 'Isabelle', 'R1.11', 'TD' , 14  , 1);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Delarue', 'Isabelle', 'R2.13', 'TD' , 14  , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R1.11', 'TD' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R2.13', 'TD' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'S1.05', 'REH', 1   , 3);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'S4.ST', 'REH', 1   , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R5.03', 'TD' , 14  , 1);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R6.03', 'TD' , 14  , 1);

INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colin', 'Jean-Yves', 'R4.01', 'TP' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colin', 'Jean-Yves', 'R5.07', 'TP' , 14  , 2);
INSERT INTO AffectationETAT(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colin', 'Jean-Yves', 'R5.09', 'TP' , 12  , 2);

