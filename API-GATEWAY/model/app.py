from flask import flask_restful
from flask import blueprints
from flask import Flask

example_blueprint = Blueprint('example_blueprint', __name__)

@example_blueprint.route('/')
def index():
    return "This is an example API"


if __name__ == "__main__":
    app.run
