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
import com.ballsteam.sokiduels.minigames.spaceinvaders.SokiInvadersScreen;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;


public class MenuScreen extends AbstractScreen {
    private final Label text = new Label("Proyecto PP", new Skin(Gdx.files.internal("ui/uiskin.json")));
    private final Skin UI_SKIN = new Skin(Gdx.files.internal("ui/uiskin.json"));

    public MenuScreen(SokiDuels main) throws XInputNotLoadedException {
        super(main);
    }
    @Override
    public void render(float delta) {
        super.render(delta);
        main.batch.begin();
        main.batch.end();
    }

    @Override
    public void buildStage() {

        text.setPosition(getWidth() / 2f, 440, Align.center);
        addActor(text);

        //buttonPlay
        TextButton buttonPlay = createButtonPlay();
        addActor(buttonPlay);

        //buttonConfig
        TextButton buttonConfig = createButtonConfig();
        addActor(buttonConfig);

        //buttonQuit
        TextButton buttonQuit = createButtonQuit();
        addActor(buttonQuit);

    }


    private TextButton createButtonPlay(){
        TextButton buttonPlay = createTextButton("CachipunScreen",
                (text.getX() - 50),(text.getY() - 100));
        buttonPlay.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new CachipunScreen(main));
                dispose();
            }
        });
        return buttonPlay;
    }


    private TextButton createButtonConfig(){
        TextButton buttonConfig = createTextButton("DanceScreen",
                (text.getX() - 50), (text.getY() - 150));
        buttonConfig.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Baile(main));
                dispose();
            }
        });
        return buttonConfig;
    }

    private TextButton createButtonQuit(){
        TextButton buttonQuit = createTextButton("SokiInvadersScreen",
                (text.getX() - 50),(text.getY() - 200));
        buttonQuit.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {return true;}
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new SokiInvadersScreen(main,main.player1,main.player2));
                dispose();
            }
        });
        return buttonQuit;
    }
    private TextButton createTextButton(String title, float posX, float posY){
        TextButton textButton = new TextButton(title, UI_SKIN);
        textButton.setPosition(posX, posY);
        int BUTTON_WIDTH = 200;
        textButton.setWidth(BUTTON_WIDTH);
        int BUTTON_HEIGHT = 40;
        textButton.setHeight(BUTTON_HEIGHT);
        return textButton;
    }

}

