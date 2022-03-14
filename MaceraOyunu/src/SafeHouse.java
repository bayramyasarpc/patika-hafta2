public  class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super("Güvenli Ev", player);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz!");
        System.out.println("Canınız yeniden doldu");
        System.out.println(this.getPlayer().getOrgHealth());
        this.getPlayer().setHealth(this.getPlayer().getOrgHealth());

        System.out.println(this.getPlayer().getInventory().isFood());
        System.out.println(this.getPlayer().getInventory().isFirewood());
        System.out.println(this.getPlayer().getInventory().isWater());

        if(this.getPlayer().getInventory().isFirewood()&&
                this.getPlayer().getInventory().isFood()&&
                this.getPlayer().getInventory().isWater()){

            System.out.println("Tüm ödüller alınmıştır");
            System.out.println("Tebrikler! Oyunu başarıyla tamamladınız!!!");
            return false;
        }
        return true;
    }
}
