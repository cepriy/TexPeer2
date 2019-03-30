package texpeer;
/**
 * Created by ?????? on 10.07.2015.
 * <p/>
 * Created by ?????? on 06.07.2015.
 * <p/>
 * Created by ?????? on 06.07.2015.
 */
/**
 *
 *В следующий версии нужно наладить правильный украинский алфавит, добавить жанры, сделать возможность сортировки слов не только
 * по алфавиту, но и по частоте!!! Для исследователей важнее список самых частых, самх редких словоформ, а не их алфавитный порядок.
 * Сделать опцию выбора языка (выпадающий список)
 * В списке слов добавить функцию поиска с подсветкой найденного слова. Для этого нужно вместо TextArea  поставить  EditorPane
 * Created by ?????? on 06.07.2015.
 *
 */

import main.java.TextFile;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;



class Expression {
    public static int CoincedNum = 0;
    public static boolean Joker = false;
    public static String userQuery="";
    public static File[] filesArray;
    public static int filesWithExpression = 0;
    public static float Medium = 0;
    public static String[] sCombTemas = {"Усі теми", "Філософія", "Українська художня література", "Історія", "Мовознавство", "Іспанська художня література", "Медицина", "Політологія", "Психологія", "Переклад", "Соціологія"};
    public static JComboBox<String> combTemas = new JComboBox<>(sCombTemas);

    public static JComboBox<String> combTemas2 = new JComboBox<>(sCombTemas);


    //constants
    public static final String strEnterYourText = "Введіть вираз для пошуку";
    public static final String strCaseMatters = "Враховувати регістр";
    public static final String strSearch = "Пошук";
    public static final String checkValidity = "Перевірити достовірність";
    public static final String strRetrieveConcordance = "Згенерувати конкорданс";
  public static final String  strEnterAnExpression="Ви не ввели жодного виразу";
  public static final String strTextAbout="Код: Фокін С.Б. і Іванов М.С." + "\n"+
          "Вибірка текстів: Іванов М.С., Фокін С.Б., Бакаржиєва І., Бендерець О.,"+
          "Бучака І., Ільїна О., Ісаєва М., Краснодзей В., Кладченко А., Лопатіна А., Мамукова К., Мудрик А., Демченко Л., Скиба В., " +
          "Ткачук О., Ярина Л., Гороховська Л., Гузенко Т., Дмитраш Я., Дембіцька К., Калініченко О., Левандовська К.-К., Медюк З., Остряніна В., Пустовійт А., " +
          "Савенкова Н., Чумаченко Д., Якименко Ю., Сім'ячко Д., Бурдук А., Глоба П., Загородько К., Кисіль М., Куць В., Ламська А.,  Никитіна К., Оборська А. " +
          "Ряднова А., Семенюшкіна А., Стрельнікова Д., Третяк М., Юнєєва В. " +
          "Корпус складається з 50 текстів українською, і 50 - іспанською мовами кінця ХХ і початку ХХІ століття, що класифіковані" +
          "за 10 різними темами. Кожен текст містить 10 000 слововживань" +
          "Перевірка достовірності полягає у порівнянні результату вибірки у кожному тексті з очікуваним результатом. Якщо вибірка достатня" +
          "і результат достовірний, згідно з теорією ймовірності, кожен результат вибірки не перевищує потрійне квадратичне відхилення.\n\n\n\n"+
          "Посилення на тексти, використані в програмі, містяться на сторінці завантажень. Усі тексти взято з відкритих джерел";

  public static final String strProgrDescription = "Програма-конкордансер ‘TexPeer 1.0’ призначена для опрацювання текстів," +
          " написаних природними мовами, алфавіт яких ґрунтується на латинських або кирилічних символах. Програма " +
          "уможливлює пошук у корпусі текстів з підрахунком статистичних показників; пошуковими виразами можуть бути " +
          "як окремі графічні слова, так і маски словоформ і виразів; у програмі по замовченню міститься корпус текстів " +
          "іспанської та української мов, обсяг кожного з яких 500 000 слововживань. Призначення програми: для досліджень " +
          "у філологічних науках; для перевірки коректності тих чи інших словоформ, висловлень, сполучень слів;" +
          " для викладання спецкурсів, пов’язаних з використанням комп’ютерних технологій у філологічній галузі.";

