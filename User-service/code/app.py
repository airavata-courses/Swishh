from flask import Flask, request

# The Resourse is the data that our Api is concerned with.
# Resourses are usually mapped into database tables as well
from flask_restful import Resource, Api, reqparse
from flask_jwt import JWT, jwt_required
from security import authenticate, identity
from user import UserRegister



app = Flask(__name__)

app.secret_key =  'jose'

# Here the Api is imported from flask_restful
# This will allow us to easily add the resourses to it. For an instance we can say " For a Resourse we can GET and POST"
api = Api(app)

jwt = JWT(app, authenticate, identity)


items = []


# The Api works with resources and every resource has to be a class
# The below code, just iterates through the items and checks for the name, if it is present then it will return that item.
class Item(Resource):
    # The below statement means that we are creating an object for the reqparser to parse the data that we need to update.
    parser = reqparse.RequestParser()
    parser.add_argument('price', type = float,
    required = True,
    help = "This field cannot be left blank"
    )
    # The below decorator generally means that, we have to authenticate, before calling the get Function.
    @jwt_required()
    def get(self, name):
        item = next(filter(lambda x: x['name'] == name, items), None)
        return {'item': item}, 200 if item is not None else 404
        # Here the next() will give tr he first item in the filter function
        # If the next does not find any value then it will simply return None
        # Inseted of the below code we can simply write the above code by using the lambda function
        #for item in items:
        #    if item['name'] == name:
        #        return item
        # Here the 404 is the http status code which the searched item is not found

    def post(self,name):
        # Using the parser object that was declared as global in the class.
        data = Item.parser.parse_args()
        if next(filter(lambda x: x['name'] == name, items), None) is not None:
            return {'message':"An item with name '{}' already exists.".format(name)}, 400
        # The below request.json will give an error, if the data is not in the json format and also if the Content-Type is not set in the POSTMAN
        # To overcome this we have 2 methods --> data = request.json(force=True) <-- This means that change the format of the input into text even if the Content-Type is not set into application/json
        # The otherone is ---> data = request.json(silent=True) <--- This means that if there is no Content-Type then return None
        item = { 'name' : name, 'price': data['price']}
        items.append(item)
        # Here we are returning to tell the application that we are just adding the item created above into the 'items' list which is declared above in the start of the code
        # Here the 201 is the HTTP Status code and it means that the data has been created and added to the database
        return item,201
    def delete(self, name):
        global items
        items = list(filter(lambda x: x['name'] != name, items))
        return {'message': 'Item Deleted'}

    def put(self, name):
        # Using the parser object that was declared as global in the class.
        data = Item.parser.parse_args()

        item = next(filter(lambda x: x['name'] == name, items), None)
        if item is None:
            item = {'name': name, 'price': data['price']}
            items.append(item)
        else:
            item.update(data)
        return item

class ItemList(Resource):
    def get(self):
        return {'items' : items}



# Tell our api that the above class is going to be accessible via our resource
# And as we have not added any endpoint above i.e. @app.route() we are just passing the file path below as a parameter
# http://127.0.0.1:5000/student/Rolf - An example of how the below logic will look
api.add_resource(Item,'/item/<string:name>')
api.add_resource(ItemList,'/items')
api.add_resource(UserRegister,'/register')
# Here  the "debug = True" will help you to debug your errors, by generating a nice new HTML page where you can check all your errors
app.run(port = 5000, debug = True)
