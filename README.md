
# Participant Management

This is a technical assignment to manage participants

## API Reference

#### Create a participant

```http
  POST /api/participants
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `code` | `string` | **Required**. code of the participant |
| `bic` | `string` | **Required**. bank identifier |
| `name` | `string` | **Required**. name of participant |
| `shortName` | `string` | **Required**. short name of participant |
| `type` | `string` | **Required**. type of participant |
| `logo` | `string` | **Required**. image of participant |
| `settlementBank` | `string` | **Required**. settlement of bank |

#### Update a participant

```http
  PUT /api/participants/{code}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `code` | `string` | **Required**. code of the participant to update |
| `bic` | `string` | bank identifier |
| `name` | `string` | name of participant |
| `shortName` | `string` | short name of participant |
| `type` | `string` | type of participant |
| `logo` | `string` | image of participant |
| `settlementBank` | `string` | settlement of bank |

#### Find all participants

```http
  GET /api/participants
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `code` | `string` | code of the participant |
| `bic` | `string` | bank identifier |
| `name` | `string` | name of participant |
| `shortName` | `string` | short name of participant |
| `type` | `string` | type of participant |
| `logo` | `string` | image of participant |
| `settlementBank` | `string` | settlement of bank |


## Deployment

To deploy this project run, you can make use of the Makefile commands to facilitate test of the app.

First to test the backend application run

```bash
  make test
```

You can see the coverage of the app that was performed using Jacoco Plugin under the target folder inside site folder in an index.html file. The code coverage reached 100% for the service layer.

run the docker compose file to run the spring app, postgreSql database, grafana, and prometheus servers using

```bash
  make run
```

To shut down your containers run

```bash
  make down
```

## Testing

In the following project, you can find two types of testing, Unit testing for the service layer and integration testing for the controller layer.

Code Coverage of the project : 88%

## Tech Stack

**Server:** Springboot, Jpa

**Frontend:** Angular

**State Management:** Ngrx

**Styling:** Angular Material UI, Tailwind

**Unit Testing:** JUnit 5, Mockito

**Database:** PostgreSql

**Containerization:** Docker, Docker compose

**API Documentation** Swagger

**Monitoring** Prometheus, Grafana

## PORTS

Springboot App : 8082

PostgreSql Database: 5432

Grafana: http://localhost:3000/

Proemetheus: http://localhost:9090/

Swagger: http://localhost:8082/swagger-ui/index.html

Frotend: http://localhost:4200/

## Support

For support, email ahmedennaime20@gmail.com