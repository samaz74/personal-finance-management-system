# Financial Management System

A personal financial management REST API built with Spring Boot, Spring Data JPA, Spring Security, and MariaDB.
Features include JWT authentication, multi-account support, transaction management, budget tracking, and Excel/PDF reporting.

## Technologies
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Spring Security + JWT
- MariaDB
- Apache POI (Excel)
- iText (PDF)
- Lombok
- Maven
- Docker

## Project Structure
- **Model** — User, Account, Bank, Category, Transaction, Budget, InvalidatedToken
- **Repository** — Spring Data JPA repositories
- **Service** — Business logic
- **Controller** — REST API endpoints
- **DTO** — Request/Response objects
- **Security** — JWT Filter, UserDetails, SecurityConfig
- **Exception** — Global exception handling

## Security
JWT-based authentication with token blacklisting.
- Tokens expire after 20 minutes
- Logged out tokens are blacklisted

### How to use

Authorization: Bearer <token>


## API Endpoints

### Auth `/api/auth`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/auth/register` | Register |
| POST | `/api/auth/login` | Login |
| POST | `/api/auth/logout` | Logout |

### Users `/api/users`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/{id}` | Get user by ID |
| GET | `/search/firstName/{name}` | Search by first name |
| GET | `/search/lastName/{name}` | Search by last name |
| GET | `/search/email/{email}` | Search by email |
| PUT | `/{id}` | Update user |
| DELETE | `/{id}` | Delete user |

### Banks `/api/banks`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/` | Get all banks |
| GET | `/{id}` | Get bank by ID |
| GET | `/search/{name}` | Search by name |
| POST | `/` | Create bank |
| PUT | `/{id}` | Update bank |
| PATCH | `/toggle/{id}` | Toggle status |

### Accounts `/api/accounts`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/user/{userId}` | Get user accounts |
| GET | `/{id}` | Get account by ID |
| POST | `/{userId}` | Create account |
| PUT | `/{id}` | Update account |
| DELETE | `/{id}` | Delete account |

### Categories `/api/categories`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/user` | Get user categories |
| GET | `/{id}` | Get category by ID |
| POST | `/` | Create category |
| PUT | `/{id}` | Update category |
| DELETE | `/{id}` | Delete category |

### Transactions `/api/transactions`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/{id}` | Get transaction by ID |
| GET | `/current/{accountId}` | Get account transactions |
| GET | `/category/{categoryId}` | Get by category |
| POST | `/` | Create transaction |
| GET | `/report/transactions/excel/{accountId}` | Excel report |
| GET | `/report/transactions/pdf/{accountId}` | PDF report |

### Budgets `/api/budgets`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/{id}` | Get budget by ID |
| GET | `/current` | Current month budget |
| GET | `/status` | Budget status |
| POST | `/` | Create budget |
| PUT | `/{id}` | Update budget |

## How to Run

### Prerequisites
- Java 21
- MariaDB
- Maven

### Setup
1. Clone the repository
```bash
git clone https://github.com/samaz74/financial-management.git
```

2. Create database
```sql
CREATE DATABASE accounting;
```

3. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/accounting
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=yourSecretKey
jwt.expiration=1200000
```

4. Run
```bash
mvn spring-boot:run
```

### Docker
```bash
docker-compose up --build
```