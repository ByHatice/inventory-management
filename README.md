# Inventory Management System

A full-stack web application for managing inventory items with low stock warnings and CRUD operations.

## Overview

This project consists of a Java Spring Boot backend and a Vue.js frontend that together provide a complete inventory management solution. Users can create, read, update, and delete inventory items, with special attention to low stock monitoring and quick quantity updates.

## Tech Stack

### Backend
- **Java 21**
- **Maven** for dependency management
- **Spring Boot 3.5.5**
- **SQLite Database**

### Frontend
- **Vue.js 3** 
- **Bootstrap 5** 
- **Vue Router** for navigation
- **Fetch API** for HTTP requests
- **Vite** for development and build

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 
- Node.js 20.19.0
- Git

### Backend Setup

1. Clone the repository:
```bash
git clone https://github.com/ByHatice/inventory-management.git
cd inventory-management/backend
```
2. Install dependencies and compile:
```bash
mvn clean install
```

3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to frontend directory:
```bash
cd ../frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start development server:
```bash
npm run dev
```

The frontend will start on `http://localhost:5173`

## API Endpoints

### Items
- `GET /all` - Get all items
- `GET /item` - Get item by ID
- `GET /low-stock` - Get low stock items
- `POST /create` - Create new item
- `PUT /update` - Update item quantity
- `DELETE /delete` - Delete item

## Assumptions & Limitations

### Technical Assumptions
- SQLite database is suitable for development and small-scale deployment
- Frontend assumes backend is running on localhost:8080 during development
- CORS is properly configured for the development environment
- No authentication/authorization is required for this application
- Asynchronous communication using Fetch API and async/await patterns

### Functional Limitations
- Item updates only support quantity changes via the current API endpoints
- Low stock threshold is hardcoded to 10 items (configurable in frontend code only)
- No user management or multi-tenancy support
- No audit trail or history tracking for item changes

### Data Assumptions
- Item quantities are always non-negative integers
- Item names and units are text fields with basic validation
- No import/export functionality for bulk data operations

### Infrastructure Assumptions
- Single-user application (no concurrent user handling)
- Local development environment setup
- No production deployment configuration included
- No backup or disaster recovery mechanisms implemented

## License

This project is licensed under the MIT License.
