import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static String tempname;
    static int tempage;
    static String temppass;
    static String temppassconf;
    static String tempmail;
    static String tempno;
    static int x = 0;
    static int count = 0;
    static int flag = 1;
    static int choice = 0;
    static boolean status = true;
    static int caps = 0;
    static int small = 0;
    static int numbers = 0;
    static int special = 0;
    static int success = 0;
    static ArrayList<hotels> hotellist = new ArrayList<>();
    static ArrayList<users> userlist = new ArrayList<>();
    static ArrayList<Cuisinesavailable> cuisinelist = new ArrayList<>();
    static class Cuisinesavailable{
        String nameofcuisine;
        ArrayList<String> hotelswithcuisine;
        ArrayList<Integer> priceofcuisine;
    }
    static class hotels{
        String hotelname;
        ArrayList<String> foods;
        ArrayList<Integer> price;
    }
    static class users{
        String username;
        int age;
        String password;
        String email;
        String mobileno;
    }
    public static void main(String[] args){
        initialisehoteldatabase();
        initialisecuisinedatabase();
        mainframe();
    }
    public static void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to Login Portal");
        System.out.println("Enter Your Email");
        tempmail = sc.next();
        System.out.println("Enter Your Password");
        temppass = sc.next();
        if(userlist.size()==0){
            System.out.println("Account doesnot exists!Please Signup");
            System.out.println();
            return;
        }
        for(int i=0;i<userlist.size();i++){
            String s = userlist.get(i).email;
            System.out.println(s);
            if(s.compareTo(tempmail)==0){
                if(userlist.get(i).password.compareTo(temppass)==0){
                    System.out.println("Login Succesfull");
                    System.out.println();
                    deliveryapp();
                }
                else{
                    System.out.println("Invalid Password! Please Enter the Correct Password");
                    System.out.println();
                    break;
                }
            }
            else{
                System.out.println("Account Doesnot Exist! Please Signup");
                System.out.println();
                break;
            }
        }
    }
    public static void deliveryapp(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("We Provide Two Way of Search");
        while(true){
            System.out.println("1.Search Restaurants");
            System.out.println("2.Search Cuisine");
            System.out.println("3.Go to Main Screen");
            int n = sc.nextInt();
            if(n==1){
                hotelsearch();
                break;
            }
            else if(n==2){
                foodsearch();
                break;
            }
            else if(n==3){
                break;
            }
            else{
                System.out.println("please enter correct choice");
                System.out.println();
            }
        }
        return;
    }
    public static void hotelsearch(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose Your Favorite Restaurants");
        for(int i=0;i<hotellist.size();i++){
            String s = hotellist.get(i).hotelname;
            System.out.println((i+1)+". "+s);
        }
        System.out.println("Enter the Restaurant Number");
        int n = sc.nextInt();
        hotelchoice(n);
        return;
    }
    public static void hotelchoice(int n){
        Scanner sc = new Scanner(System.in);
        hotels u = hotellist.get(n-1);
        int k = u.foods.size();
        System.out.println();
        System.out.println("   Cuisines Available");
        for(int i=0;i<k;i++){
            System.out.println((i+1)+". "+u.foods.get(i)+" "+u.price.get(i));
        }
        ArrayList<String> selectedfood = new ArrayList<>();
        ArrayList<Integer> priceoffood = new ArrayList<>();
        System.out.println("Enter the number assigned to your favorite food");
        System.out.println("Press 0 to go to cart");
        while(true){
            int no = sc.nextInt();
            if(no==0){
                break;
            }
            selectedfood.add(u.foods.get(no-1));
            priceoffood.add(u.price.get(no-1));
        }
        cart(selectedfood,priceoffood);
        return;
    }
    public static void foodsearch() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose Your Favorite Cuisine");
        for(int i=0;i<cuisinelist.size();i++){
            String s = cuisinelist.get(i).nameofcuisine;
            System.out.println((i+1)+". "+s);
        }
        System.out.println("Enter the Cuisine Number");
        int n = sc.nextInt();
        foodchoice(n);
        return;
    }
    public static void foodchoice(int n) {
        Scanner sc = new Scanner(System.in);
        Cuisinesavailable c = cuisinelist.get(n-1);
        int k = c.hotelswithcuisine.size();
        System.out.println();
        for(int i=0;i<k;i++){
            System.out.println((i+1)+". "+c.hotelswithcuisine.get(i)+" "+c.priceofcuisine.get(i));
        }
        System.out.println("Enter the Restaurant Number");
        int q = sc.nextInt();
        int sum = c.priceofcuisine.get(q-1);
        System.out.println(c.hotelswithcuisine.get(q-1)+" "+c.priceofcuisine.get(q-1));
        System.out.println();
        System.out.println("Your total cart value  "+sum);
        System.out.println("1.Place your order");
        System.out.println("2.Cancel the order");
        int p = sc.nextInt();
        if(p==1){
            System.out.println("Your Order was Placed Succesfully!");
            System.out.println();
            System.exit(0);
            return;
        }
        if(p==2){
            System.out.println("Your Order was Cancelled!");
            System.out.println();
            System.exit(0);
            return;
        }
    }
    public static void cart(ArrayList<String> s, ArrayList<Integer> q) {
        Scanner sc = new Scanner(System.in);
        int k = s.size();
        int sum =0;
        System.out.println();
        System.out.println("Your Cart");
        for(int i=0;i<k;i++){
            sum = sum + q.get(i);
            System.out.println(s.get(i)+" "+q.get(i));
        }
        System.out.println();
        System.out.println("Your total cart value  "+sum);
        System.out.println("1.Place your order");
        System.out.println("2.Cancel the order");
        int n = sc.nextInt();
        if(n==1){
            System.out.println("Your Order was Placed Succesfully!");
            System.exit(0);
            return;
        }
        if(n==2){
            System.out.println("Your Order was Cancelled!");
            System.exit(0);
            return;
        }
    }
    public static void signup(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to Signup Portal");
        System.out.println("Enter Your Name");
        tempname = sc.nextLine();
        System.out.println("Enter Your Age");
        tempage = sc.nextInt();
        System.out.println("Enter Your Email");
        tempmail = sc.next();
        System.out.println("Enter Your Password");
        temppass = sc.next();
        System.out.println("Confirm Your Password");
        temppassconf = sc.next();
        System.out.println("Enter Your Mobile Number");
        tempno = sc.next();
        
        x = validate();
        if(x==1){
            accountcheck();
            users u = new users();
            u.age = tempage;
            u.username = tempname;
            u.email = tempmail;
            u.password = temppass;
            u.mobileno = tempno;
            userlist.add(u);
        }
        return;
    }
    public static int validate(){
        for(int i=0;i<tempname.length();i++){
            if((tempname.charAt(i)>='a' && tempname.charAt(i)<='z') || (tempname.charAt(i)>='A' && tempname.charAt(i)<='Z')|| tempname.charAt(i)==' '){
                flag = 1;
            }
            else{
                System.out.println("Please Enter a Valid Name");
                System.out.println();
                flag = 0;
                return 0;
            }
        }
        if(flag==1){
            count = 0;
            for(int i=0;i<tempmail.length();i++){
                if((tempmail.charAt(i)=='@')||tempmail.charAt(i)=='.') count++;
            }
            if(count>=2 && tempmail.length()>=5){
               if(temppass.compareTo(temppassconf)==0){
                   if(temppass.length()>=8 && temppass.length()<=12){
                    caps = 0;
                    small = 0;
                    numbers = 0;
                    special = 0;
                    for(int i=0;i<temppass.length();i++){
                        if(temppass.charAt(i)>='A' && temppass.charAt(i)<='Z') caps++;
                        else if(temppass.charAt(i)>='a' && temppass.charAt(i)<='z') small++;
                        else if(temppass.charAt(i)>='0' && temppass.charAt(i)<='9') numbers++;
                        else if(temppass.charAt(i)=='@' || temppass.charAt(i)=='&' || temppass.charAt(i)=='#' || temppass.charAt(i)=='*') special++;
                    }
                    if (caps >= 1 && small >= 1 && numbers >= 1 && special >= 1){
                        if(tempage>0){
                            if(tempno.length()==10){
                                for(int i=0;i<tempno.length();i++){
                                    if(tempno.charAt(i)>='0' && tempno.charAt(i)<='9'){
                                        success=1;
                                    }
                                    else{
                                        System.out.println("Enter Valid Mobile Number");
                                        System.out.println();
                                        return 0;
                                    }
                                }
                                if(success==1){
                                    return 1;
                                }
                            }
                            else{
                                System.out.println("Enter 10 digit Mobile Number");
                                System.out.println();
                            }
                        }
                        else{
                            System.out.println("Enter a Valid Age");
                            System.out.println();
                            return 0;
                        }
                    }
                    else{
                        System.out.println("Please Enter a Strong Password. Your Password must contain atleast one Uppercase letter, one lowercase letter, one number and one special character");
                        System.out.println();
                        return 0;
                    }
                   }
                   else{
                       System.out.println("Password not in range. Your password length must be between length of 8-12");
                       System.out.println();
                       return 0;
                   }
               } 
               else{
                   System.out.println("Password Mismatch");
                   System.out.println();
                   return 0;
               }
            }
            else{
                System.out.println("Please Enter Valid E-Mail");
                System.out.println();
                return 0;
            }
        }
        return 1;
    }
    public static void accountcheck(){
        for(int i=0;i<userlist.size();i++){
            if(userlist.get(i).email == tempmail){
                System.out.println("Account already exists with this email!Please Login");
                System.out.println();
                break;
            }
        }
        System.out.println("Account Successfully Created");
        System.out.println();
        return;
    }
    public static void mainframe(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to our Food Delivery System");
        while(true){
            System.out.println("1.Signup");
            System.out.println("2.Login");
            System.out.println("3.Exit");
            System.out.println("Enter the Choice");
            choice= sc.nextInt();
            if(choice==1){
                signup();
            }
            else if(choice==2){
                login();
            }
            else if(choice==3){
                System.exit(0);
            }
            else{
                System.out.println("Please Enter Correct Choicesss");
            }
        }
    }
    public static void initialisehoteldatabase() { 
        hotels a = new hotels();
        String s = "";
        ArrayList<String> f = new ArrayList<>();
        a.hotelname = "CRYSTAL BOWL";
        s = "Chilli Paneer";
        f.add(s);
        s = "Aroma Rice";
        f.add(s);
        s = "Crispy BabyCorn";
        f.add(s);
        s = "Maharaja Thali";
        f.add(s);
        a.foods = f;
        ArrayList<Integer> p = new ArrayList<>();
        p.add(350);
        p.add(450);
        p.add(300);
        p.add(600);
        a.price = p;
        hotels b = new hotels();
        ArrayList<String> f1 = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        b.hotelname = "ZAIKA";
        s = "Butter Chicken";
        f1.add(s);
        s = "Biryani";
        f1.add(s);
        s = "Naan";
        f1.add(s);
        s = "Mutton Seekh Kebab";
        f1.add(s);
        p1.add(400);
        p1.add(500);
        p1.add(60);
        p1.add(350);
        b.foods = f1;
        b.price = p1;
        hotels c = new hotels();
        ArrayList<String> f2 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        c.hotelname = "LEMON GRASS";
        s = "White Sauce Pasta";
        f2.add(s);
        s = "Veg Fried Rice";
        f2.add(s);
        s = "Spring Roll";
        f2.add(s);
        s = "Mushroom Tikka";
        f2.add(s);
        p2.add(300);
        p2.add(150);
        p2.add(200);
        p2.add(250);
        c.foods = f2;
        c.price = p2;
        hotels d = new hotels();
        ArrayList<String> f3 = new ArrayList<>();
        ArrayList<Integer> p3 = new ArrayList<>();
        d.hotelname = "ROMA'S CAFE";
        s = "Cheese and Corn Pizza";
        f3.add(s);
        s = "Mexican Aloo Tikki Burger";
        f3.add(s);
        s ="Schezwan Noodles";
        f3.add(s);
        s = "StrawBerry Milkshake";
        f3.add(s);
        p3.add(350);
        p3.add(200);
        p3.add(250);
        p3.add(150);
        d.foods = f3;
        d.price = p3;
        hotellist.add(a);
        hotellist.add(b);
        hotellist.add(c);
        hotellist.add(d);
    }
    public static void initialisecuisinedatabase() {
        Cuisinesavailable a = new Cuisinesavailable();
        String s = "";
        ArrayList<String> f = new ArrayList<>();
        a.nameofcuisine = "MOMOS";
        s = "Momos and Mayo";
        f.add(s);
        s = "Wow Momos";
        f.add(s);
        s = "Red Chillies";
        f.add(s);
        s = "Hot n Spicy";
        f.add(s);
        a.hotelswithcuisine = f;
        ArrayList<Integer> p = new ArrayList<>();
        p.add(160);
        p.add(350);
        p.add(100);
        p.add(60);
        a.priceofcuisine = p;
        Cuisinesavailable b = new Cuisinesavailable();
        ArrayList<String> f1 = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        b.nameofcuisine = "BURGERS";
        s = "McDonald";
        f1.add(s);
        s = "Burger King";
        f1.add(s);
        s = "KFC";
        f1.add(s);
        s = "Leon Grill";
        f1.add(s);
        p1.add(150);
        p1.add(200);
        p1.add(250);
        p1.add(350);
        b.hotelswithcuisine = f1;
        b.priceofcuisine = p1;
        Cuisinesavailable c = new Cuisinesavailable();
        ArrayList<String> f2 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        c.nameofcuisine = "BIRYANI";
        s = "The Biryani Life";
        f2.add(s);
        s = "Behrouz Biryani";
        f2.add(s);
        s = "Champaran";
        f2.add(s);
        s = "Biryani House";
        f2.add(s);
        p2.add(350);
        p2.add(500);
        p2.add(250);
        p2.add(600);
        c.hotelswithcuisine = f2;
        c.priceofcuisine = p2;
        Cuisinesavailable d = new Cuisinesavailable();
        ArrayList<String> f3 = new ArrayList<>();
        ArrayList<Integer> p3 = new ArrayList<>();
        d.nameofcuisine = "CHICKEN WRAPS";
        s = "Faasos";
        f3.add(s);
        s = "Rolls on Wheels";
        f3.add(s);
        s ="Kaati Rolls";
        f3.add(s);
        s = "Roll n Rock";
        f3.add(s);
        p3.add(350);
        p3.add(200);
        p3.add(250);
        p3.add(150);
        d.hotelswithcuisine = f3;
        d.priceofcuisine = p3;
        cuisinelist.add(a);
        cuisinelist.add(b);
        cuisinelist.add(c);
        cuisinelist.add(d);
    }
}
