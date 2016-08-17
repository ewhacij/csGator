/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package a_office;

import assets.vo.ReqInfo;
import b_office.CommandFactory;
import b_office.MyCommand;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cij@ewha.ac.kr
 */
public class Aoffice2 implements Runnable{
    private ServerSocket serverSocket = null;
    private boolean onOff = false; // off
    private Socket socket = null;
    
    private InputStream dataIn;
    private BufferedReader inputReader;
    private Process runTimeProcess;
    private BufferedReader resultReader;
    private OutputStream dataOut;
    private BufferedWriter outputReader;
    
    private ReqInfo reqInfo = null;
    private MyCommand myCommandObj = null;
    private String myCommand = "";
    
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(1100);
            onOff = true;
            while(onOff){
                socket = serverSocket.accept();

                if( socket.isConnected() ){
                    dataIn = socket.getInputStream();
                    inputReader = new BufferedReader(new InputStreamReader(dataIn));
                    
                    reqInfo = new ReqInfo();
                    String inputXMLStr = inputReader.readLine();
                    //System.out.println("Step01 Input XML : "+inputXMLStr);
                    reqInfo.setXml_parameter(inputXMLStr);

                        myCommandObj = CommandFactory.create("PAP", reqInfo);
                        myCommand = myCommandObj.makingCommand();
                    
                    //System.out.println("Step02 Execute command : "+myCommand);
                    runTimeProcess = Runtime.getRuntime().exec(myCommand);
                    runTimeProcess.waitFor();
                    resultReader = new BufferedReader(new InputStreamReader(runTimeProcess.getInputStream()));
                    String resultStr = resultReader.readLine();
                    //System.out.println("Step03 read result data : "+resultStr);
                    StringBuffer resultStrAll = new StringBuffer();
                    resultStrAll.append(resultStr);
                    
                    while(resultStr != null){
                        resultStr = resultReader.readLine();
                        resultStrAll.append(resultStr);
                    }
                    
                    dataOut = socket.getOutputStream();
                    outputReader = new BufferedWriter(new OutputStreamWriter(dataOut));
                    outputReader.write(resultStrAll.toString());
                    outputReader.flush();
                    //System.out.println("Step04 data export : ");
                    
                    outputReader.close();
                    dataOut.close();
                    resultReader.close();
                    if(runTimeProcess != null){runTimeProcess.destroy();}
                    inputReader.close();
                    dataIn.close();
                }
            }
        } catch (Exception e) {
            FileWriter objfile = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            StringBuffer eMsg = new StringBuffer(); 
            eMsg.append("[").append(timeFormat.format(new Date())).append("]\r\n").append(this.myCommand).append("\r\n").append(e.getMessage());
            try{
                String logDate = "./" + dateFormat.format(new Date()) + "_csGator_Socket_Server.log";
                objfile = new FileWriter(logDate, true);
                objfile.write(eMsg.toString());
                objfile.write("\r\n");
                objfile.flush();
            }catch(IOException e1){
                System.out.println("E1 = "+e1.getMessage());
            }finally{
                try{
                 objfile.close();
                 objfile=null;
                 eMsg = null;
                 timeFormat=null;
                 dateFormat=null;
                }catch(Exception e2){
                    System.out.println("E2 = "+e2.getMessage());
                }
            }            
        }finally{
            try {
                if(outputReader != null){outputReader.close();}
                if(dataOut != null){dataOut.close();}
                if(resultReader != null){resultReader.close();}
                if(runTimeProcess != null){runTimeProcess.destroy();}
                if(inputReader != null){inputReader.close();}
                if(dataIn != null){dataIn.close();}
                if(!socket.isClosed()){socket.close();}
                if(!serverSocket.isClosed()){serverSocket.close();}
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
    
    public static void main(String[] args) {
        Aoffice2 obj = new Aoffice2();
        obj.run();
    }
    
}
