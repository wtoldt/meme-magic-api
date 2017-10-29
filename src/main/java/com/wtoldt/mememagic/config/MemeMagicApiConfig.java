package com.wtoldt.mememagic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created initially for tests that require autowired components but do not require a full Spring Boot test
 *
 * Created by Emily Li on 21/10/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.wtoldt.mememagic")
public class MemeMagicApiConfig {
}
