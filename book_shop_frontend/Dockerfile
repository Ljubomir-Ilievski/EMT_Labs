#Stage 1 - install dependencies and create a build folder with static files ready to be served.
FROM node:20-alpine AS build

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

#Stage 2 - Use the made static files from the previous stage to serve them with an nginx web server.
FROM nginx:stable-alpine

COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
