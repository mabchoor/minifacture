# MiniFacture

[![Java](https://img.shields.io/badge/Java-21-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-brightgreen?logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apache-maven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-success)](https://github.com/username/minifacture)

Une API REST de facturation moderne et légère, construite avec Java 21 et Spring Boot 3. MiniFacture offre une gestion complète des factures avec calculs automatiques, gestion des clients et export de données en toute simplicité.

## Table des matières

- [Fonctionnalités](#fonctionnalités)
- [Stack technologique](#stack-technologique)
- [Démarrage rapide](#démarrage-rapide)
  - [Prérequis](#prérequis)
  - [Installation](#installation)
  - [Lancement de l'application](#lancement-de-lapplication)
- [Documentation API](#documentation-api)
- [Configuration](#configuration)
- [Tests](#tests)
- [Architecture](#architecture)
- [Règles métier](#règles-métier)

## Fonctionnalités

- **Gestion des clients** : opérations CRUD complètes sur les clients
- **Génération de factures** : création de factures détaillées avec calculs automatiques
- **Calcul de TVA** : calcul automatique de la TVA avec taux configurables
- **Export de données** : export des factures et clients au format JSON
- **API RESTful** : endpoints REST clairs et documentés
- **Documentation interactive** : Swagger UI intégré pour explorer l'API
- **Sécurité** : authentification et autorisation
- **Base de données** : H2 en mémoire pour le développement

## Stack technologique

- **Backend** : Java 21, Spring Boot 3.5.0
- **Sécurité** : Spring Security
- **Base de données** : H2 (en mémoire)
- **Build** : Maven
- **Documentation** : OpenAPI 3.0 (Swagger)
- **Librairies additionnelles** : Lombok pour réduire le boilerplate

## Démarrage rapide

### Prérequis

- Java 21 ou supérieur
- Maven 3.8.0 ou supérieur
- Git

### Installation

1. Clonez le dépôt :

```bash
git clone https://github.com/mabchoor/minifacture.git
cd minifacture
```

2. Compilez le projet :

```bash
mvn clean install
```

### Lancement de l'application

Démarrez l'application avec Maven :

```bash
mvn spring-boot:run
```

L'application sera disponible à :

- **URL de base de l'API** : http://localhost:8081
- **Swagger UI** : http://localhost:8081/swagger-ui.html
- **Console H2** : http://localhost:8081/h2-console

### Identifiants par défaut

Pour le développement, utilisez ces identifiants :

- **Nom d'utilisateur** : `user`
- **Mot de passe** : `password`

> ⚠️ **Sécurité** : Ce sont des identifiants de développement par défaut. Changez-les en production.

## Documentation API

### Aperçu des endpoints

| Méthode  | Endpoint         | Description                    | Auth requise |
| -------- | ---------------- | ------------------------------ | ------------ |
| `GET`    | `/clients`       | Liste des clients              | ✅           |
| `POST`   | `/clients`       | Créer un client                | ✅           |
| `GET`    | `/clients/{id}`  | Détail d'un client             | ✅           |
| `DELETE` | `/clients/{id}`  | Supprimer un client            | ✅           |
| `GET`    | `/factures`      | Liste des factures (filtrable) | ✅           |
| `POST`   | `/factures`      | Créer une facture              | ✅           |
| `GET`    | `/factures/{id}` | Détail d'une facture           | ✅           |
| `DELETE` | `/factures/{id}` | Supprimer une facture          | ✅           |

### Exemple de requête

```bash
curl -X POST http://localhost:8081/factures \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjpwYXNzd29yZA==" \
  -d '{
    "clientId": 1,
    "dateFacture": "2025-01-15",
    "lignes": [
      {
        "description": "Service de conseil",
        "quantite": 2,
        "prixUnitaire": 500.00,
        "tauxTva": 20.0
      }
    ]
  }'
```

Pour la documentation complète de l'API, consultez le [Swagger UI](http://localhost:8081/swagger-ui.html) lorsque l'application est lancée.

## Configuration

### Configuration de la base de données

L'application utilise par défaut une base H2 en mémoire. Pour accéder à la console H2 :

- **URL** : `jdbc:h2:mem:facture-db`
- **Utilisateur** : `sa`
- **Mot de passe** : _(laisser vide)_

### Propriétés d'application

Principales options dans `application.yml` :

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:h2:mem:facture-db
    driverClassName: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

  security:
    user:
      name: user
      password: password
```

## Tests

Lancez la suite de tests :

```bash
# Lancer tous les tests
mvn test

# Lancer les tests d'intégration
mvn verify

# Run tests with coverage
mvn test jacoco:report
```

## Architecture

### Structure du projet

```
src/main/java/com/minifacture/
├── FacturationApplication.java
├── client/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   └── service/
├── facture/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   └── service/
├── config/
├── mapper/
└── util/
    ├── exception/
    └── validator/
```

### Architecture système

```mermaid
graph TB
    A[Application cliente] --> B[REST Controller]
    B --> C[Service]
    C --> D[Repository]
    D --> E[H2 Database]

    F[Filtre de sécurité] --> B
    G[Gestionnaire d'exceptions] --> B
    H[Validation] --> C
```

### Flux de création de facture

```mermaid
sequenceDiagram
    participant Client
    participant Controller
    participant Service
    participant Repository
    participant Database

    Client->>Controller: POST /factures
    Controller->>Service: createFacture(dto)
    Service->>Service: validerFacture()
    Service->>Service: calculerTotaux()
    Service->>Repository: save(facture)
    Repository->>Database: persist
    Database-->>Repository: entité sauvegardée
    Repository-->>Service: facture
    Service-->>Controller: factureDto
    Controller-->>Client: HTTP 201 Créé
```

## Règles métier

### Validation des factures

- Les factures doivent contenir au moins une ligne
- Tous les champs obligatoires doivent être renseignés
- Les quantités doivent être des nombres positifs
- Les prix unitaires doivent être supérieurs à zéro

### Taux de TVA supportés

- `0%` : produits exonérés
- `5.5%` : taux réduit (livres, alimentation, etc.)
- `10%` : taux intermédiaire (transport, restauration)
- `20%` : taux normal (biens et services courants)

### Calculs

- **Total ligne** : `quantité × prixUnitaire`
- **Montant TVA** : `totalLigne × (tauxTva / 100)`
- **Total ligne TTC** : `totalLigne + montantTva`
- **Total facture** : Somme de toutes les lignes TTC
