# juste un exemple pour la syntaxe ... 
#
verbose=true

bean.id.1=liste1
liste1.class=java.util.ArrayList

bean.id.2=liste2
liste2.class=java.util.LinkedList

bean.id.3=table1
table1.class=question1.Table
table1.property.1=collection
table1.property.1.param.1=liste1
table1.property.2=int
table1.property.2.param.1=3
table1.property.3=int
table1.property.3.param.1=4
table1.property.4=tab
table1.property.4.param.1=5 6 7 8 9

bean.id.4=table2
table2.class=question1.Table
table2.property.1=collection
table2.property.1.param.1=liste2
table2.property.2=tab
table2.property.2.param.1=5 6 7 8 9 10 11 12

bean.id.5=listeDeTables
listeDeTables.class=question1.ListeDeTables
listeDeTables.property.1=tables
listeDeTables.property.1.param.1=table1 table2

bean.id.6=fabrique
fabrique.class=question1.Factory
fabrique.property.1=instance
#fabrique.property.1.param.1=java.util.TreeSet.class
fabrique.property.1.param.1=java.util.HashSet.class

bean.id.7=set
set.class=java.util.HashSet

#bean.id.8=ihm
#ihm.class=ConfigurationIHM




