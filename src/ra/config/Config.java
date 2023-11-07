package ra.config;

import ra.model.User;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
    // format tiền
    public static DecimalFormat formatMoney = new DecimalFormat("###,###,###");
    public static final String PATH_CATALOG = "src/ra/DataBase/catalog.txt";
    public static final String PATH_PRODUCT = "src/ra/DataBase/product.txt";
    public static final String PATH_USER = "src/ra/DataBase/user.txt";
    public static final String PATH_USER_LOGIN = "src/ra/DataBase/user_principal.txt";

    public static final String PATH_ODER = "src/ra/DataBase/order.txt";

    //Phương thức đọc file
    public  List<T> readFromFile(String pathFile)  {
        List<T> tList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tList = (List<T>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException f){
            System.err.println("File not found!");
        } catch (IOException i){
            System.err.println("IOE exception!");
        } catch (ClassNotFoundException c){
            System.err.println("Class Not Found");
        }
        return tList;
    }
    //Phương thức ghi file
    public void writeToFile(String pathFile, List<T> tList){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException f){
            System.err.println("File Not Found!");
        } catch (IOException i){
            System.err.println("IOE Exception!");
        }
    }
    // validate int
    public static int validateInt() {
        int n;
        System.out.print("Mời nhập: ");
        while (true) {
            try {
                n = Integer.parseInt(scanner().nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("\033[0;31m                                                           Sai định dạng. Vui lòng chọn lại.                                \033[0;0m");
            }
        }
        return n;
    }
}
