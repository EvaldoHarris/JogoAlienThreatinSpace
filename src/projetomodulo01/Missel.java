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
public class Missel extends Movel {

	private static final int LARGURA_TELA = 1000;
	private static final int VELOCIDADE = 2;

	public Missel(int x, int y) {
		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/missel.png"));
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		visible = true;
	}

	public void mexer() {
		this.x += VELOCIDADE;
		if (this.x > LARGURA_TELA) {
			visible = false;
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
		visible = visivel;
	}

    
}
