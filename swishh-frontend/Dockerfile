FROM node:14.16.1-alpine
RUN mkdir /ui-service
COPY . /ui-service
WORKDIR /ui-service
RUN npm install 
RUN npm rebuild node-sass --force
CMD ["npm", "start"]
EXPOSE 3000