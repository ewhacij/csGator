/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package b_office;

import assets.vo.ReqInfo;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 *
 * @author cij@ewha.ac.kr
 */
public class PAP extends PublicAssets implements MyCommand{

    @Override
    public void setReqInfo(ReqInfo reqInfo) {
        this.aReqInfo = reqInfo;
    }

    @Override
    public String makingCommand() {
        StringBuffer result = new StringBuffer();
        
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuild = docFactory.newDocumentBuilder();
            doc = docBuild.parse(new InputSource(new StringReader(aReqInfo.getXml_parameter())));
            
            
            
            
            Element rootNode = (Element) doc.getDocumentElement().getElementsByTagName(aRootTagName).item(currentNodeNumber);
            result.append(rootNode.getElementsByTagName(aProgramTypeTagName).item(currentNodeNumber).getTextContent());
            result.append(" ");
            result.append(rootNode.getElementsByTagName(aStrParameterTagName).item(currentNodeNumber).getTextContent());
        } catch (Exception e) {
            System.out.println("[ERROR To PAP makingCommand] : " + e.getMessage());
        }
        return result.toString();
    }
    
}
