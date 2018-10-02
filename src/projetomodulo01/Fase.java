/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomodulo01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author evald
 */
public class Fase extends JPanel implements ActionListener {

    /**
     * Serial gerado automaticamente apenas para efeito de serialização
     */
    private static final long serialVersionUID = -5079021684583630134L;

    private int pontos;

    private int recorde;

    static int fase = 1;

    static int contT = 0;

    private int ContVida = 0;

    private int VidaCH = 100;

    private Image fundo;

    private Nave nave;

    static int i = 1;

    private Timer timer;

    private ArrayList<Inimigo> inimigos;

    public boolean emJogo;

    private int[][] coordenadas = {{2380, 29}, {1380, 89},
    {780, 109}, {580, 139}, {880, 239}, {790, 259},
    {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
    {920, 200}, {900, 259}, {660, 50}, {650, 200}, {650, 300}};

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        ImageIcon referencia = new ImageIcon(Main.class.getResource("/projetomodulo01/fundo.gif"));
        fundo = referencia.getImage();
        nave = new Nave();

        emJogo = true;
        inicializarInimigos();

        timer = new Timer(6, this);
        timer.start();
    }

    public void inicializarInimigos() {
        inimigos = new ArrayList<>();
        i = 1;
        for (int[] coordenada : coordenadas) {
            inimigos.add(new Inimigo(coordenada[0], coordenada[1]));
 
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        if (emJogo) {
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            ArrayList<Missel> misseis = (ArrayList<Missel>) nave.getMisseis();

            for (Missel missei : misseis) {
                Missel m = (Missel) missei;
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (Inimigo in : inimigos) {
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
    
            graficos.setColor(Color.white);
            graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);
            graficos.setColor(Color.white);
            graficos.drawString("PONTOS: " + pontos, 5, 30);
            graficos.setColor(Color.white);
            graficos.drawString("FASE: " + fase, 300, 25);
            graficos.drawString("VIDA: " + Inimigo.vida, 5, 45);
            if (fase == 5) {
                graficos.drawString("VIDA Chefão: " + VidaCH, 580, 45);
            }

        } else {
            ImageIcon fimJogo = new ImageIcon(Main.class.getResource("/projetomodulo01/Game_Over_1.jpg"));
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
            if (recorde < pontos) {
                recorde = pontos;
            }
            graficos.setColor(Color.white);
            graficos.drawString("RECORDE: " + recorde, 320, 60);
            graficos.drawString("PONTOS: " + pontos, 325, 40);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inimigos.isEmpty()) {
            fase++;
            inicializarInimigos();
        }

        ArrayList<Missel> misseis = (ArrayList<Missel>) nave.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {
            Missel m = (Missel) misseis.get(i);

            if (m.isVisible()) {
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }

        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo in = inimigos.get(i);

            if (in.isVisible()) {
                in.mexer();
            } else {
                inimigos.remove(i);
            }
        }

        nave.mexer();
        checarColisoes();
        repaint();

    }

    public void checarColisoes() {
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;
        Rectangle formaChefao;

        for (Inimigo tempInimigo : inimigos) {
            formaInimigo = tempInimigo.getBounds();

            if (formaNave.intersects(formaInimigo) || Inimigo.vida == 0) {
                nave.setVisivel(false);
                tempInimigo.setVisible(false);
                contT = 0;
                emJogo = false;

            }
        }

        ArrayList<Missel> misseis = (ArrayList<Missel>) nave.getMisseis();

        for (Missel tempMissel : misseis) {
            formaMissel = tempMissel.getBounds();

            for (Inimigo tempInimigo : inimigos) {
                formaInimigo = tempInimigo.getBounds();

                if (formaMissel.intersects(formaInimigo)) {
                    if (fase != 5) {
                        tempInimigo.setVisible(false);
                        tempMissel.setVisible(false);
                        pontos += 100;
                        contT++;
                        ContVida++;
                        if (ContVida == 20) {
                            if (Inimigo.vida <= 5) {
                                Inimigo.vida += 1;
                            } else {
                                ContVida = 0;
                            }
                        }
                    } else {
                        if (inimigos.size() > 1) {
                            tempInimigo.setVisible(false);
                            tempMissel.setVisible(false);
                            pontos += 100;
                            contT++;
                            ContVida++;
                            if (ContVida == 20) {
                                if (Inimigo.vida <= 5) {
                                    Inimigo.vida += 1;
                                } else {
                                    ContVida = 0;
                                }
                            }
                        } else if (inimigos.size() == 1) {
                            VidaCH--;
                            tempMissel.setVisible(false);
                            if (VidaCH == 0) {
                                tempInimigo.setVisible(false);

                                pontos += 200;
                                contT++;
                                ContVida++;
                                if (ContVida == 20) {
                                    if (Inimigo.vida <= 5) {
                                        Inimigo.vida += 1;
                                    } else {
                                        ContVida = 0;
                                    }
                                }
                            }
                        }
                    }
                }

                //}
            }
        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                emJogo = true;
                pontos = 0;
                nave = new Nave();
                inicializarInimigos();
                fase = 1;
                Inimigo.vida = 3;
                VidaCH = 100;
                Inimigo.contCH = 0;

            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
            nave.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.keyReleased(e);
        }
    }
}
