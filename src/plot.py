import pandas as pd
import matplotlib.pyplot as plt
import sys

binsNumber = 1000
if (len(sys.argv)!=1):
    binsNumber = int(int(sys.argv[1])/10)

df = pd.read_csv('rounds.csv', header=None).transpose()
#df.set_axis(['rounds'], axis='columns')
#df = df.astype({'int'})
#print(df.to_string())
print(df.mean())
df.plot.hist(bins=binsNumber)
plt.show()
