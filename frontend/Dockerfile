FROM node:19-alpine AS build

WORKDIR /app/frontend

COPY package*.json ./

RUN npm install -g @angular/cli

COPY . .

RUN npm install && \
    ng build

FROM nginx:1.17.1-alpine

COPY --from=build /app/frontend/dist/frontend /usr/share/nginx/html

EXPOSE 80