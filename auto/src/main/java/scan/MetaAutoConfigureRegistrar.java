package scan;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class MetaAutoConfigureRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    private ResourceLoader resourceLoader;

    private Environment environment;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        MetaBeanDefinitionScanner scanner =
                new MetaBeanDefinitionScanner(registry, true,this.environment, this.resourceLoader);
        Set<String> packagesToScan = this.getPackagesToScan(importingClassMetadata);
        scanner.scan(packagesToScan.toArray(new String[]{}));
    }

    private static class MetaBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
        public MetaBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,
                                         Environment environment, ResourceLoader resourceLoader) {
            super(registry, useDefaultFilters, environment, resourceLoader);
            registerFilters();
        }

        protected void registerFilters() {
            addIncludeFilter(new AnnotationTypeFilter(Meta.class));
        }
    }

    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(MetaComponentScan.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");

        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
        for (Class clz : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(clz));
        }

        if (packagesToScan.isEmpty()) {
            packagesToScan.add(ClassUtils.getPackageName(metadata.getClassName()));
        }

        return packagesToScan;


    }
}
