# CLAUDE.md - Development Guidelines

## Build Commands
- Build project: `./gradlew build`
- Run tests: `./gradlew test`
- Run single test: `./gradlew test --tests org.formation.pocplb.service.ChatModelServiceTest`
- Run application: `./gradlew bootRun`

## Code Style Guidelines
- **Imports**: Group by dependency type (Java, Spring, project packages)
- **Formatting**: 4-space indentation, opening brace on same line
- **Types**: Use Spring annotations (`@Service`, `@Repository`, etc.), Lombok annotations for boilerplate reduction
- **Naming**: Classes=PascalCase, Methods/Variables=camelCase, Constants=UPPER_SNAKE_CASE
- **Error Handling**: Log errors with SLF4J, use proper exception handling

## Architecture
- Spring Boot application using Spring AI (controllers, services, repositories pattern)
- Docker compose files for running with Chroma or Elasticsearch
- JPA entities in model.plbsi package
- Services handle AI/vectorstore operations using OpenAI models