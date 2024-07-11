print("This is going to be fun")
#Importing necessary libraries
import random
#A class for questions
class Recovery_Questions:
    #Starting off with a greeting
    def start_program_with_greetings(self):
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

RecoveryQuestions = Recovery_Questions()
RecoveryQuestions.start_program_with_greetings()
        
         
    