  public static final String strTotalProcesed = "Усього опрацьовано ";
  public static final String strTotalFound = "Усього знайдено ";
  public static final String strFilesGenitiv = " файлів";
  public static final String strYourVersionIsLimited = "Ви використовуєте обмежену версію";
   public static final String strMeanFreqPer10000 = "Середня частота на 10000 слововживань: ";
    public static final String strStandardDeviation =  "Середнє квадратичне відхилення: ";
    public static final String strTripleStandardDeviation= "Потрійне середнє квадратичне відхилення: ";
    public static final String strReliably = "Достовірно";
    public static final String strNonReliable = "НЕДОСТОВІРНО!!!";
    public static final String strTotalWordForms="Усього приблизно словоформ:";
    public static final String strFound = "знайдено";
    public static final String inFile = "У файлі";
    public static final String  strIsHyphenSeparator = "Вважати дефіс роздільником";
    public static boolean caseMatters = false;

    public static JFrame fr = new JFrame("TexPeer 1.0");

    public static JTabbedPane tabby = new JTabbedPane();

    public static JTextArea textArea = new JTextArea(20, 70);
    public static JTextField textFieldSearch = new JTextField(30);



    public static List<TextFile> textFileList = new ArrayList<>();

    public static final int CONTEXT_DEPTH = 60;
    public static final String ROMANIC_ALPHABET_SYMBOLS = "a-zA-ZáÁéÉúÚíÍóÓüÜАñÑ";
    public static final String CYRYLLIC_ALPHABET_SYMBOLS= "а-яА-ЯіІїЇґҐєЄ'’";
    public static final String NON_SEPARATE_CHARACTERS = "["+ROMANIC_ALPHABET_SYMBOLS+CYRYLLIC_ALPHABET_SYMBOLS+"]";
    public static final String SEPARATE_CHARACTERS = "[^"+ROMANIC_ALPHABET_SYMBOLS+CYRYLLIC_ALPHABET_SYMBOLS+"]";
   // public static final String NON_SEPARATE_WITH_PUNCTUATION = "[a-zA-ZáÁéÉúÚíÍóÓüÜАñÑа-яА-ЯіІїЇґҐєЄ'’.,:¡!¿ \"»«]";

    public static void printInWindow(List<String> result) {

        filesWithExpression = 0;

        for (TextFile file : textFileList) {
            if (file.getFoundInFile() > 0) filesWithExpression++;
        }

        Expression.CoincedNum=result.size();

        Expression.textArea.setText("");

        Expression.textArea.append(strTotalProcesed + filesArray.length + strFilesGenitiv + "\n");


        if (CoincedNum > 30) fr.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Expression.textArea.append(strTotalFound+ Expression.CoincedNum + " збігів " + userQuery + " в " + filesWithExpression + " файлах" + "\n");
        Medium = (float) Expression.CoincedNum / (float) textFileList.size();
        Expression.textArea.append("Середня частота на 10 000 слів: " + Medium + "\n");
        Expression.textArea.append("Середнє квадратичне відхилення: " + getStandardDeviation() + "\n");


        for (String res : result) {
            textArea.append(res + "\n");
        }


        fr.setVisible(true);

        result.clear();
    }

    private static String getPathRelativeToSubject() {
        String address = "";
        File f1 = new File("");
        if (combTemas.getSelectedItem() == "Філософія") address = f1.getAbsolutePath() + "//Basico//Filosofia";
        if (combTemas.getSelectedItem() == "Соціологія") address = f1.getAbsolutePath() + "//Basico//Sociologia";
        if (combTemas.getSelectedItem() == "Історія") address = f1.getAbsolutePath() + "//Basico//Historia";
        if (combTemas.getSelectedItem() == "Мовознавство") address = f1.getAbsolutePath() + "//Basico//Lengua";
        if (combTemas.getSelectedItem() == "Іспанська художня література") address = f1.getAbsolutePath() + "//Basico//Literarios en espanol";
        if (combTemas.getSelectedItem() == "Українська художня література") address = f1.getAbsolutePath() + "//Basico//Literarios en ucraniano";
        if (combTemas.getSelectedItem() == "Медицина") address = f1.getAbsolutePath() + "//Basico//Medicina";
        if (combTemas.getSelectedItem() == "Політологія") address = f1.getAbsolutePath() + "//Basico//Politologia";
        if (combTemas.getSelectedItem() == "Переклад") address = f1.getAbsolutePath() + "//Basico//Traduccion";
        if (combTemas.getSelectedItem() == "Психологія") address = f1.getAbsolutePath() + "//Basico//Psicologia";
        if (combTemas.getSelectedItem() == "Усі теми") address = f1.getAbsolutePath() + "//Basico";

        return address;
    }

