# Project Description
RestAPi quotes project for create, update, delete quotes using spring security and jwt.

# Project Features
1. Create, Read, Update User
2. Block users, list all users (must have role admin)
3. Like & Dislike a quote
4. Create, Update, Delete, list all comments
5. Make a quote favorite
6. Simple Uml Diagrams (Class Diagram, Use Case)

# Mock Testing - Http Method Testing
1. Create User - Post Method - (/users/createUser)

# How to install
1. Enter docker folder
```bash
cd docker
```
2. Start mysql db
```bash
docker compose -f mysql.yml up
```
3. Execute sql commands in `data.sql` that are in `src/main/resources` folder.
4. Start application
5. Try with Postman