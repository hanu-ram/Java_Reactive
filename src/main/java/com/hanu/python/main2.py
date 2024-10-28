# Modules and imports
from datetime import datetime
from datetime import date
from math import factorial
import os
from urllib import request
import calculator
from calculator import divide
import asyncio
import json

# Dates
print(date.today())
print(factorial(6))

print(calculator.add(5, 10))
print(divide(10, 5))


# class and object
class Laptop:
    def __init__(self, brand, price) -> None:
        self.brand = brand
        self.price = price

    def __str__(self):
        return f"""
        Brand: {self.brand}
        Price: {self.price}
        """


asus = Laptop("asus vivobook", 45000)

print(asus.brand)
print(asus.price)
print(asus)

# Date formats
today = datetime.today()
print(today)
print(today.strftime("%d/%m/%Y %H:%M:%S"))
print(today.strftime("%d/%B/%Y %H:%M:%S"))
print(today.strftime("%d/%b/%Y %H:%M:%S"))

# Files
file = open(".data.csv", "r+")  # x, w, r+, a
file.write("id,name,salary\n")
file.write("1,Hanu,40000\n")
file.write("2,Sushma,35000\n")
file.write("3,Vindya,50000\n")
file.close()

# Read files
file2 = open(".data.csv", "r+")  # x, w, r
# print(file2.read())
"""
In Python, when you iterate directly over a file object (e.g., for line in file2), 
the file object itself is acting as an iterator, which reads one line at a time from the file until the end. 
This is an efficient way to process large files, as it avoids loading the entire file into memory at once.

Here’s how it works:

for line in file2:: When you use a file object directly in a for loop, Python automatically reads each line one by one. 
This lazy loading approach reads each line as needed, which is memory efficient for large files.

file2.readlines(): This method reads all lines from the file at once and returns them as a list of strings, 
where each string represents a line in the file. This is useful when you need all lines at once, 
but it can be memory-intensive for very large files.
"""
for line in file2:
    print(line)

# print([f for f in file2])
file2.close()

# Auto close using below syntax like try with resources
print("-------------------Auto close------------------------")
_data_file_name = ".data.csv"
if os.path.exists(_data_file_name):
    with open(_data_file_name, "r+") as csv_file:
        next(csv_file)
        for line in csv_file:
            print(line)
else:
    print(f"File: {_data_file_name} doesn't exist")

# Fetching data from internet
response = request.urlopen('https://google.com/')


# print(response.status)
# print(response.read())

# Fetching jokes from internet
class Joke:

    def __init__(self, setup, punchline) -> None:
        self.setup = setup
        self.punchline = punchline

    def __str__(self) -> str:
        return f"setup -> {self.setup}. punchline -> {self.punchline}"


jokes = []


async def fetch_jokes():
    jokes_response = request.urlopen('https://official-joke-api.appspot.com/random_ten')
    print(jokes_response.status)
    json_jokes = json.loads(jokes_response.read())
    for joke in json_jokes:
        jokes.append(Joke(joke['setup'], joke['punchline']))


asyncio.run(fetch_jokes())
print(len(jokes))

for j in jokes:
    print(j)

