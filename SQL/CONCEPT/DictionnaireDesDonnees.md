# NOTE SUR LES INFORMATIONS DES TABLES SQL DU PROJETS



## Tables sans liaison



### CategorieIntervenants

#### Utilité
Table ou sont stockés toutes les catégorie des intervenants.

#### Informations et contraintes
- **libCatInt**      : Libellé de la catégorie d'intervenants.                                                                [ VARCHAR ]
- **coefCatInt**     : Un coefficient (différent du coef de type d'heures) qui met en heure TD. Forcement supérieur 0.        [ FLOAT   ]
- **heureMinCatInt** : Nombre d'heure minimum qu'un intervenants de cet catégorie doit faire. Forcément > 0.                  [ INTEGER ]
- **heureMaxCatInt** : Nombre d'heure maximum qu'un intervenants de cet catégorie peut faire. Forcément > 0 et > heureMinCat. [ INTEGER ]

**Clé primaire** : libCatInt



### CategorieHeures

#### Utilité 
Table ou sont stockés toutes les catégorie des intervenants.

#### Informations et contraintes
- **libCatHeur**      : Libellé de la catégorie du type d'heure.                   [ VARCHAR ]
- **coefCatHeur**     : Un coefficient qui met en heure TD. Forcement supérieur 0. [ FLOAT   ]

**Clé primaire** : libCatHeur



### Semestres

#### Utilité 
Table ou sont stockés les différents semestres.

#### Informations et contraintes
- **numSem**     : Numero du semestre.                                 [ INTEGER ]
- **nbGpTdSem**  : Nombre de groupe TD dans ce semestre. Doit être > 0 [ INTEGER ]
- **nbGpTpSem**  : Nombre de groupe TP dans ce semestre. Doit être > 0 [ INTEGER ]
- **nbEtdSem**   : Nombre d'étudiants dans ce semestre. Doit être > 0  [ INTEGER ]
- **nbSemSem**   : Nombre de semaines dans ce semestre. Doit être > 0  [ INTEGER ]

**Clé primaire** : numSem



## Tables 1er niveau de liaison


### Intervenants

#### Utilité
Table ou sont stockés tous les intervenants.

#### Informations et contraintes
- **nomInt**      : L'intervenants doit obligatoirement avoir un nom.                              [ VARCHAR ]
- **prenomInt**   : L'intervenants doit obligatoirement avoir un prenom.                           [ VARCHAR ]
- **heureMinInt** : Nombre d'heure minimum qu'un intervenants doit faire. Forcément > 0.           [ INTEGER ]
- **heureMaxInt** : Nombre d'heure maximum qu'un intervenants peut faire. Forcément > heureMinInt. [ INTEGER ]
- **libCatInt**   : L'intervenants doit obligatoirement appartenir à une catégorie existante.      [ LIAISON ]

**Clé primaire** : (nomInt, prenomInt)



### Modules

#### Utilité 
Table ou sont stockés toutes les modules.

#### Informations et contraintes
- **codeMod**     : Code du modules (Ex : R1.01/S3.01/S4.ST).                            [ VARCHAR ]
- **typeMod**     : Type de la modules. Doit être soit 'Ressource', 'SAE', 'Stage/Suivi' [ VARCHAR ]
- **libCourtMod** : Libellé raccourcit du modules                                        [ VARCHAR ]
- **libLongMod**  : Libellé allongé du modules                                           [ VARCHAR ]
- **validMod**    : Validation de la module par l'utilisateur. Simple repère.            [ BOOLEAN ]
- **semMod**      : Numero du semestre dans laquel le module est enseigné.               [ LIAISON ]

**Clé primaire** : codeMod



## Tables 2e niveau de liaison


### ModulesCatHeures

#### Utilité 
Table ou sont stockés le nombre d'heure prévue par le programme national selon les catégories d'heures et modules.

#### Informations et contraintes
- **codeMod**    : Code du modules (Ex : R1.01/S3.01/S4.ST).                 [ LIAISON ]
- **libCatHeur** : Libellé de la catégorie du type d'heure.                  [ LIAISON ]
- **nbHeurePN**  : Nombre d'heure prévue par le programme. Forcément > 0     [ INTEGER ]
- **nbHeureSem** : Nombre d'heure a distribué parsemaine. Forcément > 0      [ INTEGER ]
- **nbSemaine**  : Nombre de semaine ou le modules est eduqué. Forcément > 0 [ INTEGER ]

**Clé primaire** : (codeMod,libCatHeur)

**Note**         : Si la catégorie est HP, pas de semaine.


### Affectation

#### Utilité
Affecte les heures a des Intervenants 

#### Informations et contraintes
- **nomInt**     : Nom de l'intervenants.                                              [ LIAISON ]
- **prenomInt**  : Prenom de l'intervenants.                                           [ LIAISON ]
- **codeMod**    : Identifiants du modules.                                            [ LIAISON ]
- **libCatHeur** : Identifiants de la catégories heures.                               [ LIAISON ]
- **nbHeurSem**  : Nombre d'heure par semaine ou l'intervenants eduque la matière. > 0 [ INTEGER ]
- **nbGroupe**   : Nombre de groupe a qui l'intervenants eduque la matière. > 0        [ INTEGER ]
- **nbSem**      : Nombre de semaine ou l'intervenants eduque la matière. > 0          [ INTEGER ]
- **commentaire**: Commentaire facultatif sur l'affectation.                           [ VARCHAR ]

**Clé primaire** : ((nomInt, prenomInt), codeMod, libCatHeur)

**Note**         : La catégorie d'heure "HP" n'as pas de nbSemaine a affecte. 



**@author :** Sarah Hautot (SHOAL) 