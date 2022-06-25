FROM node:16.14.0-alpine3.15 as angular-built
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=angular-built /app/dist/frontend /usr/share/nginx/html
