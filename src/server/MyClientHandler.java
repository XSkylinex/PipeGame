package server;

import java.io.*;

public class MyClientHandler implements ClientHandler {
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader clientInput=new BufferedReader(new InputStreamReader(inFromClient));
        StringWriter stringWriter=new StringWriter(); // get string problem
        PrintWriter outToServer=new PrintWriter(stringWriter);
        PrintWriter outtoClient=new PrintWriter(outToClient);

        readInputsAndSend(clientInput, outToServer, "done");
        String fromClient = stringWriter.getBuffer().toString(); //get text from the client


    }


    private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr)
    {
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


}
