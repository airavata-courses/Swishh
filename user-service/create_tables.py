import sqlite3

def createdb():
    try:
        connection = sqlite3.connect('data.db')
        cursor = connection.cursor()
        create_table = "CREATE TABLE IF NOT EXISTS users (userid text, username text, password text)"
        cursor.execute(create_table)
        connection.commit()
        connection.close()
    except:
        print("Error in creating DB schema")
