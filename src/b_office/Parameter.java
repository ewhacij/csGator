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
public class Parameter extends PublicAssets implements MyCommand{
    
    @Override
    public String makingCommand() {
        StringBuffer result = new StringBuffer();
        result.append(analysisXml("Parameter"));
        return result.toString();
    }

    @Override
    public void setReqInfo(ReqInfo reqInfo) {
        this.aReqInfo = reqInfo;
    }

    
    
}
