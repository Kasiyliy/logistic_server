package kz.logistic.logistic_server.shared.utils.beans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Configuration
public class CustomConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
