package bsu_rfe_kirilenko_lab_6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
// Константы, задающие размер окна приложения, если оно
// не распахнуто на весь экран
private static final int WIDTH = 700;
private static final int HEIGHT = 500;
private JMenuItem pauseMenuItem;
private JMenuItem resumeMenuItem;
// Поле, по которому прыгают мячи
private Field field = new Field();
private BouncingBall ball1;
// Конструктор главного окна приложения
public MainFrame() {
super("Программирование и синхронизация потоков");
setSize(WIDTH, HEIGHT);
Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
setLocation((kit.getScreenSize().width - WIDTH)/2,
(kit.getScreenSize().height - HEIGHT)/2);
// Установить начальное состояние окна развѐрнутым на весь экран
setExtendedState(MAXIMIZED_BOTH);
//Создать меню
JMenuBar menuBar = new JMenuBar();
setJMenuBar(menuBar);
JMenu ballMenu = new JMenu("Мячи");
Action addBallAction = new AbstractAction("Добавить мяч")
{
public void actionPerformed(ActionEvent event) {
field.addBall();
if (!pauseMenuItem.isEnabled() &&
!resumeMenuItem.isEnabled()) {
//Ни один из пунктов меню не являются
//доступными - сделать доступным "Паузу"
pauseMenuItem.setEnabled(true);
}
}
};

menuBar.add(ballMenu);
ballMenu.add(addBallAction);
JMenu controlMenu = new JMenu("Управление");
menuBar.add(controlMenu);
Action pauseAction = new AbstractAction("Приостановить движение"){
public void actionPerformed(ActionEvent event) {
field.pause();
pauseMenuItem.setEnabled(false);
resumeMenuItem.setEnabled(true);
}
};
pauseMenuItem = controlMenu.add(pauseAction);
pauseMenuItem.setEnabled(false);
Action resumeAction = new AbstractAction("Возобновить движение") {
public void actionPerformed(ActionEvent event) {
field.resume();
pauseMenuItem.setEnabled(true);
resumeMenuItem.setEnabled(false);
}
};
resumeMenuItem = controlMenu.add(resumeAction);
resumeMenuItem.setEnabled(false);
JButton buttonPluse = new JButton("+");
buttonPluse.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ev) {
	field.pluse();

}
});
pauseMenuItem.setEnabled(false);
JButton buttonMinuse = new JButton("-");
buttonMinuse.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ev) {
	field.minuse();
}
});
Box hboxButtons1 = Box.createHorizontalBox();
hboxButtons1.setBounds(250, 250, 50, 50);
hboxButtons1.add(Box.createHorizontalGlue());
hboxButtons1.add(buttonPluse);
hboxButtons1.add(Box.createHorizontalStrut(100));
hboxButtons1.add(buttonMinuse);
hboxButtons1.add(Box.createHorizontalGlue());
hboxButtons1.setBorder(
BorderFactory.createLineBorder(Color.BLACK));
Box contentBox = Box.createVerticalBox();
contentBox.add(field);
contentBox.add(hboxButtons1);
getContentPane().add(contentBox, BorderLayout.CENTER);
}
//Главный метод приложения
public static void main(String[] args) {
//Создать и сделать видимым главное окно приложения
MainFrame frame = new MainFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}
