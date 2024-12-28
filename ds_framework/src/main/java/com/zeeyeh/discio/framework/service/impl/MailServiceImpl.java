package com.zeeyeh.discio.framework.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.zeeyeh.discio.framework.service.MailService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Leon_Keiran
 * @description 邮件业务实现类
 * @date 2024/12/28/周六 20:09:01
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    JavaMailSender javaMailSender;
    @Value("${ds.name")
    private String dsName;
    @Value("${ds.mail.from")
    private String mailFrom;

    @Override
    public void sendVerifyCodeMail(String email, String title, String verifyCode) {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("verifyCode.vtl");
        String text = template.render(Dict.create().set("code", verifyCode));
        sendMail(mailFrom, email, dsName, title, text);
    }

    @Override
    public void sendVerifyAccountMail(String email, String title, String link) {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("verifyAccount.vtl");
        String text = template.render(Dict.create().set("link", link));
        sendMail(mailFrom, email, dsName, title, text);
    }

    @Override
    public void sendMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }
}
