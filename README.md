# SlotSwapperBackend

Backend service for the Slot Swapper application.

GitHub: [https://github.com/consoleLo-g/SlotSwapperBackend.git](https://github.com/consoleLo-g/SlotSwapperBackend.git)  
API Base URL: [https://slotswapperbackend-bg3b.onrender.com](https://slotswapperbackend-bg3b.onrender.com)  
Swagger UI: [https://slotswapperbackend-bg3b.onrender.com/swagger-ui/index.html?url=/v3/api-docs#](https://slotswapperbackend-bg3b.onrender.com/swagger-ui/index.html?url=/v3/api-docs#)

## Overview
Spring Boot backend managing users, slots, and bookings with JWT authentication and MongoDB persistence.

## Tech Stack
- Java 21  
- Spring Boot  
- Spring Security (JWT)  
- MongoDB  
- Maven  
- Docker  

## Getting Started

### Build & Run
```bash
git clone https://github.com/consoleLo-g/SlotSwapperBackend.git
cd SlotSwapperBackend
./mvnw clean package -DskipTests
java -jar target/SlotSwapperBackend-<version>.jar
```

## Docker Run
```
docker build -t slotswapperbackend .
docker run -e JWT_SECRET=yourSecret \
           -e MONGODB_URI=yourMongoUri \
           -p 8080:8080 slotswapperbackend
```
## Configuration
Set via environment variables:

- JWT_SECRET – JWT secret

- JWT_EXPIRATION – Token expiry (ms, default 86400000)

- MONGODB_URI – MongoDB connection string

- MONGODB_DB – Database name (default: slotswapper)

- SPRING_PROFILES_ACTIVE – Spring profile (default: prod)

## API Endpoints

### Auth Controller
| Path | Method | Description |
|------|--------|-------------|
| /auth/register | POST | Register a new user |
| /auth/login    | POST | Authenticate user → JWT |
| /auth/me       | GET  | Get current user info |

### User Controller
| Path | Method | Description |
|------|--------|-------------|
| /users/update | PUT    | Update user details |
| /users        | GET    | Get all users |
| /users        | DELETE | Delete user(s) |
| /users/{id}   | GET    | Get user by ID |

### Swap Controller
| Path | Method | Description |
|------|--------|-------------|
| /swaps/{id}/status | PUT  | Update swap status |
| /swaps/request     | POST | Request a swap |
| /swaps/all         | GET  | Get all swaps |

### Event Controller
| Path | Method | Description |
|------|--------|-------------|
| /events/{id}               | PUT  | Update event |
| /events/{id}/make-swappable | PUT  | Make event swappable |
| /events/create             | POST | Create new event |
| /events/user               | GET  | Get events for current user |
| /events/all                | GET  | Get all events |
| /events                    | DELETE | Delete event(s) |

## Deployment

- Build Docker image

- Set environment variables on Render

- Expose port 8080

## Security

JWT authentication; send token in Authorization: Bearer <token> for protected endpoints.
