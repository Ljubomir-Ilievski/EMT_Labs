# Stage 1: Build the frontend application
FROM node:20-alpine as builder

WORKDIR /app

# Declare build arguments that will be passed from docker-compose.yml
ARG VITE_BACKEND_HOST
ARG VITE_BACKEND_PORT

# Copy package.json and package-lock.json (or yarn.lock) first
# to leverage Docker's build cache
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of your application source code
COPY . .

# Build the Vite application.
# Vite automatically picks up environment variables prefixed with VITE_
# that are present in the build environment (which includes ARGs).
RUN npm run build

# Stage 2: Serve the built application with a lightweight web server (e.g., Nginx)
FROM nginx:alpine

# Copy the built application from the builder stage
COPY --from=builder /app/dist /usr/share/nginx/html

# Expose port 80 (default for Nginx)
EXPOSE 80

# Command to run Nginx when the container starts
CMD ["nginx", "-g", "daemon off;"]