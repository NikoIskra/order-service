
# Order service

Part of a multi module project. Handles orders.

## Features

- Store order data and items
- CRUD operations for accounts and roles
- Asynchrounous sending and listening of messages using Amazon SQS
- Unit and integration tests for positive and negative scenarios
- Code generation using openAPI
## Tech Stack

- Java Spring Boot
- PostgreSQL
- Dockerfile and docker-compose file
- Spotless Gradle
- GitHub actions which ensure all tests pass and Spotless has been applied
- Flyway migration scripts
- API specification using OpenAPI specification

## API Reference

#### Healtcheck

```http
  GET /api/v1/healthcheck
```

#### Create order [.http file](https://github.com/NikoIskra/order-service/blob/main/requests/create_order.http)

```http
  POST /api/v1/order
```

Header: 

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `account-id`      | `uuid` | **Required**. id of provider's account |

Request body:

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `providerId`      | `long` | **Required**. id of provider |
| `comment`      | `string` | **Optional**. order comment, length < 512 |
| `clientContact`      | `string` | **Required**. client's contact information, length < 512 |
| `deliveryAddress`      | `string` | **Optional**. client's delivery address, length < 512 |
| `orderItemId`      | `long` | **Required**. id of item |
| `orderSubItemIds`      | `array` | **Optional**.  array of SubItem id's, each with their own id and quantity|


#### Get order [.http file](https://github.com/NikoIskra/order-service/blob/main/requests/get_order.http)

```http
  GET /api/v1/order/{order-id}
```

Header: 

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `account-id`      | `uuid` | **Required**. id of provider's account |
| `order-id`      | `integer` | **Required**. id of order |


## Database model
Orders:

- `ID : long`
- `orderNumber : string`
- `providerId : uuid`
- `clientId : uuid`
- `comment : string`
- `totalPriceCents : integer`
- `clientContact : string`
- `deliveryAddress : string`
- `stage : string`
- `status : string`
- `CreatedAt : timestamp`
- `UpdatedAt : timestamp`

Order Item:

- `ID : long`
- `orderId : long`
- `providerItemId : long`
- `quantity : integer`
- `priceCents : integer`

Order Sub Item:

- `ID : long`
- `orderItemId : long`
- `providerSubItemId : long`
- `quantity : integer`
- `priceCents : integer`

Order Transition Log:

- `ID : string`
- `orderId : long`
- `comment : string`
- `totalPriceCents : integer`
- `clientContact : string`
- `deliveryAddress : string`
- `stage : string`
- `completeStatus : string`
- `CreatedAt : timestamp`

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DB_HOST`

`POSTGRES_DB`

`POSTGRES_USER`

`POSTGRES_PASSWORD`


## Run Locally

Clone the project

```
  git clone https://github.com/NikoIskra/order-service
```

Go to the project directory

```
  cd order-service
```

Build .jar file

```
  ./gradlew build
```

Start WSL and locate service directory

```
  cd /mnt/c/order-service
```

Run docker compose

```
docker compose up
```

