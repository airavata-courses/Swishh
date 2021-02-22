# This is to check if your python is communicating properly with your sqlite database
import sqlite3

# Setting up a connection with our database
connection = sqlite3.connect('data.db')

# A cursor is just like our mouse and it will help in running the query, starting at a point in our database etc.
cursor = connection.cursor()

# Schema for my Table
create_table = "CREATE TABLE users (id int, username text, password text)"

# Executing the database
cursor.execute(create_table)

user = (1, 'sid', '1234')

insert_query = "INSERT INTO users VALUES (?,?,?)"

cursor.execute(insert_query, user)


users = [
    (2, 'sam', 'Zcxzx'),
    (3, 'sai', 'asdf')
]

# This will run on the users above, line by line and insert into our database
cursor.executemany(insert_query, users)

# To retrieve data out
select_query = "SELECT * FROM users"
for row in cursor.execute(select_query):
    print(row)

# Whenever we insert data, we have to tell the connection to actually save the changes into my data.db file
connection.commit()


# The below statement is to make sure that the connection is closed and the database is not receiving anymore data
# If you don't do this, the DB will wait for consuming resources, which is not a good practice. So we should always close the connection once we have inserted the data
connection.close()
