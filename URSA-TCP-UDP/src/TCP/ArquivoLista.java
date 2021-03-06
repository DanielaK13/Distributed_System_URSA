/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author daniela
 */
public class ArquivoLista implements Serializable{
    
    private Integer operacao;
    private List<Object> objetos;
    private Integer ret;
    String retorno; 
    private Calendar data;
   
    public ArquivoLista() {
    }
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public int getOperacao() {
        return operacao;
    }
    
    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }
    
    public int getRet(){
        return ret;
    }
    
    public void setRet(Integer ret){
        this.ret = ret;
    }

    void setObjetos(List<Object> listaOportunidades) {
        this.objetos = listaOportunidades;
    }
    
    public List<Object> getObjetos() {
        return objetos;
    }
   
}
