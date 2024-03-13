    import org.springframework.context.annotation.Bean
    import org.springframework.context.annotation.Configuration
    import org.springframework.mail.javamail.JavaMailSender
    import org.springframework.mail.javamail.JavaMailSenderImpl

    @Configuration
    class SendGmailAdapter {

        @Bean
        fun getJavaMailSender(): JavaMailSender {
            val mailSender = JavaMailSenderImpl()
            mailSender.host = "smtp.gmail.com"
            mailSender.port = 587

            mailSender.username = ""
            mailSender.password = ""

            val props = mailSender.javaMailProperties
            props["mail.transport.protocol"] = "smtp"
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.debug"] = "true"

            return mailSender
        }


    }