package omar.lo.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        * Cette méthode vide pour éviter que Spring génère un password au démarrage!!
        * */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/GestionCatalogue/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/GestionCatalogue/Categories/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/GestionCatalogue/Categories/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/GestionCatalogue/Categories/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/GestionCatalogue/Categories/**")
                .hasAuthority("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/GestionCatalogue/Produits/**")
                .hasAuthority("ADMIN_PROD")
                .antMatchers(HttpMethod.PUT, "/GestionCatalogue/Produits/**")
                .hasAuthority("ADMIN_PROD")
                .antMatchers(HttpMethod.POST, "/GestionCatalogue/Produits/**")
                .hasAuthority("ADMIN_PROD")
                .antMatchers(HttpMethod.PATCH, "/GestionCatalogue/Produits/**")
                .hasAuthority("ADMIN_PROD")
                .and()
                .authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
