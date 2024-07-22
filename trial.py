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
        try:
            if connection:
               #performing database operations
               cursor = connection.cursor()
               #query to test
               cursor.execute("SELECT * FROM users2")
        except Exception as e:
            print(f"An error occurred: {e}")
        finally:
            if connection:
                connection.close()
        
        try:
            while True:
                mood = input("How do you feel today? (e.g., Happy, Sad, Anxious, Hopeful): ")
                if mood:
                    break
                else:
                    print("Error: Input cannot be empty.")
            print(mood)

            while True:
                reason_for_mood = input("Any particular reason for this mood?: ")
                if reason_for_mood:
                    break
                else:
                    print("Error: Input cannot be empty.")
            print(reason_for_mood)
        except Exception as e:
            print(f"An error occurred while capturing mood: {e}")
     
        print("\033[1mGratitude\033[0m")
        #Using a loop to take in the three responses
        
        def get_three_inputs():  # Function for getting three inputs
            things = []  # List to handle the three inputs
            try:
                for i in range(3):  # Ensuring exactly 3 items
                    while True:  # Keep asking until a valid input is given
                        thing = input(f"Number {i+1}: ")
                        if thing:  # Checking if input is not empty
                            things.append(thing)  # Adding responses to the list
                            break  # Exiting loop once a valid input is given
                        else:
                            print("Error: Input cannot be empty.")
            except KeyboardInterrupt:
                print("\nOperation cancelled.")
                return None
            return things
        
        #Realized when modularizing i can use a method inside another
        def capture_grateful_things_today():
            print("List three things you are grateful for today.")
            grateful_things = get_three_inputs()
            #Avoiding error
            if grateful_things is None:
                print("Gratitude input was cancelled.")
            else:
                print(f"You are grateful for:", ', ' .join(grateful_things))
            return grateful_things
        #Calling the nested function within personal_reflection method
        self.capture_grateful_things_today = capture_grateful_things_today
        self.capture_grateful_things_today()

        
        print("\033[1mSpiritual and Mental Health\033[0m")
        print("\033[1mPrayer and Meditation\033[0m")

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
        
        prayer_meditation, feelings_after = handle_healthy_habit("Did you pray or meditate today? ",
                                                 "pray and meditate",
                                                 "How did it make you feel? (e.g., Calmer, More focused): ")

    

        print("\033[1mAffirmations\033[0m")  
        def capture_affirmations():
            print("Write down three positive affirmations you said to yourself today: ") 
            affirmations = get_three_inputs()
            if affirmations is None:
                print("Affirmation input was cancelled.")
            else:
                print(f"Affirmations:", ', ' .join(affirmations))
                return affirmations #i will wish to use this to insert in db

        #calling the nested function
        self.capture_affirmations = capture_affirmations
        self.capture_affirmations()

        
        print("\033[1mDaily Activities\033[0m")
        print("\033[1mHealthy habits\033[0m")

        
        #Usage
        exercise_status = handle_healthy_habit("Did you exercise today?", "exercise")
        balancedMeals_status = handle_healthy_habit("Did you eat healthy meals today?","eat healthy meals today")
        enoughSleep_status = handle_healthy_habit("Did you get enough sleep last night? (Yes/No)?", "sleep enough today")

        print("\033[1mAddiction Tracking\033[0m")
        #usage of the method in addiction tracking
        cravings_status, handling_method = handle_healthy_habit(
            "Did you experience any cravings for drugs or porn today? ",
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
            "How did it help you? "
        )
        print("\033[1mAccountability Partner\033[0m")
        Accountability_partner_convo, help_from_her_him = handle_healthy_habit (
            "Did you check in with your accountability partner today? ",
            "talk to my accountability partner",
            "How did that conversation go? "
        )
        print("\033[1mReflection and Planning\033[0m")
        print("\033[1mChallenges\033[0m")
        challenge_of_day = input("What was the most challenging part of your day? ")
        dealing_with_challenge = input("How did you deal with it? ")

        print("\033[1mVictories\033[0m")
        victories = input("What was your biggest victory or achievement today? ")
        print("\033[1mImprovements\033[0m")
        improvements = input("What can you do differently tomorrow to stay on track with your recovery? ")
        #Tomorrow's goals section
        print("\033[1mTomorrow's Goals\033[0m")

        def capture_tomorrows_plan():
            print("List three goals you want to achieve tomorrow. ")
            tommorrows_plan = get_three_inputs()
            if tommorrows_plan is None:
                print("Tommorrow's input was cancelled.")
            else:
                print(f"Tomorrows Three must-do plans :", ', ' .join(tommorrows_plan))
            return tommorrows_plan

        #calling the nested function within personal_reflection method
        self.capture_tomorrows_plan = capture_tomorrows_plan
        self.capture_tomorrows_plan()
        


        print("\033[1m__Daily Summary\033[0m")
        print("\033[1mOverall Progress\033[0m")
        print("Reflect on your overall progress and feelings about your recovery journey today.")

        
#using the methods
RecoveryQuestions = Recovery_Questions()#First instantiating the class
RecoveryQuestions.start_program_with_greetings()
RecoveryQuestions.personal_reflection()
        
         
    
