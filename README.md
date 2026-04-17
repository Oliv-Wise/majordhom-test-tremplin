# majordhom-test-tremplin

## À propos de moi 
Je m’appelle Marvens Oliver Joseph, étudiant en L3 informatique dans le parcours MIAGE de l’Université de Lille. 
Je suis à la recherche d’un stage de fin de licence d’une durée d’au moins 2 mois.
Quelques liens vers mes profils : 
Mon LinkedIn : https://www.linkedin.com/in/marvens-oliver-joseph ; 
Mon GitHub : https://github.com/Oliv-Wise ; 
Mon portfolio, qui présente plusieurs projets réalisés : https://oliv-wise.github.io/My_Portfolio/

Je suis ravi de vous présenter mon travail dont le code source est disponible ici : 
https://github.com/Oliv-Wise/majordhom-test-tremplin

## Photo de la page principale 
Un screenshot de la page principale : 
![alt text](image.png)

## Stack technique et choix
### Front-end

React 19 :  Pour construire une interface dynamique et gérer proprement les interactions du formulaire, notamment la mise à jour en temps réel des champs, l’ajout/suppression de disponibilités et l’affichage des retours utilisateur après soumission.

TypeScript : Je l’ai utilisé pour mieux encadrer la structure des données du formulaire, sécuriser les manipulations côté front et rendre plus explicite le contrat entre ce qui est saisi dans l’interface et ce qui est envoyé à l’API.

Fichier de types dédié (contact.ts) : J’ai volontairement déplacé les types hors du composant principal pour éviter de surcharger encore plus App.tsx, pour garder une structure plus lisible et centraliser la définition des données métier du formulaire.

CSS : J’ai choisi de ne pas utiliser de bibliothèque UI afin de garder un contrôle fin sur l’intégration visuelle et reproduire la maquette avec davantage de précision, notamment sur les espacements, les proportions, les colonnes et le rendu global sur fond d’image.

Fetch API : Je l’ai utilisée pour la communication front-back, afin de rester sur une solution suffisante pour un exercice de cette taille, tout en gardant visible la logique d’envoi HTTP.

### Back-end

Spring Boot 4.0.5 / Java 17 : J’ai choisi cette stack back-end car elle permet de structurer rapidement une API avec une séparation nette entre contrôleur, service et accès aux données.

JdbcTemplate : Je l’ai utilisé à la place d’un ORM pour garder une maîtrise directe des requêtes SQL, ce qui me semblait pertinent pour un exercice court avec un modèle relationnel simple et deux tables à alimenter.

H2 Database : Elle m’a permis de mettre en place rapidement une base locale légère, facile à lancer et pratique à vérifier via sa console pendant les phases de test.

Maven : Je l’ai utilisé pour gérer les dépendances et lancer facilement le projet back-end dans un cadre Java/Spring.

## Lancement du projet
Le projet est séparé en deux parties : un front-end React et un back-end Spring Boot.
Le front-end est configuré pour démarrer sur le port 5173.
Le back-end démarre sur le port 8080.
Si l’un de ces ports est déjà utilisé, il faut libérer le port concerné avant de lancer le projet.

### 1. Cloner le projet
 
```bash
git clone <url-du-repo>
cd <nom-du-repo>
```

### 2. Lancer le back-end
Depuis le dossier `backend`, exécuter : 
```bash
cd backend
./mvnw spring-boot:run
```
Pour compiler les tests unitaires :
Depuis la racine du back-end, éxecuter : 
```bash
./mvnw test 
```
- Le serveur démarre par défaut ici : http://localhost:8080
- La console H2 est disponible ici : http://localhost:8080/h2-console
avec les paramètres suivants : 
JDBC URL : jdbc:h2:file:./data/majordhomdb
User Name : sa
Password : laisser vide

### 3. Lancer le front-end : 
Depuis le dossier `frontend`, exécuter :
```bash
cd frontend
npm install
npm run dev
```
Le front démarre par défaut sur : http://localhost:5173

### 4. Utilisation
- Ouvrir l’application front-end dans le navigateur
- Remplir le formulaire
- Ajouter au moins une disponibilité
- Cliquer sur Envoyer
- Les données sont alors transmises à l’API puis enregistrées en base de données
 
### 5. Vérification des données enregistrées

Après l’envoi du formulaire, les données enregistrées peuvent être vérifiées avec les requêtes suivantes : 

```sql
SELECT * FROM contact_requests;
SELECT * FROM availabilities;
```

## Questions: 
### Avez-vous trouvé l’exercice facile ou difficile ? Qu’est-ce qui vous a posé problème ?

