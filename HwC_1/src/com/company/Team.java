package com.company;

public class Team {
    protected String teamName;

    private Animal[] animals = new Animal[4];

    public Team(String teamName, Animal[] a1) {
        this.teamName = teamName;
        this.animals = a1;
    }
    public Team(String teamName, Animal a1, Animal a2,Animal a3, Animal a4 ){
        this.teamName = teamName;
        this.animals[0] = a1;
        this.animals[1] = a2;
        this.animals[2] = a3;
        this.animals[3] = a4;
    }


    public  void  teamPassInfo(){
        for (int i = 0; i <this.animals.length ; i++) {
            if (this.animals[i].onDistance){
                System.out.println(this.animals[i].type + " " + this.animals[i].name + " прошел дистанцию");
            }
        }
    }
    public  void  teamInfo(){
        for (int i = 0; i <this.animals.length ; i++) {
            if (this.animals[i].onDistance){
                System.out.println(this.animals[i].type + " " + this.animals[i].name + " прошел дистанцию");
            }else
                System.out.println(this.animals[i].type + " " + this.animals[i].name + " не прошел дистанцию");
        }
    }

    public Animal[] getAnimals() {
        return animals;
    }
}
