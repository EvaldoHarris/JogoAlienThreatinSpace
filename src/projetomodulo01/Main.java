/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomodulo01;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import static sun.misc.ClassFileTransformer.add;

/**
 *
 * @author evald
 */
public class Main extends JFrame implements Runnable { // <---- precisa colocar o JFrame

    /**
     * Serial gerado automaticamente apenas para efeito de serialização
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Main() {
        construirMenuBar();
        construirFase();
        configurarTela();

        createBufferStrategy(2);
        Thread t = new Thread((Runnable) this);
        t.start();

        this.setIconImage(new ImageIcon(getClass().getResource("/projetomodulo01/nave2.png")).getImage()); 

    }

    public static void main(String[] args) {
        new Main();

    }

    private JMenuBar construirMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(new LineBorder(Color.red));
        JMenu menu = new JMenu("Opções");
        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Jogo Modulo 1, Aluno Evaldo Harris RA: 163247", "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem sair = new JMenuItem("Sair ( Esc )");
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }

        });

        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(sair);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        return menuBar;
    }

    private JPanel construirFase() {
        Fase fase = new Fase();
        add(fase);
        return fase;
    }

    private void configurarTela() {
        setSize(750, 620);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Alien Threat in Space");
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
