/**
 * AuthenticationService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 11:29
 */

package egorov.mockito.user;

public class AuthenticationService {
   UserRepository userRepository;

   public AuthenticationService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public boolean checkUser(String userId) throws RuntimeException{
      UserEntity user = userRepository.getUserById(userId);
      return user != null;
   }
}
