package br.ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.List;

@NamedQueries({
    @NamedQuery(name = "profileById", query = "select p from Profile p where p.id = :id"),
    @NamedQuery(name = "profileByName", query = "select p from Profile p where p.name = :name"),
    @NamedQuery(name = "profileByEmail", query = "select p from Profile p where p.email = :email"),
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
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "wishlist", joinColumns = @JoinColumn(name = "id_profile"), inverseJoinColumns = @JoinColumn(name = "id_game"))
  private List<Game> wishlist;

  @NonNull
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "lib", joinColumns = @JoinColumn(name = "id_profile"), inverseJoinColumns = @JoinColumn(name = "id_game"))
  private List<Game> lib;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "id_profile"), inverseJoinColumns = @JoinColumn(name = "id_friend"))
  private List<Profile> friends;

  @Min(value = 0, message = "O nível deve ser no mínimo 0")
  private int level;

  public Integer getCountWishList() {
    return this.wishlist.size();
  };

  public Integer getCountFriends() {
    return this.friends.size();
  };

  public Integer getCountLib() {
    return this.lib.size();
  };
}
