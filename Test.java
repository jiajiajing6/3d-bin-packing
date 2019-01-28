class Test{
    public static void main(String args[]){
        try{
            BinPacking ob1 = new BinPacking();
            ob1.read_boxes_file();
            System.out.println();

            ob1.read_conts_file();
            System.out.println();

            ob1.least_no_boxes();
            System.out.println();
            ob1.display_stuff();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}