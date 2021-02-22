from werkzeug.security import safe_str_cmp
from user import User

# Function to authenticate the User:
def authenticate(username, password):
    # Setting a defualt value to None, if the user does not exist
    user = User.find_by_username(username)
    if user and safe_str_cmp(user.password, password):
        return user

def identity(payload):
    user_id = payload['identity']
    # Setting a default value to None, if the user_id does not exist
    return User.find_by_id(user_id)
