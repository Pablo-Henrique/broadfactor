package com.broadfactor.util;

import com.broadfactor.handler.exceptions.ParseFormatterErrorException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CnpjUtils {

    public static boolean validatorCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }
        return cnpj.length() == 14;
    }

    public static String formatCnpj(String cnpj) {
        try{
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            System.out.println(mask.valueToString(cnpj));
            return mask.valueToString(cnpj);
        }catch (ParseException ex) {
            throw new ParseFormatterErrorException("Problema em formatar os valores para mascara de cnpj");
        }
    }
}
