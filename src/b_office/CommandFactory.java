/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package b_office;

import assets.vo.ReqInfo;

/**
 *
 * @author cij@ewha.ac.kr
 */
public class CommandFactory {
    //private static CommandFactory instance = new CommandFactory();
    private CommandFactory() {}
    //public static CommandFactory getInstance(){return instance;}
    
    public static MyCommand create(String msg, ReqInfo info){
        MyCommand obj = null;
            switch(msg){
                case "program":{
                    obj = new Program();
                    obj.setReqInfo(info);
                };break;
                case "parameter":{
                    obj = new Parameter();
                    obj.setReqInfo(info);
                };break;
                case "PAP":{
                    obj = new PAP();
                    obj.setReqInfo(info);
                };break;
            }
        return obj;
    }
    
}
