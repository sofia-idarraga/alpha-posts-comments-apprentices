package com.sofka.alphapostcomments.application.config.jwt;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JwtProvider {

    private String secretKey="What-do-you-mean-youve-never-seen-Blade-Runner?";

    //In milliseconds
    private long validTime= 3600000; //1h*
}
