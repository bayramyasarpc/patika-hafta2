import java.util.Scanner;

public class Game {
    public Scanner input= new Scanner(System.in);
    public void start(){
        System.out.println("Macera oyununa hosgeldiniz!");
        System.out.println("Lütfen isim giriniz:");
        //String playerName=input.nextLine();

        Player player=new Player("Bayram");
        System.out.println("Sayın "+ player.getPlayerName()+ " bey/hanım benim küçük tatlı oyunuma hosgeldiniz!");
        System.out.println("Sizden keyif almanızı ve oyun sonrası geri dönüşlerinizi bekliyor olacağım");

        player.selectChar();

        Location location=null;
        while (true){
            player.printPlayerInfo();

            System.out.println("\n####Bölgeler####");
            System.out.println("-----------------------------");
            System.out.println("1-Güvenli Ev -->Burası sizin için güvenli, düşman yoktur!");
            System.out.println("2-Eşya Dükkanı -> Silah ve zırh satın alabilirsiniz");
            System.out.println("3-Mağara --> Ödül<Yemek> Mağaraya gir,dikkat zombi var");
            System.out.println("4-Orman --> Ödül<Odun> Ormana git,dikkat vampir var");
            System.out.println("5-Nehir --> Ödül<Su> Nehire git,dikkat ayı var");
            System.out.println("0-Çıkış yap");
            System.out.println("-------------------------------");
            System.out.println("Şimdi lütfen gitmek istediğiniz yeri seçiniz:");
            int selectLoc=input.nextInt();



            switch (selectLoc){

                case 0:
                    location=null;
                    break;
                case 1:
                    location= new SafeHouse(player);
                    break;
                case 2:
                    location=new ToolStore(player);
                    break;
                case 3:
                    location=new Cave(player);
                    break;
                case 4:
                    location=new Forest(player);
                    break;
                case 5:
                    location=new River(player);
                    break;
                default:
                    location=new SafeHouse(player);
            }
            if(location==null){
                System.out.println("Oyundan kactın!");
                break;
            }
            if(!location.onLocation()){
                System.out.println("GAME OVER!");
                break;
            }

        }
    }
}
