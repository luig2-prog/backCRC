package com.back.crc.utils;

public class OperationReceptor {

    private String TX;
    private String g;
    private String crc;
    private char[] residuos;
    
    private String resultCRC;
    
    private char[] minuendoE; //Este es el array que acumula los diferentes minuendos del emisor; se sabe que g bendría siendp el sustraendo
    private char[] minuendoR; //Este es el array que acumula los diferentes minuendos del receptor; se sabe que g bendría siendp el sustraendo
    
    private char[] arrayDigitDR;
    private char[] arrayDigitG;
    private char[] subArray;
    private char[] arrayR;
    private String result;
   
    
    
    
    public String getResultCRC() {
		return resultCRC;
	}

	public void setResultCRC(String resultCRC) {
		this.resultCRC = resultCRC;
	}

	public String getTX() {
        return TX;
    }

    public void setTX(String tx) {
        this.TX = tx;
    }

    public String getG() {
        return g;
    }

    
    public void setCRC(String crc) {
        this.crc = crc;
    }
    
    public String getCRC() {
        return crc;
    }
    
    public void setG(String g) {
        this.g = g;
    }

    public char getArrayDigitG(int i) {
        return arrayDigitG[i];
    }

    public void setArrayDigitG(char[] arrayDigitG) {
        this.arrayDigitG = arrayDigitG;
    }

    public char getSubArray(int i) {
        return subArray[i];
    }

    public void setSubArray(char[] subArray) {
        this.subArray = subArray;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public char getMinuendosE(int i){
        return minuendoE[i];
    }
    
    public char getMinuendosR(int i){
        return minuendoR[i];
    }

    public int nDigitG() { //Número de digitos que tiene G
        arrayDigitG = getG().toCharArray();
        return arrayDigitG.length;
    }

    
    
    public String digitR() {
        String var = "";
        for (int i = 0; i < getG().length() - 1; i++) {
            var = var + "0";
        }
        return var;
    }
    
    public int nDigitR() {
        arrayR = digitR().toCharArray();
        return arrayR.length;
    }

    public int nDigitDR() { //Cantidad de digitos que tiene D + R
        arrayDigitDR = (getTX()).toCharArray();
        return arrayDigitDR.length;
    }

    public String CRC() {
        String crc = "";
        String var = receptor();
        char array[] = receptor().toCharArray();
        int tam = array.length;
        crc = var.substring(tam - nDigitR());
        
        return crc;
    }

//    public String TX() { //TRAMA
//        return d + CRC();
//    }

    public String receptor() {
        char arrayTX[] = getTX().toCharArray();
        String CRC = "";
        String subChain = getTX().substring(0, nDigitG());
        arrayDigitG = getG().toCharArray();
        int nDigitTX = arrayTX.length;
        int count = nDigitG() - 1;
        result = "";
        String acumular = "";
        String minuendos="";
        char subArreglo[];

        while (count < nDigitTX) {
            String chain = "";
            subArreglo = subChain.toCharArray();

            if (subArreglo.length == nDigitG()) {
                result = result + "1";
                minuendos+=subChain;
                minuendoR=minuendos.toCharArray();
                for (int j = 0; j < nDigitG(); j++) {
                    if (subArreglo[j] == arrayDigitG[j]) {
                        chain += "0";
                    } else {
                        chain += "1";
                    }

                }
                acumular = acumular + chain;
                if (count == nDigitDR() - 1) {
                    return acumular + chain;
                }

                if (count < nDigitDR()) {
                    chain = chain + arrayTX[count + 1];
                    acumular = acumular + arrayTX[count + 1];
                }

            } else {

                String var = subChain + arrayTX[count];// para cuando el indice 0 de subArray sea 0 (Validar)
                if (count + 1 < nDigitDR()) {
                    acumular = acumular + arrayTX[count + 1];
                }
                if (subArreglo.length < nDigitG() || var.equals("0")) {
                    result = result + "0";
                }
                if (count + 1 < nDigitDR()) {
                    subArreglo = (subChain + arrayTX[count + 1]).toCharArray();
                    chain = subChain + arrayTX[count + 1];
                }
            }

            int index = chain.indexOf('1');
            if (index == -1) {
                chain = "";
            } else {
                chain = chain.substring(index);
            }
            subChain = chain;
            subArreglo = subChain.toCharArray();

            count++;
        }
        return acumular;        //Retorna una cadena acumulada de los diferentes reciduos

    }
    public String reciduoReceptor() {
        String reciduo = "";
        String var = receptor();
        char array[] = receptor().toCharArray();
        int tam = array.length;
        reciduo = var.substring(tam - nDigitG());

        return reciduo;

    }
    public void mostrar(){
        String acumulados = new String(minuendoE);
        System.out.println(acumulados);
        residuos = minuendoE;
        
    }

	public char[] getResiduos() {
		return residuos;
	}

	public void setResiduos(char[] residuos) {
		this.residuos = residuos;
	}
}
