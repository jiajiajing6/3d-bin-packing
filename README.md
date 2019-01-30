# 3d-bin-packing
### Based on Intelligent FIRST-FIT box placement having maximal spacing covered. This is my own custom algorithm on my own.
What this code does is simple, it fits most of the 3D boxes in more than one 3D containers. In other words, it attempts to match a set of 3D items to one or more in a set of 3D containers. 


3D here means boxes/containers having Length-Width-Height


To use this code, make two .CSV files one named _**Boxes.csv**_ and other named _**Container.csv**_

**_Boxes.csv_ format:**

Column 1 _id_

Column 2 _Name_

Column 3 _Width(x)_

Column 4 _Height(y)_

Column 5 _Length(z)_

Column 6 _Weight_

Column 7 _Number of items_


**_Container.csv_ format:**

Column 1 _id_

Column 2 _Name_

Column 3 _Width(x)_

Column 4 _Height(y)_

Column 5 _Length(z)_

Column 6 _Weight_

Column 7 _Quantity_


Have a look at the sample file given. 

To run this nust have Java JDK 11 installed


Here co-ordinate of the box in the container is also determined, along with height at each level.



This project was done under supervision of my mentor Raj Sir

© Mohitesh Ch Agarwal | _All rights reserved._

**For education purpose only**
