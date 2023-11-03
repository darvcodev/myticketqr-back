import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>()
    {

        override fun configure(http: HttpSecurity) {
            http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
        }
    }
