FROM node:13.12.0-alpine

RUN mkdir /ui-service

COPY . /ui-service

WORKDIR /ui-service

RUN mkdir /node_modules
RUN chmod -R a+rw .

RUN npm config set unsafe-perm true

RUN npm install --unsafe-perm=true --allow-root

RUN npm rebuild node-sass

ENV PATH ./node_modules/.bin:$PATH

CMD ["npm", "start"]

EXPOSE 3000