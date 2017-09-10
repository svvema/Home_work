package com.company;

public class Course {
  private Obstacle[] course = {new Cross(500), new Wall(2), new Water(5), new Cross(800)};

    public Course() {
    }

    public void trail(Team team) {
        for(int i = 0; i < team.getAnimals().length; i++){
            for(int j = 0; j < course.length; j++){
                course[j].doIt(team.getAnimals()[i]);
                if(!team.getAnimals()[i].onDistance) break;
            }
        }
    }
}
