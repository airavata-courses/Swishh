FROM python:3.8.5-alpine
COPY . /docker-image-service
WORKDIR /docker-image-service

RUN python -m pip install --upgrade pip
RUN pip install -r requirements.txt
RUN pip install opencv
EXPOSE 5006

ENTRYPOINT [ "python" ]

CMD [ "Image_meta_data_service\server.py" ]
