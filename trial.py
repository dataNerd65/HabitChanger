#Importing necessary libraries
import random
from dbutil import DBUtility

#connection to be made global for all methods and classes
connection = DBUtility.get_connection()

print("\033[1mDaily Recovery Journal\033[0m") #Acting like a label

#A class for questions
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
        if connection:
            #performing database operations
            cursor = connection.cursor()
            #query to test
            cursor.execute("SELECT * FROM users2")
               
            connection.close()
        mood = input("How do you feel today? (e.g., Happy, Sad, Anxious, Hopeful): ")
        print(mood)
        reason_for_mood = input("Any particular reason for this mood?: ")
        print(reason_for_mood)
        print("\033[1mGratitude\033[0m")
        #Using a loop to take in the three responses
        print("List three things you are grateful for today.")
        grateful_things = [] #List to strore the responses
        for i in range(3):
            thing = input(f"Number {i+1}: ")
            grateful_things.append(thing) #Adding responses to the list
        
        #printing list
        print("You are grateful for:", grateful_things)

        print("\033[1mSpiritual and Mental Health\033[0m")
        print("\033[1mPrayer and Meditation\033[0m")


        while True:
            prayer_meditation = input("Did you pray or meditate today? (Yes/No): ").lower()
            if prayer_meditation == "yes":
               print("Yes, I did pray or meditate.")
               after_prayer_feeling = input("How did it make you feel? (e.g., Calmer, More focused): ")
               break
            elif prayer_meditation == "no":
                print("I did not pray or meditate.")
                break
            else:
               print("Invalid response! Please answer with 'Yes' or 'No'.")
        
    


#using the methods
RecoveryQuestions = Recovery_Questions()#First instantiating the class
RecoveryQuestions.start_program_with_greetings()
RecoveryQuestions.personal_reflection()
        
         
    
