import pandas as pd

csv = pd.read_csv(".data.csv")
id_ = csv[['name', 'salary']]

# for k, v in id_.values:
#     if v > 40000:
#         print(f"{k} salary is {v}")

# print(csv.head(2))

# print(csv)
# salary_ = csv[csv['salary'] > 40000]

# print(csv.sort_values(by=['salary'], ascending= False))

# print(csv.describe())
# csv.fillna(value='d')
# print(csv.dropna())

# print(csv.groupby('salary').sum())
print(csv.pivot_table(index='id', values='salary', aggfunc='mean'))