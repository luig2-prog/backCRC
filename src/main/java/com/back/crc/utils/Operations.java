package com.back.crc.utils;

public class Operations<residuos> {

    private String d;
    private String g;
    private String r;
    
    private String crc;
    private String residuos;
    private String[] residuosArr;
    private String resTX;
    
    private char[] minuendoE; //Este es el array que acumula los diferentes minuendos del emisor; se sabe que g bendría siendp el sustraendo
    private char[] minuendoR; //Este es el array que acumula los diferentes minuendos del receptor; se sabe que g bendría siendp el sustraendo
    
    private char[] arrayDigitDR;
    private char[] arrayDigitG;
    private char[] subArray;
    private char[] arrayR;
    private String result;
    
   
   
    
    
    public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getResTX() {
		return resTX;
	}

	public void setResTX(String resTX) {
		this.resTX = resTX;
	}

	public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
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
        setR(var);
        return var;
    }
    
    public int nDigitR() {
        arrayR = digitR().toCharArray();
        return arrayR.length;
    }

    public int nDigitDR() { //Cantidad de digitos que tiene D + R
        arrayDigitDR = (getD() + digitR()).toCharArray();
        return arrayDigitDR.length;
    }

    public String emisor() {
    	
    	residuosArr = new String[100];
        arrayDigitDR = (getD() + digitR()).toCharArray();
        String subChain = (getD() + digitR()).substring(0, nDigitG());
        arrayDigitG = getG().toCharArray();
        int count = nDigitG() - 1;
        result = "";
        String acumular = "";
        String minuendos = "";
        int arraT = 0;
        String prueb = "";
        while (count < nDigitDR()) {
            String chain = "";
            subArray = subChain.toCharArray();
            if (subArray.length == nDigitG()) {
                result = result + "1";
                
                minuendos+=subChain;
                minuendoE=minuendos.toCharArray();
                for (int j = 0; j < nDigitG(); j++) {
                    if (subArray[j] == arrayDigitG[j]) {
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
                    chain = chain + arrayDigitDR[count + 1];
                    acumular = acumular + arrayDigitDR[count + 1];
                }

            } else {

                String var = subChain + arrayDigitDR[count];// para cuando el indice 0 de subArray sea 0 (Validar)
                if (count + 1 < nDigitDR()) {
                    acumular = acumular + arrayDigitDR[count + 1];
                }
                if (subArray.length < nDigitG() || var.equals("0")) {
                    result = result + "0";
                }
                if (count + 1 < nDigitDR()) {
                    subArray = (subChain + arrayDigitDR[count + 1]).toCharArray();
                    chain = subChain + arrayDigitDR[count + 1];
                }
            }

            int index = chain.indexOf('1');
            if (index == -1) {
                chain = "";
            } else {
                chain = chain.substring(index);
            }
            subChain = chain;
            subArray = subChain.toCharArray();
            prueb = acumular;
            setResiduosArr(arraT, prueb);
//            System.out.print(count);
            prueb = "";
            arraT++;
            count++;

//            acumular = "";
        }
        return acumular;                //Retorna una cadena acumulada de los diferentes reciduos
        
    }

    public String CRC() {
        String crc = "";
        String var = emisor();
        char array[] = emisor().toCharArray();
        int tam = array.length;
        crc = var.substring(tam - nDigitR());
        
        return crc;
    }

    public String TX() { //TRAMA
        return d + CRC();
    }

    public String receptor() {
        char arrayTX[] = TX().toCharArray();
        String CRC = "";
        String subChain = TX().substring(0, nDigitG());
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

        residuos = emisor();
    }

	public String getResiduos() {
		return residuos;
	}

	public void setResiduos(String residuos) {
		this.residuos = residuos;
	}

	public String[] getResiduosArr() {
		return residuosArr;
	}

	public void setResiduosArr(int pos, String residuosArr) {
		this.residuosArr[pos] = residuosArr;
	}
}
