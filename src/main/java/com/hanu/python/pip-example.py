import requests
import asyncio
import json
import pyttsx3

class Joke:

    def __init__(self, setup, punchline) -> None:
        self.setup = setup
        self.punchline = punchline

    def __str__(self) -> str:
        return f"setup -> {self.setup}. punchline -> {self.punchline}"


url = "https://official-joke-api.appspot.com/random_ten"

async def fetch_jokes():
    jokes = requests.get(url)
    print(jokes.status_code)
    return jokes.text


jokes_list: Joke = []

jokes_response = json.loads(asyncio.run(fetch_jokes()))

for joke_response in jokes_response:
    jokes_list.append(Joke(joke_response['setup'], joke_response['punchline']))

for joke in jokes_list:
    print(joke)
    # pyttsx3.speak("Setup")
    # pyttsx3.speak(joke.setup)
    # pyttsx3.speak('Punchline')
    # pyttsx3.speak(joke.punchline)
    
    
engine = pyttsx3.init()
rate = engine.getProperty('rate')
engine.setProperty('rate', 100)
engine.setProperty('volume', 5.0)
voices = engine.getProperty('voices')
engine.setProperty('voice', voices[0].id )
# engine.say("Hi All people")
engine.runAndWait()

try:
    10/0
except(Exception) as e:
    print("Exception occured ", e)
finally:
    print("execution completed")