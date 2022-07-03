import com.codingame.gameengine.runner.MultiplayerGameRunner;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
        //        gameRunner.setSeed(-8289918975308209200l);

        int nbPlayers = 8;

        for(int i=0; i<nbPlayers; i++) {
            gameRunner.addAgent(
                    "E:\\Desktop\\codingame\\_myContrib\\Sea of Pirates\\venv\\Scripts\\python.exe starterAIs/starter.py",
                    String.format("Player %d", i),
                    "https://static.codingame.com/servlet/fileservlet?id=82828488085989&format=navigation_avatar"
            );
        }

        gameRunner.start();
    }

    private static String compileJava(String botFile) throws IOException, InterruptedException {

        File outFolder = Files.createTempDir();

        System.out.println("Compiling Boss.java... " + botFile);
        Process compileProcess = Runtime.getRuntime().exec(
                new String[] {
                        "bash", "-c", "javac " + botFile + " -d " + outFolder.getAbsolutePath()
                }
        );
        compileProcess.waitFor();
        return "java -cp " + outFolder + " Player";
    }
}