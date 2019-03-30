package texpeer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class ConcordanceFrame {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FileReadHelper fileReadHelper = new FileReadHelper();
//        System.out.println(fileReadHelper.convertTextFileToString("fileread.doc"));
//        System.out.println(fileReadHelper.readFileAsStream("primer.txt"));
        Expression.fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Expression.fr.setSize(900, 400);
        Expression.fr.setLocationRelativeTo(null);
        //   Expression.fr.setBounds(400, 200, 680, 420);
        Expression.fr.getContentPane().setBackground(Color.CYAN);
        ImageIcon img = new ImageIcon("icon_eye.png");
        Expression.fr.setIconImage(img.getImage());

        Expression.fr.setResizable(true);
        Expression.fr.setVisible(true);

        JPanel panel = new JPanel();
        JPanel mainpanel2 = new JPanel(new GridLayout(0, 1));
        JPanel mainpanelConc = new JPanel(new BorderLayout());
        JPanel mainpanelConcHalf1 = new JPanel(new BorderLayout());
        JPanel mainpanelConcHalf2 = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridLayout(0, 1));

        Expression.fr.add(panel);
        Expression.fr.add(mainpanel2);
        Expression.fr.add(mainpanelConc);
        mainpanelConc.setLayout(new BoxLayout ( mainpanelConc, BoxLayout.X_AXIS));
        mainpanelConcHalf1.setSize(200, 400);
        mainpanelConcHalf2.setSize(800, 400);
        mainpanelConcHalf1.setBackground(Color.GREEN);
        mainpanelConcHalf2.setBackground(new Color(0,130,220));
        mainpanelConc.add(mainpanelConcHalf1);
        mainpanelConc.add(mainpanelConcHalf2);

        panel1.setMaximumSize( new Dimension(1000,100));
        panel2.setSize(800, 400);


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panel1);
        panel.add(panel2);

        final JRadioButton radioButtonCase2 = new JRadioButton(Expression.strCaseMatters);
        final JRadioButton radioButtonIsHyphenSeparator = new JRadioButton(Expression.strIsHyphenSeparator);
        final JRadioButton radioButtonCase = new JRadioButton(Expression.strCaseMatters);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 1;
        c1.gridy = 0;
        radioButtonIsHyphenSeparator.setBackground(Color.lightGray);
        panel1.add(radioButtonIsHyphenSeparator, c1);


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 0;
        radioButtonCase.setBackground(Color.lightGray);
        panel1.add(radioButtonCase, c);




        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 3;
        d.gridy = 1;
        d.fill = 1;

        final JButton butSearch = new JButton(Expression.strSearch);
        butSearch.setForeground(new Color(0, 0, 253));
        butSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GridBagConstraints f = new GridBagConstraints();
        f.gridx = 3;
        f.gridy = 2;
        panel1.add(butSearch, f);

        final JButton butCalcRelibl = new JButton(Expression.checkValidity);
        butSearch.setForeground(new Color(0, 0, 253));

        GridBagConstraints f1 = new GridBagConstraints();
        f1.gridx = 3;
        f1.gridy = 4;
        panel1.add(butCalcRelibl, f1);

        panel1.add(Expression.textFieldSearch, d);
        panel1.add(Expression.combTemas);
        panel2.add(Expression.textArea);

