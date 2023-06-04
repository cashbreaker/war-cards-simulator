cd src
javac *.java
java Main $1 $2
rm *.class
python3 plot.py $1
cd ..
