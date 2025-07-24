# Stage 1: Build the frontend application
FROM node:20-alpine as builder

WORKDIR /app

ARG VITE_BACKEND_HOST=backend.local
ARG VITE_BACKEND_PORT=8080
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the built application with a lightweight web server (e.g., Nginx)
FROM nginx:alpine

COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]