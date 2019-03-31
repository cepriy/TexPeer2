package texpeer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.TreeMap;


public class ConcordanceFrame {
    public void run() {
        Expression expression = new Expression();
        expression.fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        expression.fr.setSize(900, 400);
        expression.fr.setLocationRelativeTo(null);
        expression.fr.getContentPane().setBackground(Color.CYAN);
        ImageIcon img = new ImageIcon("icon_eye.png");
        expression.fr.setIconImage(img.getImage());

        expression.fr.setResizable(true);
        expression.fr.setVisible(true);

        JPanel panel = new JPanel();
        JPanel mainpanel2 = new JPanel(new GridLayout(0, 1));
        JPanel mainpanelConc = new JPanel(new BorderLayout());
        JPanel mainpanelConcHalf1 = new JPanel(new BorderLayout());
        JPanel mainpanelConcHalf2 = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridLayout(0, 1));

        expression.fr.add(panel);
        expression.fr.add(mainpanel2);
        expression.fr.add(mainpanelConc);
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
        final JRadioButton radioButtonIsHyphenSeparator = new JRadioButton(expression.strIsHyphenSeparator);
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

        panel1.add(expression.textFieldSearch, d);
        panel1.add(expression.combTemas);
        panel2.add(expression.textArea);

        expression.textFieldSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JRootPane rootPane2 = SwingUtilities.getRootPane(expression.textFieldSearch);
                rootPane2.setDefaultButton(butSearch);
                if (expression.textFieldSearch.getText().equals(Expression.strEnterYourText)) {
                    expression.textFieldSearch.setText("");
                    expression.combTemas2.setSelectedIndex(expression.combTemas.getSelectedIndex()); //synchronyze the two combo boxes
                    radioButtonCase2.setSelected(radioButtonCase.isSelected()); //synchronyze the two radioButtonsr
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                JRootPane rootPane2 = SwingUtilities.getRootPane(expression.textFieldSearch);
                rootPane2.setDefaultButton(butSearch);
            }
        });


        JScrollPane scroll = new JScrollPane(expression.textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel2.add(scroll);
        expression.textFieldSearch.setText(Expression.strEnterYourText);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        expression.textFieldSearch.setBorder(border);
        expression.textArea.setBackground(new Color(190, 200, 100));
        GridBagConstraints e = new GridBagConstraints();
        e.gridx = 3;
        e.gridy = 2;


        Expression.tabby.addTab("Конкорданс", panel);
        Expression.tabby.addTab("Інфо", mainpanel2);
        Expression.tabby.addTab("Список словоформ", mainpanelConc);
        expression.fr.add(Expression.tabby);

        panel1.setBackground(Color.lightGray);
        JTextArea textAbout = new JTextArea();
        textAbout.setLineWrap(true);
        textAbout.setWrapStyleWord(true); // eto dlia perenosa texta

        mainpanel2.add(textAbout); // eto dlia perenosa texta


        textAbout.append(Expression.strProgrDescription+"\n\n");
        textAbout.append(Expression.strTextAbout);
        mainpanelConcHalf2.setLayout(new GridBagLayout());

        JTextArea wordsListTextArea = new JTextArea(20,70);
        wordsListTextArea.setBackground(new Color(170, 180, 255));
        mainpanelConcHalf1.add(wordsListTextArea);
        JScrollPane scrollPane = new JScrollPane(wordsListTextArea);
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
        expression.combTemas2.setSize(30,70);

        mainpanelConcHalf2.add(expression.combTemas2, combLayout);
        retrConcBtn.addActionListener(e13 -> {
            expression.combTemas.setSelectedIndex(expression.combTemas2.getSelectedIndex());
            expression.getFilesArray();
            wordsListTextArea.setText("");
            TreeMap<String, Integer> sortedWordsAndCount = expression.getSortedWordsAndCount();
            expression.drawResult(wordsListTextArea, sortedWordsAndCount);
            javax.swing.SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition( new Point(0, 0) ));
        });

        radioButtonCase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                expression.caseMatters=radioButtonCase.isSelected();
            }
        });

        radioButtonCase2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                radioButtonCase.setSelected(radioButtonCase2.isSelected()); //synchronyze the two combo boxes
                expression.caseMatters=radioButtonCase.isSelected();
            }
        });
        expression.fr.setVisible(true);
        long startTime = System.currentTimeMillis();// DLIA IZMERENIA VREMENI DEISTVIA PROGRAMMY
            butSearch.addActionListener(e1 -> {
                expression.combTemas2.setSelectedIndex(expression.combTemas.getSelectedIndex());
                radioButtonCase2.setSelected(radioButtonCase.isSelected());
                if (expression.textArea.getText() == null) {
                    JOptionPane.showMessageDialog(null, Expression.strEnterAnExpression);
                    return;
                }
                expression.getFilesArray();
                String expressionToFind = expression.textFieldSearch.getText();
                long startTime1 = System.currentTimeMillis();// DLIA IZMERENIA VREMENI DEISTVIA PROGRAMMY
                expression.textArea.setText("");
                if (radioButtonCase.isSelected()) {
                    expression.caseMatters = true;
                }
                expressionToFind = expression.formatQuery(expressionToFind, expression.caseMatters);


                expression.printInWindow(expression.getListOfResultsByQuery(expressionToFind, expression.caseMatters));


                long timeSpent = System.currentTimeMillis() - startTime1;
                System.out.println("Программа выполнялась " + timeSpent + " миллисекунд");

            });

        butCalcRelibl.addActionListener(e12 -> expression.calculateReliability());

    }
}
