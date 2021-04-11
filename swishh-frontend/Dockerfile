FROM node:14.16.1-alpine as build-step
#RUN mkdir /ui-service
#COPY . /ui-service
#WORKDIR /ui-service
#RUN npm install 
RUN mkdir /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build
RUN npm rebuild node-sass --force



FROM nginx:1.19.9-alpine
COPY --from=build-step /app/build /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]