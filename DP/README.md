# PARTIE : David Sene && Pierre Rainero

1. Lancer le registre en lui passant en parametre le path vers les .class du package common :  
	
	Exemple : start rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false -J-Djava.rmi.server.codebase=file:C:\Users\hp\Documents\java_workplace\R-seaux-SI3\DP\common\target\classes\
	
	
2. Lancer le serveur (RmiServer) normalement sous eclipse puis le client (ClientRmi).    




#MODIFICATIONS EFFECTUEES:  

1. Ajout de l'interface Repository   

2. Deplacement des classes Idea et Student dans le package common pour qu'ils soient accessibles par le client.(mais seulement sous forme de copies des objets presents sur le serveur)  

3. Modification des  classes Idea et Student pour qu'elles implementent Serializable (puisque les copies des objets transitent par le reseau)  

4. Redefinition de la methode equals dans les classes Idea et Student pour eviter des incoherances dues a la copie des objets  
  
  
#ATTENTION :  

Lorsqu'un objet non distant (c'est le cas de Idea et Student) est passé comme parametre lors d'un appel distant ou est retourné comme resultat, il est completement copié    


#A VERIFIER:  
Il faut maintenant verifier la coherence de certains appels de methodes distantes a cause de la copies des objets de type Idea et Student  

