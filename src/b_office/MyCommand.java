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
public interface MyCommand {
    public void setReqInfo(ReqInfo reqInfo);
    public String makingCommand();
}
