import java.io.*;
import java.util.*;

class Box{ 
    //this conatins the attributes of BOXES
    int id;
    String name;
    double x;
    double y;
    double z;
    double volume;
    double weight; 
    double wunit;

    Box(int id, String name, double x,double z,double y,double w){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        volume = x*y*z;
        weight = w;
        wunit = weight/volume;
    }

    @Override
    public String toString() {
        return "Box [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +", X =" + x+", Y =" + y+", Z =" + z+ "]";
    }

    public String toStringFinal() {
        return "Box [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +  "]";
    }
}

class BoxFinal{
    //this conatins the attributes of BOXES
    int id;
    String name;
    Box box;
    int level;

    double coor_x;
    double coor_y;

    BoxFinal(int id, String name, Box box,int level, double coor_x, double coor_y){
        this.id = id;
        this.name = name;
        this.box = box;
        this.level = level;

        this.coor_x = coor_x;
        this.coor_y = coor_y;
    }

    @Override
    public String toString() {
        return "Box [id=" + box.id + ", Name=" + box.name + ", Volume=" + box.volume + ", Weight=" + box.weight +  ", size="+box.x+" X "+box.y+" X "+box.z+" ,(X,Y)="+coor_x+","+coor_y+" ,level="+level+"]";
    }
}

class BoxComparator implements Comparator<Box> {
    public int compare(Box b1, Box b2) {
        return (int)(b1.wunit - b2.wunit);
    }
}

class BoxComparatorHt implements Comparator<Box> {
    public int compare(Box b1, Box b2) {
        return (int)(b1.z - b2.z);
    }
}

class BoxComparatorVol implements Comparator<Box> {
    public int compare(Box b1, Box b2) {
        return (int)(b1.volume - b2.volume);
    }
}

class BoxComparatorWt implements Comparator<Box> {
    public int compare(Box b1, Box b2) {
        return (int)(b1.weight - b2.weight);
    }
}

class BoxFinalComparatorId implements Comparator<BoxFinal> {
    public int compare(BoxFinal b1, BoxFinal b2) {
        return (b1.id - b2.id);
    }
}

class Level{
    int l;
    double ht;
    
    Level(int l,double ht){
        this.l = l;
        this.ht = ht;
    }

    @Override
    public String toString() {
        return "sheet_adding_info [Sheet_No=" + l + ", height=" + ht +  "]";
    }

}

class Container{
    //this conatins the attributes of CONTAINERS
    int id;
    String name;
    double volume;
    double weight; 
    double wunit;
    double x;
    double y;
    double z;
    double xt;
    double yt;
    double zt;

    int cx;
    int cy;
    int cz;

    double z_orignal;

    boolean flag_rev;
    boolean first_flag;
    boolean flag_level;

    int level;

    List<Level> levels = new ArrayList();

    Container(int id, String name, double x,double z,double y,double w){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;

        z_orignal = z;

        volume = x*y*z;
        weight = w;
        wunit = weight/volume;
        xt = this.x;
        yt = this.y;
        zt =this.z;
        level = 0;

        flag_rev=false;
        first_flag = true;
        flag_level = false;

        cx=0;
        cy=0;
        cz=0;
    }

    @Override
    public String toString() {
        return "Container [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +", Volume_per_unit=" + wunit+  "]";
    }

    public String toStringFinal() {
        return "Container [id=" + id + ", Name=" + name + ", Volume=" + volume + ", Weight=" + weight +  "]";
    }
}

class ContainerComparator implements Comparator<Container> {
    public int compare(Container c1, Container c2) {
        return (int)(c1.wunit - c2.wunit);
    }
}

class ContainerComparatorHt implements Comparator<Container> {
    public int compare(Container c1, Container c2) {
        return (int)(c1.z - c2.z);
    }
}

class ContainerComparatorVol implements Comparator<Container> {
    public int compare(Container c1, Container c2) {
        return (int)(c1.volume - c2.volume);
    }
}

class BinPacking{

