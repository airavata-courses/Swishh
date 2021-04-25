FROM python:3.8.2
COPY . /userservice
WORKDIR /userservice
RUN dir
RUN pip install -r requirements.txt

RUN touch data.db
RUN chmod a+rw data.db

EXPOSE 5003

ENTRYPOINT [ "python" ]

CMD [ "app.py" ]