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

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;



class Expression {

    public static int[][] MassivAbsChastot = new int[100][1];
    public static int CoincedNum = 0;
    public static int AmountOfWords = 1;
    public static int MaxAmountOfWords = 15;
    public static boolean Joker = false;
    public static String userQuery="";
    public static boolean foundInFile = false;
    public static String FilesArray[];
    public static int filesWithExpression = 0;
    public static int severalFilesTogether = 0;

    public static int filesNumberCounter=0;
    public static float Medium = 0;
    public static String[] sCombTemas = {"Усі теми", "Філософія", "Українська художня література", "Історія", "Мовознавство", "Іспанська художня література", "Медицина", "Політологія", "Психологія", "Переклад", "Соціологія"};
    public static JComboBox combTemas = new JComboBox(sCombTemas);

    public static JComboBox combTemas2 = new JComboBox(sCombTemas);

    public static String address = new String("");


    public static boolean owerflow = false;

    //constants
    public static String strEnterYourText = "Введіть вираз для пошуку";
    public static String strCaseMatters = "Враховувати регістр";
    public static String strSearch = "Пошук";
    public static String checkValidity = "Перевірити достовірність";
    public static String strRetrieveConcordance = "Згенерувати конкорданс";
  public static String  strEnterAnExpression="Ви не ввели жодного виразу";
  public static String strTextAbout="Код: Фокін С.Б. і Іванов М.С." + "\n"+
          "\"Вибірка текстів: Іванов М.С., Фокін С.Б., Бакаржиєва І., Бендерець О., \" +\n" +
          "                \"Бучака І., Ільїна О., Ісаєва М., Краснодзей В., Кладченко А., Лопатіна А., Мамукова К., Мудрик А., Демченко Л., Скиба В., \" +\n" +
          "                \"Ткачук О., Ярина Л., Гороховська Л., Гузенко Т., Дмитраш Я., Дембіцька К., Калініченко О., Левандовська К.-К., Медюк З., Остряніна В., Пустовійт А., \" +\n" +
          "                \"Савенкова Н., Чумаченко Д., Якименко Ю., Сім'ячко Д., Бурдук А., Глоба П., Загородько К., Кисіль М., Куць В., Ламська А.,  Никитіна К., Оборська А.\" +\n" +
          "                \"Ряднова А., Семенюшкіна А., Стрельнікова Д., Третяк М., Юнєєва В.\"+ \"\\n\"+ \"\\n\"+ \"\\n\"+\n" +
          "                \"Корпус складається з 50 текстів українською, і 50 - іспанською мовами кінця ХХ і початку ХХІ століття, що класифіковані \" +\n" +
          "                \"за 10 різними темами. Кожен текст містить 10 000 слововживань\"+ \"\\n\" +\n" +
          "                \"Перевірка достовірності полягає у порівнянні результату вибірки у кожному тексті з очікуваним результатом. Якщо вибірка достатня \" +\n" +
          "                \"і результат достовірний, згідно з теорією ймовірності, кожен результат вибірки не перевищує потрійне квадратичне відхилення\" \n\n\n\n"+
          "Посилення на тексти, використані в програмі, містяться на сторінці завантажень. Усі тексти взято з відкритих джерел";

  public static String strTotalProcesed = "Усього опрацьовано ";
  public static String strTotalFound = "Усього знайдено ";
  public static String strFilesGenitiv = " файлів";
  public static String strYourVersionIsLimited = "Ви використовуєте обмежену версію";
public static String strTooManyCoincidences = "Забагато збігів. Обмежте пошук!";
   public static String strMeanFreqPer10000 = "Середня частота на 10000 слововживань: ";
    public static String strStandardDeviation =  "Середнє квадратичне відхилення: ";
    public static String strTripleStandardDeviation= "Потрійне середнє квадратичне відхилення: ";
    public static String strReliably = "Достовірно";
    public static String strNonReliable = "Недостовірно";
    public static String strTotalWordForms="Усього приблизно словоформ:";

    public static boolean caseMatters = false;

    public static JFrame fr = new JFrame("TexPeer 1.0");

    public static JTabbedPane tabby = new JTabbedPane();

    public static JTextArea textArea = new JTextArea(20, 70);
    public static JTextField textFieldSearch = new JTextField(30);



    public static List<TextFile> textFileList = new ArrayList<>();

    public static final int CONTEXT_DEPTH = 60;



    public static void PrintInWindow(List result) {

        Expression.CoincedNum=result.size();

        Expression.textArea.setText("");

        Expression.textArea.append(strTotalProcesed + FilesArray.length + strFilesGenitiv + "\n");


        if (CoincedNum > 30) fr.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Expression.textArea.append(strTotalFound+ Expression.CoincedNum + " збігів " + userQuery + " в " + filesWithExpression + " файлах" + "\n");
        Medium = (float) Expression.CoincedNum / (float) severalFilesTogether;
        Expression.textArea.append("Середня частота на 10 000 слів: " + Medium + "\n");
        Expression.textArea.append("Середнє квадратичне відхилення: " + GetStandardDeviation() + "\n");


        for (Object res : result
             )
        {
            textArea.append(res.toString()+ "\n");
        }


        fr.setVisible(true);
        filesWithExpression = 0;
    }

    public static String getPathRelativeToSubject()
    {


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
        if (combTemas.getSelectedItem() == "Усі теми") address = f1.getAbsolutePath() + "//Basico//Todos los temas";

        return address;
    }

