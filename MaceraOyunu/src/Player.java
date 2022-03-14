
import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int orgHealth;
    private int money;
    private String playerName;
    private String charName;
    private Inventory inventory;

    public Scanner input= new Scanner(System.in);

    //constructor
    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory=new Inventory();
    }

    public void selectChar() {
        MyCharacter[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("-  Karakter\t\tHasar\tSağlık\tPara");
        for (MyCharacter myChar : charList) {
            System.out.println(myChar.getId() + "-" + " " + myChar.getName() + "\t\t" +
                    myChar.getDamage() + "\t\t" + myChar.getHealthy() + "\t\t" + myChar.getMoney());
        }
        System.out.println("--------------------------");
        System.out.print("Lütfen seçmek istediğiniz karakter numarasını giriniz:");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                System.out.println("yanlış bir numara girdiniz");
                System.out.println("Biz yardımcı olalım ve samurai karakterini verelim");
                initPlayer(new Samurai());
        }
        System.out.println("Tebrikler! Artık bir "+ this.charName+"'siniz");
        System.out.println("Özellikleriniz "+"Hasarınız:"+this.damage+"  Sağlığınız:"+this.health+"  Paranız:"+this.money);
        System.out.println("\nKarakterimizi ve özelliklerimizi gördüğümüze göre artık oyunun bölgelerine gidebilriz");
    }
    public void initPlayer(MyCharacter myCharacter){
        this.setDamage(myCharacter.getDamage());
        this.setHealth(myCharacter.getHealthy());
        this.setOrgHealth(myCharacter.getHealthy());
        this.setMoney(myCharacter.getMoney());
        this.setCharName(myCharacter.getName());

    }

    public void printPlayerInfo(){
        System.out.println(
                "Silahınız: "+ this.getInventory().getWeapon().getName()+
                "\t,Zırhınız: "+ this.getInventory().getArmor().getName()+
                "\t,Bloklama: "+ this.getInventory().getArmor().getBlock()+
                "\t,Hasarınız: "+ this.getTotalDamage()+
                "\t,Saglık durumunuz: "+ this.getHealth()+
                "\t,Paranız: "+this.getMoney()
        );
    }

    public int getDamage() {
        return damage;
    }
    public int getTotalDamage() {
        return damage+this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOrgHealth() {
        return orgHealth;
    }

    public void setOrgHealth(int orgHealth) {
        this.orgHealth = orgHealth;
    }
}
