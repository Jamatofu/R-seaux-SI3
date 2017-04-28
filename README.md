
## Liste des requêtes :

- s'identifier
- ajouter une idée
- participer à une idée
- lister les étudiants par idée
- se déconnecter
- administration d'idée
	- approuver la participation d'un étudiant à une idée
	- changer statut : idée => projet
	- modifier l'idée
	- pouvoir lister ses propres idées

## Ressources :

- Idée
- Étudiant

## Action :

Sur la gestion des idées/projets : 

 - ajouter(description, auteur) : _ajouter une idée_
 - changerEtat(id) : _Fais passer de l'état **idée** à l'état **projet**_
 - supprimer(id) : _supprimer une idée_
 - changerDescription(idIdée, description) : _changer la description d'un projet_
 - changerNom(idIdée, name) : _changer le nom d'un projet
 
 Permet de récupérer des idées/projets
 
 - getAll() : _Récupère la liste de toutes les idées_
 - get(id) : _Récupère une idée entière_
 - getDescription(id) : _Récupère la description d'une idée_
 - getName(id) : _Récupère le nom d'une idée_
 
 
 Gère l'intéraction entre un étudiant et les idées
 
 - participer(idEtudiant, idProjet) : _Ajoute un étudiant comme possible participant à un projet_
 - validerParticipation(idEtudiant, idProjet) : _Valide un étudiant comme partipant à un projet_
 - getCandidats(idProjet) : _Récupère les candidats d'un projet_