package com.ballsteam.sokiduels.minigames.sokiDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bala { //TODO: RENAME ALL THE VARIABLES AND METHODS TO ENGLISH
    Sprite bulletSprite;
    Vector2 posBullet;
    int dirrecion;

    public Bala(Vector2 vector2,int dirrecion) {
        // Inicialización de la bala y su sprite.
        bulletSprite = new Sprite(new Texture(Gdx.files.internal("sokidefense/laserBullet.png")));
        bulletSprite.setScale(0.5f);
        bulletSprite.rotate(90*dirrecion);
        this.posBullet = vector2;
        this.dirrecion = dirrecion;
    }

    public void motion(int dirrecion) {
        switch (dirrecion) {
            case 0 -> posBullet.y += Gdx.graphics.getDeltaTime() * 200;
            case 1 -> posBullet.x -= Gdx.graphics.getDeltaTime() * 200;
            case 2 -> posBullet.y -= Gdx.graphics.getDeltaTime() * 200;
            case 3 -> posBullet.x += Gdx.graphics.getDeltaTime() * 200;
        }

    }

    public void draw(SpriteBatch batch) {
        // Método para dibujar la bala.
        motion(dirrecion);
        bulletSprite.setPosition(posBullet.x, posBullet.y);
        bulletSprite.draw(batch);
    }
}
