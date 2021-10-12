#
verbose=true

# ToyType racingCarType = new ToyType("Racing car");
# ToyType policeCarType = new ToyType("Police car");
# ToyType teddyBear     = new ToyType("Teddy Bear"); 

bean.id.1=racingCarType
racingCarType.class=exemple_kindergarten.entity.ToyType
racingCarType.property.1=name
racingCarType.property.1.param.1=Racing car

bean.id.2=policeCarType
policeCarType.class=exemple_kindergarten.entity.ToyType
policeCarType.property.1=name
policeCarType.property.1.param.1=Police car

bean.id.3=teddyBear
teddyBear.class=exemple_kindergarten.entity.ToyType
teddyBear.property.1=name
teddyBear.property.1.param.1=Teddy Bear

bean.id.4=fireTruckType
fireTruckType.class=exemple_kindergarten.entity.ToyType
fireTruckType.property.1=name
fireTruckType.property.1.param.1=Fire truck

# Toy ferrari = new Toy("Ferrari","Red",racingCarType,0.4);
# Toy laFireTruck = new Toy("Los Angeles Fire Truck","Red",fireTruckType,3.0);
# Toy policeCar = new Toy("New York Police Department car","White",policeCarType,0.9);
# Toy timmy = new Toy("Timmy The Bear","Brown",teddyBear,0.35);


bean.id.5=ferrari
ferrari.class=exemple_kindergarten.entity.Toy
ferrari.property.1=name
ferrari.property.1.param.1=Ferrari
ferrari.property.2=color
ferrari.property.2.param.1=Red
ferrari.property.3=type
ferrari.property.3.param.1=racingCarType
ferrari.property.4=weight
ferrari.property.4.param.1=0.4

# Toy laFireTruck = new Toy("Los Angeles Fire Truck","Red",fireTruckType,3.0);
bean.id.6=laFireTruck
laFireTruck.class=exemple_kindergarten.entity.Toy
laFireTruck.property.1=name
laFireTruck.property.1.param.1=Los Angeles Fire Truck
laFireTruck.property.2=color
laFireTruck.property.2.param.1=Red
laFireTruck.property.3=type
laFireTruck.property.3.param.1=fireTruckType
laFireTruck.property.4=weight
laFireTruck.property.4.param.1=3.0

# Toy policeCar = new Toy("New York Police Department car","White",policeCarType,0.9);
bean.id.7=policeCar
policeCar.class=exemple_kindergarten.entity.Toy
policeCar.property.1=name
policeCar.property.1.param.1=New York Police Department car
policeCar.property.2=color
policeCar.property.2.param.1=White
policeCar.property.3=type
policeCar.property.3.param.1=policeCarType
policeCar.property.4=weight
policeCar.property.4.param.1=0.9

# Toy timmy = new Toy("Timmy The Bear","Brown",teddyBear,0.35);
bean.id.8=timmy
timmy.class=exemple_kindergarten.entity.Toy
timmy.property.1=name
timmy.property.1.param.1=Timmy The Bear
timmy.property.2=color
timmy.property.2.param.1=Brown
timmy.property.3=type
timmy.property.3.param.1=teddyBear
timmy.property.4=weight
timmy.property.4.param.1=0.35



# Child johny = new Child("Johny",7);
# johny.getFavouriteToys().add(ferrari);  
# johny.getFavouriteToys().add(laFireTruck);
bean.id.9=johny
johny.class=exemple_kindergarten.entity.Child
johny.property.1=name
johny.property.1.param.1=Johny
johny.property.2=age
johny.property.2.param.1=7
johny.property.3=favouriteToys
johny.property.3.param.1=ferrari laFireTruck

# Child johny2 = new Child("Johny",10);
# johny2.getFavouriteToys().add(policeCar);
bean.id.10=johny2
johny2.class=exemple_kindergarten.entity.Child
johny2.property.1=name
johny2.property.1.param.1=Johny
johny2.property.2=age
johny2.property.2.param.1=10
johny2.property.3=favouriteToys
johny2.property.3.param.1=policeCar


# Child max = new Child("Max",8);
# max.getFavouriteToys().add(policeCar);
# max.getFavouriteToys().add(laFireTruck);
bean.id.11=max
max.class=exemple_kindergarten.entity.Child
max.property.1=name
max.property.1.param.1=Max
max.property.2=age
max.property.2.param.1=8
max.property.3=favouriteToys
max.property.3.param.1=policeCar laFireTruck


# Child jenny = new Child("Jenny",5);
# jenny.getFavouriteToys().add(timmy);
bean.id.12=jenny
jenny.class=exemple_kindergarten.entity.Child
jenny.property.1=name
jenny.property.1.param.1=Jenny
jenny.property.2=age
jenny.property.2.param.1=5
jenny.property.3=favouriteToys
jenny.property.3.param.1=timmy


bean.id.13=spec_johny
spec_johny.class=exemple_kindergarten.specification.ChildNameSpecification
spec_johny.property.1=name
spec_johny.property.1.param.1=Johny

bean.id.14=spec_red_toy
spec_red_toy.class=exemple_kindergarten.specification.ChildLikesSpecifiedToySpecification
spec_red_toy.property.1=specification
spec_red_toy.property.1.param.1=spec_toy_color_red

bean.id.15=spec_toy_color_red
spec_toy_color_red.class=exemple_kindergarten.specification.ToyColorSpecification
spec_toy_color_red.property.1=color
spec_toy_color_red.property.1.param.1=Red

#new Not<Child>(new ChildLikesSpecifiedToySpecification(new ToyColorSpecification("Red")));
bean.id.16=spec_not_red_toy
spec_not_red_toy.class=question1.Not
spec_not_red_toy.property.1=spec
spec_not_red_toy.property.1.param.1=spec_red_toy

#SpecificationI<Child> spec_johny_likes_red_toys = new And<Child>(spec_johny,spec_red_toy);
bean.id.17=spec_johny_likes_red_toys
spec_johny_likes_red_toys.class=question1.And
spec_johny_likes_red_toys.property.1=spec1
spec_johny_likes_red_toys.property.1.param.1=spec_johny
spec_johny_likes_red_toys.property.2=spec2
spec_johny_likes_red_toys.property.2.param.1=spec_red_toy

bean.id.18=spec_schoolchildren
spec_schoolchildren.class=exemple_kindergarten.specification.SchoolChildrenSpecification
# in Poland - from 6 years old to 19 y.o.)
spec_schoolchildren.property.1=min
spec_schoolchildren.property.1.param.1=6
spec_schoolchildren.property.2=max
spec_schoolchildren.property.2.param.1=19

bean.id.19=spec_weight_toy
spec_weight_toy.class=exemple_kindergarten.specification.ToyWeightBetweenSpecification
spec_weight_toy.property.1=min
spec_weight_toy.property.1.param.1=0
spec_weight_toy.property.2=max
spec_weight_toy.property.2.param.1=10

bean.id.20=toyColorRed
toyColorRed.class=exemple_kindergarten.specification.ToyColorSpecification
toyColorRed.property.1=color
toyColorRed.property.1.param.1=Red

bean.id.21=toys_setof
toys_setof.class=exemple_kindergarten.entity.SetOf
toys_setof.property.1=addAll
toys_setof.property.1.param.1=ferrari laFireTruck policeCar timmy

bean.id.22=children_setof
children_setof.class=exemple_kindergarten.entity.SetOf
children_setof.property.1=addAll
children_setof.property.1.param.1=johny johny2 max jenny