    public static void getFilesArray() throws URISyntaxException {
        String fAdress = "";
        File f1 = new File(fAdress);
        address = f1.getAbsolutePath() + "//Basico";
        address=   getPathRelativeToSubject();
        File f = new File(address);
        FilesArray = f.list();
        try {
            fillListOfTextFiles();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        severalFilesTogether = f.list().length;
        if (FilesArray.length > 200) JOptionPane.showMessageDialog(null, "Too many files");

        if (FilesArray.length == 0) JOptionPane.showMessageDialog(null, "Files not found");
        if ((FilesArray.length !=10 && (FilesArray.length == 200)))
        {
            JOptionPane.showMessageDialog(null, strYourVersionIsLimited);
            System.exit(0);
        }

    }


    public static String formatQuery(String Exprsn, boolean caseMatters) {
        Joker = false;

        if (!caseMatters) {
            Expression.caseMatters = false;
            Exprsn = Exprsn.toLowerCase();
        }

                Exprsn = Exprsn.replace("*", "[^\\s]*");
                Exprsn = Exprsn.replace("?", "[^\\s]");
                System.out.println(Exprsn);

       return         Exprsn;


    }


    public static String getResultString(TextFile file, int index, String query){
        StringBuilder result = new StringBuilder();
        String fragment="";

if(index>=CONTEXT_DEPTH)
       fragment = file.getContent().substring(index - CONTEXT_DEPTH, index + CONTEXT_DEPTH + query.length());
else
    fragment = file.getContent().substring(index , index + CONTEXT_DEPTH + query.length());
       fragment= fragment.replace(query, query.toUpperCase()); // Pokazyvaem bolshimi bukvami slolo ili frazu zaprosa

        result.append(file.getName()).append(" : ").append(fragment);

        return result.toString();
    }

    public static List<String> getListOfResultsByQuery(String query, boolean caseMatters){


        query = formatQuery(query, caseMatters);
        List<String> result = new ArrayList<>();

        for (TextFile textFile : textFileList) {

            Matcher mtcher;
            if (caseMatters) // Obrabatyvaiem radioknipku registra
             mtcher = Pattern.compile(query).matcher(textFile.getContent());
            else
             mtcher = Pattern.compile(query).matcher(textFile.getContent().toLowerCase());

            if (mtcher.find())
            {
                result.add(getResultString(textFile, mtcher.start(), query));
                filesWithExpression++; // schitaem kolichestvo failov, v kotorykh est vyrazhenie
                while (mtcher.find())
                result.add(getResultString(textFile, mtcher.start(), query));
                 //TO DO   Nuzhno poschitat kolichestvo naidennogo v kazhdom faile, sokranit v kakom-to massive, chtob potom
               // ispolzovat pri podschiote dostovernosti

            }

        }
              return result;
    }

    public static void fillListOfTextFiles() throws FileNotFoundException {
      //  textFileList.clear();
        for (String s : FilesArray) {
            Scanner scanner = new Scanner(new File(address + "//" + s), "UTF-16");
            StringBuilder content = new StringBuilder();
            String temp;
            while (scanner.hasNext()){
                temp = scanner.nextLine();
                content.append(temp);
            }

                textFileList.add(new TextFile(s, content.toString()));
        }
    }

    public static String[] getAllWords()  {
        StringBuilder allContents= new StringBuilder();
        for (TextFile txtFile : textFileList) {

            if (caseMatters)
            allContents.append(txtFile.getContent());
            else
                allContents.append(txtFile.getContent().toLowerCase());


        }
       return allContents.toString().trim().split("[^A-Za-zñáéúíóüÁÉÍÓÚÜА-Яа-яїЇґҐєЄ'’]");
    }


    public static void getSortedWordsAndCount(JTextArea txtAr) throws IOException {

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

                       getListOfResultsByQuery(word, caseMatters);


                    Expression.PrintInWindow(getListOfResultsByQuery(word, caseMatters) );


                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        });

    }


        public static float GetStandardDeviation()//ETU FUNKTSIU ZAPUSKAT TOLKO POSLE "FIND EXPRESSION"
    {
        float StandardDeviation = 0;
float SumaTmp=0;
        float Dyspersia=0;

        for (int i=0; i<severalFilesTogether; i++) {
         SumaTmp=  SumaTmp+ (((float)MassivAbsChastot[i][0]-Medium)*((float)MassivAbsChastot[i][0]-Medium));
           // System.out.println((((float)MassivAbsChastot[i][0]-Medium)*((float)MassivAbsChastot[i][0]-Medium)));

        }
        Dyspersia=SumaTmp/(float)severalFilesTogether;

        Dyspersia=Dyspersia*2;// TREBA POMNOZHYTE; BO TILKY POLOVYNA FAILIV ISPANSKYH. KOLY ROZDILEMO KONKORANSY; TODI MOZHNA BUDE PRUBRATY TSEI RIADOK


        StandardDeviation=Dyspersia/2;
        for (int i =0;i<100;i++)
        {
           float x =  Dyspersia/StandardDeviation;

            StandardDeviation=(x+StandardDeviation)/2;
        }


        return StandardDeviation;
    }


    public static int calculateReliability ()
    {
float StandardDeviation=GetStandardDeviation();
        Expression.textArea.setText("");
        Expression.textArea.append(strMeanFreqPer10000 + Medium + "\n");
        Expression.textArea.append(strStandardDeviation + StandardDeviation + "\n");

        Expression.textArea.append(strTripleStandardDeviation + StandardDeviation*3 + "\n");

        for (int i=0; i<MassivAbsChastot.length; i++) {
            if (MassivAbsChastot[i][0]<(StandardDeviation*3))
            Expression.textArea.append(i+" "+MassivAbsChastot[i][0]+ strReliably + "\n");
            else
                Expression.textArea.append(i+" "+MassivAbsChastot[i][0]+ strNonReliable + "\n");
        }


        return 0;
    }
}





