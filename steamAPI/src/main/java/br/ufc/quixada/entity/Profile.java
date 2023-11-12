package br.ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@NamedQueries({
    @NamedQuery(name = "profileById", query = "select p from Profile p where p.id = :id"),
    @NamedQuery(name = "profileByName", query = "select p from Profile p where p.name = :name"),
    @NamedQuery(name = "profileByEmail", query = "select p from Profile p where p.email = :email")
})

@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NonNull
  private String name;
  @NonNull
  private String email;
  @NonNull
  private String nick_name;
  @NonNull
  private String local;

  @NonNull
  @OneToMany(cascade=CascadeType.ALL)
  @JoinTable(name="wishlist",joinColumns=@JoinColumn(name="id_profile"), inverseJoinColumns=@JoinColumn(name="id_game"))
  private List<Game> wishlist;

  @NonNull
  @OneToMany(cascade=CascadeType.ALL)
  @JoinTable(name="lib", joinColumns=@JoinColumn(name="id_profile"), inverseJoinColumns=@JoinColumn(name="id_game"))
  private List<Game> lib;

  @NonNull
  @ManyToMany
  @JoinTable(name="friends", joinColumns=@JoinColumn(name = "id_profile"), inverseJoinColumns=@JoinColumn(name = "id_friend"))
  private List<Profile> friends;
  
  @NonNull
  private int level;

  // public Integer getCountWishList(){
  //   return this.wishlist.length();
  // };
}
