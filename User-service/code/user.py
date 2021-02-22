# Here are we are accessing the database
import sqlite3
from flask_restful import Resource,reqparse
class User:
    def __init__(self, _id, username, password):
        self.id = _id
        self.username = username
        self.password = password

    # This is a class method which we are using. This makes it more abstract and easy to use
    @classmethod
    def find_by_username(cls, username):
        connection = sqlite3.connect('data.db')
        cursor = connection.cursor()


        query = "SELECT * FROM users WHERE username=?"
        result = cursor.execute(query, (username,))
        row = result.fetchone()

        if row is not None:
        # Here we are using the cls, to say that we belong to the same class as above, which in case if changed then, that won't disturb the flow. to use this we have used a decorator above - @classmethod
        # " *row " - This gives us a scalable option which means, based on the user input, the no. of rows will depend
        # That is if the user gives, "id", "name" and "password" - 3 rows will form. If in future the user adds another row then, it will that row as a new one.
            user = cls(*row)
        else:
            user = None

        connection.close()
        return user

    @classmethod
    def find_by_id(cls, _id):
        connection = sqlite3.connect('data.db')
        cursor = connection.cursor()


        query = "SELECT * FROM users WHERE id=?"
        result = cursor.execute(query, (_id,))
        row = result.fetchone()

        if row is not None:
        # Here we are using the cls, to say that we belong to the same class as above, which in case if changed then, that won't disturb the flow. to use this we have used a decorator above - @classmethod
        # " *row " - This gives us a scalable option which means, based on the user input, the no. of rows will depend
        # That is if the user gives, "id", "name" and "password" - 3 rows will form. If in future the user adds another row then, it will that row as a new one.
            user = cls(*row)
        else:
            user = None

        connection.close()
        return user


class UserRegister(Resource):
    parser = reqparse.RequestParser()
    parser.add_argument('username',
                        type = str,
                        required = True,
                        help = "This field cannot be left blank")

    parser.add_argument('password',
                        type = str,
                        required = True,
                        help = "This field cannot be left blank")
    def post(self):
        data = UserRegister.parser.parse_args()
        connection = sqlite3.connect('data.db')
        cursor = connection.cursor()

        # So as the Id is autoincrementing, we are giving it as NUll
        query = "INSERT INTO users VALUES (NULL, ?, ?)"

        cursor.execute(query, (data['username'], data['password']))

        connection.commit()
        connection.close()

        return {'message': "User created successfully."}, 201
