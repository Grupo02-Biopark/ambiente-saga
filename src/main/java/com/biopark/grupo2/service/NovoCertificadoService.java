package com.biopark.grupo2.service;

import com.biopark.grupo2.model.Certificado;
import com.biopark.grupo2.model.Empresa;
import com.biopark.grupo2.repository.RepositoryCertificado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class NovoCertificadoService {

    @Autowired
    private RepositoryCertificado repositoryCertificado;

    public Certificado criarCertificado(Long idFormulario, Empresa empresa){

        Certificado certificado = new Certificado();

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, 1);


        Date vencimento = calendar.getTime();

        certificado.setId_formulario(idFormulario);
        certificado.setEmpresa(empresa);
        certificado.setVencimento(vencimento);

        repositoryCertificado.save(certificado);

        return certificado;
    }

}
