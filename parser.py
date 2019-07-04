li = []
with open('infos.dat', 'r') as file:
    for line in file:
        line = line.split('\n')[0]
        li.append(line.split('Ã§'))

n_vendor = len([x for x in li if x[0] == '001'])
n_clients = len([x for x in li if x[0] == '002'])
sales = [x for x in li if x[0] == '003']

newli = []
sale_sum = 0
for sale in sales:
    sale[2] = sale[2].split('[')[1]
    sale[2] = sale[2].split(']')[0]
    sale[2] = sale[2].split(',')
    for item in sale[2]: 
        item = item.split('-')
        sale_sum += int(item[1])*float(item[2])
    sale[2], sale_sum = sale_sum, 0
    newli.append(sale)

sale_max = newli[0]
sale_min = newli[0]
for sale in newli:
    if(sale[2] > sale_max[2]):
        sale_max = sale
    if(sale[2] < sale_min[2]):
        sale_min = sale

print('Pior vendedor: ' + str(newli[newli.index(sale_min)][3]))
print('ID da melhor venda: ' + str(newli[newli.index(sale_max)][1]))
print('Nro de vendedores: ' + str(n_vendor))
print('Nro de clientes: '+ str(n_clients))