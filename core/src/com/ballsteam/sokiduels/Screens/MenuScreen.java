package com.ballsteam.sokiduels.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.ballsteam.sokiduels.SokiDuels;
import com.ballsteam.sokiduels.minigames.Cachipun.CachipunScreen;
import com.ballsteam.sokiduels.minigames.baile.Baile;
import com.ballsteam.sokiduels.minigames.sokiDefense.SokiDefenseScreen;
import com.ballsteam.sokiduels.minigames.spaceinvaders.SokiInvadersScreen;
import com.ballsteam.sokiduels.player.Player;


public class MenuScreen extends AbstractScreen {
    private final Label text = new Label("Proyecto PP", new Skin(Gdx.files.internal("ui/uiskin.json")));
    private final Skin UI_SKIN = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Player J1;
    private Player J2;

    public MenuScreen(Player J1, Player J2, SokiDuels main) {
        super(main);
        this.J1=J1;
        this.J2=J2;
    }

    @Override
    public void buildStage() {

        text.setPosition(getWidth() / 2f, 440, Align.center);
        addActor(text);

        //buttonPlay
        TextButton buttonPlay = createButtonCachipunScreen();
        addActor(buttonPlay);

        //buttonConfig
        TextButton buttonConfig = createButtonBaile();
        addActor(buttonConfig);

        //buttonQuit
        TextButton buttonQuit = createButtonSokiInvaders();
        addActor(buttonQuit);

    }

    private TextButton createButtonCachipunScreen(){
        TextButton buttonPlay = createTextButton("CachipunScreen",
                (text.getX() - 50),(text.getY() - 100));
        buttonPlay.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new CachipunScreen(main, J1, J2));
                dispose();
            }
        });
        return buttonPlay;
    }


    private TextButton createButtonBaile(){
        TextButton buttonConfig = createTextButton("BaileScreen",
                (text.getX() - 50), (text.getY() - 150));
        buttonConfig.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Baile(J1, J2, main));
                dispose();
            }
        });
        return buttonConfig;
    }

    private TextButton createButtonSokiInvaders(){
        TextButton buttonQuit = createTextButton("SokiInvadersScreen",
                (text.getX() - 50),(text.getY() - 200));
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {return true;}
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new SokiInvadersScreen(main,J1,J2));
                dispose();
            }
        });
        return buttonQuit;
    }
    private TextButton createTextButton(String title, float posX, float posY){
        TextButton textButton = new TextButton(title, UI_SKIN);
        textButton.setPosition(posX, posY);
        int BUTTON_WIDTH = 200;
        int BUTTON_HEIGHT = 50;
        textButton.setWidth(BUTTON_WIDTH);
        textButton.setHeight(BUTTON_HEIGHT);
        return textButton;
    }
}

