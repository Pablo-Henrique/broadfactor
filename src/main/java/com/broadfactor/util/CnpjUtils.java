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

    /**
     * Formta o cnpj informado do padrao 63680865000166 para 63.680.865/0001-66
     *
     * @param cnpj a ser formatado.
     * @return cnpj formatado
     */
    public static String maskFormatterToCnpj(String cnpj) {
        try{
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            System.out.println(mask.valueToString(cnpj));
            return mask.valueToString(cnpj);
        }catch (ParseException ex) {
            throw new ParseFormatterErrorException("Não foi possivel realizar essa operação!");
        }
    }
}
