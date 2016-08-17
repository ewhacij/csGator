/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assets.vo;

/**
 *
 * @author cij@ewha.ac.kr
 */
public class ReqInfo {
    private String http_session_id;
    private String xml_parameter;
    private String json_parameter;

    public String getHttp_session_id() {
        return http_session_id;
    }

    public void setHttp_session_id(String http_session_id) {
        this.http_session_id = http_session_id;
    }

    public String getXml_parameter() {
        return xml_parameter;
    }

    public void setXml_parameter(String xml_parameter) {
        this.xml_parameter = xml_parameter;
    }

    public String getJson_parameter() {
        return json_parameter;
    }

    public void setJson_parameter(String json_parameter) {
        this.json_parameter = json_parameter;
    }

    
    
}
