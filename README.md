# Projet Algorithme des Mobilité - Routage et Mensonge

[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)  
[![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](https://forthebadge.com)


Nous nous trouvons dans un graphe.
Nous avons un noeud appelé destination. Nous allons construire un sous-graphe tel que chaque
noeud de ce sous-graphe ait comme parent son voisin le plus proche de la destination. L’idée est
de construire des ’chemins’ vers la destination. La destination sera mise en vert et augmentée
de taille. Nous visualisons les relations parents-enfants entre les noeuds avec des liens en gras
et des flèches.

### Pré-requis

Pour bien compiler ce projet, il est recommendé d'utiliser Intellij (IDE)

[JBotSim](https://jbotsim.io/?p=examples/helloworld) - Voir prise en Main de JBotSim


### Installation

Pour commencer, clonez le projet https://github.com/sarars1/projetAlgomob.git, et utilisez Intellij pour l'ouvrir et compiler.

[Le fichier est constitué ainsi]                                                                                                       
**RouterNode.java** constitue notre algorithme principale                                                                                              
**JParentLinkPainter.java** nous permet de visualiser nos liens en gras avec des flèches qu'indiquent de l'enfant au parent                                         
Rendez-vous dans le **MainRouteur.java** pour executer le projet


## Démarrage

Une fois compilé, nous obtenons une fenêtre JBotSim, nous pouvons alors charger une topologie déjà définie**testTopo.iml** ou **t2.iml**.
Ou définir une nouvelle topologie, en ajoutant des noeuds avec un click gauche. 

Et choisir le sommet de destination (Ctrl+click gauche), qui devient **Vert**                                                                                     Quand on casse un lien, le sommet devient un possible menteur **Orange**,                                                                                          Et si le noeud ou ses voisins sont des menteurs ils deviennent **Rouge**                                             
Et pour un/des sommet(s) isolés, il(s) devient **Rose**,                                                                                                       


Désormais, à vous de jouer !!!

## Fabriqué avec

Pour développer notre projet, nous avons utilisé

* [IntelliJ](https://www.jetbrains.com/fr-fr/idea/) - Editeur de textes

## Auteurs

* **Ousmane Bah** _alias_ [@obah4567](https://github.com/obah4567)
* **Sara Real Santos** _alias_ [@saras1](https://github.com/sarars1)

## License

Ce projet est libre 2021-2022


