import multiprocessing.pool

print("Hi Hanu")

a = 10
b = 20
number = [1, 2, 3, 5, 6, 7]
print(a)
print(b)
b = "Meow"
print(b)
name: str = "Vindya"
isOk = True
print(name, '\n' + b)
print(type(isOk))


# names: list = []
def do_something():
    return "I am easy to learn"


print(do_something())

"""
This is multi line comments
you can use this for docs
"""

# Strings
my_name = "Hanumanth Ramsetty"
print(my_name.capitalize())

# String formatting

to = 'Janaki'
from_name = 'Hanumanth R'
meeting_date = '2024-11-05'
email = """
Hi {}
Hope you are doing well, I want to you to send the invitation
for the next meeting on the Inbound portal on {}.

Thanks,
{}
"""
print(email.format(to,
                   meeting_date,
                   from_name)
      )  # Should pass same no. of args w.r.t {} in the string template. otherwise error

whatsapp_message = f"""
Hi. 
Can we have a call at {'7 PM'}
or else shall we meet on {'Thursday'}   
"""

print(whatsapp_message)

print("Hanu" != "Hanus")
print("Hanu" == "Hanu")
print("Hanu" in "Hanus")
print("Hanu" not in "Hanus")

# Indentation
# identation should be followed in python

# Operators
# Arithmetic operators

# One extra feature is **
print(2 ** 4)

# Same Comparison operators like in java

# ==, != >=, <= >, <

# Logical Operators

check = (10 > 3) and (20 > 10)
print("check =", check)
check = (10 < 3) and (20 > 10)
print("check =", check)
check = (10 < 3) or (20 > 10)
print("check =", check)
check = not ((10 < 3) and (20 > 10))
print("check =", check)
print(not ("Hanu" == "Ram"))

# if statement
number1 = 10
if number1 > 0:
    print(f"{number1} is a positive number")
elif number1 == 0:
    print(number1)
else:
    print(f"{number1} is a negative number")

# Ternary If like in java (ternary operator)
print("Ten is big" if (10 > 100) else "Ten is not big")

# List
number_list = [4, 1, 4, 25, 21, 5]
print(number_list.append(23))
print(number_list[0])

multi_list = [4, 2, 6, 7, [2, 5, 7, 9, 10]]
print(multi_list[4][1])

# inbuilt methods in List
# number_list.sort()
# number_list.reverse()
# number_list.append(3)
# print(number_list.count(4))
# number_list.insert(3, 33)
# number_list.clear()
number_list.remove(23)  # in remove we have to give value
print(number_list.pop())  # in pop we have to give index
print(number_list)
# del number_list[3]
# del number_list[3:5]

# Sets

set_one = {1, 2, 3, 1, 5, 5, 4}
print(set_one)
set_alpha = {'A', 'A', 'B', 'C', 'C'}
print(set_alpha)
# Union | Intersection | Difference

lettersA = {'A', 'B', 'C'}
lettersB = {'A', 'X', 'Y', 'Z'}
union = lettersA | lettersB
intersection = lettersA & lettersB
difference = lettersB - lettersA
print(f"union is {union}")
print(f"intersection is {intersection}")
print(f"difference is {difference}")
print(f"intersection using inbuilt methods: {lettersB.intersection(lettersA)}")

# Dictionaries
print('---------------Dictionaries---------------')
person = {
    "name": "Hanu",
    "age": 24,
    "gender": "Male",
    "address": "India",
    "company": ["India", "USA"]
}

print(person["name"])
person["age"] = 23
print(person)
print(f"Gender is {person.get('gender')}")
print(person.setdefault("Occupation", "Developer"))
print(person.__setitem__("Interests", ["Coding", "Cricket"]))
key = person.items()
print(type(key))
print(person)
# loops
print('-------------Loops-------------')
for num in number_list:
    print(f"{num}")

for set_val in set_alpha:
    print(f"{set_val}")

for k in person:
    print(f"Key = {k} | Value = {person[k]}")

print('-------------')

for k, v in person.items():
    print(f"Key = {k} | Value = {v}")
    if type(v) is list:
        for val in v:
            print(val)
# while and break,continue
print('-------------While and break-------------')
init = 1
while init <= 10:
    if init > 5:
        break
    print(init)
    init += 1
else:
    print("Ended the while")
print('-------------')
init1 = 0
while init1 < 10:
    init1 += 1
    if init1 < 6:
        continue
    print(init1)

# Functions
print('-------------Functions-------------')


def greet(name, age=None):
    print(f"Hello {name} and I guess your age is {age}.")


greet("Hanu", 21)


def test_my_method(v1, v5: int, v4="ghd", v2=None, **v3):
    print(v1)
    if v2 is not None:
        print(v2)
    print(v3.values())
    print(v5)
    print(v4)


test_my_method("I am meow!!", 20, userName='hanuvin', password='secret')
test_my_method("I am meow", v4="secret", v5=100)


def method1() -> str:
    return "method1"


print(method1())

squares = [x ** 2 for x in range(10)]
print(squares)

print(*number_list)
info = {"name": "Alice", "age": 25}
print(*info)


def greet(name, age):
    print(f"Hello, {name}. You are {age} years old.")


vsl = [1, 2]
greet(*vsl)


def example_function(*args: int, **kwargs: str) -> None:
    # print("a =", a)
    # print("b =", b)
    print("args =", args)
    print("kwargs =", kwargs)


example_function(1, 2, 3, 4, x="Meow1", y="Meow2")

person.__setitem__("key", 10)
# print(person)
fkadjf = [3, 42, 2, 52, 15, 1, 452]


def printw(*args):
    for arg in args:
        print(arg)


printw(*fkadjf)
test = []
test.append()
print(test)
