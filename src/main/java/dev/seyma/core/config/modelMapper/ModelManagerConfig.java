package dev.seyma.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelManagerConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
