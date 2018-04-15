import com.giang.kesach.models.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

public static boolean get(Man m){
    if(m.getAge()==1){
        m.setName("gag");return true;}
        return false;
}
	public static void main(String []args) {
        UserInfo u = new UserInfo();
        u.getAddress().setCity("sd");
        System.out.println(u.getAddress());
    }
	}

