/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomodulo01;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


/**
 *
 * @author evald
 */
public class Inimigo extends Movel {

    private static final int LARGURA_TELA = 600;
    private static final int VELOCIDADE = 1;

    static int cont = 0;
    static int contCH = 0;
    static int vida = 3;
    static int vidaCH = 3;

    public Inimigo(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon referencia;
        if ((Fase.fase == 5 || Fase.fase == 10) && contCH == 0) {
            referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/chefao.png"));
            contCH = 1;
        } else if (contCH == 1 && (Fase.fase == 5 || Fase.fase == 10)) {
            referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/inimigo_2.png"));
            System.out.println(referencia.getClass().toString());
        } else {
            if (cont++ % 3 == 0) {
                referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/inimigo.png"));
            } else {
                referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/inimigo_1.png"));
            }

        }

        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        visible = true;
    }

    /**
     *
     */
    public void mexer() {

        if (this.x < 0) {
            this.x = LARGURA_TELA;
            vida--;
            Fase.contT = 0;

        } else {
            this.x -= VELOCIDADE;
        }
    }

    /**
     *
     * @return
     */
   

    /**
     *
     * @return
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     *
     * @return
     */
    @Override
    public Image getImagem() {
        return imagem;
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

    @Override
    public int getAltura() {
        return altura;
    }

    @Override
    public int getLargura() {
        return largura;
    }

    @Override
    public boolean isVisivel() {
        return visible;
    }

    @Override
    public void setVisivel(boolean visivel) {
        this.visible = visivel;
    }

}
