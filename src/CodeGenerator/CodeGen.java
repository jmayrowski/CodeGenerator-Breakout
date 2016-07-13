package CodeGenerator;


import CodeGenerator.grammar.LevelBaseListener;
import CodeGenerator.grammar.LevelLexer;
import CodeGenerator.grammar.LevelParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jasmin on 10.07.2016.
 */
public class CodeGen extends LevelBaseListener {

    public int counter;
    public String Tupelwert;
    public static int numberBricks;
    /*static String inpath = "D:\\Programmierprojekte\\Breakout V2.0\\src\\CodeGenerator\\csv\\level_1.csv"; // Pfad
    static String outpath = "D:\\Programmierprojekte\\Breakout V2.0\\src\\CodeGenerator\\out\\PlayField.java"; // Pfad*/

    //static String inpath ; // Pfad
    static String outpath ; // Pfad fuer die Ausgabe
    public String output;
    public static DateFormat dateFormat;
    public static Date date;
    private ArrayList<String> codefragments;
    private int csvFileCount;

    public CodeGen(){
        codefragments = new ArrayList<String>();
        csvFileCount = 0;
        counter = 0;
        //inpath = "src\\CodeGenerator\\csv\\level_1.csv";
        outpath = "src\\CodeGenerator\\out\\MultiPlayFieldFactory.java";
        output = "";
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();
        codefragments.add("package Breakout;\n" +
                "\n" +
                "import com.almasb.fxgl.entity.GameEntity;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "\n" +
                "/**\n" +
                " * Created by Jasmin on "+dateFormat.format(date)+".\n" +
                " */\n" +
                "public class MultiPlayFieldFactory {\n\n");
    }

    public static void main(String[] args) throws IOException {

        File directory = new File("src\\CodeGenerator\\csv\\");
        File[] inputFiles = directory.listFiles((d, name) -> name.endsWith(".csv"));
        LevelBaseListener listener = new CodeGen();
        for(int i = 0; i < inputFiles.length;i++){
            FileReader tmp = new FileReader(inputFiles[i]);
            LevelLexer lexer = new LevelLexer(new ANTLRInputStream(tmp));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            LevelParser parser = new LevelParser(tokens);

            LevelParser.FileContext fileContext = parser.file();

            ParseTreeWalker walker = new ParseTreeWalker();

            walker.walk(listener,fileContext);
            System.out.print("walked csv file:"+inputFiles[i].getName()+"\n");
        }
        System.out.print("Finished walking, generating java file...\n");
        FileWriter writer = new FileWriter(outpath);
        File outputFile =new File (outpath);
        if (!outputFile.exists())
            outputFile.createNewFile();

        System.out.print("Anzahl der erzeugten Funktionen " + ((CodeGen)listener).getCount());
        writer.write(((CodeGen)listener).getOutput());
        writer.flush();
        writer.close();

    }

    public int getCount(){
        return csvFileCount;
    }

    public String getOutput(){
        String completeCode = "";
        for (String codefragment:codefragments) {
            completeCode+=codefragment;
        }
        completeCode += generateManagementFunctions();
        completeCode+="}\n";
        return completeCode;
    }
    private String generateManagementFunctions(){
        String code = "\tpublic static int getPlayFieldCount() {\n" +
                "\t\treturn "+csvFileCount+";\n" +
                "\t}\n" +
                "\n" +
                "\tpublic static ArrayList<GameEntity> getLevel(int levelIndex){\n" +
                "\n";
        for (int i =0;i< csvFileCount;i++){
            code += "\t\tif(levelIndex == "+i+"){return getPlayFieldByIndex"+i+"();}\n";
        }
        code += "\n" +
                "        return null;\n" +
                "    }\n";
        return code;
    }
    @Override
    public void enterFile(LevelParser.FileContext ctx) {
        counter = 0;
        output = "    private static ArrayList<GameEntity> getPlayFieldByIndex"+csvFileCount+"(){\n" +
                "\n" +
                "        BrickFactory bf = new BrickFactory();\n" +
                "        ArrayList<GameEntity> playField = new ArrayList<GameEntity>();\n";
    }


    @Override
    public void exitFile(LevelParser.FileContext ctx) {
        //  System.out.println("/**Wenn kein Fehler auftaucht, dann wurde das File korrekt geparsed.*/");
        output += "\t\treturn playField;\n" +
                "\t}\n\n";
        codefragments.add(output);
        csvFileCount++;
    }

    @Override
    public void enterTupel(LevelParser.TupelContext ctx) {
        //System.out.print(ctx.getText());
        String[] tmp = ctx.getText().split("\\)"); // removes \n and <EOF>
        output += "\t\tplayField.add(bf.initBrick"+tmp[0]+"));\n ";
        counter++;
    }

}