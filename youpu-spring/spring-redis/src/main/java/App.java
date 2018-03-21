import com.youpu.domain.User;
import com.youpu.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = userService.findByName("baoan");
        System.out.println(user.getUsername()+":"+user.getPassword()+":"+user.getBirthday());
         user = userService.findByName("baoan");
        System.out.println("第二次查找:"+ user.getUsername()+":"+user.getPassword()+":"+user.getBirthday());
        userService.updateUser(user);
        System.out.println("更新数据");
        user = userService.findByName("baoan");
        System.out.println("第二次查找:"+ user.getUsername()+":"+user.getPassword()+":"+user.getBirthday());
         user = userService.findByName("baoan");
        System.out.println("第二次查找:"+ user.getUsername()+":"+user.getPassword()+":"+user.getBirthday());

    }
}
