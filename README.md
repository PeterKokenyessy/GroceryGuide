## Grocery Guide

    A full-stack web application for comparing grocery prices across multiple online stores (e.g. Tesco, Auchan).
    The goal of the system is to help users find the cheapest shopping option based on their selected products.

# Team

This project was developed by a team of four:

- [Zoli](https://github.com/zoleman)
- [Bobita](https://github.com/pbobita)
- [Aron](https://github.com/aron166)
- [Peter (Me)](https://github.com/PeterKokenyessy)


# Features
-  Product search across multiple stores
- Shopping list management
- Full cart price comparison
- Automated price collection 
- Tech Stack
    - Backend: Spring Boot
    - Frontend: React
    - Database: PostgreSQL
    - Other: Docker

# Testing

The project includes multiple levels of testing to ensure reliability:

Unit & Integration tests: implemented with JUnit
End-to-end tests: implemented with Selenium
# CI/CD & Workflow

The project follows a modern Git-based development workflow:

- Feature-based branching strategy
- Pull Request-based development
- Automated checks on PR (build + tests)    
CI pipeline ensures all tests pass before merge

Continuous Integration is configured using GitHub Actions, which:

builds the project
runs all JUnit tests (unit + integration)
executes Selenium tests

Only successfully verified branches can be merged into the main branch.