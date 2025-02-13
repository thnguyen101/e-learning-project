FROM node:22-slim as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .

RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist/angular-ui/browser /usr/share/nginx/html/ui/
COPY nginx.conf /etc/nginx/nginx.conf
