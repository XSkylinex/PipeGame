package server;

import classes.PipeGameBoard;
import classes.Rotations;

import java.io.*;
import java.util.Collection;

public class MyClientHandler implements ClientHandler {
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader clientInput=new BufferedReader(new InputStreamReader(inFromClient));
        StringWriter stringWriter=new StringWriter(); // get string problem
        PrintWriter outToServer=new PrintWriter(stringWriter);
        PrintWriter outtoClient=new PrintWriter(outToClient);

        readInputsAndSend(clientInput, outToServer, "done");
        String fromClient = stringWriter.getBuffer().toString(); //get text from the client

        PipeGameBoard gameBoardProblem =new PipeGameBoard(fromClient);
        PipeGameBoard gameBoardSolution=solveBoard(gameBoardProblem);
        Rotations rotations=new Rotations(gameBoardProblem,gameBoardSolution);
        Collection<String> commends=rotations.returnAllRotations();

        for (String commend : commends) {
            //System.out.println("commend: "+commend);
            outtoClient.println(commend);
            outtoClient.flush();
        }
        outtoClient.println("done");
        outtoClient.flush();
        outtoClient.close();

    }


    private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr) {
        try {
            String line;
            while(!(line=in.readLine()).equals(exitStr))
            {
                out.println(line);
                out.flush();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public PipeGameBoard solveBoard(PipeGameBoard pipeGameBoardProblem) {
        return null;
    }
}



//public class MainTrain {
//    // run your server here
//    static Server s;
//    public static void runServer(int port){
//        s=new MyServer(port);
//        s.start(new MyClientHandler());
//    }
//    // stop your server here
//    public static void stopServer(){
//        s.stop();
//    }
//    public static void main(String[] args) {
//        int port=6400;
//        runServer(port);
//        //stopServer();
//        System.out.println("done");
//    }
//}