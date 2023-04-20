package HomeClasses.docx;

import java.io.File;
import java.util.*;

public class WordWriter {
    private final int[] taskArray;
    private final int countVariants;
    private FileDocx fileDocx;
    private FileDocx fileAnswers;
    private final String filesPath;
    private final String[] specialSymbols = {"xᵢ", "xᵢ₊₁", "xᵢ - xᵢ₊₁", "nᵢ", "n₃"};
    private final String [] specialSymbolsX = {"x₁", "x₂" ,"x₃", "x₄"};
    public WordWriter(int[] taskArray, int countVariants, String filesPath){
        this.taskArray = taskArray;
        this.countVariants = countVariants;
        this.filesPath = filesPath;
        File theDir = new File(filesPath);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        createVariants();
    }
    String arrayToString(int[] array){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < array.length-1; i++)
            str.append(array[i]).append(", ");
        str.append(array[array.length - 1]).append(" ");
        return str.toString();
    }
    int getRandomNumber(int min, int max){
        return new Random().nextInt(max - min) + min;
    }

    double getRandomNumber(double min, double max){
        return min + Math.random() * (max - min);
    }

    void createVariants(){
        fileAnswers = new FileDocx(filesPath + "\\ответы");
        fileAnswers.initTable(taskArray.length+1, countVariants + 1);
        fileAnswers.initRow(countVariants);
        fileAnswers.initCol(taskArray);
        for(int variant = 1; variant <= countVariants; variant++){
            //code create File variant
            fileDocx = new FileDocx(filesPath + "\\Вариант " + variant);
            fileDocx.newParagraph();
            fileDocx.addHeader("Тест 2. Вариант " + variant);
            for(int task = 0; task < taskArray.length; task++){
                fileDocx.newParagraph();
                createTask(taskArray[task], variant, task+1);
            }
            fileDocx.printToFile();
        }
        fileAnswers.printToFile();
    }

    void createTask(int t, int var, int countTask) {
        switch (t){
            case 1:
                fileDocx.addTextBolt("1. ");
                fileAnswers.addTaleItem(createTask1(var), countTask, var);
                break;
            case 2:
                fileDocx.addTextBolt("2. ");
                fileAnswers.addTaleItem(createTask2(var), countTask, var);
                break;
            case 3:
                fileDocx.addTextBolt("3. ");
                fileAnswers.addTaleItem(createTask3(var), countTask, var);
                break;
            case 4:
                fileDocx.addTextBolt("4. ");
                fileAnswers.addTaleItem(createTask4(var), countTask, var);
                break;
            case 5:
                fileDocx.addTextBolt("5. ");
                fileAnswers.addTaleItem(createTask5(var), countTask, var);
                break;
            case 7:
                fileDocx.addTextBolt("7. ");
                fileAnswers.addTaleItem(createTask7(var), countTask, var);
                break;
            case 8:
                fileDocx.addTextBolt("8. ");
                fileAnswers.addTaleItem(createTask8(var), countTask, var);
                break;
            case 9:
                fileDocx.addTextBolt("9. ");
                fileAnswers.addTaleItem(createTask9(var), countTask, var);
                break;
        }
    }

    String createTask1(int var){
        while (var > 4)
            var -= 4;

        double answer;
        int[] n = {12, 12, 16, 18, 18, 18, 19, 20, 21, 22, 22, 23, 25, 25};
        int[] a = {18, 19, 20, 22};
        n[6] = a[var-1];
        answer = (double) (n[6] + n[7]) / 2;
        fileDocx.addTextBreak("Медиана вариационного ряда " + arrayToString(n) + "равна:");
        String[] s = {"19.5", "20.0", "19.0", "21.0"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Double.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

    String createTask2(int var){
        while (var > 4)
            var -= 4;
        int [] n = new int[9];
        int answer = 0;
        if(var == 1) {
            n = new int[]{1, 4, 5, 5, 7, 9, 9, 9, 12};
            answer = 9;
        }
        else if (var == 2) {
            n = new int[]{1, 4, 5, 5, 7, 9, 12, 12, 12};
            answer = 12;
        }
        else if (var == 3) {
            n = new int[]{1, 4, 5, 5, 5, 7, 9, 9, 12};
            answer = 5;
        }
        else if (var == 4) {
            n = new int[]{1, 1, 1, 5, 7, 7, 9, 9, 12};
            answer = 1;
        }
        fileDocx.addTextBreak("Мода вариационного ряда " + arrayToString(n) + "равна:");
        String[] s = {"1", "5", "9", "12"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Integer.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

    String createTask3(int var){
        while (var > 4)
            var -= 4;
        int answer = 0;
        String[] rowTable = new String[]{ specialSymbols[2], "0-2", "2-4", "4-6", "6-8", "8-10" };
        String questionStr = "Из генеральной совокупности извлечена выборка объёма n = ";
        if(var == 1){
            fileDocx.addTextBreak(questionStr + 80);
            answer = 28;
        }
        else if(var == 2){
            fileDocx.addTextBreak(questionStr + 62);
            answer = 10;
        }
        else if(var == 3){
            fileDocx.addTextBreak(questionStr + 68);
            answer = 16;
        }
        else if(var == 4){
            fileDocx.addTextBreak(questionStr + 72);
            answer = 20;
        }
        //запись таблицы
        fileDocx.initTable(2, 6);
        fileDocx.addTableArrayRow(rowTable, 0);
        rowTable = new String[]{specialSymbols[3], "6", "14", specialSymbols[4], "20", "12"};
        fileDocx.addTableArrayRow(rowTable, 1);

        fileDocx.newParagraph();
        fileDocx.addTextBreak("Тогда значение " + specialSymbols[4] + " равно:");

        String[] s = {"20", "16", "10", "28"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Integer.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

    String createTask4(int var){
        while (var > 4)
            var -= 4;
        double answer = 0.0;
        String[] rowTable = new String[]{ specialSymbols[0], "3", "4", "5", "6", "7" };
        String questionStr = "Из генеральной совокупности извлечена выборка объёма n = ";
        if(var == 1){
            fileDocx.addTextBreak(questionStr + 100);
            answer = 0.18;
        }
        else if(var == 2){
            fileDocx.addTextBreak(questionStr + 132);
            answer = 0.5;
        }
        else if(var == 3){
            fileDocx.addTextBreak(questionStr + 107);
            answer = 0.25;
        }
        else if(var == 4){
            fileDocx.addTextBreak(questionStr + 95);
            answer = 0.13;
        }
        //запись таблицы
        fileDocx.initTable(2, 6);
        fileDocx.addTableArrayRow(rowTable, 0);
        rowTable = new String[]{specialSymbols[3], "15", "35", specialSymbols[4], "25", "7"};
        fileDocx.addTableArrayRow(rowTable, 1);

        fileDocx.newParagraph();
        fileDocx.addTextBreak("Тогда относительная частота варианты " + specialSymbols[0] + " = 5 равна:");

        String[] s = {"0.5", "0.25", "0.18", "0.13"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Double.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

    String createTask5(int var){
        while (var > 4)
            var -= 4;
        double answer = 0.0;
        fileDocx.addTextBreak("Из генеральной совокупности извлечена выборка объема n = 160");

        if(var == 1){
            answer = 0.15;
        }
        else if(var == 2){
            answer = 0.36;
        }
        else if(var == 3){
            answer = 0.25;
        }
        else if(var == 4){
            answer = 0.13;
        }
        fileDocx.addTextBreak("полигон частот которой имеет вид: ");
        fileDocx.addPicture("src\\res\\image\\график задание 5.jpg", 386, 244);
        fileDocx.addTextBreak("Тогда относительная частота варианты " + specialSymbolsX[var-1] + " = " + var *2 +" в выборке равна:");

        String[] s = {"0.15", "0.36", "0.25", "0.13"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Double.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

    String createTask7(int var){
        while (var > 4)
            var -= 4;
        double answer = 0.0;
        String[] rowTable = new String[]{ specialSymbols[0], "12", "14", "15", "19"};
        String questionStr = "Из генеральной совокупности извлечена выборка объёма n = ";
        if(var == 1){
            fileDocx.addTextBreak(questionStr + 40 + ":");
            answer = 13.1;
        }
        else if(var == 2){
            fileDocx.addTextBreak(questionStr + 27.5 + ":");
            answer = 19.0;
        }
        else if(var == 3){
            fileDocx.addTextBreak(questionStr + 655 + ":");
            answer = 0.8;
        }
        else if(var == 4){
            fileDocx.addTextBreak(questionStr + 349 + ":");
            answer = 1.5;
        }
        //запись таблицы
        fileDocx.initTable(2, 5);
        fileDocx.addTableArrayRow(rowTable, 0);
        rowTable = new String[]{specialSymbols[3], "22", "14", "3", "1"};
        fileDocx.addTableArrayRow(rowTable, 1);

        fileDocx.newParagraph();
        fileDocx.addTextBreak("Тогда несмещенная оценка математического ожидания равна: ");

        String[] s = {"0.8", "1.5", "13.1", "19.0"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Double.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }
    String createTask8(int var){
        while (var > 4)
            var -= 4;
        double answer = 0.0;
        String questionStr = "В результате измерений некоторой физической величины одним прибором (без систематических ошибок) получены следующие результаты (в мм): ";
        if(var == 1){
            fileDocx.addTextBreak(questionStr + "4,6; 6,2; 6,6. Тогда несмещенная оценка дисперсии равна:");
            answer = 1.12;
        }
        else if(var == 2){
            fileDocx.addTextBreak(questionStr + "5,2; 6,4; 7,0. Тогда несмещенная оценка дисперсии равна:");
            answer = 0.84;
        }
        else if(var == 3){
            fileDocx.addTextBreak(questionStr +"7,3; 8,4; 9,2. Тогда несмещенная оценка дисперсии равна:");
            answer = 0.91;
        }
        else if(var == 4){
            fileDocx.addTextBreak(questionStr + "5,2; 6,5; 9,3. Тогда несмещенная оценка дисперсии равна:");
            answer = 4.39;
        }

        String[] s = {"1.12", "0.84", "0.91", "4.39"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Double.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

    String createTask9(int var){
        while (var > 4)
            var -= 4;
        int answer = 0;
        String questionStr = "Проведено четыре измерения (без систематических ошибок) некоторой случайной величины (в мм): 2, 3, 4, x. Если выборочная дисперсия равна ";
        if(var == 1){
            fileDocx.addTextBreak(questionStr + 3.5 + ", то значение x равно:");
            answer = 7;
        }
        else if(var == 2){
            fileDocx.addTextBreak(questionStr + 0.5 + ", то значение x равно:");
            answer = 3;
        }
        else if(var == 3){
            fileDocx.addTextBreak(questionStr + 12.5 + ", то значение x равно:");
            answer = 11;
        }
        else if(var == 4){
            fileDocx.addTextBreak(questionStr + 27.5 + ", то значение x равно:");
            answer = 15;
        }

        String[] s = {"7", "3", "11", "15"};
        List<String> v = new ArrayList<>(Arrays.asList(s));
        Collections.shuffle(v);
        fileDocx.addText("   а) " + v.get(0) + "   б) " + v.get(1) + "   в) " + v.get(2) + "   г) " + v.get(3));
        String [] b = new String[] {"а", "б", "в","г"};
        int k = 0;
        for(String i : v){
            if(i.equals(Integer.toString(answer)))
                return b[k];
            k ++;
        }
        return "Error";
    }

}
