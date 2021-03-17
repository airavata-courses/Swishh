FROM python:3.8.2
COPY /code /userservice
WORKDIR /userservice
RUN dir
RUN pip install -r requirements.txt

EXPOSE 5003

ENTRYPOINT [ "python" ]

CMD [ "app.py" ]