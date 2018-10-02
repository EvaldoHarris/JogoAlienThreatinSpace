/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomodulo01;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author evald
 */
public abstract class Movel {

	protected int x, y,vida;
	
	protected int altura, largura;
	
	protected boolean visible;
	
	protected  Image imagem;
	
	
	public abstract Rectangle getBounds();
	
	public abstract int getX();
        
        //public abstract int getVida();
	
	public abstract int getY();
	
	public abstract int getAltura();
	
	public abstract int getLargura();
	
	public abstract boolean isVisivel();
	
	public abstract void setVisivel(boolean visivel);
	
	public abstract Image getImagem();
}