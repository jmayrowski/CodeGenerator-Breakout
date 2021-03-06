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

    static String outpath ; // Pfad fuer die Ausgabe
    public String output;
    public static DateFormat dateFormat;
    public static Date date;
    private ArrayList<String> codefragments; // Liste für die einzelnen CodeFragments
    private int csvFileCount; // Zaehlt wie viele CSV Files eingelesen wurden

    public CodeGen(){ // Konstruktur der beim erstellen des Objekts aufgerufen wird, beinhaltet den Header
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
        File[] inputFiles = directory.listFiles((d, name) -> name.endsWith(".csv")); // Lambda, scannt Ordner nach CSV Files und legt sie in File-Liste ab
        LevelBaseListener listener = new CodeGen();
        for(int i = 0; i < inputFiles.length;i++){ // For Schleife die den Parser, Lexer und Walker aufruft
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
            outputFile.createNewFile(); // Schreibt Daten in Java File

        System.out.print("Anzahl der erzeugten Funktionen " + ((CodeGen)listener).getCount());
        writer.write(((CodeGen)listener).getOutput());
        writer.flush();
        writer.close();

    }

    public int getCount(){
        return csvFileCount;
    }

    public String getOutput(){ // Setzt alle einzelnen generierten CodeFragments zusammen
        String completeCode = "";
        for (String codefragment:codefragments) {
            completeCode+=codefragment;
        }
        completeCode += generateManagementFunctions();
        completeCode+="}\n";
        return completeCode;
    }
    private String generateManagementFunctions(){ // Erzeugt den letzten CodeBlock für das Java File
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
    public void enterFile(LevelParser.FileContext ctx) { // Code der bei Oeffnung eines Files erzeugt wird
        counter = 0;
        output = "    private static ArrayList<GameEntity> getPlayFieldByIndex"+csvFileCount+"(){\n" +
                "\n" +
                "        BrickFactory bf = new BrickFactory();\n" +
                "        ArrayList<GameEntity> playField = new ArrayList<GameEntity>();\n";
    }


    @Override
    public void exitFile(LevelParser.FileContext ctx) { // Code der bei schließen eines Files erzeugt wird
        //  System.out.println("/**Wenn kein Fehler auftaucht, dann wurde das File korrekt geparsed.*/");
        output += "\t\treturn playField;\n" +
                "\t}\n\n";
        codefragments.add(output);
        csvFileCount++;
    }

    @Override
    public void enterTupel(LevelParser.TupelContext ctx) { // Code der bei betreten eines Tupels erzeugt wird
        //System.out.print(ctx.getText());
        String[] tmp = ctx.getText().split("\\)"); // removes \n and <EOF>
        output += "\t\tplayField.add(bf.initBrick"+tmp[0]+"));\n ";
        counter++;
    }

}