package br.com.Bandtec.RavenCrown.Infra.Business;

import br.com.Bandtec.RavenCrown.Entity.UsuarioEntity;
import br.com.Bandtec.RavenCrown.Infra.DAL.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogBussines {

    private Log logWriter = new Log();

    public void logLoginHistory(UsuarioEntity user){

        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Date d = new Date();

        String time = df.format(d) +" | "+user.getEmail();

        logWriter.logLogin(time);
    }

}