Dans l’ensemble, j’ai trouvé l’exercice accessible sur le plan technique, car il mobilise des notions que j’ai déjà rencontrées dans mes projets : création d’un formulaire en React, gestion d’état avec useState, envoi des données vers une API REST et enregistrement en base de données côté back-end.

En revanche, l’exercice devient plus exigeant dès qu’on cherche à le faire proprement et avec minutie. La partie la plus délicate pour moi a été l’intégration fidèle de la maquette : ajuster les proportions des champs, retrouver une disposition visuellement cohérente en deux colonnes, gérer les détails d’alignement, la taille des boutons radio, ainsi que le comportement de l’image de fond sans perdre les éléments visuels importants.

Le second vrai point d’attention a été la liaison complète entre le front et le back. Il s’agissait de faire circuler un flux de données cohérent entre React, le contrôleur Spring Boot, le service métier, le repository JDBC et la base H2, en tenant compte de la spécificité qu’une demande peut aussi contenir plusieurs disponibilités.

Enfin, j’ai également dû corriger quelques points techniques pendant le développement, qui sont liés à l’organisation des types TypeScript dans un fichier dédié, la gestion des imports de types, ainsi qu’un problème côté back-end lié à la récupération de la clé générée après insertion en base.

Ce n’était donc pas un exercice “dur” au sens bloquant, mais c’était un exercice qui demandait de la rigueur à chaque étape.

 ### Avez-vous appris de nouveaux outils pour répondre à l’exercice ? Si oui, lesquels ?
Oui. L’outil qui m’a le plus apporté ici est la console H2, que je connaissais de nom mais que je n’avais pas encore vraiment utilisée comme outil de vérification dans un projet complet.

Cet exercice m’a permis de l’utiliser pour inspecter la base locale, vérifier la création des tables, exécuter des requêtes SQL simples et surtout confirmer que les données envoyées depuis le front étaient bien enregistrées côté back-end. Cela m’a été utile non seulement pour valider le fonctionnement global, mais aussi pour comprendre plus précisément où se situait un problème lorsqu’un envoi de formulaire échouait.

Au-delà de l’outil lui-même, j’ai surtout appris à mieux m’appuyer sur la base comme point de contrôle pendant le développement, et pas uniquement sur l’interface front ou les logs de l’application.

 ### Quelle est la place du développement web dans votre cursus de formation ?
Le développement web occupe une place prépondérante dans mon parcours, à la fois dans ma formation universitaire et dans mes projets personnels.

J’ai d’abord acquis les bases du front-end avec HTML, CSS et JavaScript dans ma formation. Puis j’ai approfondi mes compétences en front avec React, que j’ai particulièrement apprécié pour sa logique de composants et la manière dont il structure les interfaces. C’est d’ailleurs ce qui m’a poussé à réaliser d’autres projets web en dehors des cours, afin d’acquérir plus de compétences et d’avoir des expériences professionnelles.

En parallèle, mon cursus m’a aussi amené à travailler le développement côté serveur. Avec notamment du JavaScript côté client et serveur, de la programmation asynchrone, Node.js, Express, MongoDB, ainsi que d’autres projets orientés architecture logicielle. Plus récemment, j’ai aussi commencé à intégrer TypeScript à mes projets, pour devenir plus structuré et plus fiable dans ma manière d’écrire du code.

Le web représente donc pour moi à la fois un terrain d’application de mes compétences techniques, un espace de progression, et un domaine dans lequel je souhaite continuer à évoluer professionnellement.

### Avez-vous utilisé un LLM ? Si oui, comment intégrez-vous les LLM à chaque étape de votre workflow ?
 Oui, j’ai utilisé un LLM comme un outil d’assistance. Je l’ai surtout utilisé pendant trois moments de mon développement. 
 D’abord, pour accélérer certaines phases de réflexion technique, par exemple pour comparer plusieurs manières d’organiser le code, pour décider entre utiliser une base H2 ou Oracle.
 Ensuite, pour gagner du temps sur des ajustements visuels précis pendant l’intégration de la maquette, notamment sur les dimensions des règles CSS, les espacements et certains comportements CSS qui demandaient plusieurs itérations. 
 
Enfin, pour identifier des incohérences comme des erreurs de syntaxe et corriger.

Je garde toujours la main sur les choix finaux. Je teste ce qui est proposé, je corrige, j’adapte au contexte du projet, et je ne conserve que ce que je comprends et que je trouve cohérent. 
Dans cet exercice, l’IA m’a surtout servi d’accélérateur de réflexion, d’aide au débogage et d’appui pour ajuster plus finement l’intégration de la maquette. 
