package com.ballsteam.sokiduels.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyboardInput extends PlayerInput {
    private boolean hasConfigOne;
    private int KEY_LEFT;
    private int KEY_RIGHT;
    private int KEY_UP;
    private int KEY_DOWN;
    private int KEY_A;
    private int KEY_B;

    /*
        public KeyboardInput(){
        if (isPlayerOne){
            KEY_LEFT = Keys.A;
            KEY_RIGHT = Keys.D;
            KEY_UP = Keys.W;
            KEY_DOWN = Keys.S;
            KEY_A = Keys.E;
            KEY_B = Keys.SPACE;
        } else {
            KEY_LEFT = Keys.J;
            KEY_RIGHT = Keys.L;
            KEY_UP = Keys.I;
            KEY_DOWN = Keys.K;
            KEY_A = Keys.U;
            KEY_B = Keys.M;
        }
    }*/

    public KeyboardInput(boolean configOne){
        this.hasConfigOne = configOne;
        mapping();

    }

    @Override
    public void update(){
        setInputValues();
    }
    @Override
    public boolean interacted(){
            return !hasPlayer && (key(KEY_A) || key(KEY_B) || key(KEY_UP) || key(KEY_DOWN) || key(KEY_LEFT) || key(KEY_RIGHT));
    }
    private void setInputValues(){
        LEFT_RIGHT = valueDeltaX();
        UP_DOWN = valueDeltaY();
        A = key(KEY_A);
        B = key(KEY_B);
    }
    private boolean key(int keyValue){
        return Gdx.input.isKeyPressed(keyValue);
    }
    private int valueDeltaX(){
        return Boolean.compare(key(KEY_RIGHT),key(KEY_LEFT));
    }
    private int valueDeltaY(){
        return Boolean.compare(key(KEY_UP),key(KEY_DOWN));
    }
    private void mapping(){
        if (hasConfigOne){
            KEY_LEFT = Keys.A;
            KEY_RIGHT = Keys.D;
            KEY_UP = Keys.W;
            KEY_DOWN = Keys.S;
            KEY_A = Keys.C;
            KEY_B = Keys.V;
        } else {
            KEY_LEFT = Keys.J;
            KEY_RIGHT = Keys.L;
            KEY_UP = Keys.I;
            KEY_DOWN = Keys.K;
            KEY_A = Keys.O;
            KEY_B = Keys.P;
        }
    }
}
