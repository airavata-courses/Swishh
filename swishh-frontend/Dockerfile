FROM node:13.12.0-alpine
RUN mkdir /ui-service
COPY . /ui-service
WORKDIR /ui-service
RUN sudo chown -R $USER:$(id -gn $USER) /.config
RUN npm install --silent
RUN npm rebuild node-sass --force
ENV PATH ./node_modules/.bin:$PATH
CMD ["npm", "start"]
EXPOSE 3000