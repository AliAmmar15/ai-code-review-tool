# AI Code Review Tool

**Team Name:** BugBuster  
**Team Member:** Ali Ammar

## What is AI Code Review Tool?

AI code review tool is a tool that checks code for bugs, bad practices, and ways to make it better. It gives helpful feedback so you can improve your code faster.

## Who is it for?

- **Students** – Learn and improve coding assignments  
- **Developers** – Catch mistakes before release  
- **Teachers** – Quickly review student code

## Key Features

- Auto code review for Java
- Easy-to-use JavaFX app
- Backend built with Spring Boot
- Saves your code reviews in a database
- (Optional) GitHub integration for PR reviews

## Tech Used

- JavaFX (Frontend)
- Spring Boot (Backend)
- PostgreSQL/MySQL (Database)
- OpenAI API (Optional for smarter analysis)
- Hosting: AWS, Firebase, or Google Cloud

## Project Timeline

- Week 1-2: Backend + Database
- Week 3: JavaFX frontend
- Week 4: Code analysis logic
- Week 5: GitHub integration + testing

## Time Estimate

- Backend: 12 hrs  
- Frontend: 8 hrs  
- Code Analysis: 15 hrs  
- Testing: 8 hrs  
**Total: ~43 hrs**

## Challenges

- Detecting real code issues
- Giving clear suggestions
- Handling big code files

## To Run backend
- navigate to backend folder
- In terminal, run "export OPENAI_API_KEY=your_openai_api_key_here" (replace with youre actual OpenAI key)
- In terminal, run mvn clean install and mvn spring-boot:run
- backend should now be running

## To Run frontend
- navigate to frontend folder
- in terminal, run mvn clean install and mvn javafx:run
- frontend should now be running



