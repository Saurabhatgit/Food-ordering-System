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
        initialisedatabase();
        mainframe();
    }
    public static void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Login Portal");
        System.out.println("Enter Your Email");
        tempmail = sc.next();
        System.out.println("Enter Your Password");
        temppass = sc.next();
        if(userlist.size()==0){
            System.out.println("Account doesnot exists!Please Signup");
            return;
        }
        for(int i=0;i<userlist.size();i++){
            String s = userlist.get(i).email;
            System.out.println(s);
            if(s.compareTo(tempmail)==0){
                if(userlist.get(i).password.compareTo(temppass)==0){
                    System.out.println("Login Succesfull");
                    deliveryapp();
                }
                else{
                    System.out.println("Invalid Password! Please Enter the Correct Password");
                    break;
                }
            }
            else{
                System.out.println("Account Doesnot Exist! Please Signup");
                break;
            }
        }
    }
    public static void deliveryapp(){
        Scanner sc = new Scanner(System.in);
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
            }
        }
        return;
    }
    public static void hotelsearch(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Your Favorite Restaurants");
        for(int i=0;i<hotellist.size();i++){
            String s = hotellist.get(i).hotelname;
            System.out.println((i+1)+s);
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
        System.out.println("Cuisine"+" "+"Cost");
        for(int i=0;i<k;i++){
            System.out.println((i+1)+u.foods.get(i)+" "+u.price.get(i));
        }
        ArrayList<String> selectedfood = new ArrayList<>();
        ArrayList<Integer> priceoffood = new ArrayList<>();
        System.out.println("Enter the number assigned to your favorite food");
        System.out.print("Press 0 to go to cart");
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
        System.out.println("Choose Your Favorite Cuisine");
        for(int i=0;i<cuisinelist.size();i++){
            String s = cuisinelist.get(i).nameofcuisine;
            System.out.println((i+1)+s);
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
        for(int i=0;i<k;i++){
            System.out.println((i+1)+c.hotelswithcuisine.get(i)+" "+c.priceofcuisine.get(i));
        }
        System.out.println("Enter the Restaurant Number");
        int q = sc.nextInt();
        int sum = c.priceofcuisine.get(q-1);
        System.out.println(c.hotelswithcuisine.get(q-1)+" "+c.priceofcuisine.get(q-1));
        System.out.println("Your total cart value"+sum);
        System.out.println("1.Place your order");
        System.out.println("2.Cancel the order");
        int p = sc.nextInt();
        if(p==1){
            System.out.println("Your Order was Placed Succesfully!");
            System.exit(0);
            return;
        }
        if(p==2){
            System.out.println("Your Order was Cancelled!");
            System.exit(0);
            return;
        }
    }
    public static void cart(ArrayList<String> s, ArrayList<Integer> q) {
        Scanner sc = new Scanner(System.in);
        int k = s.size();
        int sum =0;
        System.out.println("Your Cart");
        for(int i=0;i<k;i++){
            sum = sum + q.get(i);
            System.out.println(s.get(i)+" "+q.get(i));
        }
        System.out.println("Your total cart value"+sum);
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
                                        return 0;
                                    }
                                }
                                if(success==1){
                                    return 1;
                                }
                            }
                            else{
                                System.out.println("Enter 10 digit Mobile Number");
                            }
                        }
                        else{
                            System.out.println("Enter a Valid Age");
                            return 0;
                        }
                    }
                    else{
                        System.out.println("Please Enter a Strong Password. Your Password must contain atleast one Uppercase letter, one lowercase letter, one number and one special character");
                        return 0;
                    }
                   }
                   else{
                       System.out.println("Password not in range. Your password length must be between length of 8-12");
                       return 0;
                   }
               } 
               else{
                   System.out.println("Password Mismatch");
                   return 0;
               }
            }
            else{
                System.out.println("Please Enter Valid E-Mail");
                return 0;
            }
        }
        return 1;
    }
    public static void accountcheck(){
        for(int i=0;i<userlist.size();i++){
            if(userlist.get(i).email == tempmail){
                System.out.println("Account already exists with this email!Please Login");
                break;
            }
        }
        System.out.println("Account Successfully Created");
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
    public static void initialisedatabase() { 
    }
}
