FROM python:3.8.2-slim-buster
COPY . /docker-image-service
WORKDIR /docker-image-service

RUN pip install -r requirements.txt
EXPOSE 5006

ENTRYPOINT [ "python" ]

CMD [ "Image_meta_data_service\server.py" ]
