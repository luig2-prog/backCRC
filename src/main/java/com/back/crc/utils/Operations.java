package com.back.crc.utils;

public class Operations {

    private String d;
    private String r;
    private String g;
    private char[] arrayDigitDR;
    private char[] arrayDigitG;
    private char[] subArray;
    private char[] arrayR;
    private String result;

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getG() {
        return g;
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

    public int nDigitG() { //Número de digitos que tiene G
        arrayDigitG = getG().toCharArray();
        return arrayDigitG.length;
    }

    public int nDigitDR() { //Cantidad de digitos que tiene D + R
        arrayDigitDR = (getD() + getR()).toCharArray();
        return arrayDigitDR.length;
    }

    public int nDigitR() {
        arrayR = getR().toCharArray();
        return arrayR.length;
    }

   
    public String emisor() {
        String CRC = "";
        arrayDigitDR = (getD() + getR()).toCharArray();
        String subChain = (getD() + getR()).substring(0, nDigitG());
        arrayDigitG = getG().toCharArray();
        int count = nDigitG() - 1;
        result = "";
        String acumular = "";
        while (count < nDigitDR()) {
            String chain = "";
            System.out.println("Contador" + count);
            System.out.println("subArray" + subChain);
            subArray = subChain.toCharArray();
            if (subArray.length == nDigitG()) {
                result = result + "1";
                System.out.println("Result: " + result);
                for (int j = 0; j < nDigitG(); j++) {
                    if (subArray[j] == arrayDigitG[j]) {
                        chain += "0";
                    } else {
                        chain += "1";
                    }
                    
                    System.out.println("Residuo: " + chain);
                }
                acumular= acumular + chain;
                if(count == nDigitDR()-1){
                return acumular+chain;
                }
                
                if (count < nDigitDR()) {
                    chain = chain + arrayDigitDR[count + 1];
                    System.out.println("residuo + que baja: " + chain);
                    acumular=acumular+arrayDigitDR[count + 1];
                }

            } else {

                String var = subChain + arrayDigitDR[count];// para cuando el indice 0 de subArray sea 0 (Validar)
                if (count + 1 < nDigitDR()) {
                    System.out.println("residuo + que baja: 0 al cociente" + subChain + arrayDigitDR[count + 1]);
                    acumular=acumular+arrayDigitDR[count + 1];
                }
                System.out.println("Mirar si está actualizando" + subArray.length);
                if (subArray.length < nDigitG() || var.equals("0")) {
                    result = result + "0";
                }
                if (count + 1 < nDigitDR()) {
                    subArray = (subChain + arrayDigitDR[count + 1]).toCharArray();
                    chain = subChain + arrayDigitDR[count + 1];
                }
                System.out.println("Result: " + result);

            }

            int index = chain.indexOf('1');
            System.out.println("indice " + index);
            if (index == -1) {
                chain = "";
            } else {
                chain = chain.substring(index);
            }
            System.out.println("reciduo sin los ceros a la izquierda" + chain);
            subChain = chain;
            System.out.println("subChain: " + subChain);
            subArray = subChain.toCharArray();

            System.out.println("zzzzzzzzzzzzzzzzzzzzzz" + acumular);

            count++;

            System.out.print("\n");
            System.out.print("\n");
        }
        return acumular;
    }
    
    public String CRC(){
        String crc="";
        String var = emisor();
        char array[]=emisor().toCharArray();
        int tam=array.length;
        crc = var.substring(tam - nDigitR());
        
        return crc;
    }
    
    public String TX(){
        return d + CRC();
    }

    
    public String receptor(){
        char arrayTX[]=TX().toCharArray();
        String CRC = "";
        String subChain = TX().substring(0, nDigitG());
        arrayDigitG = getG().toCharArray();
        int nDigitTX = arrayTX.length;
        int count = nDigitG() - 1;
        result = "";
        String acumular = "";
        char subArreglo[];
        
        while (count < nDigitTX) {
            String chain = "";
            System.out.println("Contador" + count);
            System.out.println("subArray" + subChain);
            subArreglo = subChain.toCharArray();
            
            if (subArreglo.length == nDigitG()) {
                result = result + "1";
                System.out.println("Result: " + result);
                for (int j = 0; j < nDigitG(); j++) {
                    if (subArreglo[j] == arrayDigitG[j]) {
                        chain += "0";
                    } else {
                        chain += "1";
                    }
                    
                    System.out.println("Residuo: " + chain);
                }
                acumular= acumular + chain;
                if(count == nDigitDR()-1){
                return acumular+chain;
                }
                
                if (count < nDigitDR()) {
                    chain = chain + arrayTX[count + 1];
                    System.out.println("residuo + que baja: " + chain);
                    acumular=acumular+arrayTX[count + 1];
                }

            } else {

                String var = subChain + arrayTX[count];// para cuando el indice 0 de subArray sea 0 (Validar)
                if (count + 1 < nDigitDR()) {
                    System.out.println("residuo + que baja: 0 al cociente" + subChain + arrayTX[count + 1]);
                    acumular=acumular+arrayTX[count + 1];
                }
                System.out.println("Mirar si está actualizando" + subArreglo.length);
                if (subArreglo.length < nDigitG() || var.equals("0")) {
                    result = result + "0";
                }
                if (count + 1 < nDigitDR()) {
                    subArreglo = (subChain + arrayTX[count + 1]).toCharArray();
                    chain = subChain + arrayTX[count + 1];
                }
                System.out.println("Result: " + result);

            }

            int index = chain.indexOf('1');
            System.out.println("indice " + index);
            if (index == -1) {
                chain = "";
            } else {
                chain = chain.substring(index);
            }
            System.out.println("reciduo sin los ceros a la izquierda" + chain);
            subChain = chain;
            System.out.println("subChain: " + subChain);
            subArreglo = subChain.toCharArray();

            System.out.println("Acumulado de los diferentes reciduos de las operaciones" + acumular);

            count++;

            System.out.print("\n");
            System.out.print("\n");
        }
        return acumular;
        
    }
    
    public String reciduoReceptor(){
        String reciduo="";
        String var = receptor();
        char array[]=receptor().toCharArray();
        int tam=array.length;
        reciduo = var.substring(tam - nDigitG());
        
        return reciduo;
    
    }
     public void mensaje(){
        
        String chain="";
        for(int i=0; i<nDigitG(); i++){
            chain=chain+"0";
        }
        
        if(reciduoReceptor().equals(chain)){
//            JOptionPane.showMessageDialog(null, "Recibido correctamente");
        }else{
//            JOptionPane.showMessageDialog(null, "No recibido");
        }
    }
}
