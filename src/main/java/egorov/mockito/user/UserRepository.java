/**
 * UserRepository.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:05
 */

package egorov.mockito.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRepository {
   public void saveUser(UserEntity user){
      System.out.println("Saved user " + user.getName() + " to database");
   }

   public UserEntity getUserById(String userId){
      if (userId == null){
         throw new RuntimeException("User ID is null in getUserById()");
      }
      if (userId.length()==0){
         throw new IllegalArgumentException("User Id is empty");
      }
      return new UserEntity("User name", 30);
   }

   public UserEntity getUserByEmail(String email){
      Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
      Matcher matcher = pattern.matcher(email);

      if (!matcher.matches()){
         throw new IllegalArgumentException("Email is incorrect");
      }
      // Here code we take User from DB
      return null;// I return null cause DB is not available at the moment
   }

}
