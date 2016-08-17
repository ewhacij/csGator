/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package b_office;

import assets.vo.ReqInfo;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 *
 * @author cij@ewha.ac.kr
 */
public abstract class PublicAssets {
    protected ReqInfo aReqInfo;
    protected String aRootTagName = "info";
    protected String aSessionIdTagName = "session_id";
    protected String aProgramTypeTagName = "program_type";
    protected String aStrParameterTagName = "str_parameter";
    
    protected DocumentBuilderFactory docFactory;
    protected DocumentBuilder docBuild;
    protected Document doc;
    protected int currentNodeNumber = 0;
    
    
    protected String analysisXml(String type){
        String result = "";
        docFactory = null;
        docBuild = null;
        doc = null;
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuild = docFactory.newDocumentBuilder();
            doc = docBuild.parse(new InputSource(new StringReader(aReqInfo.getXml_parameter())));
            switch(type){
                case "Program":{
                    result = ( (Element) doc.getDocumentElement().getElementsByTagName(aRootTagName).item(currentNodeNumber) ).getElementsByTagName(aProgramTypeTagName).item(currentNodeNumber).getTextContent();
                };break;
                case "Parameter":{
                    result = ( (Element) doc.getDocumentElement().getElementsByTagName(aRootTagName).item(currentNodeNumber) ).getElementsByTagName(aStrParameterTagName).item(currentNodeNumber).getTextContent();
                };break;
            }
        } catch (Exception e) {
            System.out.println("[ERROR To analysisXml] : " + e.getMessage());
        }
        
        return result;
    }
    
    
    public String getXMLSample(){
        /*
        <?xml version='1.0' encoding='utf-8'?>
        <reqinfo>
            <info>
              <session_id>xkkfer1231aweq</session_id>
              <program_type>Rscript</program_type>
              <str_parameter>param1,param2,param3</str_parameter>
            </info>
        </reqinfo>
        */
        return "<?xml version='1.0' encoding='utf-8'?><reqinfo><info><session_id>xkkfer1231aweq</session_id><program_type>Rscript</program_type><str_parameter>param1,param2,param3</str_parameter></info></reqinfo>";
    }
}
