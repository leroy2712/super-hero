import java.util.ArrayList;
import java.util.List;

public class Super {
    private String mName;
    private int mAge;
    private String mPower;
    private String mWeakness;
    private static List<Super> instances = new ArrayList<Super>();

    public Super(String name, int age, String power, String weakness){
        mName = name;
        mAge = age;
        mPower = power;
        mWeakness = weakness;
        instances.add(this);
    }

    public String getName(){
        return mName;
    }

    public int getAge(){
        return mAge;
    }

    public String getPower(){
        return mPower;
    }

    public String getWeakness(){
        return mWeakness;
    }

    public static List<Super> all (){
        return instances;
    }
}