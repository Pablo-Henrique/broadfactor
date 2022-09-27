package com.broadfactor.util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CnpjUtils {

    public static boolean validatorCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }
        return cnpj.length() == 14;
    }

    public static String formatCnpj(String cnpj) throws ParseException {
        try{
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            System.out.println(mask.valueToString(cnpj));
            return mask.valueToString(cnpj);
        }catch (ParseException ex) {
            throw new ParseException("Não foi possivel conveter para formato CNPJ", 500);
        }
    }

}
