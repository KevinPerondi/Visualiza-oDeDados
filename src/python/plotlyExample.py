import plotly
import csv

import plotly.figure_factory as ff
import pandas as pd

plotly.tools.set_credentials_file(username='kevinperondi', api_key='U0dNJhj7hmqpwuYloor7')
plotly.tools.set_config_file(world_readable=False,sharing='private')

#ficheiro = pd.read_csv('/home/todos/alunos/cm/a1552287/Downloads/pescadores_por_estado.csv')
ficheiro = open('/home/todos/alunos/cm/a1552287/Downloads/pescadores_por_estado.csv', 'rb')
csvFile = csv.reader(ficheiro)

valores = {}

estados = []

numeroPescadores = []

for row in csvFile:
	line = row[0].split(";")
	if len(line[0]) <= 2:
		if(str(line[0]) == '-2'):
			valores['Sem Estado'] = line[1]
			estados.append('Sem Estado')
			numeroPescadores.append(line[1])
		else:
			valores[line[0]] = line[1]
			estados.append(line[0])
			numeroPescadores.append(line[1])

#print valores
#print estados
#print numeroPescadores

scope = ['Seila']

colorscale = ["#F5F5F5", "#DCDCDC", "#C0C0C0",  "#A9A9A9", "#808080", "#696969", "#000000"]

#checar esse metodo create_choropleth
fig = ff.create_choropleth(estados=estados,
							numeroPescadores=numeroPescadores,
							scope=scope,
							colorscale=colorscale
							#county_outline={'color': 'rgb(15, 15, 55)', 'width': 0.5},
							#state_outline={'width': 1},
							#legend_title='pop. per county',
							#title='Oregon'
						)

py.iplot(fig, filename='choropleth_oregon_ono_simplification_factor')

#estados = ficheiro['estado'].tolist()

#for x in estados:
#	print x

#import plotly.figure_factory as ff
#import pandas as pd
#
#plotly.tools.set_credentials_file(username='kevinperondi', api_key='U0dNJhj7hmqpwuYloor7')
#plotly.tools.set_config_file(world_readable=False,sharing='private')
#
#scope = ['Oregon']
#df_sample = pd.read_csv(
#    'https://raw.githubusercontent.com/plotly/datasets/master/minoritymajority.csv'
#)
#df_sample_r = df_sample[df_sample['STNAME'].isin(scope)]
#
#values = df_sample_r['TOT_POP'].tolist()
#fips = df_sample_r['FIPS'].tolist()
#
#colorscale = ["#8dd3c7", "#ffffb3", "#bebada", "#fb8072",
#              "#80b1d3", "#fdb462", "#b3de69", "#fccde5",
#              "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f",
#              "#8dd3c7", "#ffffb3", "#bebada", "#fb8072",
#              "#80b1d3", "#fdb462", "#b3de69", "#fccde5",
#              "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f",
#              "#8dd3c7", "#ffffb3", "#bebada", "#fb8072",
#              "#80b1d3", "#fdb462", "#b3de69", "#fccde5",
#              "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f"]
#
#fig = ff.create_choropleth(
#    fips=fips, values=values, scope=scope,
#    colorscale=colorscale, round_legend_values=True,
#    simplify_county=0, simplify_state=0,
#    county_outline={'color': 'rgb(15, 15, 55)', 'width': 0.5},
#    state_outline={'width': 1},
#    legend_title='pop. per county',
#    title='Oregon'
#)
#
#py.iplot(fig, filename='choropleth_oregon_ono_simplification_factor')