/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomodulo01;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author evald
 */
public class Nave extends Movel {

    private int dx, dy;

    private final List<Missel> misseis;

    public Nave() {
        ImageIcon referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/nave2.png"));
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getHeight(null);

        misseis = new ArrayList<>();

        this.x = 100;
        this.y = 100;
    }

    public void mexer() {
        x += dx;
        y += dy;

        if (this.x < 1) {
            this.x = 1;
        }

        if (this.x > 462) {
            this.x = 462;
        }

        if (this.y < 1) {
            this.y = 1;
        }

        if (this.y > 490) {
            this.y = 490;
        }
    }

    public void atira() {
        if (Fase.contT < 15) {
            this.misseis.add(new Missel(this.x + this.largura, this.y + this.altura - 60));
        } else {
            this.misseis.add(new Missel(this.x + this.largura - 60, this.y + this.altura - 90));
            this.misseis.add(new Missel(this.x + this.largura - 60, this.y + this.altura - 30));
        } if (Fase.contT >= 30) {
            this.misseis.add(new Missel(this.x + this.largura, this.y + this.altura - 60));
            this.misseis.add(new Missel(this.x + this.largura - 60, this.y + this.altura - 90));
            this.misseis.add(new Missel(this.x + this.largura - 60, this.y + this.altura - 30));
        }

    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            atira();
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = -1;

        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 1;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = -1;

        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;

        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;

        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;

        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;

        }
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isVisivel() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    @Override
    public void setVisivel(boolean visible) {
        this.visible = visible;
    }

    public List<Missel> getMisseis() {
        return misseis;
    }

    /**
     *
     * @return
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     *
     * @return
     */
    @Override
    public Image getImagem() {
        return imagem;
    }

    @Override
    public int getAltura() {
        return altura;
    }

    @Override
    public int getLargura() {
        return largura;
    }

}
