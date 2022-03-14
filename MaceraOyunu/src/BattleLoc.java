import java.util.Locale;
import java.util.Random;

public abstract class BattleLoc extends Location{

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    public BattleLoc(String name, Player player,Obstacle obstacle,String award,int maxObstacle) {
        super(name, player);
        this.obstacle=obstacle;
        this.award=award;
        this.maxObstacle=maxObstacle;
    }

    @Override
    public boolean onLocation() {
        if(this.getName().equals("Mağara")){
            if(this.getPlayer().getInventory().isFood()){
                System.out.println("Zaten yemeğiniz var tekrar giremezsiniz!");
                return true;
            }
        }
        if(this.getName().equals("Orman")){
            if(this.getPlayer().getInventory().isFirewood()){
                System.out.println("Zaten odunun var tekrar giremezsiniz!");
                return true;
            }
        }
        if(this.getName().equals("Nehir")){
            if(this.getPlayer().getInventory().isWater()){
                System.out.println("Zaten suyunuz var tekrar giremezsiniz!");
                return true;
            }
        }
        int obsNumber = this.randomObstacleNum();
        System.out.println("Şuan: "+ this.getName()+ " içindesin");
        System.out.println("Dikkatli ol!Burada "+obsNumber+" tane " +this.getObstacle().getName()+ " yaşıyor!");
        System.out.print("<S>avaş yada <K>aç:");

        String selectSituation=input.nextLine();
        selectSituation=selectSituation.toUpperCase();

        if(selectSituation.equals("S")&&combat(obsNumber)){
            //Savas
            System.out.println(this.getName() +"'daki tüm düşmanları yendiniz!");
            //burda award alcam
            //hangi battle loc oldugunu kontrol et ve ona göre ödül ver.
            if(this.getName().equals("Mağara")){
                this.getPlayer().getInventory().setFood(true);
            }
            if(this.getName().equals("Nehir")){
                this.getPlayer().getInventory().setWater(true);
            }
            if(this.getName().equals("Orman")){
                this.getPlayer().getInventory().setFirewood(true);
            }

            return true;
        }
        if(this.getPlayer().getHealth()<=0){
            return  false;
        }
        return true;
    }

    public boolean combat(int obsNum){
        for(int i=1;i<=obsNum;i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjHealth());
            playerStatus();
            obstacleStatus(i);
            //player ve obstacle canları hala varsa
            int savasSırası =savasSırası();
            while(this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0){
                System.out.println("<V>ur yada <K>aç : ");
                String selectCombat=input.nextLine();
                selectCombat=selectCombat.toUpperCase();

                if(selectCombat.equals("V")){

                    //player vur
                    if(savasSırası==0){
                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.obstacle.getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth()>0){
                            System.out.println();
                            System.out.println("Canavar size vurdu!");
                            int obstacleDamage=
                                    this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();

                            if(obstacleDamage<0){
                                obstacleDamage=0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                            afterHit();
                        }
                     //ilk canavar vurcak
                    }else{
                        System.out.println("Canavar size vurdu!");
                        int obstacleDamage=
                                this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();

                        if(obstacleDamage<0){
                            obstacleDamage=0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                        afterHit();
                        if(this.getPlayer().getHealth()>0){
                            System.out.println("\nSiz vurdunuz!");
                            this.getObstacle().setHealth(this.obstacle.getHealth()-this.getPlayer().getTotalDamage());
                            afterHit();
                        }

                    }



                    //kacmayı secti
                }else{
                    return false;
                }
            }//while loop

            if(this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                System.out.println("\n\nDüsmanı yendiniz!");
                System.out.println(this.getObstacle().getEarnedMoney()+" para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getObstacle().getEarnedMoney());
                System.out.println("Güncel paranız: "+this.getPlayer().getMoney());
            }else{
                return false;
            }
        }//for loop
        return  true;
    }
    public int savasSırası(){

        Random r =new Random();
        return r.nextInt(2);
    }

    public void  afterHit(){
        System.out.println("Canınız : "+ this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+ " canı : "+ this.getObstacle().getHealth());
    }

    public void playerStatus(){
        System.out.println("### Oyuncu Değerleri ###");
        System.out.println("---------------------");
        System.out.println("Sağlık : "+this.getPlayer().getHealth());
        System.out.println("Hasar : "+this.getPlayer().getTotalDamage());
        System.out.println("Silah : "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : "+this.getPlayer().getMoney());
    }

    public void obstacleStatus(int i){
        System.out.println("### "+ i+"."+this.getObstacle().getName() +" Değerleri ###");
        System.out.println("---------------------");
        System.out.println("Sağlık : "+this.getObstacle().getHealth());
        System.out.println("Hasar : "+this.getObstacle().getDamage());
        System.out.println("Para : "+this.getObstacle().getEarnedMoney());
    }

    public int randomObstacleNum(){
        Random r= new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
