package com.flex.utils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;


public class Base64Dec{

    //according to RFC 4648 and RFC 2045
    public final static char[] base64alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!/".toCharArray();
    public final static char base64pad = '='; //unused

    public static void main(String[] args){
    	System.out.println(new String(base64alphabet).indexOf("z")); 
//        String test = "FF";
////        Base64.getEncoder().encodeToString(new BigInteger(hex, 16).toByteArray());
//        new BigInteger(val)
//        System.out.println( new BigInteger("F",16).longValue());
//        System.out.println( base16to64( test ) );
    }

    /**Return the base 64 value of an input base 16 string.
     */
    public static String base16to64(String hex){
        BigInteger value = base16to10(hex); //base 10 value

        // --- convert base 10 to 64 ---
        int digits = 1; // # digits in base 64 we'll need
        while( value.compareTo(new BigInteger("64").pow(digits)) != -1 ){ //while the decimal value >= 64^i
            digits++;
        }

        String ret = "";
        for(int i = digits - 1; i >= 0; i--){ //start at most significant digit

            //this digit must be an A (represents 0)
            if( value.subtract(new BigInteger("64").pow(i)).compareTo(BigInteger.ZERO) == -1){
                ret += "A";
            }
            else{
                for(int d = 63; d >= 1; d--){
                    //digit found: [ value - (64^i)*d >= 0 ]  with d the greatest possible
                    if( value.subtract(new BigInteger("64").pow(i).multiply(
                            new BigInteger(Integer.toString(d)))).compareTo(BigInteger.ZERO) != -1){
                        value = value.subtract(new BigInteger("64").pow(i).multiply( new BigInteger(Integer.toString(d))));
                        ret += base64alphabet[d];
                        break;
                    }
                }
            }//end else
        }

        return ret;
    }//end base16to64()

    /**Return the BigInteger decimal value of an input hexadecimal string.
     */
    public static BigInteger base16to10(String hex){
        hex = hex.toLowerCase();

        //hex is 0-9 then a-f
        BigInteger value = BigInteger.ZERO; //base 10 value
        for(int i = 0; i < hex.length(); i++){ //moving from most significant digit towards 16^0

            int digit = (int) hex.charAt(i);
            //if the hex digit is 0-9
            if( '0' <= hex.charAt(i) && hex.charAt(i) <= '9')
                digit = digit - '0'; 
            //else the hex digit is a-f
            else
                digit = digit - 'a' + 10; 

            BigInteger temp = new BigInteger("" + digit); // digit as BigInt
            temp = temp.multiply( new BigInteger("16").pow( hex.length() - 1 - i) ); // digit * 16^index
            value = value.add(temp);
        }

        return value;
    }//end base16to10()
}