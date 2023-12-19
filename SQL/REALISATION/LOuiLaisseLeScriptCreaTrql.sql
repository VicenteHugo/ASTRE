

/* Données dans la table CatégoriesIntervenant */
INSERT INTO CategorieIntervenantsEtat1 VALUES('Prof'  ,'Professeurs'             ,2,600,1024);
INSERT INTO CategorieIntervenantsEtat1 VALUES('Cher'  ,'Professeurs-Chercheur'   ,4,300,600 );
INSERT INTO CategorieIntervenantsEtat1 VALUES('Vac'   ,'Vacataire'               ,4,50 ,100 );
INSERT INTO CategorieIntervenantsEtat1 VALUES('Cont'  ,'Contractuel'             ,4,30 ,80  );
INSERT INTO CategorieIntervenantsEtat1 VALUES('A'     ,'Autre'                   ,4,1  ,20  );



/* Données dans la table Module */
/* S1 */
INSERT INTO ModulesEtat1 VALUES('R1.01',1,'Ressource','Init-Dev'   ,'Initiation au développement'                           ,false,10);
INSERT INTO ModulesEtat1 VALUES('R1.02',1,'Ressource','Dev-Web'    ,'Développement interfaces Web'                          ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R1.03',1,'Ressource','Intro-Archi','Introduction Architecture'                             ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R1.04',1,'Ressource','Intro-Sys'  ,'Introduction Système'                                  ,false,8 );
INSERT INTO ModulesEtat1 VALUES('R1.05',1,'Ressource','Intro-BD'   ,'Introduction Base de données'                          ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R1.06',1,'Ressource','Maths-Dis'  ,'Mathématiques discrètes'                               ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R1.07',1,'Ressource','Out-Fond'   ,'Outils mathématiques fondamentaux'                     ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R1.08',1,'Ressource','GPO'        ,'Gestion de projet et des organisations'                ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R1.09',1,'Ressource','Eco'        ,'Économie durable et numérique'                         ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R1.10',1,'Ressource','Ang'        ,'Anglais Technique'                                     ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R1.11',1,'Ressource','Com'        ,'Bases de la communication'                             ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R1.12',1,'Ressource','PPP'        ,'Projet Professionnel et Personnel'                     ,false,2 );
INSERT INTO ModulesEtat1 VALUES('P1.01',1,'PPP'      ,'Port'       ,'Portfolio'                                             ,false,12);
INSERT INTO ModulesEtat1 VALUES('S1.01',1,'Sae'      ,'Impl-Besoin','Implémentation d un besoin client'                     ,false,0 );
INSERT INTO ModulesEtat1 VALUES('S1.02',1,'Sae'      ,'Comp-Algo'  ,'Comparaison d approches algorithmiques'                ,false,0 );
INSERT INTO ModulesEtat1 VALUES('S1.05',1,'Sae'      ,'Rec-Besoins','Recueil de besoins'                                    ,false,0 );
INSERT INTO ModulesEtat1 VALUES('S1.06',1,'Sae'      ,'Env-info'   ,'Découverte de l environnement économique et écologique',false,0 );

/* S2 */
INSERT INTO ModulesEtat1 VALUES('R2.01',2,'Ressource','Dev-Objet'  ,'Développement orienté objets'              ,false,10);
INSERT INTO ModulesEtat1 VALUES('R2.02',2,'Ressource','Dev-IHM'    ,'Développement d applications IHM'          ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R2.03',2,'Ressource','Quali-dev'  ,'Qualité de développement'                  ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R2.04',2,'Ressource','Com-Bas-Niv','Communication et fonctionnement bas niveau',false,5 );
INSERT INTO ModulesEtat1 VALUES('R2.05',2,'Ressource','Intro-Res'  ,'Introduction aux services réseaux'         ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R2.06',2,'Ressource','Exp-BD'     ,'Exploitation d une base de données'        ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R2.07',2,'Ressource','Graph'      ,'Graphes'                                   ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R2.13',2,'Ressource','Com'        ,'Communication Technique'                   ,false,2 );
INSERT INTO ModulesEtat1 VALUES('S2.01',2,'Sae'      ,'Dev-Appli'  ,'Développement d une application'           ,false,0 );
INSERT INTO ModulesEtat1 VALUES('S2.02',2,'Sae'      ,'Exp-Algo'   ,'Exploration algorithmique d un problème'   ,false,0 );
INSERT INTO ModulesEtat1 VALUES('S2.03',2,'Sae'      ,'Serv-Res'   ,'Installation de services réseau '          ,false,0 );
INSERT INTO ModulesEtat1 VALUES('S2.04',2,'Sae'      ,'Exp-BD'     ,'Exploitation d une base de données'        ,false,0 );

/* S3 */
INSERT INTO ModulesEtat1 VALUES('R3.01',3,'Ressource','Dev-Web'  ,'Développement WEB'                   ,false,10);
INSERT INTO ModulesEtat1 VALUES('R3.02',3,'Ressource','Dev-Eff'  ,'Développement Efficace'              ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R3.03',3,'Ressource','Anal'     ,'Analyse'                             ,false,10);
INSERT INTO ModulesEtat1 VALUES('R3.05',3,'Ressource','Prog-Sys' ,'Programmation Système '              ,false,10);
INSERT INTO ModulesEtat1 VALUES('R3.07',3,'Ressource','SQL'      ,'SQL dans un langage de programmation',false,2 );
INSERT INTO ModulesEtat1 VALUES('R3.08',3,'Ressource','Proba'    ,'Probabilité'                         ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R3.11',3,'Ressource','Droit-Num','Droits des contrats et du numérique' ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R3.14',3,'Ressource','PPP 3'    ,'Projet Professionnel et Personnel'   ,false,12);
INSERT INTO ModulesEtat1 VALUES('P3.01',3,'PPP'      ,'Port'     ,'Portfolio'                           ,false,2 );
INSERT INTO ModulesEtat1 VALUES('S3.01',3,'Sae'      ,'Dev-Appli','Développement d une application'     ,false,0 );

/* S4 */
INSERT INTO ModulesEtat1 VALUES('R4.01',4,'Ressource','Archi-log' ,'Architecture logicielle'             ,false,10);
INSERT INTO ModulesEtat1 VALUES('R4.02',4,'Ressource','Quali-dev' ,'Qualité de développement 4'          ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R4.03',4,'Ressource','Quali-Rela','Qualité et au delà du relationnel'   ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R4.04',4,'Ressource','Opti-Met'  ,'Methode d optimisation'              ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R4.08',4,'Ressource','Virtu'     ,'Virtualisation'                      ,false,5 );
INSERT INTO ModulesEtat1 VALUES('S4.ST',4,'Stage'    ,'Stage'     ,'Stages'                              ,false,2 );

/* S5 */
INSERT INTO ModulesEtat1 VALUES('R5.01',5,'Ressource','Init-Manag','Initiation au management d’une équipe de projet informatique',false,10);
INSERT INTO ModulesEtat1 VALUES('R5.02',5,'Ressource','PPP'       ,'Projet Personnel et Professionnel'                           ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R5.03',5,'Ressource','Com-Polit' ,'Politique de communication'                                  ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R5.05',5,'Ressource','Prog-Avan' ,'Programmation avancée'                                       ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R5.06',5,'Ressource','Prog-Multi','Programmation Multimédia - Traitement de l image'            ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R5.07',5,'Ressource','Auto-Prod' ,'Automatisation de la chaîne de production'                   ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R5.09',5,'Ressource','Virtu-Avan','Virtualisation avancée'                                      ,false,5 );
INSERT INTO ModulesEtat1 VALUES('R5.14',5,'Ressource','Ang'       ,'Anglais'                                                     ,false,2 );
INSERT INTO ModulesEtat1 VALUES('S5.01',5,'Sae'      ,'Dev-Avan'  ,'Développement avancé'                                        ,false,0 );

/* S6 */
INSERT INTO ModulesEtat1 VALUES('R6.01',6,'Ressource','Init-Entr','Initiation à l’entrepreneuriat'                            ,false,10);
INSERT INTO ModulesEtat1 VALUES('R6.02',6,'Ressource','Droit-Num','Droit du numérique et de la propriété intellectuelle'      ,false,2 );
INSERT INTO ModulesEtat1 VALUES('R6.03',6,'Ressource','Com'      ,'Communication : organisation et diffusion de l’information',false,2 );
INSERT INTO ModulesEtat1 VALUES('S6.ST',6,'Stage'    ,'Stage'    ,'Stages'                                                    ,false,2 );



/* Données dans la table Intervenant */
INSERT INTO IntervenantsEtat1 VALUES('Le Pivert' , 'Philippe'  , 150, 200, 4, 'Prof');
INSERT INTO IntervenantsEtat1 VALUES('Colignon'  , 'Thomas'    , 60 , 70 , 5, 'Vac' );
INSERT INTO IntervenantsEtat1 VALUES('Dubocage'  , 'Tiphaine'  , 60 , 70 , 5, 'Vac' );
INSERT INTO IntervenantsEtat1 VALUES('Legrix'    , 'Bruno'     , 150, 200, 4, 'Prof');
INSERT INTO IntervenantsEtat1 VALUES('Boukachour', 'Hadhoum'   , 100, 150, 4, 'Cher');
INSERT INTO IntervenantsEtat1 VALUES('Guinand'   , 'Frederic'  , 100, 150, 4, 'Cher');
INSERT INTO IntervenantsEtat1 VALUES('Pytel'     , 'Steve'     , 60 , 70 , 5, 'Vac' );
INSERT INTO IntervenantsEtat1 VALUES('Boukachour', 'Jaouad'    , 100, 150, 4, 'Cher');
INSERT INTO IntervenantsEtat1 VALUES('Zahour'    , 'Abderrazak', 100, 150, 4, 'Cher');
INSERT INTO IntervenantsEtat1 VALUES('Boudebous' , 'Dalila'    , 100, 150, 4, 'Cher');
INSERT INTO IntervenantsEtat1 VALUES('Sadeg'     , 'Bruno'     , 80 , 100, 5, 'Cont');
INSERT INTO IntervenantsEtat1 VALUES('Alabboud'  , 'Hassan'    , 80 , 100, 5, 'Cont');
INSERT INTO IntervenantsEtat1 VALUES('Pascal'    , 'Rembert'   , 80 , 100, 5, 'Cont');
INSERT INTO IntervenantsEtat1 VALUES('Griette'   , 'Quentin'   , 100, 150, 4, 'Cher');
INSERT INTO IntervenantsEtat1 VALUES('Laffaech'  , 'Quentin'   , 150, 200, 4, 'Prof');
INSERT INTO IntervenantsEtat1 VALUES('Foubert'   , 'Jean'      , 100, 150, 4, 'Cont');
INSERT INTO IntervenantsEtat1 VALUES('Bertin'    , 'Sebastien' , 60 , 70 , 5, 'Cont');
INSERT INTO IntervenantsEtat1 VALUES('Delarue'   , 'Isabelle'  , 60 , 70 , 5, 'Cont');
INSERT INTO IntervenantsEtat1 VALUES('Nivet'     , 'Laurence'  , 150, 200, 4, 'Prof');
INSERT INTO IntervenantsEtat1 VALUES('Colin'     , 'Jean-Yves' , 100, 150, 4, 'Prof');



/* Données dans la table ModulesCatHeuresEtat1 */
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.01', 'CM' , 8 , 2, 2 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.01', 'TD' , 10, 4, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.01', 'TP' , 10, 2, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.01', 'Tut', 5 , 1, 5 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.02', 'TD' , 5 , 5, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.02', 'TP' , 5 , 3, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.03', 'TD' , 8 , 2, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.04', 'TD' , 10, 2, 7 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.04', 'TP' , 2 , 4, 7 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.05', 'TD' , 5 , 2, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R1.05', 'TP' , 5 , 4, 14);

INSERT INTO ModulesCatHeuresEtat1 VALUES('R2.01', 'TP'   , 10, 4 , 13);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R2.01', 'TD'   , 5 , 2 , 13);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R2.05', 'CM'   , 10, 1 , 7 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('R2.05', 'TD'   , 8 , 2 , 7 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('R2.07', 'TD'   , 10, 2 , 13);
INSERT INTO ModulesCatHeuresEtat1 VALUES('S2.01', 'Autre', 2 , 10, 2 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('S2.01', 'CM'   , 2 , 1 , 1 );
INSERT INTO ModulesCatHeuresEtat1 VALUES('S2.02', 'Autre', 2 , 9 , 2 );

INSERT INTO ModulesCatHeuresEtat1 VALUES('R3.01', 'TD' , 10, 2, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R3.01', 'TP' , 2 , 4, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R3.03', 'TD' , 5 , 2, 10);
INSERT INTO ModulesCatHeuresEtat1 VALUES('P3.01', 'REH', 5 , 2, 2);

INSERT INTO ModulesCatHeuresEtat1 VALUES('R4.01', 'TD' , 10, 2, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R4.01', 'TP' , 2 , 4, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R4.02', 'TD' , 5 , 2, 10);
INSERT INTO ModulesCatHeuresEtat1 VALUES('S4.ST', 'REH', 5 , 2, 2);

INSERT INTO ModulesCatHeuresEtat1 VALUES('R5.01', 'TD'   , 10, 2 , 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R5.01', 'TP'   , 2 , 4 , 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R5.02', 'TD'   , 5 , 2 , 10);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R5.14', 'TD'   , 8 , 2 , 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('S5.01', 'Autre', 5 , 10, 2);

INSERT INTO ModulesCatHeuresEtat1 VALUES('R6.01', 'TD' , 10, 2, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R6.01', 'TP' , 2 , 4, 14);
INSERT INTO ModulesCatHeuresEtat1 VALUES('R6.02', 'TD' , 5 , 2, 10);
INSERT INTO ModulesCatHeuresEtat1 VALUES('S6.ST', 'REH', 5 , 2, 2 );



/* Données dans la table AffectationEtat1 */
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe,commentaire) VALUES('Le Pivert', 'Philippe', 'R1.01', 'CM', 1 , 3, 'Réunion début année');
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe) VALUES('Le Pivert', 'Philippe', 'R1.01', 'TD', 14, 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R1.01', 'TP', 14, 4);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R2.01', 'TD', 14, 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R2.02', 'TD', 12, 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R3.02', 'TD', 11, 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R3.03', 'TD', 10, 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Le Pivert', 'Philippe', 'R5.06', 'TD', 8 , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colignon', 'Thomas', 'R1.01', 'TP', 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colignon', 'Thomas', 'R2.01', 'TP', 12 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colignon', 'Thomas', 'R2.03', 'TP', 10 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Dubocage', 'Tiphaine', 'R1.01', 'TP', 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Dubocage', 'Tiphaine', 'R2.02', 'TP', 12 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R1.01', 'TD', 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R2.01', 'TP', 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R2.03', 'TP', 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R2.07', 'TD', 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R3.02', 'TP', 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R4.08', 'TD', 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Legrix', 'Bruno', 'R5.06', 'TD', 13 , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R1.05', 'TD' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R1.12', 'TD' , 3  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R3.03', 'TD' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'S3.01', 'REH', 2  , 4);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Hadhoum', 'R5.05', 'TP' , 2  , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Guinand', 'Frederic', 'R1.02', 'TP', 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Guinand', 'Frederic', 'R5.05', 'TP', 14  , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Pytel', 'Steve', 'R1.02', 'TP', 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Pytel', 'Steve', 'R2.04', 'TD', 10 , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R1.03', 'TP' , 8  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R1.04', 'TP' , 6  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R3.05', 'TP' , 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'R4.04', 'TP' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boukachour', 'Jaouad', 'S2.03', 'REH', 2  , 3);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R1.03', 'TP', 8 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R1.04', 'TP', 6 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R2.04', 'TP', 10, 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R2.05', 'TP', 8 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Zahour', 'Abderrazak', 'R3.05', 'TP', 8 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R1.05', 'TP' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R1.05', 'TD' , 12 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R2.06', 'TP' , 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'S2.04', 'REH', 2  , 3);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Boudebous', 'Dalila', 'R4.03', 'TP' , 12 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R1.05', 'TP' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R2.06', 'TP' , 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R3.07', 'TP' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Sadeg', 'Bruno', 'R4.03', 'TP' , 12 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R1.06', 'TD' , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R1.07', 'TD' , 12 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R2.07', 'TD' , 13 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Alabboud', 'Hassan', 'R3.08', 'TD' , 14 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Pascal', 'Rembert', 'R1.06', 'TD' , 14 , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Griette', 'Quentin', 'R1.07', 'TD'  , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Griette', 'Quentin', 'R3.08', 'TD'  , 14 , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Griette', 'Quentin', 'S1.02', 'REH' , 1  , 3);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R1.08', 'TD' , 13  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R1.09', 'TD' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'S1.05', 'REH', 1   , 3);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R3.11', 'TD' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R5.01', 'TD' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Laffaech', 'Quentin', 'R6.02', 'TD' , 14  , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Foubert', 'Jean', 'R1.10', 'TD' , 14  , 3);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Foubert', 'Jean', 'S1.06', 'REH', 1   , 3);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Foubert', 'Jean', 'R5.14', 'TD' , 14  , 2);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Bertin', 'Sebastien', 'R1.11', 'TD' , 14  , 1);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Bertin', 'Sebastien', 'R2.13', 'TD' , 14  , 1);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Bertin', 'Sebastien', 'R5.03', 'TD' , 14  , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Delarue', 'Isabelle', 'R1.11', 'TD' , 14  , 1);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Delarue', 'Isabelle', 'R2.13', 'TD' , 14  , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R1.11', 'TD' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R2.13', 'TD' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'S1.05', 'REH', 1   , 3);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'S4.ST', 'REH', 1   , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R5.03', 'TD' , 14  , 1);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Nivet', 'Laurence', 'R6.03', 'TD' , 14  , 1);

INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colin', 'Jean-Yves', 'R4.01', 'TP' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colin', 'Jean-Yves', 'R5.07', 'TP' , 14  , 2);
INSERT INTO AffectationEtat1(nomInt,prenomInt,codeMod,libCatHeur,nbSem,nbGroupe)  VALUES('Colin', 'Jean-Yves', 'R5.09', 'TP' , 12  , 2);