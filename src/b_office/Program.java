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
public class Program extends PublicAssets implements MyCommand{
    
    @Override
    public String makingCommand() {
        StringBuffer result = new StringBuffer();
        //String reqXml = "<?xml version='1.0' encoding='utf-8'?><reqinfo><info><session_id>xkkfer1231aweq</session_id><program_type>Rscript</program_type><str_parameter>param1,param2,param3</str_parameter></info></reqinfo>";
        result.append(analysisXml("Program"));
        return result.toString();
    }

    @Override
    public void setReqInfo(ReqInfo reqInfo) {
        this.aReqInfo = reqInfo;
    }

/*
    private String analysisXml(String reqXml){
        String result = "";
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docFactory.newDocumentBuilder();
            Document doc = docBuild.parse(new InputSource(new StringReader(reqXml)));
            
            //String session_id = ( (Element) doc.getDocumentElement().getElementsByTagName(aRootTagName).item(0) ).getElementsByTagName(aSessionIdTagName).item(0).getTextContent();
            String program_type = ( (Element) doc.getDocumentElement().getElementsByTagName(aRootTagName).item(0) ).getElementsByTagName(aProgramTypeTagName).item(0).getTextContent();
            //String str_parameter = ( (Element) doc.getDocumentElement().getElementsByTagName(aRootTagName).item(0) ).getElementsByTagName(aStrParameterTagName).item(0).getTextContent();
            result = program_type;
            //
//            Element root = doc.getDocumentElement();
//            
//            int cnt = root.getElementsByTagName("info").getLength();
//            Node node = null;
//            Element enode = null;
//            System.out.println("cnt "+cnt);
//            
//            for(int a = 0; a < cnt; a++){
//                node = root.getElementsByTagName("info").item(a);
//                enode = (Element) node;
//                System.out.println(enode.getElementsByTagName("session_id").item(0).getTextContent());
//            }
            //
//            System.out.println("session_id : "+session_id);
//            System.out.println("program_type : "+program_type);
//            System.out.println("str_parameter : "+str_parameter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
*/

}
