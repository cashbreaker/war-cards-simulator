import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('rounds.csv', header=None).transpose()
#df.set_axis(['rounds'], axis='columns')
#df = df.astype({'int'})
#print(df.to_string())
print(df.mean())
df.plot.hist(bins=100)
plt.show()
