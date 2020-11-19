package br.com.casadocodigo.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(
            ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }

    public <T> JpaRepository<T, Long> getRepositoryBean(Class<T> entityClass) {
        return (JpaRepository<T, Long>) applicationContext.getBean(
                String.format("%sRepository", decapitalize(entityClass.getSimpleName())), JpaRepository.class);
    }

    private String decapitalize(String name) {
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

}
