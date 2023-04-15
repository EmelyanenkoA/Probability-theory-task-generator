import java.util.*;

public class WordWriter {
    private final int[] taskArray;
    private final int counterVariantos;
    private FileDocx fileDocx;
    private FileDocx fileOtvet;
    WordWriter(int[] taskArray, int counterVariantos){
        this.taskArray = taskArray;
        this.counterVariantos = counterVariantos;

        createToVariantos();
    }
    String arrayToString(int[] array){
        String str = "";
        for(int i = 0; i < array.length-1; i++)
            str += array[i] + ", ";
        str+= array[array.length-1] + " ";
        return str;
    }

    int getRandomNumber(int min, int max){
        return new Random().nextInt(max - min) + min;
    }

    double getRandomNumber(double min, double max){
        return min + Math.random() * (max - min);
    }

    void createToVariantos(){
        //create File result
        fileOtvet = new FileDocx("ответы");
        fileOtvet.initTable(taskArray.length+1, counterVariantos);
        for(int variant = 1; variant <= counterVariantos; variant++){
            //code create File variant
            fileDocx = new FileDocx("Вариант " + String.valueOf(variant));
            fileOtvet.addTaleItem("Вар-" + variant,0, variant-1);
            fileDocx.newParagraph();
            fileDocx.addHeader("Тест 2. Вариант " + variant);
            for(int task = 0; task < taskArray.length; task++){
                fileDocx.newParagraph();
                createTask(taskArray[task], variant-1);
            }
            fileDocx.printToFile();
        }
        fileOtvet.printToFile();
    }

    void createTask(int t, int var){
        switch (t){
            case 1:
                fileDocx.addTextBolt("1. ");
                fileOtvet.addTaleItem(createTask1(var+1), 1, var);
                break;
            case 2:
                break;
            case 3:
                //other code
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
        fileDocx.addText("   а) " + v.get(0) + "б) " + v.get(1) + "в) " + v.get(2) + "г) " + v.get(3));
        for(String i : s){
            if(i.equals(Double.toString(answer)))
                return i;
        }
        return "Error";
    }
}
