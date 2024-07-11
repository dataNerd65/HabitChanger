from configparser import ConfigParser
import mysql.connector
from mysql.connector import Error
class DBUtility:
    @staticmethod
    def get_connection():
        #Initializing the ConfigParser
        config = ConfigParser()
        #Reading the config file
        config.read('config.ini')
        try:
            connection = mysql.connector.connect(
                host = config.get('mysql', 'host'),
                user = config.get('mysql', 'user'),
                password = config.get('mysql', 'password'),
                database= config.get('mysql', 'database'),
            )
            return connection
        except Error as e:
            print(f"Error while connecting to MySql: {e}")
            return None