package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// é um component bean
@Component
// terá um construtor com os argumentos enviados
@RequiredArgsConstructor
public class EmailService {
    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${remetente}")
    private String usuario;

    public static final int POST = 1;
    public static final int PUT = 2;
    public static final int DELETE = 3;

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(){
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(from);
        mensagem.setTo("eduardo.azevedo@dbccompany.com.br");
        mensagem.setSubject("Testando java email sender");
        mensagem.setText("Olá\nTudo bem?");
        emailSender.send(mensagem);
    }

    public void sendWithAttachment() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo("mariana.kaori@usp.br");
        helper.setSubject("Assunto 1");
        helper.setText("Meu texto");

        File file1 = new File("src/main/java/br/com/vemser/pessoaapi/imgs/imagem.jpg");
        FileSystemResource file = new FileSystemResource(file1);
        helper.addAttachment(file1.getName(), file);

        emailSender.send(message);
    }


    public void sendEmail(String nome, Integer id, String email, Integer tipoRequisicao) throws TipoRequisicaoInvalido {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email);
            switch (tipoRequisicao){
                case POST -> {
                    mimeMessageHelper.setSubject("EnderecoEntity cadastrado no petshop Padawans!");
                }
                case PUT -> {
                    mimeMessageHelper.setSubject("EnderecoEntity editado no petshop Padawans!");
                }
                case DELETE -> {
                    mimeMessageHelper.setSubject("EnderecoEntity deletado no petshop Padawans!");
                }
                default -> throw new TipoRequisicaoInvalido("Requisicao inválida!");
            }
            mimeMessageHelper.setText(geContentFromTemplate(nome, id, email, tipoRequisicao), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate(String nome, Integer id, String email, Integer requisicao) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();

        // local
        dados.put("usuario", usuario);
        dados.put("email", from);

        // da requisicao
        dados.put("nome", nome);
        dados.put("emailRequisicao", email);
        dados.put("id", id);


        String html = null;
        switch (requisicao){
            case POST -> {
                Template template = fmConfiguration.getTemplate("create-email-template.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
            }
            case PUT -> {
                Template template = fmConfiguration.getTemplate("edit-email-template.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
            }
            case DELETE -> {
                Template template = fmConfiguration.getTemplate("delete-email-template.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
            }
        }

        return html;
    }


}
