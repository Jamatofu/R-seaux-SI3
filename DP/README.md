# PARTIE : David Sene && Pierre Rainero

## Liste des requêtes :
- S'identifier
- Ajouter une idée
- Participer à une idée
- Lister les étudiants par idée
- Se déconnecter
- Administration d'idée
	- Approuver la participation d'un étudiant à une idée
	- Changer statut : idée => projet
	- Modifier l'idée
	- Pouvoir lister ses propres idées

## Fonctionnement :
Pour réalisé ce protocole nous avons mis en place un ensemble de classes que l'on peut découper en 4 packages : 
1. "Processing", soit le "modèle" de notre application (une idée, un étudiant, etc...)
2. "Communication", soit une classe "Request" et une classe "Query. "Request" qui va correspondre à un objet sérialisable qui sera envoyé par le client vers le serveur. Ce dernier va alors l'interpréter et effectuer l'action correspondante grace aux attributs de cet object (resource <=> classe du modèle à utiliser, method <=> méthode à utiliser, args <=> argmuents nécessaire pour trouver l'objet à modifier/créer et pour la méthode). Le server va alors renvoyer un object "Query" au client.
3. Le server.
4. Le client.

### Ressources : 
- Idea
- Student
- Repository

### Actions :
Sur la gestion des idées/projets : 
 - **addIdea(idEtudiant, titre, description)** : _ajouter une idée_
 - **changeState(idIdée)** : _Fais passer de l'état **idée** à l'état **projet**_
 - **removeIdea(idIdée)** : _supprimer une idée_
 - **changerDescription(idIdée, description)** : _changer la description d'un projet_
 - **getTitle(idIdée, titre)** : _changer le titre d'un projet
 
Permet de récupérer des idées/projets :
 - **getAll()** : _Récupère la liste de toutes les idées_
 - **getAllIdeas(idEtudiant)** : _Récupère la liste de toutes les idées d'un étudiant_
 - **getIdea(idIdée)** : _Récupère une idée entière_
 - **getDescription(idIdée)** : _Récupère la description d'une idée_
 - **getTitle(idIdée)** : _Récupère le nom d'une idée_
 
Gère l'intéraction entre un étudiant et les idées :
 - **addContributor(idEtudiant, idIdée)** : _Ajoute un étudiant comme possible participant à un projet_
 - **agreeParticipant(idEtudiant, idIdée)** : _Valide un étudiant comme partipant à un projet_
 - **getContributors(idIdée)** : _Récupère les candidats d'un projet_
 
Pour le super utilisateur :
 - **getProjets()** : _Récupère tout les projets_

 ## Classes nécessaires/partagées (sérialisées) :
 Le serveur et le client doivent se partager les classes "Request" et "Query". Toutes les classes de processing et du server ne sont nécessaires qu'au serveur, le client ne manipule pas directement le "modèle", il intéragit avec grace au server (par le biais de "Request") et obtiens l'affichage de son action via "Query". Les classes du client ne sont bien sur nécessaires qu'au client.
