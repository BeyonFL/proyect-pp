package com.ballsteam.sokiduels.minigames.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    final Vector2 bullet;
    final Sprite bulletSprite = new Sprite(new Texture(Gdx.files.internal("sokiInvaders/laserBullet.png")));
    final boolean isPlayerOne;

    public Bullet(boolean isPlayerOne) {
        // Inicialización de la bala y su sprite.
        if (!isPlayerOne) bulletSprite.flip(false,true);
        bulletSprite.setScale(0.69f);
        bullet = new Vector2(0,0);
        this.isPlayerOne = isPlayerOne;
    }

    public void motion() {
        if (isPlayerOne){
            bullet.y += Gdx.graphics.getDeltaTime() * 400;
        } else {
            bullet.y -= Gdx.graphics.getDeltaTime() * 400;
        }
    }

    public void draw(SpriteBatch batch) {
        // Método para dibujar la bala.
        bulletSprite.setPosition(bullet.x, bullet.y);
        bulletSprite.draw(batch);
        motion();
    }
}
