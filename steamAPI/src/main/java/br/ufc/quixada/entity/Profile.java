package br.ufc.quixada.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.List;

@NamedQueries({
    @NamedQuery(name = "profileById", query = "select p from Profile p where p.id = :id"),
    @NamedQuery(name = "profileByName", query = "select p from Profile p where p.name = :name"),
    @NamedQuery(name = "profileByEmail", query = "select p from Profile p where p.email = :email"),
})

@Document
@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @NonNull
  private String name;
  @NonNull
  private String email;
  @NonNull
  private String nickName;
  @NonNull
  private String local;

  @NonNull
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "lib", joinColumns = @JoinColumn(name = "idProfile"), inverseJoinColumns = @JoinColumn(name = "idGame"))
  private List<Game> lib;

  @ManyToMany(cascade = CascadeType.REFRESH)
  @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "idProfile"), inverseJoinColumns = @JoinColumn(name = "idFriend"))
  private List<Profile> friends;

  @Min(value = 0, message = "O nível deve ser no mínimo 0")
  private int level;

  public Integer getCountFriends() {
    return this.friends.size();
  };

  public Integer getCountLib() {
    return this.lib.size();
  }
}
