import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Human human = new Human(20, 20, "めちゃくちゃかっこいい名前", 10, true);
        Psychics psychics = new Psychics(20, 20, "めちゃくちゃイケてる名前", 20, Ability.ELECTRON_MASTER, 20, true);

        human.attack(psychics, 2, 2);

    }
}

class Human implements Command{
    private int healthy;
    private int HP;
    private String name;
    private UUID uniqueId;
    private int age;
    private boolean sex;
    private final Utilities util = new Utilities();

    public Human(int healthy, int HP, String name, int age, boolean sex) {
        this.healthy = healthy;
        this.HP = HP;
        this.name = name;
        this.uniqueId = UUID.randomUUID();
        this.age = age;
        this.sex = sex;
    }

    @Override
    public void attack(Human target, int atk, int probability) {
        if (util.calcProbability(probability)) {
            target.setHP(target.getHP() - atk);
            System.out.println(name + "は" + target.getName() + "に" + atk + "ダメージ与えた！！！");
        } else {
            System.out.println(target.getName() + "は" + name + "の攻撃をかわした！！！");
        }
    }

    @Override
    public void escape(int probability) {
        if (util.calcProbability(probability)) {
            System.out.println("逃げられた...");
        } else {
            System.out.println("逃げられなかった...");
        }
    }

    @Override
    public void defence(int def, int probability) {
        if (util.calcProbability(probability)) {
            System.out.println("防御できた...");
        } else {
            System.out.println("防御できなかった..."); //TODO ダメージをおう処理を追加
        }
    }

    @Override
    public void skill(Human target, Skill skill) {
        //TODO skillを使用する処理を追加
    }

    @Override
    public void tools(Human target, Tool tool) {
        //TODO ツールを使用する処理を追加
    }

    public int getHealthy() {
        return healthy;
    }

    public int getHP() {
        return HP;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public int getAge() {
        return age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}

class Psychics extends Human {
    private int MP;
    private Ability ability;

    public Psychics(int healthy, int HP, String name, int MP, Ability ability, int age, boolean sex) {
        super(healthy, HP, name, age, sex);
        this.MP = MP;
        this.ability = ability;
    }

    public int getMP() {
        return MP;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

enum Ability {
    ELECTRON_MASTER,
    TELEPORT,
    THERMAL_HAND,
    AERO_HAND,
    HYDRO_HAND,
    FLOAT_DIAL,
    MENTAL_OUT
}

enum Skill {
    SKILL_1,
    SKILL_2,
    SKILL_3
    //スキルは名前が思いつかなかったのでパス(´・ω・`)
}

enum Tool {
    HEAL_POTION,
    MIDDLE_HEAL_PORTION,
    GREAT_HEAL_POTION,
    MANA_POTION,
    MIDDLE_MANA_POTION,
    GREAT_MANA_POTION,
    FRUITS_OF_HEAL,
    FRUITS_OF_MANA
}

interface Command {
    void attack(Human target, int atk, int probability);
    void escape(int probability);
    void defence(int def, int probability);
    void skill(Human target, Skill skill);
    void tools(Human target, Tool tool);
}

class Utilities {
    public boolean calcProbability(int probability) {
        int rnd = new Random().nextInt(probability);
        return rnd == 0;
    }
}