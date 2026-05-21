# Travel Booking

Spring Boot microservices project with Eureka discovery, Zuul API Gateway, and separate flight, hotel, and car rental services.

## Services

- Discovery Service: `8761`
- API Gateway: `8080`
- Flight Service: `8081`
- Hotel Service: `8082`
- Car Rental Service: `8083`

## Run Locally

Requirements:

- Docker
- Docker Compose

```powershell
docker compose up --build
```

API routes go through the gateway:

- `http://localhost:8080/api/flights`
- `http://localhost:8080/api/hotels`
- `http://localhost:8080/api/car-rentals`

## Deploy On AWS Server

On the server, install Docker and Docker Compose, then clone the repository:

```bash
git clone <your-github-repo-url>
cd travel-booking
docker compose up --build -d
```

Open these ports in the AWS security group if you need external access:

- `8080` for API Gateway
- `8761` for Eureka dashboard, optional

For production image tags, copy `.env.example` to `.env` and update the values:

```bash
cp .env.example .env
docker compose up --build -d
```
