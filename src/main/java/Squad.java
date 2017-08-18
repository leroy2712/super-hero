import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String mName;
    private int mMax;
    private String mCause;
    private static List<Squad> instances = new ArrayList<Squad>();
    private int mId;
    private List<Hero> mHeros;

    public Squad(String name, int max, String cause) {
        mName = name;
        mMax = max;
        mCause = cause;
        instances.add(this);
        mId = instances.size();
        mHeros = new ArrayList<Hero>();
    }

    public String getName() {
        return mName;
    }

    public int getMax() {
        return mMax;
    }

    public String getCause() {
        return mCause;
    }

    public static List<Squad> all() {
        return instances;
    }

    public static void clear() {
        instances.clear();
    }

    public int getId() {
        return mId;
    }

    public static Squad find(int id) {
        try {
            return instances.get(id - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public List<Hero> getHeroes() {
        return mHeros;
    }

    public void addHero(Hero hero) {
        mHeros.add(hero);
    }
}