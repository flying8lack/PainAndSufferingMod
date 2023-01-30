package com.flying_8lack.painmod.client;

public class ClientTitleCard {

    private static int time = 0;
    private static boolean should_display = false;


    public void display(){
            time = 60;

    }

    public boolean isShould_display(){
        return should_display;
    }

    public void update(){
        if(time > 0){
            time -= 1;
            should_display = true;
        } else {
            should_display = false;
        }
    }
}
