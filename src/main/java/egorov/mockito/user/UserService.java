/**
 * UserService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:05
 */

package egorov.mockito.user;

public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserEntity user){
        System.out.println("Saved user " + user.getName() + " in UserService");
        userRepository.saveUser(user);
    }

    public UserEntity userByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

}
