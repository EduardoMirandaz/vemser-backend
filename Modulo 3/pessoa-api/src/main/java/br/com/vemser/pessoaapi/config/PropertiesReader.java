package br.com.vemser.pessoaapi.config;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesReader {
    @Value("${ambiente}")
    private String ambiente;

    public String getAmbiente() {
        return ambiente;
    }
}