    public static void getFilesArray() {
        String address = getPathRelativeToSubject();
        File f = new File(address);
        List<File> listFilesForFolder = listFilesForFolder(f);
        filesArray = listFilesForFolder.toArray(new File[0]);
        if (filesArray == null || filesArray.length == 0) JOptionPane.showMessageDialog(null, "Files not found " + address);
        if (filesArray.length > 200) JOptionPane.showMessageDialog(null, "Too many files");
        if ((filesArray.length !=10 && (filesArray.length == 200))) {
            JOptionPane.showMessageDialog(null, strYourVersionIsLimited);
            System.exit(0);
        }

        try {
            fillListOfTextFiles();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<File> listFilesForFolder(final File folder) {
        final List<File> files = new ArrayList<>();
        if (folder == null) {
            return files;
        }
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(listFilesForFolder(fileEntry));
            } else {
                files.add(fileEntry);
            }
        }
        return files;
    }


    public static String formatQuery(String exprsn, boolean caseMatters) {
        Joker = false;

        if (!caseMatters) {
            Expression.caseMatters = false;
            exprsn = exprsn.toLowerCase();
        }

                exprsn = exprsn.replace("*", "[^\\s]"+ "*");
                exprsn = exprsn.replace("?", NON_SEPARATE_CHARACTERS);
               // System.out.println(exprsn);

       return         exprsn;
    }

    public static boolean isSeparator(char character) {
        if (character == ' ') return true;
        return Character.toString(character).matches(SEPARATE_CHARACTERS);
    }

   public static String higlightMatchingString(String fragment, String query){
        Matcher mtcher = Pattern.compile(query.toLowerCase()).matcher(fragment.toLowerCase());
        while (mtcher.find())
        {
            fragment= fragment.substring(0, mtcher.start())+fragment.substring(mtcher.start(), mtcher.end()).toUpperCase()+fragment.substring(mtcher.end());
        }
        return fragment;
    }

    public static String getResultString(TextFile file, int index, String query) {


        StringBuilder result = new StringBuilder();
        String fragment = "";

        //   System.out.println("INDEX : " + (index - CONTEXT_DEPTH) + " " + (index + CONTEXT_DEPTH + query.length()));
        if (index > file.getContent().length() - CONTEXT_DEPTH - query.length()) {
            fragment = file.getContent().substring(index - CONTEXT_DEPTH, index + query.length());
        } else if (index >= CONTEXT_DEPTH) {
            fragment = file.getContent().substring(index - CONTEXT_DEPTH, index + CONTEXT_DEPTH + query.length());
        } else if (file.getContent().length() < (query.length() + CONTEXT_DEPTH * 2)) {
            fragment = file.getContent();
        } else {
            fragment = file.getContent().substring(index, index + CONTEXT_DEPTH + query.length());
        }

         System.out.println("Query : " + query);
        fragment=higlightMatchingString(fragment, query);



        result.append(file.getName()).append(" : ").append(fragment);

        return result.toString();
    }

    public static List<String> getListOfResultsByQuery(String query, boolean caseMatters){
        List<String> result = new ArrayList<>();

        for (TextFile textFile : textFileList) {
            textFile.setFoundInFile(0);
            int resultsInFileCount=0;
            Matcher mtcher;
            if (caseMatters) // Obrabatyvaiem radioknipku registra
             mtcher = Pattern.compile(query).matcher(textFile.getContent());
            else
             mtcher = Pattern.compile(query).matcher(textFile.getContent().toLowerCase());
            if (mtcher.find()) {
                    if (mtcher.start() == 0 && isSeparator( textFile.getContent().charAt( mtcher.end()))){
                        resultsInFileCount++;
                        result.add(getResultString(textFile, mtcher.start(), query));
                    }
                    if (mtcher.start()>0 && isSeparator( (textFile.getContent().charAt(mtcher.start()-1)))&& isSeparator( (textFile.getContent().charAt( mtcher.end())))) {
                        resultsInFileCount++;
                        result.add(getResultString(textFile, mtcher.start(), query));
                    }
                    while (mtcher.start()>0 && mtcher.find() ) {
                        if (mtcher.end() >= textFile.getContent().length() - 1 || (isSeparator(textFile.getContent().charAt(mtcher.start() - 1)) && isSeparator(textFile.getContent().charAt(mtcher.end())))){
                            resultsInFileCount++;
                            result.add(getResultString(textFile, mtcher.start(), query));
                        }
                    }

            }
            textFile.setFoundInFile(resultsInFileCount);

        }
        return result;
    }

