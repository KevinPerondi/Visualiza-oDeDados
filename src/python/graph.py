#Author: Kevin Perondi Regis
#University: UTFPR-CM
#First step: pip install matplotlib
#Last step: run on Terminal >> python graph.py

import matplotlib.pyplot as plt
import csv

def getMillions(valor):
	div = valor/1000000
	sobra = valor%1000000
	return str(div)+','+str(valor)[:3]

inputFile = open('input.csv','rb')
reader = csv.reader(inputFile)

dicio = {}
meses = []
valores = []
valorTotal = 0;
firstLine = True

#file reader
for linha in reader:
	if firstLine:
		firstLine = False
	else:
		content = linha[0]
		splitter = content.split(';')
		meses.append(splitter[0])
		valores.append(long(splitter[1]))
		dicio[splitter[0]] = long(splitter[1])


fig, ax = plt.subplots()

#creating a black line between points
ax.plot(meses,valores,color='black')

#creating points
for mes in meses:
	arredonda = getMillions(dicio[mes])
	ax.plot(mes, dicio[mes],'o',ms=13, lw=2, alpha=0.7, label=mes[:3]+' '+arredonda+'e6')

#inserting grid
ax.grid()

#inserting labels and title
ax.set_xlabel('Meses')
ax.set_ylabel('Valores em Reais')
ax.set_title('Valores de defeso sacados em 2010.')

#inserting legend
plt.legend()

#showing
plt.show()