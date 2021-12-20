# Projet Algorithme des Mobilité - Routage et Mensonge

[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)  
[![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](https://forthebadge.com)


Nous nous trouvons dans un graphe à la base connexe, mais qui peut varier dans le temps.
Nous avons un noeud appelé destination. Nous allons construire un sous-graphe tel que chaque
noeud de ce sous-graphe ait comme parent son voisin le plus proche de la destination. L’idée est
de construire des ’chemins’ vers la destination. La destination sera mise en jaune et augmentée
de taille. Nous visualisons les relations parents-enfants entre les noeuds avec des liens en gras
et des flèches.

### Pré-requis

Pour bien compiler ce projet, il est recommender d'utiliser Intellij (IDE)

[JBotSim](https://jbotsim.io/?p=examples/helloworld) - Voir prise en Main de JBotSim


### Installation

Pour commencer, clonez le projet https://github.com/sarars1/projetAlgomob.git, et utilisez Intellij pour l'ouvrir et compiler.

[Le fichier est constituer ainsi]                                                                                                       
**RouterNode.java** constituer notre algorithme principale (a revoir)                                                                                             
**JParentLinkPainter.java** nous permet de visualiser nos liens en gras avec des fleches qui indique de l'enfant au parent                                         
Rendez-vous dans le **MainRouteur.java** pour executer le projet


## Démarrage

Une fois compiler, nous obtenons une fénetre JBotSim, nous pouvons alors uploade une topologie déjà defini **testTopo.iml** ou **t2.iml**.
Ou definir une nouvelle topologie, simplement avec un clic droit de notre souris.

Et choisir le sommet de destination, qui deviens **Jaune**                                                                                                         
Quand on casser un lien, le sommet deviens un menteur **Rouge**,                                                                                                   
Et ses voisins devient des possibles menteurs **Orange**,                                                                                                       
Et pour un/des sommet(s) isolés, il(s) devient **Rose**,                                                                                                       


Désormais, à vous de jouer !!!

## Fabriqué avec

Pour développer notre projet, nous avons utilsez

* [IntelliJ](https://www.jetbrains.com/fr-fr/idea/) - Editeur de textes

## Auteurs

* **Ousmane Bah** _alias_ [@obah4567](https://github.com/obah4567)
* **Sara Real Santos** _alias_ [@saras1](https://github.com/sarars1)

## License

Ce projet est libre 2021-2022