    public static void fillListOfTextFiles() throws FileNotFoundException {
        textFileList.clear();

        for (File file : filesArray) {
            Scanner scanner = new Scanner(file, "UTF-16");
            StringBuilder content = new StringBuilder();
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                content.append(line);
            }

            textFileList.add(new TextFile(file.getAbsolutePath(), file.getName(), content.toString()));
//            System.out.println(file);
//            System.out.println(content);
           // System.out.println(textFileList.size());
        }
    }

    public static String[] getAllWords()  {
        StringBuilder allContents= new StringBuilder();
        for (TextFile txtFile : textFileList) {
            if (caseMatters)
                allContents.append(txtFile.getContent());
            else
                allContents.append(txtFile.getContent().toLowerCase());
            allContents.append(" ");
        }
       return allContents.toString().trim().split(SEPARATE_CHARACTERS);
    }


    public static void getSortedWordsAndCount(JTextArea txtAr) throws IOException {

        // TODO: 30/03/19 REFACTORING!!!

        TreeMap<String, Integer> wordsAndCount = new TreeMap<>();
        String[] fileToArray = getAllWords();

        int index = 0;
        while (index < fileToArray.length) {
            if (wordsAndCount.containsKey(fileToArray[index])) {

                wordsAndCount.put(fileToArray[index], wordsAndCount.get(fileToArray[index]) + 1);


            } else {
                wordsAndCount.put(fileToArray[index], 1);
                 }
            index++;
        }
        txtAr.append(strTotalWordForms);
        txtAr.append(" " + wordsAndCount.size() + "\n");
        for (Map.Entry<String, Integer> wordCount : wordsAndCount.entrySet()) {

            txtAr.append(wordCount.getKey() + " " + wordCount.getValue() + "\n");
        }



        txtAr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) {

                    return;
                }
                if (e.getClickCount() != 2) {
                    return;
                }

                int offset = txtAr.viewToModel(e.getPoint());

                try {
                    int rowStart = Utilities.getRowStart(txtAr, offset);
                    int rowEnd = Utilities.getRowEnd(txtAr, offset);
                    String selectedLine = txtAr.getText().substring(rowStart, rowEnd);

                    String word = selectedLine.split(" ")[0];
                    tabby.setSelectedIndex(0);
                 //   System.out.println("WordLength "+textFileList.size());

//                       getListOfResultsByQuery(word, caseMatters);

                       printInWindow(getListOfResultsByQuery(word, caseMatters));



                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        });

    }


    //ETU FUNKTSIU ZAPUSKAT TOLKO POSLE "FIND EXPRESSION"
        public static float getStandardDeviation(){
      //  System.out.println("FILES WITH EXPRESSION ");
        float standardDeviation;
        float sumaTmp=0;
        float dyspersia;

        for (int i = 0; i < textFileList.size(); i++) {
         sumaTmp= (float) (sumaTmp+ ( Math.pow(textFileList.get(i).getFoundInFile() - Medium, 2)));
            System.out.println(textFileList.get(i).getFoundInFile()+" "+sumaTmp);
        }
        dyspersia=sumaTmp/(float)textFileList.size();

            System.out.println("Dyspers "+dyspersia);
//        dyspersia=dyspersia*2;// TREBA POMNOZHYTE; BO TILKY POLOVYNA FAILIV ISPANSKYH. KOLY ROZDILEMO KONKORDANSY; TODI MOZHNA BUDE PRUBRATY TSEI RIADOK


        standardDeviation=dyspersia/2;
        for (int i =0;i<100;i++)
        {
           float x = dyspersia/standardDeviation;

            standardDeviation=(x+standardDeviation)/2;
        }


        return standardDeviation;
    }


    public static int calculateReliability ()
    {
        float StandardDeviation= getStandardDeviation();
        Expression.textArea.setText("");
        Expression.textArea.append(strMeanFreqPer10000 + Medium + "\n");
        Expression.textArea.append(strStandardDeviation + StandardDeviation + "\n");
        Expression.textArea.append(strTripleStandardDeviation + StandardDeviation*3 + "\n");


        List<String>  resultReliability = new ArrayList<>();

        for (TextFile file : textFileList)
        {
            if (file.getFoundInFile() < (StandardDeviation * 3))
                resultReliability.add(inFile+" \""+file.getName()+"\" "+strFound+" "+file.getFoundInFile() + " " + strReliably + "\n");
            else
                resultReliability.add(file.getName()+" \""+" "+strFound+"\" "+file.getFoundInFile() + " " + strNonReliable + "\n");
        }

        printInWindow(resultReliability);

        return 0;
    }
}





