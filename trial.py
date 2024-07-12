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

        print("\033[1mAffirmations\033[0m")  
        print("Write down three positive affirmations you said to yourself today: ") 
        affirmations_list = [] #list also
        for i in range(3):
            affirmations = input(f"Affirmation {i+1}:")
            affirmations_list.append(affirmations)
        
        print("\033[1mDaily Activities\033[0m")
        print("\033[1mHealthy habits\033[0m")

        #Amethod for repetitive questions and is versatile
        def handle_healthy_habit(question, type, follow_up_question=None):
            follow_up_response = None #Initialize variable to store follow-up response if any
            while True:
                response = input(question + " (Yes/No): ").lower()
                if response == "yes":
                    print(f"Yes i did {type}.")
                    if follow_up_question:
                        follow_up_response = input(follow_up_question)
                    break
                elif response == "no":
                    print(f"I did not {type}.")
                    break
                else:
                    print("Invalid response! Please answer with 'Yes' or 'No'.")
            return response, follow_up_response #returning both initial and follow up responses

        #Usage
        exercise_status = handle_healthy_habit("Did you exercise today?", "exercise")
        balancedMeals_status = handle_healthy_habit("Did you eat healthy meals today?","eat healthy meals today")
        enoughSleep_status = handle_healthy_habit("Did you get enough sleep last night? (Yes/No)?", "sleep enough today")

        print("\033[1mAddiction Tracking\033[0m")
        #usage of the method in addiction tracking
        cravings_status, handling_method = handle_healthy_habit(
            "Did you experience any cravings for drugs or porn today?",
            "experience cravings",
            "How did you handle them? "
        )
        print("\033[1mRelapse Check\033[0m")
        relapse_status, trigger_and_avoidance = handle_healthy_habit(
            "Did you fall into any addiction today? ",
            "relapse",
            "What do you think triggered it and how can you avoid it next time? "
        )
        print("\033[1mSupport System\033[0m")
        print("\033[1mConnection\033[0m")
        connection_status, help = handle_healthy_habit(
            "Did you talk to a sponsor, therapist, or support group today? ",
            "talk to someone",
            "How did it help you?"
        )
        print("\033[1mAccountability Partner\033[0m")
        Accountability_partner_convo, help_from_her_him = handle_healthy_habit (
            "Did you check in with your accountability partner today? ",
            "talk to my accountability partner"
            "How did that conversation go? "
        )
        print("\033[1mReflection and Planning\033[0m")
        print("\033[1mChallenges\033[0m")
        challenge_of_day = input("What was the most challenging part of your day? :")
        dealing_with_challenge = input("How did you deal with it? ")

        print("\033[1mVictories\033[0m")
        victories = input("What was your biggest victory or achievement today? ")




#using the methods
RecoveryQuestions = Recovery_Questions()#First instantiating the class
RecoveryQuestions.start_program_with_greetings()
RecoveryQuestions.personal_reflection()
        
         
    
