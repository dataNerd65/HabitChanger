#Importing necessary libraries
import random
from dbutil import DBUtility
#A class for questions
print("\033[1mDaily Recovery Journal\033[0m") #Acting like a label
class Recovery_Questions:
    #Starting off with a greeting
    def start_program_with_greetings(self):
        #Initialized a data structure list that will be starting off the program
        greetings = [
            "Hello, I hope you are feeling hopeful today.",
            "Good day! Remember, every day is a new opportunity for growth.",
            "Hi there! Your strength is an inspiration.",
            "Welcome! Today is another step on your journey to recovery.",
            "Hello! It's a beautiful day to celebrate your progress.",
            "Greetings! Keep focusing on the positive strides you're making.",
            "Hi! Remember, it's okay to ask for help whenever you need it.",
            "Good to see you! Every small victory is worth celebrating.",
            "Hello! Let's make today a day of healing and progress.",
            "Hi! You're doing great, and we're all rooting for you."
        ]
        greeting = random.choice(greetings)
        print(greeting)

    def personal_reflection(self):
        print("\033[1mPersonal Reflection\033[0m")
        #Using the connection
        connection = DBUtility.get_connection()
        if connection:
            #performing database operations
            cursor = connection.cursor()
            #query
            cursor.execute("SELECT * FROM users2")
            records = cursor.fetchall()
            for record in records:
                print(record)
            connection.close()
        print("How do you feel today? (e.g., Happy, Sad, Anxious, Hopeful):")

RecoveryQuestions = Recovery_Questions()
RecoveryQuestions.start_program_with_greetings()
RecoveryQuestions.personal_reflection()
        
         
    
