package com.ballsteam.sokiduels.minigames.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.ballsteam.sokiduels.Screens.AbstractScreen;
import com.ballsteam.sokiduels.SokiDuels;

import java.util.stream.IntStream;

public class SokiInvadersScreen extends AbstractScreen {
    Spaceship spaceShip;
    Spaceship spaceShip2;
    Sprite fondo;
    Array<Alien> aliens;
    Array<Alien> soki;
    int direccion;
    long lastDropTime;

    public SokiInvadersScreen(SokiDuels main) {
        super(main);
        spaceShip = new Spaceship(Input.Keys.RIGHT, Input.Keys.LEFT, Input.Keys.UP,true);
        spaceShip2 = new Spaceship(Input.Keys.D, Input.Keys.A, Input.Keys.W,false);
        aliens = new Array<>();
        direccion = 1;
        fondo = new Sprite(new Texture("fondo.png"));
        crearMatriz(aliens);
        soki = new Array<>();
        spawnSoki();
    }
    @Override
    public void buildStage() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.batch.begin();
        fondo.draw(main.batch);
        caidaAliens();
        colisionBullet();
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnSoki();
        drawOnscreenText();
        spaceShip.draw(main.batch);
        spaceShip2.draw(main.batch);
        main.batch.end();
    }

    @Override
    public void dispose() {
        spaceShip.dispose();
        soki.forEach(Alien::dispose);
        fondo.getTexture().dispose();
    }

    public static void crearMatriz(Array<Alien> aliens) {
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 10).forEach(j -> aliens.add(new Alien(new Vector2(120 + (j * 57), 180 + (i * 57))))));
    }
    public void caidaAliens(){
        soki.forEach(drop -> {
            drop.draw(main.batch);
            drop.posAlien.x += 150 * Gdx.graphics.getDeltaTime();
            if (drop.posAlien.y + 64 < 0) soki.removeValue(drop, true);
            colisionBulletAlien(drop, spaceShip);
            colisionBulletAlien(drop, spaceShip2);
        });
    }

    public void colisionBulletAlien(Alien drop, Spaceship spaceShip) {
        spaceShip.bullets.forEach(bullet -> {
            if (bullet.bulletSprite.getBoundingRectangle().overlaps(drop.alienSprite.getBoundingRectangle())) {
                soki.removeValue(drop, true);
                drop.dispose();
                spaceShip.bullets.removeValue(bullet, true);
                spaceShip.score++;
            }
        });
    }

    public void colisionBullet(){
        spaceShip.bullets.forEach(bullet -> {
                if (bullet.bulletSprite.getBoundingRectangle().overlaps(spaceShip2.shipSprite.getBoundingRectangle())) {
                    spaceShip.bullets.removeValue(bullet, true);
                    spaceShip2.score--;
                }
        });
        spaceShip2.bullets.forEach(bullet -> {
                if (bullet.bulletSprite.getBoundingRectangle().overlaps(spaceShip.shipSprite.getBoundingRectangle())) {
                    spaceShip2.bullets.removeValue(bullet, true);
                    spaceShip.score--;
                }
        });
    }
    private void drawOnscreenText() {
        main.font.draw(main.batch, "Score: " + spaceShip.score, 15, 20);
        main.font.draw(main.batch, "Score: " + spaceShip2.score, 720, 460);
    }
    private void spawnSoki() {
        Alien enemy = new Alien(new Vector2(0, MathUtils.random(72, 370)));
        soki.add(enemy);
        lastDropTime = TimeUtils.nanoTime();
    }
}