package ua.sansan.yagrpgapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
    private static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Autowired
    private DataSource dataSource;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(toH2Console())
//                        .permitAll()
//                .anyRequest().authenticated())
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(toH2Console())
//                        .disable()
//                )
//                .formLogin((form) -> form
//                .loginPage("/login")
//                .loginProcessingUrl("/signin")
//
//                .usernameParameter("username")  // Username parameter, used in name attribute
//                // of the <input> tag. Default is 'username'.
//                .passwordParameter("password")  // Password parameter, used in name attribute
//                // of the <input> tag. Default is 'password'.
//                .successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
//                    logger.info("Login " + auth.getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//                    req.getSession().setAttribute("teacher", auth.getName());
//                    req.getSession().setAttribute("exams", "");
//                    req.getSession().setAttribute("warning", "");
//                    req.getSession().setAttribute("message", "");
//                    res.sendRedirect("/"); //"("/?search"); // Redirect user to index/home page
//                })
////    .defaultSuccessUrl("/")   // URL, where user will go after authenticating successfully.
//                // Skipped if successHandler() is used.
//                .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
//                    String errMsg="";
//                    logger.info("Login failed: " + req.getParameter("username") + " " + req.getParameter("password") + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
////                    if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
////                        errMsg="Невірний логін чи пароль. Invalid username or password.";
////                    }else{
////                        errMsg="Помилка / Error "+exp.getMessage();
////                    }
//                    req.getSession().setAttribute("warning", errMsg);
//
//                    res.sendRedirect("/"); // Redirect user to login page with error message.
//                })
//                        .permitAll());
//    }

    //TODO finish experiment
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u")
//                        .password("u")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/h2-console/**");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                //.passwordEncoder(bCryptPasswordEncoder)
//                .usersByUsernameQuery("select username, password, active from system_users where username = ?1 or email = ?1")
//                .authoritiesByUsernameQuery("select u.username, ur.role_id from system_users u inner join user_role ur on u.id = ur.user_id where u.username=?");
//    }
        //
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(toH2Console())
                        .permitAll()
                        .requestMatchers(mvc.pattern("/"))
                        .permitAll()
                        .requestMatchers(mvc.pattern("/home"))
                        .permitAll()
                        .requestMatchers(mvc.pattern("/game"))//.hasRole("USER").anyRequest()
                        .permitAll()
                ).csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console())
                        .disable()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(antMatcher("/resources/**"), antMatcher("/static/**"), antMatcher("/css/**"), antMatcher("/js/**"), antMatcher("/img/**"), antMatcher("/h2-console/**"));
    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u")
//                        .password("p")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(toH2Console())
//                        .disable()
//                )
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(toH2Console()).permitAll()
//                )
//                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
//        return http.build();
//    }
}