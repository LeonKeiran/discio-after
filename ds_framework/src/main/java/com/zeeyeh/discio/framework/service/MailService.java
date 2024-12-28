package com.zeeyeh.discio.framework.service;

/**
 * @author Leon_Keiran
 * @description 邮件业务接口
 * @date 2024/12/28/周六 20:08:47
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public interface MailService {

    void sendVerifyCodeMail(String email, String title, String verifyCode);

    void sendVerifyAccountMail(String email, String title, String link);

    void sendMail(String from, String to, String cc, String subject, String content);
}
