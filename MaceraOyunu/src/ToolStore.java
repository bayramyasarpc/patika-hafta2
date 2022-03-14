public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super("Mağaza", player);
    }

    @Override
    public boolean onLocation() {
        boolean showMenu=true;
        while(showMenu){
            System.out.println("Mağazaya hosgeldiniz");
            System.out.println("1-Silahlar");
            System.out.println("2-Zırhlar");
            System.out.println("3-Çıkış yap");
            System.out.println("Seçiminiz:");
            int selectTool=input.nextInt();

            switch (selectTool){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    quit();
                    System.out.println("bir daha bekleriz");
                    showMenu=false;
                    break;
            }
        }
        return true;
    }



    public void printWeapon(){
        //  Weapon[] weapons={new Gun(),new Sword(),new Rifle()};
        System.out.println("####Silahlar####");
        for (Weapon w: Weapon.weapons()) {
            System.out.println(w.getId() + "- " + w.getName() + "<Para: " + w.getPrice() + " , Hasar : " + w.getDamage() + ">");
        }
    }
    

    public void quit(){
        System.out.println("cıkıs yap");
    }

    public void buyWeapon(){

        System.out.println("Silahınızı seçiniz:");


        int selectWeaponID=input.nextInt();
        while (selectWeaponID<1 || selectWeaponID>Weapon.weapons().length){
            System.out.println("Geçersiz değer, tekrar giriniz-çıkmak için -1 basınız");
            selectWeaponID=input.nextInt();

        }

        Weapon selectedWeapon=Weapon.getWeaponObjById(selectWeaponID);
        if(selectedWeapon != null){
            if(selectedWeapon.getPrice()>this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır");
            }else{
                //Satın almamın gerçekleştiği alan
                System.out.println(selectedWeapon.getName()+" silahınızı satın aldınız");
                int balance =this.getPlayer().getMoney()-selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız: "+this.getPlayer().getMoney());

                System.out.println("Önceki silahınız:"+this.getPlayer().getInventory().getWeapon().getName());


                this.getPlayer().getInventory().setWeapon(selectedWeapon);

                System.out.println("Yeni silahınız:"+this.getPlayer().getInventory().getWeapon().getName());
            }
        }
    }

    public void printArmor(){
        System.out.println("####Zırhlar####");
        for (Armor a : Armor.armors()){
            System.out.println(
                    a.getId()+ " - " + a.getName()+ "<Para: "+ a.getPrice()+ ",Zırh: "+ a.getBlock()+ " >"
            );
        }
    }

    private void buyArmor() {
        System.out.println("Zırhınızı seçiniz:");


        int selectArmorID=input.nextInt();
        while (selectArmorID<1 || selectArmorID>Armor.armors().length){
            System.out.println("Geçersiz değer, tekrar giriniz");
            selectArmorID=input.nextInt();
        }

        Armor selectedArmor=Armor.getArmorObjById(selectArmorID);
        if(selectedArmor != null){
            if(selectedArmor.getPrice()>this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır");
            }else{
                //Satın almamın gerçekleştiği alan
                System.out.println(selectedArmor.getName()+" zırhınızı satın aldınız!");
                int balance =this.getPlayer().getMoney()-selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız: "+this.getPlayer().getMoney());



                this.getPlayer().getInventory().setArmor(selectedArmor);

            }
        }
    }
}