//        Expression.textFieldSearch.addMouseListener(new MouseListener(){
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//              //  System.out.println("Clicked");
//                JRootPane rootPane2 = SwingUtilities.getRootPane(Expression.textFieldSearch);
//                rootPane2.setDefaultButton(butSearch);
//                if (Expression.textFieldSearch.getText().equals(Expression.strEnterYourText)) {
//                    Expression.textFieldSearch.setText("");
//                    Expression.combTemas2.setSelectedIndex(Expression.combTemas.getSelectedIndex()); //synchronyze the two combo boxes
//                    radioButtonCase2.setSelected(radioButtonCase.isSelected()); //synchronyze the two radioButtonsr
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                JRootPane rootPane2 = SwingUtilities.getRootPane(Expression.textFieldSearch);
//                rootPane2.setDefaultButton(butSearch);
//            }
//        });

        Expression.textFieldSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //  System.out.println("Clicked");
                JRootPane rootPane2 = SwingUtilities.getRootPane(Expression.textFieldSearch);
                rootPane2.setDefaultButton(butSearch);
                if (Expression.textFieldSearch.getText().equals(Expression.strEnterYourText)) {
                    Expression.textFieldSearch.setText("");
                    Expression.combTemas2.setSelectedIndex(Expression.combTemas.getSelectedIndex()); //synchronyze the two combo boxes
                    radioButtonCase2.setSelected(radioButtonCase.isSelected()); //synchronyze the two radioButtonsr
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                JRootPane rootPane2 = SwingUtilities.getRootPane(Expression.textFieldSearch);
                rootPane2.setDefaultButton(butSearch);
            }
        });


        JScrollPane scroll = new JScrollPane(Expression.textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel2.add(scroll);

        Expression.textFieldSearch.setText(Expression.strEnterYourText);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Expression.textFieldSearch.setBorder(border);
        // Expression.textFieldSearch.setColumns(70);


        // Expression.textArea.setText("Введіть вираз для пошуку");
        Expression.textArea.setBackground(new Color(190, 200, 100));
//        Box box1 = new Box(BoxLayout.Y_AXIS);
//        box1.add(Box.createVerticalStrut(105));
//        box1.setBackground(Color.CYAN);
//        box1.setOpaque(true);

        GridBagConstraints e = new GridBagConstraints();
        e.gridx = 3;
        e.gridy = 2;
        // panel1.add(box1, e);


        Expression.tabby.addTab("Конкорданс", panel);
        Expression.tabby.addTab("Інфо", mainpanel2);
        Expression.tabby.addTab("Список словоформ", mainpanelConc);
        Expression.fr.add(Expression.tabby);

        panel1.setBackground(Color.lightGray);
        JTextArea textAbout = new JTextArea();
        textAbout.setLineWrap(true);
        textAbout.setWrapStyleWord(true); // eto dlia perenosa texta

        mainpanel2.add(textAbout); // eto dlia perenosa texta


        textAbout.append(Expression.strProgrDescription+"\n\n");
        textAbout.append(Expression.strTextAbout);

        // mainpanelConc.setLayout(new GridBagLayout());
        //      mainpanelConcHalf1.setLayout(new GridBagLayout());
        //     mainpanelConcHalf2.setLayout(new BoxLayout(mainpanelConcHalf2, BoxLayout.Y_AXIS));
        mainpanelConcHalf2.setLayout(new GridBagLayout());

        JTextArea ConcordList = new JTextArea(20,70);
        ConcordList.setBackground(new Color(170, 180, 255));
        //   GridBagConstraints textConc = new GridBagConstraints();
        //  textConc.gridx = 1;
        //   textConc.gridy = 3;
        //     textConc.fill = 26;
        mainpanelConcHalf1.add(ConcordList);
        JScrollPane scrollPane = new JScrollPane(ConcordList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainpanelConcHalf1.add(scrollPane);

        textAbout.setLineWrap(true);
        textAbout.setWrapStyleWord(true);


        JButton retrConcBtn = new JButton(Expression.strRetrieveConcordance);
        retrConcBtn.setBackground(new Color(170, 80, 10));
        GridBagConstraints getConcLayout = new GridBagConstraints();
        GridBagConstraints combLayout = new GridBagConstraints();
        //    retrButt.fill = GridBagConstraints.HORIZONTAL;
        //  getConcLayout.weightx = 0.5;
        getConcLayout.gridx = 2;
        getConcLayout.gridy = 12;
        mainpanelConcHalf2.add(retrConcBtn, getConcLayout);

        GridBagConstraints radioButLayoyt = new GridBagConstraints();
        radioButLayoyt.gridx = 2;
        radioButLayoyt.gridy = 8;

        mainpanelConcHalf2.add(radioButtonCase2, radioButLayoyt);

        combLayout.weightx = 0.5;
        combLayout.gridx = 2;
        combLayout.gridy = 10;
        Expression.combTemas2.setSize(30,70);

        mainpanelConcHalf2.add(Expression.combTemas2, combLayout);



        retrConcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Expression.combTemas.setSelectedIndex(Expression.combTemas2.getSelectedIndex());
                    Expression.getFilesArray();
                   // Expression.getAllWords();
                    ConcordList.setText("");
                    Expression.getSortedWordsAndCount(ConcordList);
                } catch (IOException ignored) {
                    JOptionPane.showMessageDialog(mainpanel2, "No files FOUND");
                }
            }
        });
radioButtonCase.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Expression.caseMatters=radioButtonCase.isSelected();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
});
        radioButtonCase2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                radioButtonCase.setSelected(radioButtonCase2.isSelected()); //synchronyze the two combo boxes
                Expression.caseMatters=radioButtonCase.isSelected();

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });


        // Expression.fr.setResizable(true);
        Expression.fr.setVisible(true);
        // String ExpressionToFind = JOptionPane.showInputDialog(null, Expression.panel, "Введіть вираз для пошуку");
        long startTime = System.currentTimeMillis();// DLIA IZMERENIA VREMENI DEISTVIA PROGRAMMY




            butSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Expression.combTemas2.setSelectedIndex(Expression.combTemas.getSelectedIndex());
                radioButtonCase2.setSelected(radioButtonCase.isSelected());
                if (Expression.textArea.getText() == null)
{
    JOptionPane.showMessageDialog(null, Expression.strEnterAnExpression);

    return;
}
                    Expression.getFilesArray();
                   // Expression.getAllWords();
                String expressionToFind = Expression.textFieldSearch.getText();
                long startTime = System.currentTimeMillis();// DLIA IZMERENIA VREMENI DEISTVIA PROGRAMMY
                Expression.textArea.setText("");
                if (radioButtonCase.isSelected()) Expression.caseMatters = true;

                expressionToFind= Expression.formatQuery(expressionToFind, Expression.caseMatters);


                Expression.printInWindow(Expression.getListOfResultsByQuery(expressionToFind, Expression.caseMatters));


                long timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("Программа выполнялась " + timeSpent + " миллисекунд");

            }
        });

        butCalcRelibl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expression.calculateReliability();
            }
        });

    }
}