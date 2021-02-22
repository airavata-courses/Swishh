import sqlite3

connection = sqlite3.connect('data.db')

cursor = connection.cursor()

# Using Integer Primary Key for auto-incrementing the value of Ids'.
# So when we add a new user, we only have to specify the username and password, the ID will be automatically assigned
create_table = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username text, password text)"
cursor.execute(create_table)



connection.commit()



connection.close()