    List<Box> boxes = new ArrayList();
    List<Container> conts = new ArrayList();

    //some constants for the program
    double arr[][] = new double[1000][1000];
    int n=0;

    private static final String COMMA_DELIMITER = ",";

    private static final int BOX_ID_IDX = 0;
    private static final int BOX_NAME_IDX = 1;
    private static final int BOX_X_IDX = 2;
    private static final int BOX_Y_IDX = 3;
    private static final int BOX_Z_IDX = 4;
    private static final int BOX_W_IDX = 5;
    private static final int BOX_NO_IDX = 6;

    private static final int CONT_ID_IDX = 0;
    private static final int CONT_NAME_IDX = 1;
    private static final int CONT_X_IDX = 2;
    private static final int CONT_Y_IDX = 3;
    private static final int CONT_Z_IDX = 4;
    private static final int CONT_W_IDX = 5;
    private static final int CONT_NO_IDX = 6;

    void read_boxes_file(){

        BufferedReader fileReader = null;
        try{
            String line = "";

            //create the file reader
            fileReader = new BufferedReader(new FileReader("Boxes.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if(tokens.length > 0) {
                    //Create a new student object and fill his  data
                    int tmp = Integer.parseInt(tokens[BOX_NO_IDX]);
                    while(tmp>0){
                        //System.out.println(Integer.parseInt(tokens[BOX_ID_IDX])+" "+  tokens[BOX_NAME_IDX]+" "+  Long.parseLong(tokens[BOX_X_IDX])+" "+ Long.parseLong(tokens[BOX_Y_IDX])+" "+ Long.parseLong(tokens[BOX_Z_IDX])+" "+ Long.parseLong(tokens[BOX_W_IDX]));
                        Box box = new Box(Integer.parseInt(tokens[BOX_ID_IDX]), tokens[BOX_NAME_IDX], Double.parseDouble(tokens[BOX_X_IDX]),Double.parseDouble(tokens[BOX_Y_IDX]),Double.parseDouble(tokens[BOX_Z_IDX]), Double.parseDouble(tokens[BOX_W_IDX]));
                        boxes.add(box);
                        tmp--;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } 
        finally {
            try {
                fileReader.close();
            }
            catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
            
    }

    void read_conts_file(){

        BufferedReader fileReader = null;
        try{
            String line = "";

            //create the file reader
            fileReader = new BufferedReader(new FileReader("Container.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if(tokens.length > 0) {
                    //Create a new student object and fill his  data
                    try{
                        int tmp = Integer.parseInt(tokens[CONT_NO_IDX]);
                        while(tmp>0){
                            Container cont = new Container(Integer.parseInt(tokens[CONT_ID_IDX]), tokens[CONT_NAME_IDX], Double.parseDouble(tokens[CONT_X_IDX]),Double.parseDouble(tokens[CONT_Y_IDX]),Double.parseDouble(tokens[CONT_Z_IDX]), Double.parseDouble(tokens[CONT_W_IDX]));
                            conts.add(cont);
                            tmp--;
                        }
                    }
                    catch(Exception e){
                        Container cont = new Container(Integer.parseInt(tokens[CONT_ID_IDX]), tokens[CONT_NAME_IDX], Double.parseDouble(tokens[CONT_X_IDX]),Double.parseDouble(tokens[CONT_Y_IDX]),Double.parseDouble(tokens[CONT_Z_IDX]), Double.parseDouble(tokens[CONT_W_IDX]));
                        conts.add(cont);
                    }
                }
            
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } 
        finally {
            try {
                fileReader.close();
            }
            catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
            
    }

    void least_no_boxes(){
        List<BoxFinal> boxesfinal = new ArrayList();

        for(Container cont2:conts){
            arr[n][0]=cont2.volume;
            arr[n][1]=cont2.weight;
            n++;
        }

        System.out.println("###---LEAST NUMBER OF BOXES---###");

        //Boxes are sorted in INCREASING ORDER OF VOLUMES
        Collections.sort(boxes, new BoxComparatorVol());    //OPTIONAL TEST - Collections.reverse(boxes);

        //Containers are sorted in INCREASING ORDER OF VOLUMES
        Collections.sort(conts, new ContainerComparatorVol());  //OPTIONAL TEST - Collections.reverse(conts);

        int c = boxes.size();   //c stores the number of boxes

        System.out.println("List of boxes may or maynot fit:-");
        try{
            //for each box trying to fit in the smallest container first
            //if it does not fit try the next smallest container
            for(Box box: boxes){
                Iterator itr = conts.iterator();    //itr for looping over the containers
                
                boolean flag_tmp = false;           //flag_tmp is used to check whether box is added or not
                                                    //if not added it will print the box 

                OUTER:                              //OUTER for comming out of it using break statement
                while(itr.hasNext()){               //checking whether there is containers left
                    Container cont = (Container)itr.next();     //storing the container in the cont reference

                    double tp1 = cont.xt;           //tp1 is storing the remaining length of container x side
                    double tp2 = cont.yt;           //tp2 is storing the remaining length of container y side

                    if(cont.flag_rev == false){     //cont.flag_rev is an flag variable to initially assign cont.zt
                        cont.zt = box.z;            
                        cont.flag_rev = true;       //as it is initially assigned it is changed to true
                    }

                    if((cont.volume>=box.volume) && (cont.weight>=box.weight)){     //checking if box wt and volume constraint is matched
                        int cff = 0;                                                //cff is a counter variable to store number of combinations

                        while(cff<=9){
                            if((cont.zt>=box.z) || (cont.z>=box.z)){                //checking whether the container height is fitting the box or not
                                int cfk = 0;                                        //cfk is a counter variable to store number of combinations
                                
                                while(cfk<=6){
                                    if((cont.xt>=box.x) && (cont.yt>=box.y)){       //if the box fits the remaining space then
                                        cont.volume-=box.volume;                    //decrementing cont volume
                                        cont.weight-=box.weight;                    //decrementing cont weight
                                                    
                                        double trp = cont.zt;                       //trp is a temporary variable to store previous max height of that level
                                        cont.zt = Math.max(cont.zt,box.z);          //changing cont.zt

                                        if(cont.zt!=trp){                           //if it is not equal 
                                            cont.z = cont.z - cont.zt + trp;        //chaning the cont.z --visualize IT
                                        }

                                        if(cont.first_flag){                        //for the first time the container is choosen
                                            cont.z-=cont.zt;
                                            cont.first_flag = false;                //toggling the cont.first_flag
                                            cont.level++;                           //for initial level leveling up
                                        }

                                        //Making new reference and Adding the box into the BoxFinal reference
                                        BoxFinal tp = new BoxFinal(cont.id,cont.name,box,cont.level,cont.x-cont.xt,cont.y-cont.yt);
                                        boxesfinal.add(tp);
                                        c--;
                                        cff++;
                                        cfk++;

                                        flag_tmp = true;

                                        //two temporary variables changing to find the maximum sized rectangle --visualize IT
                                        tp1-=box.x;
                                        tp2-=box.y;

                                        if(tp1>tp2){
                                            cont.xt = tp1;
                                        }
                                        else if(tp1<tp2){
                                            cont.yt = tp2;
                                        }

                                        // ---FOR TESTING PURPOSE---
                                        // System.out.println("Test O/p:");
                                        // System.out.println("\t"+tp.toString());
                                        // System.out.println("\tContainorID: "+cont.id+"  Containor_name="+cont.name);
                                        // System.out.printf("\tRemaining_Wt=%.2f   remaining_volume=%.2f   level=%d   remaining_depth=%.2f\n",cont.weight,cont.volume,cont.level-1,cont.z);
                                        // System.out.printf("\tcont.xt=%.2f   cont.yt=%.2f   cont.zt=%.2f\n",cont.xt,cont.yt,cont.zt);

                                        //as box is added break the OUTER loop and add another box
                                        break OUTER;
                                    }
                                    //if box is not added due to reduced space but there is scope of level available --VISUALIZE IT
                                    else if(cont.z>=box.z && (cont.x>=box.x && cont.y>=box.y) && (cont.xt<box.x || cont.yt<box.y)){
                                        Level tlevel = new Level(cont.level,cont.z_orignal - cont.z);   //creating reference of level
                                        cont.levels.add(tlevel);                                        //adding the old level height

                                        cont.level++;                                                   //leveling up
                                        cont.xt = cont.x;                                               //reseting the contatiner xt and yt
                                        cont.yt = cont.y;

                                        tp1 = cont.x;                                                   //leveling up
                                        tp2 = cont.y;                                                   //so resetting the container temp variables

                                        cont.zt = box.z;                                                   //changing the container zt
                                        cont.z -= cont.zt;
                
                                        cont.flag_level = true;                                            //as it is leveling up so flag is changed

                                        cff++;
                                        cfk++;
                                    }
                                    else{
                                        //else twisting the container
                                        double t5 = box.x;
                                        box.x = box.y;
                                        box.y = t5;

                                        cff++;
                                        cfk++;
                                    }
                                }
                            }
                            else if((cont.z>=box.z) && (box.y<box.x)){
                                //vertically twisting the conatiner having least height --VISUALIZE IT
                                cont.zt = Math.max(cont.zt,box.y);

                                double th = box.z;
                                box.z = box.y;
                                box.y = th;  

                                cff++;
                            }
                            else if((cont.z>=box.z) && (box.x<box.y)){
                                //vertically twisting the conatiner having least height --VISUALIZE IT
                                cont.zt = Math.max(cont.zt,box.x);

                                double th = box.z;
                                box.z = box.x;
                                box.x = th;  

                                cff++;
                            }
                            else{
                                //if none of the cases are true
                                //incrementing the combinations to make while loop to end
                                cff++;
                            }
                        }
                    }
                    else{
                        continue OUTER;     //try the same box in the next container
                    }
                }

                if(flag_tmp==false){
                    System.out.println(box.toString());     //displaying the non filled boxes
                }

                Collections.sort(conts, new ContainerComparatorVol());      //again sorting the conatiners for the next iteration
                //Collections.reverse(conts);
            }

            System.out.println();
        
            //print the Boxes list --that are filled
            Collections.sort(boxesfinal, new BoxFinalComparatorId());
            System.out.println("The boxes and containers are:-");
            int tp_prev_id = 1;

            for(BoxFinal tp : boxesfinal){
                if(tp_prev_id!=tp.id){
                    System.out.println();
                }
                System.out.println("Container ID:"+tp.id+" Container Name:"+tp.name+"\tBoxDetails:"+tp.toString());
                tp_prev_id = tp.id;
            }
            
            System.out.println("Total number of boxes="+boxes.size());
            System.out.println("Number of boxes left="+c);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    void display_stuff(){
        System.out.println("\nChecking the efficiency of packing:-");
        System.out.println("Number of containers: "+conts.size());
        for(Container cont:conts){
            System.out.println("Container ID:"+cont.id);
            System.out.println("\tContainer_name:"+cont.name);
            System.out.printf("\tContainer_wt=%.2f\tcontainer_volume=%.2f\n",(arr[cont.id-1][1]-cont.weight),(arr[cont.id-1][0]-cont.volume));
            System.out.printf("\tContainer_wt_total=%.2f\tcontainer_vol_total=%.2f\n",arr[cont.id-1][1],arr[cont.id-1][0]);
            System.out.printf("\twt%%=%.2f\tvol%%=%.2f\n",((arr[cont.id-1][1]-cont.weight)/arr[cont.id-1][1])*100,((arr[cont.id-1][0]-cont.volume)/arr[cont.id-1][0])*100);
            
            for(Level plevel: cont.levels){
                System.out.println("\t\t"+plevel.toString());
            }
        }
    }
}