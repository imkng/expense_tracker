package in.krishna.expensetrackerapi.config;

import in.krishna.expensetrackerapi.security.CustomUserDetailsService;
import in.krishna.expensetrackerapi.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter(){
        return new JwtRequestFilter();
    }

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "webjars/**",
            "/login", "/register", "/", "/reg", "/contact",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("Krishna").password("12345").authorities("User")
//                .and()
//                .withUser("Ankit").password("12345").authorities("admin")
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails userDetails1 = User.withUsername("Krishna").password("12345").authorities("user").build();
//        UserDetails userDetails2 = User.withUsername("ankit").password("12345").authorities("admin").build();
//
//        //create user
//        userDetailsManager.createUser(userDetails1);
//        userDetailsManager.createUser(userDetails2);
//
//        // we need to tell spring security that user has been created for that
//        // we will use AuthenticationManagerBuilder auth
//        auth.userDetailsService(userDetailsManager);
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    // also need to provide password encoders
    // to encodes password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
