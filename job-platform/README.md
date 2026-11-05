# CareerBoard — Job Portal

A full-stack recruitment platform foundation built with Java 17, Spring Boot, Spring Data JPA, H2 and a responsive vanilla-JavaScript frontend.

## Current implementation
- REST endpoints to create, list, filter, update and delete job listings.
- Search by title/company, location and employment type.
- Bean Validation for incoming job data.
- Centralized API error responses.
- Persistent local H2 database.
- Responsive frontend with safe HTML escaping and API error states.

## Run locally
### Backend
```bash
cd backend
mvn spring-boot:run
```
The API starts at `http://localhost:8080`.

### Frontend
Serve the `frontend` directory with a local static server, for example VS Code Live Server. Open its generated URL. The backend CORS configuration allows localhost:5500 and localhost:5173.

## API
| Method | Endpoint | Purpose |
|---|---|---|
| GET | /api/jobs | List jobs |
| GET | /api/jobs?q=java&location=Kolkata&type=Internship | Filter jobs |
| POST | /api/jobs | Create a listing |
| PUT | /api/jobs/{id} | Update a listing |
| DELETE | /api/jobs/{id} | Delete a listing |

POST/PUT JSON:
```json
{"title":"Java Developer Intern","company":"Example Labs","location":"Kolkata","employmentType":"Internship","description":"Work with Java and REST APIs."}
```

## Roadmap
Authentication and role-based authorization, candidate profiles, resume storage, applications and status tracking, PostgreSQL migration, automated tests, deployment, and explainable resume-to-job matching are planned follow-on milestones. These features are not represented as implemented yet.

## Scope and safety
This is a portfolio/demo application. Seed data is not supplied and job listings must be added through the API. Do not upload real candidate personal data until authentication, authorization, secure file handling and privacy controls are implemented.
